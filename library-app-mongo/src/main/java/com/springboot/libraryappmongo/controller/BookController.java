package com.springboot.libraryappmongo.controller;

import com.springboot.libraryappmongo.dto.ApiResponse;
import com.springboot.libraryappmongo.models.Book;
import com.springboot.libraryappmongo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/{id}")
    public ApiResponse findById(@PathVariable String id){
        return ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .data(bookService.findById(id))
                .message("book retrieved successfully !")
                .build();
    }

    @GetMapping("")
    public ApiResponse findAll(){
        return ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .data(bookService.findAll())
                .message("books retrieved successfully !")
                .build();
    }
    @GetMapping("/popular")
    public ApiResponse findPolpular(){
        return ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .data(bookService.findPopular())
                .message("books retrieved successfully !")
                .build();
    }

    @GetMapping("/author/{authorId}")
    public ApiResponse findByAuthor(@PathVariable String authorId,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "12") int size){
        return ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .data(bookService.findByAuthor(authorId, page, size))
                .message("books retrieved successfully !")
                .build();
    }

    @PostMapping("")
    public ApiResponse save(@RequestBody Book book){
        return ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CREATED)
                .code(HttpStatus.CREATED.value())
                .data(bookService.saveBook(book))
                .message("book created successfully !")
                .build();
    }

    @GetMapping("/search")
    public ApiResponse searchByCriteres(@RequestParam(defaultValue = "", required = false) String title,
                                                       @RequestParam(defaultValue = "", required = false) String category,
                                                       @RequestParam(defaultValue = "0", required = false) int pages_min,
                                                       @RequestParam(defaultValue = "10000", required = false) int pages_max,
                                                       @RequestParam(defaultValue = "", required = false) String author,
                                                       @RequestParam(defaultValue = "0", required = false) int year_min,
                                                       @RequestParam(defaultValue = "9999", required = false) int year_max,
                                                       @RequestParam(defaultValue = "0", required = false) float price_min,
                                                       @RequestParam(defaultValue = "999999", required = false) float price_max,
                                                       @RequestParam(defaultValue = "0", required = false) int page,
                                                       @RequestParam(defaultValue = "12", required = false) int size){
        Page<Book> booksByCriteres = bookService.searchByCriteres(title, category, pages_min, pages_max, author, year_min, year_max, price_min, price_max, page, size);
        return ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .data(booksByCriteres)
                .message("books retrieved successfully !")
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteById(@PathVariable String id){
        bookService.delete(id);
        return ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .message("book deleted successfully !")
                .build();
    }


    @GetMapping("/newest")
    public ApiResponse findNewest(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "12") int size){
        return ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .data(bookService.findNewest(page, size))
                .message("books retrieved successfully !")
                .build();
    }

    @GetMapping("/featured")
    public ApiResponse findFeatured(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "12") int size){
        return ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .data(bookService.findFeatured(page, size))
                .message("books retrieved successfully !")
                .build();
    }

    @GetMapping("/best-sellers")
    public ApiResponse findBestSellers(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "12") int size){
        return ApiResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .data(bookService.findBestSeller(page, size))
                .message("books retrieved successfully !")
                .build();
    }

}
