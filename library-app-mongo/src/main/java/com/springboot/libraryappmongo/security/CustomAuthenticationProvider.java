package com.springboot.libraryappmongo.security;

import com.springboot.libraryappmongo.exception.EntityNotFoundException;
import com.springboot.libraryappmongo.exception.InvalidEntityException;
import com.springboot.libraryappmongo.models.User;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {


        Object principal = authentication.getPrincipal();
        if(principal instanceof User){
            return new UsernamePasswordAuthenticationToken(principal, ((User) principal).getPassword(), ((User) principal).getAuthorities());
        }
        String email = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        if(email == null || password == null){
            throw new BadCredentialsException("invalid username/password");
        }

        User user = (User) getUserDetailsService().loadUserByUsername(email);

        if(user == null){
            throw new EntityNotFoundException("user with this email not found");
        }

        if(!user.isEnabled()){
            throw new InvalidEntityException("this account is not yet activated, \n please check your email for activation token");
        }

        if(!getPasswordEncoder().matches(password, user.getPassword())){
            throw new InvalidEntityException("password doesnt match");
        }

        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        authenticationToken.setDetails(authentication.getDetails());

        return authenticationToken;
    }
}
