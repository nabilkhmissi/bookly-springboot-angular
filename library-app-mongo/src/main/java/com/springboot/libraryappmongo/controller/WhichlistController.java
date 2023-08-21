package com.springboot.libraryappmongo.controller;

import com.springboot.libraryappmongo.dto.ApiResponse;
import com.springboot.libraryappmongo.models.Book;
import com.springboot.libraryappmongo.models.Cart;
import com.springboot.libraryappmongo.models.Wishlist;
import com.springboot.libraryappmongo.service.CartService;
import com.springboot.libraryappmongo.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/wishlist")
@RequiredArgsConstructor
public class WhichlistController {

    private final WishlistService wishlistService;

    @PostMapping("/update")
    public ApiResponse addToWishlist(@RequestBody Wishlist wishlist) {
        return ApiResponse.builder()
                .status(HttpStatus.CREATED)
                .code(HttpStatus.CREATED.value())
                .data(wishlistService.updateWishlist(wishlist))
                .message("wishlist updated successfully !")
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse getWishlist(@PathVariable String id) {
        return ApiResponse.builder()
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .data(wishlistService.findById(id))
                .message("wishlist retrieved successfully !")
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse clearWishlist(@PathVariable String id) {
        return ApiResponse.builder()
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .data(wishlistService.clearWishlist(id))
                .message("wishlist updated successfully !")
                .build();
    }
}
