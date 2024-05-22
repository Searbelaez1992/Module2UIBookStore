package com.example.BookStore.service;

import com.example.BookStore.models.BookStore;
import com.example.BookStore.repository.BookStoreRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookStoreService {

    private final BookStoreRepository bookStoreRepository;
    private final ProductService productService;

    public BookStoreService(BookStoreRepository bookStoreRepository, ProductService productService) {
        this.bookStoreRepository = bookStoreRepository;
        this.productService = productService;
    }

    public List<BookStore> getBookStores(String keyWord1,String keyWord2,String keyWord3,String keyWord4,String keyWord5,String keyWord6 ){
        if(keyWord1 != null || keyWord2 != null || keyWord3 != null || keyWord4 != null || keyWord5 != null || keyWord6 != null)
            return bookStoreRepository.findAll(keyWord1, keyWord2, keyWord3, keyWord4, keyWord5, keyWord6);
        return bookStoreRepository.findAll();
    }

    public BookStore save(BookStore bookStore){

        if(bookStore.getId() !=null )
            bookStore.setId(null);
        BookStore entity = bookStoreRepository.save(bookStore);
        bookStore.setProduct(productService.findProductById(bookStore.getProduct().getId()));

        return entity;
    }

    public BookStore findBookStoreById(Long bookStoreId) {

        return bookStoreRepository.findById(bookStoreId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid BookStore id "+bookStoreId));
    }

    public BookStore updateBookStore(Long id,  BookStore bookStore){
        bookStoreRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid BookStore id"+id));

        bookStore.setId(id);

        return bookStoreRepository.save(bookStore);
    }

    public void deleteBookStore( Long id){
        BookStore bookStore = bookStoreRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid BookStore id"+id));
        bookStoreRepository.delete(bookStore);
    }

}
