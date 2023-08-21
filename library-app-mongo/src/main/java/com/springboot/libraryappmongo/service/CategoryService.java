package com.springboot.libraryappmongo.service;

import com.springboot.libraryappmongo.exception.EntityNotFoundException;
import com.springboot.libraryappmongo.models.Category;
import com.springboot.libraryappmongo.repo.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepo categoryRepo;
    private final MongoTemplate mongoTemplate;

    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    public List<Category> findAll() {

        return categoryRepo.findAll();
    }

    public List<Category> searchByCriteres(String designation) {
        Query query = new Query().
                addCriteria(Criteria.where("designation").is(designation));
        List<Category> categories1 = mongoTemplate.find(query, Category.class);
        List<Category> categories = categoryRepo.searchByDesignation(designation.toLowerCase());
        return categories1;
    }

    public Category findById(String id) {
        Optional<Category> category = categoryRepo.findById(id);
        if (!category.isPresent()) {
            throw new EntityNotFoundException("category not found : id = " + id);
        }
        return category.get();
    }

    public void deleteById(String id) {
        Category category = findById(id);
        categoryRepo.deleteById(category.getId());
    }
}
