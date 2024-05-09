package com.example.BookStore.controller;

import com.example.BookStore.models.Product;
import com.example.BookStore.service.ProductService;

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
@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {

    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Product theFrankensteinProduct;
    private Product itProduct;

    @BeforeEach
    void init(){
        theFrankensteinProduct = new Product();
        theFrankensteinProduct.setId(1l);
        theFrankensteinProduct.setName("Frankenstein");
        theFrankensteinProduct.setDescription("One of BBC's 100 Novels That Shaped Our World");
        theFrankensteinProduct.setAuthor("Mary Shelley");
        theFrankensteinProduct.setPrice(70);
        theFrankensteinProduct.setImagePath("https://ik.imagekit.io/panmac/9781509827756.jpg");

        itProduct = new Product();
        itProduct.setId(2l);
        itProduct.setName("iT");
        itProduct.setDescription("The intrepid heroes of this very long and sophisticated novel love each othe");
        itProduct.setAuthor("Stephen King");
        itProduct.setPrice(110);
        itProduct.setImagePath("https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/IC_first_edition.jpg");
    }

    @Test
    @DisplayName("It Should save the product object to the database")
    void shouldCreateNewProduct() throws Exception {

        when(productService.addProduct(any(Product.class))).thenReturn(theFrankensteinProduct);

        this.mockMvc.perform(post("/api/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(theFrankensteinProduct)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name",is(theFrankensteinProduct.getName())))
                .andExpect(jsonPath("$.author",is(theFrankensteinProduct.getAuthor())))
                .andExpect(jsonPath("$.description",is(theFrankensteinProduct.getDescription())))
                .andExpect(jsonPath("$.imagePath",is(theFrankensteinProduct.getImagePath())));
    }

    @Test
    @DisplayName("It Should Return all the products object form the database")
    void ShouldFetchAllProducts() throws Exception {

        List<Product> list = new ArrayList<>();
        list.add(theFrankensteinProduct);
        list.add(itProduct);

        when(productService.getProducts()).thenReturn(list);

        this.mockMvc.perform(get("/api/product"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",is(list.size())));
    }

    @Test
    @DisplayName("It Should Return one products object by id from the database")
    void ShouldFetchOneProductById() throws Exception {

        when(productService.findProductById(anyLong())).thenReturn(theFrankensteinProduct);

        this.mockMvc.perform(get("/api/product/{id}",1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(theFrankensteinProduct.getName())))
                .andExpect(jsonPath("$.author",is(theFrankensteinProduct.getAuthor())))
                .andExpect(jsonPath("$.description",is(theFrankensteinProduct.getDescription())))
                .andExpect(jsonPath("$.imagePath",is(theFrankensteinProduct.getImagePath())));

    }

    @Test
    @DisplayName("It Should delete a product object from the database")
    void ShouldDeleteProduct() throws Exception {

        doNothing().when(productService).deleteProduct(anyLong());

        this.mockMvc.perform(delete("/api/product/{id}",1l))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("It Should update a products from the database")
    void ShouldUpdateProduct() throws Exception {

        when(productService.updateProduct(anyLong(),any(Product.class))).thenReturn(theFrankensteinProduct);

        this.mockMvc.perform(post("/api/product/{id}",1l)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(theFrankensteinProduct)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(theFrankensteinProduct.getName())))
                .andExpect(jsonPath("$.author",is(theFrankensteinProduct.getAuthor())))
                .andExpect(jsonPath("$.description",is(theFrankensteinProduct.getDescription())))
                .andExpect(jsonPath("$.imagePath",is(theFrankensteinProduct.getImagePath())));
    }
}
