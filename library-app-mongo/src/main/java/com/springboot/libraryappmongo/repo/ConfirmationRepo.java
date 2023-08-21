package com.springboot.libraryappmongo.repo;

import com.springboot.libraryappmongo.models.Confirmation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ConfirmationRepo extends MongoRepository<Confirmation, String> {
    @Query("{ token: ?0 }")
    Confirmation findByToken(String token);
}
