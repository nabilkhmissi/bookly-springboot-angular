package com.springboot.libraryappmongo.repo;

import com.springboot.libraryappmongo.models.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface AuthorRepo extends MongoRepository<Author, String> {

    @Query("{ 'id': { $regex: ?0, $options: 'i' }, 'nationality' : { $regex: ?1, $options: 'i' } }")
    Page<Author> searchByCriteres(String id, String nationality, Pageable page);
}
