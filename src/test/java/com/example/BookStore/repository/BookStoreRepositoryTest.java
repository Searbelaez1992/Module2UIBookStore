package com.example.BookStore.repository;

import com.example.BookStore.models.BookStore;
import com.example.BookStore.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class BookStoreRepositoryTest {

    @Autowired
    private BookStoreRepository bookStoreRepository;

    private BookStore firstBookStore;
    private BookStore secondBookStore;

    @BeforeEach
    void init() {

        firstBookStore = new BookStore();
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

        secondBookStore.setProduct(theFrankensteinProduct);
        secondBookStore.setAvaibleQty(19);
        secondBookStore.setBookedQty(12);
        secondBookStore.setSoldQty(33);
    }

    @Test
    @DisplayName("It Should save the BookStore to the database")
    void save(){
        //Arrange

        //Act
        BookStore newBookStore = bookStoreRepository.save(firstBookStore);
        //Assert
        assertNotNull(newBookStore);
        assertThat(newBookStore.getId()).isNotEqualTo(null);
    }

    @Test
    @DisplayName("It should return the BookStore list with size of 4")
    void getAllBookStores(){

        bookStoreRepository.save(firstBookStore);
        bookStoreRepository.save(secondBookStore);

        List<BookStore>  list = bookStoreRepository.findAll();

        assertNotNull(list);
        assertThat(list).isNotNull();
        assertEquals(4,list.size());
    }

    @Test
    @DisplayName("It should return the BookStore by its id")
    void getBookStoreById(){

        bookStoreRepository.save(firstBookStore);

        BookStore existingBookStore = bookStoreRepository.findById(firstBookStore.getId()).get();

        assertNotNull(existingBookStore);
        assertEquals(10,existingBookStore.getAvaibleQty());
    }

    @Test
    @DisplayName("It should updated the BookStore with the SoldQTY 800")
    void updateBookStore(){

        bookStoreRepository.save(firstBookStore);

        BookStore existingBookStore = bookStoreRepository.findById(firstBookStore.getId()).get();
        existingBookStore.setSoldQty(800);

        BookStore newBookStore = bookStoreRepository.save(existingBookStore);

        assertEquals(800,newBookStore.getSoldQty());
        assertEquals(10,newBookStore.getAvaibleQty());
    }

    @Test
    @DisplayName("It should delete the existing BookStore")
    void deletetBookStore(){


        bookStoreRepository.save(firstBookStore);
        long id = firstBookStore.getId();

        bookStoreRepository.save(secondBookStore);

        bookStoreRepository.delete(firstBookStore);
        Optional<BookStore> existingBookStore = bookStoreRepository.findById(id);
        List<BookStore> list = bookStoreRepository.findAll();

        assertEquals(3,list.size());
        assertThat(existingBookStore).isEmpty();

    }

}
