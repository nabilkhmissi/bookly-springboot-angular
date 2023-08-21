package com.springboot.libraryappmongo.repo;

import com.springboot.libraryappmongo.models.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CartRepo extends MongoRepository<Cart, String> {


    @Query("{ userId : ?0 }")
    Cart findByUserId(String userId);

}
