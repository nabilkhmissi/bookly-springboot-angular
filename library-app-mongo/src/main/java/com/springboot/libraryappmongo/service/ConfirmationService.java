package com.springboot.libraryappmongo.service;

import com.springboot.libraryappmongo.models.Confirmation;
import com.springboot.libraryappmongo.repo.ConfirmationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfirmationService {
    private final ConfirmationRepo confirmationRepo;
    public Confirmation create(String userId){
        return confirmationRepo.save(new Confirmation(userId));
    }

    public Confirmation findByToken(String token){
        return confirmationRepo.findByToken(token);
    }
}
