package com.springboot.libraryappmongo.service;

import com.springboot.libraryappmongo.authentication.RegisterRequest;
import com.springboot.libraryappmongo.exception.EntityNotFoundException;
import com.springboot.libraryappmongo.exception.InvalidEntityException;
import com.springboot.libraryappmongo.models.Confirmation;
import com.springboot.libraryappmongo.models.User;
import com.springboot.libraryappmongo.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartService cartService;
    private final WishlistService wishlistService;
    private final ConfirmationService confirmationService;
    private final EmailService emailService;

    public String save(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()) != null) {
            throw new InvalidEntityException("This email is already in use");
        }
        User user = saveNewUser(request);

        Confirmation confirmation = confirmationService.create(user.getId());
        emailService.sendActivationMail(user.getFirstName(), user.getEmail(), confirmation.getToken());
        return "Account created successfully, please check your email for activation link";
    }

    public String activateUser(String id) {
        User user = findById(id);
        user.setEnabled(true);
        userRepository.save(user);
        return "user activated successfully";
    }

    private User findById(String id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new EntityNotFoundException(String.format("user with this id not found : id = %s", id));
        }
        return user.get();
    }

    private User saveNewUser(RegisterRequest request) {
        User userAccount = new User();
        userAccount.setFirstName(request.getFirstName());
        userAccount.setLastName(request.getLastName());
        userAccount.setEmail(request.getEmail());
        userAccount.setGender(request.getGender());
        userAccount.setBirthday(request.getBirthday());
        userAccount.setPassword(passwordEncoder.encode(request.getPassword()));
        userAccount.setRoles("USER_ROLE");
        userAccount.setCartId(cartService.createNewCart());
        userAccount.setWhishlistId(wishlistService.createNewWhishlist());
        userAccount.setEnabled(false);
        userRepository.save(userAccount);
        return userAccount;
    }
}
