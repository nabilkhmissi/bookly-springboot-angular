package com.springboot.libraryappmongo.repo;

import com.springboot.libraryappmongo.models.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WhishlistRepo extends MongoRepository<Wishlist, String> {

}
