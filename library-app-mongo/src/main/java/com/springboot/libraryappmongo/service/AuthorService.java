package com.springboot.libraryappmongo.service;

import com.springboot.libraryappmongo.exception.EntityNotFoundException;
import com.springboot.libraryappmongo.models.Author;
import com.springboot.libraryappmongo.repo.AuthorRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepo authorRepo;

    public Author save(Author author) {
        return authorRepo.save(author);
    }

    public Page<Author> findAllByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return authorRepo.findAll(pageable);
    }

    public List<Author> findAll() {
        return authorRepo.findAll();
    }

    public Page<Author> searchByCriteres(String id, String nationality, Pageable pageable) {
        return authorRepo.searchByCriteres(id, nationality, pageable);
    }

    public Author findById(String id) {
        Optional<Author> author = authorRepo.findById(id);
        if (!author.isPresent()) {
            throw new EntityNotFoundException("author not found : id = " + id);
        }
        return author.get();
    }

    public void deleteById(String id) {
        Author author = findById(id);
        authorRepo.deleteById(author.getId());
    }
}
