package com.example.BookStore.service;

import com.example.BookStore.models.Product;
import com.example.BookStore.repository.ProductRepository;
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
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;


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
    void save(){

        when(productRepository.save(any(Product.class))).thenReturn(theFrankensteinProduct);

        Product newProduct = productService.addProduct(theFrankensteinProduct);

        assertNotNull(newProduct);
        assertThat(newProduct.getName()).isEqualTo("Frankenstein");
    }

    @Test
    @DisplayName("It Should return list of products with size 2")
    void getProducts(){

        List<Product> list = new ArrayList<>();
        list.add(theFrankensteinProduct);
        list.add(itProduct);

        when(productRepository.findAll()).thenReturn(list);

        List<Product> products = productService.findAll();

        assertEquals(2,products.size());
        assertNotNull(products);
    }

    @Test
    @DisplayName("It Should return  the product object")
    void findProductById() {

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(theFrankensteinProduct));

        Product existingProduct = productService.findProductById(1l);

        assertNotNull(existingProduct);
        assertThat(existingProduct.getId()).isEqualTo(1l);
    }

    @Test
    @DisplayName("It Should throw a Exception")
    void findProductByIdForException() {

        assertThrows(RuntimeException.class,() -> {
            productService.findProductById(2l);
        });
    }

    @Test
    @DisplayName("It Should update a product into the database")
    void updateProduct() {

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(theFrankensteinProduct));
        when(productRepository.save(any(Product.class))).thenReturn(theFrankensteinProduct);
        theFrankensteinProduct.setAuthor("Isaac Asimov");

        productService.updateProduct(1l,theFrankensteinProduct);

        Product updatedProduct = productService.findProductById(1l);

        assertNotNull(updatedProduct);
        assertEquals("Isaac Asimov", updatedProduct.getAuthor());
    }

    @Test
    @DisplayName("It Should delete a product from the database")
    void deleteProduct() {

        when(productRepository.findById(anyLong())).thenReturn(Optional.of(theFrankensteinProduct));
        doNothing().when(productRepository).delete(any(Product.class));

        productService.deleteProduct(1l);

        verify(productRepository,times(1)).delete(theFrankensteinProduct);
    }
}
