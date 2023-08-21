package com.springboot.libraryappmongo.service;

import com.springboot.libraryappmongo.exception.EntityNotFoundException;
import com.springboot.libraryappmongo.models.Book;
import com.springboot.libraryappmongo.repo.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepo bookRepo;

    public Book saveBook(Book book) {
        return bookRepo.save(book);
    }

    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    public Page<Book> searchByCriteres(String title, String category, int pages_min, int pages_max, String author, int year_min, int year_max, float price_min, float price_max, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "title");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Book> books = bookRepo.searchByCriteres(title, pages_min, pages_max, year_min, year_max, price_min, price_max, category, author, pageable);
        return books;
    }

    public Page<Book> findByAuthor(String authorId, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "title");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Book> books = bookRepo.searchByAuthor(authorId, pageable);
        return books;
    }

    public Book findById(String id) {
        Optional<Book> book = bookRepo.findById(id);
        if (!book.isPresent()) {
            throw new EntityNotFoundException("book not found : id = " + id);
        }
        return book.get();
    }

    public Page<Book> findFeatured(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookRepo.findFeatured(pageable);
    }

    public Page<Book> findNewest(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "addedDate");
        Pageable pageable = PageRequest.of(page, size, sort);
        return bookRepo.findAll(pageable);
    }

    public List<Book> findPopular() {
        return bookRepo.findPopular();
    }

    public Page<Book> findBestSeller(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.ASC, "sale_number");
        Pageable pageable = PageRequest.of(page, size, sort);
        return bookRepo.findAll(pageable);
    }

    public void delete(String id) {
        Book book = findById(id);
        bookRepo.deleteById(book.getId());
    }
}
