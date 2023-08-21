package com.springboot.libraryappmongo.controller;

import com.springboot.libraryappmongo.dto.ApiResponse;
import com.springboot.libraryappmongo.models.Cart;
import com.springboot.libraryappmongo.models.CartItem;
import com.springboot.libraryappmongo.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/update")
    public ApiResponse addToCart(@RequestBody Cart cart) {
        return ApiResponse.builder()
                .status(HttpStatus.CREATED)
                .code(HttpStatus.CREATED.value())
                .data(cartService.addToCart(cart))
                .message("cart updated successfully !")
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse getCart(@PathVariable String id) {
        return ApiResponse.builder()
                .status(HttpStatus.CREATED)
                .code(HttpStatus.CREATED.value())
                .data(cartService.findById(id))
                .message("cart retrieved successfully !")
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse clearCart(@PathVariable String id) {
        return ApiResponse.builder()
                .status(HttpStatus.OK)
                .code(HttpStatus.OK.value())
                .data(cartService.clearCart(id))
                .message("cart updated successfully !")
                .build();
    }
}
