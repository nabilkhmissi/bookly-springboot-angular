package com.springboot.libraryappmongo.service;

import com.springboot.libraryappmongo.exception.EntityNotFoundException;
import com.springboot.libraryappmongo.models.Book;
import com.springboot.libraryappmongo.models.Wishlist;
import com.springboot.libraryappmongo.repo.WhishlistRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WhishlistRepo whishlistRepo;

    public Wishlist updateWishlist(Wishlist wishlist) {
        return whishlistRepo.save(wishlist);
    }
    public Wishlist removeFromWichlist(String id, Book item) {
        Wishlist wishlist = findById(id);
        List<Book> list = wishlist.getItems().stream().filter(i -> i.getId().equals(item.getId())).toList();
        if(list.size() != 0){
            return null;
        }
        wishlist.getItems().remove(item);
        return whishlistRepo.save(wishlist);
    }

    public String createNewWhishlist(){
        return whishlistRepo.save(Wishlist.builder().items(Collections.emptyList()).build()).getId();
    }

    public Wishlist findById(String id){
        Optional<Wishlist> whishlist = whishlistRepo.findById(id);
        if(!whishlist.isPresent()){
            throw new EntityNotFoundException(String.format("whishlist with this id : %s not found", id));
        }
        return whishlist.get();
    }

    public void deleteAll(){
        this.whishlistRepo.deleteAll();
    }

    public Wishlist clearWishlist(String id) {
        Wishlist wishlist = findById(id);
        wishlist.setItems(Collections.emptyList());
        return whishlistRepo.save(wishlist);
    }
}
