package com.example.RESTDataJPA.controllers;

import com.example.RESTDataJPA.models.Book;
import com.example.RESTDataJPA.repositories.BookRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    private final Logger log = LoggerFactory.getLogger(BookController.class);
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostMapping("/api/books")
    public ResponseEntity<Book> create(@RequestBody Book book){
        if (book.getId() != null) {
            log.warn("Book already exists");
            return ResponseEntity.badRequest().build();
        }
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/api/books")
//    @ApiOperation("Search all books")
    public ResponseEntity<List<Book>> findAll(){
        return ResponseEntity.ok(bookRepository.findAll());
    }

    @GetMapping("/api/books/{id}")
    public ResponseEntity<Book> findOneById(@PathVariable long id){
        Optional<Book> bookOpt = bookRepository.findById(id);
        if(bookOpt.isPresent())
            return ResponseEntity.ok(bookOpt.get());
        else
            return ResponseEntity.notFound().build();
    }

    @PutMapping("/api/books")
    public ResponseEntity<Book> update(@RequestBody Book book){
        if(book.getId() == null) {
            log.warn("Cannot update without book Id");
            return ResponseEntity.badRequest().build();
        }
        if(!bookRepository.existsById(book.getId())) {
            log.warn("Trying to update a non extistent book");
            return ResponseEntity.notFound().build();
        }
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/api/books/{Id}")
    public ResponseEntity<Book> delete(@PathVariable Long Id){
        if(!bookRepository.existsById(Id)){
            log.warn("Trying to delete a non extistent book");
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(Id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/books")
    public ResponseEntity<Book> deleteAll(){
        if(bookRepository.count() > 0){
            log.info("REST request to delete all books");
            bookRepository.deleteAll();
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
