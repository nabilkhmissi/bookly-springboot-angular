package com.springboot.libraryappmongo.service;

import com.springboot.libraryappmongo.authentication.RegisterRequest;
import com.springboot.libraryappmongo.dto.UserDto;
import com.springboot.libraryappmongo.exception.EntityNotFoundException;
import com.springboot.libraryappmongo.exception.InvalidEntityException;
import com.springboot.libraryappmongo.models.Confirmation;
import com.springboot.libraryappmongo.models.User;
import com.springboot.libraryappmongo.repo.UserRepository;
import com.springboot.libraryappmongo.validators.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartService cartService;
    private final WishlistService wishlistService;

   /* public UserDto findById(Long id) {
        User user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new EntityNotFoundException("user with this id not found : id = " + id);
        }
        return UserDto.fromEntity(user.get());
    }
*/
/*
    public List<UserDto> findAll(){
        return userRepository.findAll().stream().map(UserDto::fromEntity).toList();
    }
*/

    public UserDto save(RegisterRequest request) {
        List<String> errors = UserValidator.validate(request);
        if (userRepository.findByEmail(request.getEmail()) != null) {
            errors.add("this email is already used");
        }
        if (!errors.isEmpty()) {
            throw new InvalidEntityException(errors);
        }

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User userAccount = new User();
        userAccount.setFirstName(request.getFirstName());
        userAccount.setLastName(request.getLastName());
        userAccount.setPassword(passwordEncoder.encode(request.getPassword()));
        userAccount.setEmail(request.getEmail());
        userAccount.setGender(request.getGender());
        userAccount.setBirthday(request.getBirthday());
        userAccount.setRoles("USER_ROLE");
        userAccount.setCartId(cartService.createNewCart());
        userAccount.setWhishlistId(wishlistService.createNewWhishlist());
        userAccount.setEnabled(false);
        User user = userRepository.save(userAccount);
        return UserDto.fromEntity(user);
    }

    public String activateUser(String id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()){
            throw new EntityNotFoundException(String.format("user with this id not found : id = %s", id));
        }
        User user1 = user.get();
        user1.setEnabled(true);
        userRepository.save(user1);
        return "user activated successfully";
    }

  /*  public UserDto updateUser(UserDto UserDto) {
        Optional<User> user = userRepository.findById(UserDto.getId());
        if(!user.isPresent()){
            throw new EntityNotFoundException(String.format("User with id : %s not found", UserDto.getId()));
        }
        User updatedUserAccount = user.get();
        updatedUserAccount.setFirstName(UserDto.getFirstName());
        updatedUserAccount.setLastName(UserDto.getLastName());
        updatedUserAccount.setEmail(UserDto.getEmail());
        updatedUserAccount.setId(UserDto.getId());
                return UserDto.fromEntity(userRepository.save(updatedUserAccount));
    }*/

    /*public void delete(Long id) {
        Optional<UserAccount> todo = userRepository.findById(id);
        if(!todo.isPresent()){
            throw new EntityNotFoundException(String.format("User with id : %d not found", id));
        }
        userRepository.deleteById(id);
    }*/
}
