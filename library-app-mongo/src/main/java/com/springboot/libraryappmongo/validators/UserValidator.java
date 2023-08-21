package com.springboot.libraryappmongo.validators;

import com.springboot.libraryappmongo.authentication.RegisterRequest;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {

    public static List<String> validate(RegisterRequest request) {
        List<String> errors = new ArrayList<>();


        if (StringUtils.isEmpty(request.getFirstName())
                || StringUtils.isEmpty(request.getLastName())
                || StringUtils.isEmpty(request.getEmail())
                || StringUtils.isEmpty(request.getGender())
                || StringUtils.isEmpty(request.getPassword())) {
            errors.add("please provide all informations");
            return errors;
        }

        if (StringUtils.isEmpty(request.getEmail())) {
            errors.add("email cannot be empty");
        }
        if (StringUtils.isEmpty(request.getPassword())) {
            errors.add("password cannot be empty");
        }
        if (StringUtils.isEmpty(request.getFirstName())) {
            errors.add("first cannot be empty");
        }
        if (StringUtils.isEmpty(request.getLastName())) {
            errors.add("last cannot be empty");
        }
        return errors;
    }
}
