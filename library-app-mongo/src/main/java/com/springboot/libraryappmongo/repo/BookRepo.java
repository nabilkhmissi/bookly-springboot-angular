package com.springboot.libraryappmongo.repo;

import com.springboot.libraryappmongo.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BookRepo extends MongoRepository<Book, String> {


  @Query("{ 'title': { $regex: ?0, $options: 'i' }, 'pages' : { $gte: ?1, $lte : ?2 }, 'year' : { $gte : ?3, $lte : ?4 }, price : { $gte : ?5, $lte : ?6 }, 'category': { $regex: ?7, $options: 'i' }, authorId : { $regex: ?8, $options: 'i' } }")
    Page<Book> searchByCriteres(String title, int pages_min, int pages_max, int year_min, int year_max, float price_min, float price_max, String category, String author, Pageable pageable);


    @Query("{ 'pages': { $gte: ?0, $lte: ?1 } }")
    List<Book> findByPages(int min, int max);

  @Aggregation(pipeline = {
          "{ '$sort' : { 'sale_number' : -1 } }",
          "{ '$limit' : 5 }"
  })
  List<Book> findBestSeller();

    @Query("{ 'tags': 'featured' }")
    Page<Book> findFeatured(Pageable pageable);

    @Query("{ 'rating' : 5  }")
    List<Book> findPopular();

    @Query("{ 'authorId': { $regex: ?0, $options: 'i' }}")
  Page<Book> searchByAuthor(String authorId, Pageable pageable);
}
