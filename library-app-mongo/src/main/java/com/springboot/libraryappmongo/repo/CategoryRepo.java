package com.springboot.libraryappmongo.repo;

import com.springboot.libraryappmongo.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CategoryRepo extends MongoRepository<Category, String> {

    @Query("{ 'designation': { $regex: ?0, $options: 'i' }}")
    List<Category> searchByDesignation(String designation);
}
