package com.springboot.libraryappmongo.controller;

import com.springboot.libraryappmongo.dto.ApiResponse;
import com.springboot.libraryappmongo.models.Category;
import com.springboot.libraryappmongo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public ApiResponse findById(@PathVariable String id){
        return ApiResponse.builder()
                .status(HttpStatus.CREATED)
                .code(HttpStatus.CREATED.value())
                .data(categoryService.findById(id))
                .message("category retrieved successfully !")
                .build();    }

    @GetMapping("")
    public ApiResponse findAll(){
        return ApiResponse.builder()
                .status(HttpStatus.CREATED)
                .code(HttpStatus.CREATED.value())
                .data(categoryService.findAll())
                .message("categories retrieved successfully !")
                .build();
    }


    @PostMapping("")
    public ApiResponse save(@RequestBody Category category){
        return ApiResponse.builder()
                .status(HttpStatus.CREATED)
                .code(HttpStatus.CREATED.value())
                .data(categoryService.save(category))
                .message("category created successfully !")
                .build();
    }

    @GetMapping("/search")
    public ApiResponse searchByDesignation(@RequestParam(defaultValue = "", required = false) String designation){
        List<Category> categoriesByCriteres = categoryService.searchByCriteres(designation);
        return ApiResponse.builder()
                .status(HttpStatus.CREATED)
                .code(HttpStatus.CREATED.value())
                .data(categoriesByCriteres)
                .message("categories retrieved successfully !")
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable String id) {
        categoryService.deleteById(id);
        return ApiResponse.builder()
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .message("category deleted successfully !")
                .build();
    }
}
