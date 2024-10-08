package com.springboot.libraryappmongo.service;

import com.springboot.libraryappmongo.exception.EntityNotFoundException;
import com.springboot.libraryappmongo.models.Confirmation;
import com.springboot.libraryappmongo.repo.ConfirmationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ConfirmationService {
    private final ConfirmationRepo confirmationRepo;

    public Confirmation create(String userId) {
        return confirmationRepo.save(new Confirmation(userId));
    }

    public Confirmation findByToken(String token) {
        Confirmation confirmation = confirmationRepo.findByToken(token);
        if(confirmation == null){
            throw new EntityNotFoundException("confirmation with this token not found");
        }
        return confirmation;
    }

    public boolean validateConfirmation(Confirmation confirmation) {
        if (!LocalDateTime.now().isBefore(confirmation.getTokenExpiration())) {
            return false;
        }
        return true;
    }
}
