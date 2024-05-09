package com.example.BookStore.service;

import com.example.BookStore.models.BookStore;
import com.example.BookStore.models.Product;
import com.example.BookStore.repository.BookStoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookStoreServiceTest {

    @InjectMocks
    private BookStoreService bookStoreService;

    @Mock
    private BookStoreRepository bookStoreRepository;

    @Mock
    private ProductService productService;

    private BookStore firstBookStore;
    private BookStore secondBookStore;

    @BeforeEach
    void init() {

        firstBookStore = new BookStore();
        firstBookStore.setId(1l);

        Product theFrankensteinProduct = new Product();
        theFrankensteinProduct.setId(1l);
        theFrankensteinProduct.setName("Frankenstein");
        theFrankensteinProduct.setDescription("One of BBC's 100 Novels That Shaped Our World");
        theFrankensteinProduct.setAuthor("Mary Shelley");
        theFrankensteinProduct.setPrice(70);
        theFrankensteinProduct.setImagePath("https://ik.imagekit.io/panmac/9781509827756.jpg");

        firstBookStore.setProduct(theFrankensteinProduct);
        firstBookStore.setAvaibleQty(10);
        firstBookStore.setBookedQty(13);
        firstBookStore.setSoldQty(8);

        secondBookStore = new BookStore();
        secondBookStore.setId(2l);
        secondBookStore.setProduct(theFrankensteinProduct);
        secondBookStore.setAvaibleQty(19);
        secondBookStore.setBookedQty(12);
        secondBookStore.setSoldQty(33);
    }

    @Test
    @DisplayName("It Should save the BookStore object to the database")
    void save(){

        when(productService.findProductById(anyLong())).thenReturn(firstBookStore.getProduct());
        when(bookStoreRepository.save(any(BookStore.class))).thenReturn(firstBookStore);

        BookStore newBookStore = bookStoreService.save(firstBookStore);

        assertNotNull(newBookStore);
        assertThat(newBookStore.getAvaibleQty()).isEqualTo(10);
    }

    @Test
    @DisplayName("It Should return list of BookStore with size 2")
    void getBookStores(){

        List<BookStore> list = new ArrayList<>();
        list.add(firstBookStore);
        list.add(firstBookStore);

        when(bookStoreRepository.findAll()).thenReturn(list);

        List<BookStore> bookStores = bookStoreService.findAll();

        assertEquals(2,bookStores.size());
        assertNotNull(bookStores);
    }

    @Test
    @DisplayName("It Should return  the BookStore object")
    void findBookStoreById() {

        when(bookStoreRepository.findById(anyLong())).thenReturn(Optional.of(firstBookStore));

        BookStore existingBookStore = bookStoreService.findBookStoreById(1l);

        assertNotNull(existingBookStore);
        assertThat(existingBookStore.getId()).isEqualTo(1l);
    }

    @Test
    @DisplayName("It Should throw a Exception")
    void findBookStoreByIdForException() {

        when(bookStoreRepository.findById(1l)).thenReturn(Optional.of(firstBookStore));

        assertThrows(RuntimeException.class,() -> {
            bookStoreService.findBookStoreById(2l);
        });
    }

    @Test
    @DisplayName("It Should update a BookStore into the database")
    void updateBookStore() {

        when(bookStoreRepository.findById(anyLong())).thenReturn(Optional.of(firstBookStore));
        when(bookStoreRepository.save(any(BookStore.class))).thenReturn(firstBookStore);
        firstBookStore.setAvaibleQty(65);

        bookStoreService.updateBookStore(1l,firstBookStore);

        BookStore updatedBookStore = bookStoreService.findBookStoreById(1l);

        assertNotNull(updatedBookStore);
        assertEquals(65, updatedBookStore.getAvaibleQty());
    }

    @Test
    @DisplayName("It Should delete a BookStore from the database")
    void deleteBookStore() {

        when(bookStoreRepository.findById(anyLong())).thenReturn(Optional.of(firstBookStore));
        doNothing().when(bookStoreRepository).delete(any(BookStore.class));

        bookStoreService.deleteBookStore(1l);

        verify(bookStoreRepository,times(1)).delete(firstBookStore);
    }

}
