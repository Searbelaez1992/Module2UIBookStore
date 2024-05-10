package com.example.BookStore.controller;


import com.example.BookStore.models.BookStore;
import com.example.BookStore.models.Product;
import com.example.BookStore.service.BookStoreService;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig
@WebMvcTest(controllers = BookStoreController.class)
public class BookStoreControllerTest {

    @MockBean
    private BookStoreService bookStoreService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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
    void shouldCreateNewBookStore() throws Exception {

        when(bookStoreService.save(any(BookStore.class))).thenReturn(firstBookStore);

        this.mockMvc.perform(post("/api/bookstore")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(firstBookStore)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.avaibleQty",is(firstBookStore.getAvaibleQty())))
                .andExpect(jsonPath("$.soldQty",is(firstBookStore.getSoldQty())))
                .andExpect(jsonPath("$.bookedQty",is(firstBookStore.getBookedQty())));
    }

    @Test
    @DisplayName("It Should Return all the BookStore object from the database")
    void ShouldFetchAllBookStores() throws Exception {

        List<BookStore> list = new ArrayList<>();
        list.add(firstBookStore);
        list.add(secondBookStore);

        when(bookStoreService.findAll()).thenReturn(list);

        this.mockMvc.perform(get("/api/bookstore"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",is(list.size())));
    }

    @Test
    @DisplayName("It Should Return one BookStore object by id from the database")
    void ShouldFetchOneBookStoreById() throws Exception {

        when(bookStoreService.findBookStoreById(anyLong())).thenReturn(firstBookStore);

        this.mockMvc.perform(get("/api/bookstore/{id}",1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.avaibleQty",is(firstBookStore.getAvaibleQty())))
                .andExpect(jsonPath("$.soldQty",is(firstBookStore.getSoldQty())))
                .andExpect(jsonPath("$.bookedQty",is(firstBookStore.getBookedQty())));
    }

    @Test
    @DisplayName("It Should delete a BookStore object from the database")
    void ShouldDeleteBookStore() throws Exception {

        doNothing().when(bookStoreService).deleteBookStore(anyLong());

        this.mockMvc.perform(delete("/api/bookstore/{id}",1l))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("It Should update a BookStore from the database")
    void ShouldUpdateBookStore() throws Exception {

        when(bookStoreService.updateBookStore(anyLong(),any(BookStore.class))).thenReturn(firstBookStore);

        this.mockMvc.perform(post("/api/bookstore/{id}",1l)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(firstBookStore)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.avaibleQty",is(firstBookStore.getAvaibleQty())))
                .andExpect(jsonPath("$.soldQty",is(firstBookStore.getSoldQty())))
                .andExpect(jsonPath("$.bookedQty",is(firstBookStore.getBookedQty())));
    }
}
