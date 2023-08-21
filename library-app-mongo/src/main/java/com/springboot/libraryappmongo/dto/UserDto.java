package com.springboot.libraryappmongo.dto;

import com.springboot.libraryappmongo.models.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class UserDto {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate Birthday;
    private String gender;
    private String email;
    private String cartId;
    private String wishlistId;



    public static UserDto fromEntity(User User) {
        return UserDto.builder()
                .id(User.getId())
                .email(User.getEmail())
                .firstName(User.getFirstName())
                .lastName(User.getLastName())
                .gender(User.getGender())
                .cartId(User.getCartId())
                .wishlistId(User.getWhishlistId())
                .build();
    }

    public static User toEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setEmail(userDto.getEmail());
        user.setBirthday(userDto.getBirthday());
        user.setLastName(user.getLastName());
        user.setGender(userDto.getGender());
        user.setCartId(userDto.getCartId());
        user.setWhishlistId(userDto.getWishlistId());
        return user;
    }
}