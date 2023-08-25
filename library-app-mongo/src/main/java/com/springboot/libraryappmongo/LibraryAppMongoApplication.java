package com.springboot.libraryappmongo;

import com.springboot.libraryappmongo.repo.BookRepo;
import com.springboot.libraryappmongo.service.DBInitService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@RequiredArgsConstructor
public class LibraryAppMongoApplication {
    private final DBInitService dbInitService;

    public static void main(String[] args) {
        SpringApplication.run(LibraryAppMongoApplication.class, args);
    }

  /*  @Bean
    public CommandLineRunner commandLineRunner(BookRepo bookRepo) {
        return args -> {
            dbInitService.initAuthors();
        };
    }*/

}

