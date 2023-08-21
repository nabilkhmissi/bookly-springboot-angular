package com.springboot.libraryappmongo.security;

import com.springboot.libraryappmongo.models.User;
import com.springboot.libraryappmongo.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User usr = userRepository.findByEmail(username);
        if(usr == null){
          throw new BadCredentialsException("invalid username/password");
        }
        return usr;
    }
}
