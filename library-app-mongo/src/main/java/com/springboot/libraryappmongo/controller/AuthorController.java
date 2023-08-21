package com.springboot.libraryappmongo.controller;

import com.springboot.libraryappmongo.dto.ApiResponse;
import com.springboot.libraryappmongo.models.Author;
import com.springboot.libraryappmongo.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/author")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/{id}")
    public ApiResponse findById(@PathVariable String id) {
        return ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .data(authorService.findById(id))
                .message("author retrieved successfully !")
                .build();
    }

    @PostMapping("")
    public ApiResponse save(@RequestBody Author Author) {
        return ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CREATED)
                .code(HttpStatus.CREATED.value())
                .data(authorService.save(Author))
                .message("author created successfully !")
                .build();
    }

    @GetMapping("/search")
    public ApiResponse searchByCriteres(@RequestParam(defaultValue = "", required = false) String id,
                                        @RequestParam(defaultValue = "", required = false) String nationality,
                                        @RequestParam(defaultValue = "0", required = false) int page,
                                        @RequestParam(defaultValue = "12", required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Author> AuthorsByCriteres = authorService.searchByCriteres(id, nationality, pageable);
        return ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .data(AuthorsByCriteres)
                .message("authors retrieved successfully !")
                .build();
    }

    @GetMapping("")
    public ApiResponse getAllByPage(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "12") int size) {
        return ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .data(authorService.findAllByPage(page, size))
                .message("authors retrieved successfully !")
                .build();
    }

    @GetMapping("/all")
    public ApiResponse getAll() {
        return ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .data(authorService.findAll())
                .message("authors retrieved successfully !")
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable String id) {
        authorService.deleteById(id);
        authorService.deleteById(id);
        return ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .message("author deleted successfully !")
                .build();
    }

}
