package com.springboot.libraryappmongo.service;

import com.springboot.libraryappmongo.exception.EntityNotFoundException;
import com.springboot.libraryappmongo.models.Cart;
import com.springboot.libraryappmongo.repo.CartRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepo cartRepo;

    public Cart addToCart(Cart cart) {
        Cart selected_cart = findById(cart.getId());
        selected_cart.setItems(cart.getItems());
        return cartRepo.save(selected_cart);
    }

    public Cart clearCart(String id){
        Cart cart = findById(id);
        cart.setItems(Collections.emptyList());
        return cartRepo.save(cart);
    }

    public String createNewCart(){
        return cartRepo.save(Cart.builder().items(Collections.emptyList()).build()).getId();
    }

    public Cart findById(String id){
        Optional<Cart> cart = cartRepo.findById(id);
        if(!cart.isPresent()){
            throw new EntityNotFoundException(String.format("cart with this id : %s not found", id));
        }
        return cart.get();
    }
}
