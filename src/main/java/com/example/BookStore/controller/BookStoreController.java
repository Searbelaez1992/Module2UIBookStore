package com.example.BookStore.controller;

import com.example.BookStore.models.BookStore;
import com.example.BookStore.models.Booking;
import com.example.BookStore.service.BookStoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookstore")
public class BookStoreController {

    private final BookStoreService bookStoreService;

    public BookStoreController(BookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }

    @GetMapping
    public List<BookStore> getBookStores() {
        return bookStoreService.findAll();
    }

    @GetMapping("/{id}")
    public BookStore getBookStore(@PathVariable Long id) {
        return bookStoreService.findBookStoreById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookStore saveBookStore(@RequestBody BookStore bookStore) {
        return bookStoreService.save(bookStore);

    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookStore updateBookStore(@PathVariable long id, @RequestBody BookStore bookStore) throws Exception {

        return bookStoreService.updateBookStore(id,bookStore);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookStore(@PathVariable Long id)
    {
        bookStoreService.deleteBookStore(id);
        return ResponseEntity.noContent().build();
    }
}
