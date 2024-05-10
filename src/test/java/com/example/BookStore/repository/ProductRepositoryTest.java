package com.example.BookStore.repository;

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
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    private Product theFrankensteinProduct;
    private Product itProduct;

    @BeforeEach
    void init(){
        theFrankensteinProduct = new Product();
        theFrankensteinProduct.setName("Frankenstein");
        theFrankensteinProduct.setDescription("One of BBC's 100 Novels That Shaped Our World");
        theFrankensteinProduct.setAuthor("Mary Shelley");
        theFrankensteinProduct.setPrice(70);
        theFrankensteinProduct.setImagePath("https://ik.imagekit.io/panmac/9781509827756.jpg");

        itProduct = new Product();
        itProduct.setName("iT");
        itProduct.setDescription("The intrepid heroes of this very long and sophisticated novel love each othe");
        itProduct.setAuthor("Stephen King");
        itProduct.setPrice(110);
        itProduct.setImagePath("https://upload.wikimedia.org/wikipedia/commons/thumb/1/1a/IC_first_edition.jpg");
    }

    @Test
    @DisplayName("It Should save the product to the database")
    void save(){
        //Arrange

        //Act
        Product newProduct = productRepository.save(theFrankensteinProduct);
        //Assert
        assertNotNull(newProduct);
        assertThat(newProduct.getId()).isNotEqualTo(null);
    }

    @Test
    @DisplayName("It should return the Products list with size of 5")
    void getAllProducts(){

        productRepository.save(theFrankensteinProduct);
        productRepository.save(itProduct);

        List<Product>  list = productRepository.findAll();

        assertNotNull(list);
        assertThat(list).isNotNull();
        assertEquals(5,list.size());
    }

    @Test
    @DisplayName("It should return the product by its id")
    void getProductById(){

        productRepository.save(theFrankensteinProduct);

        Product existingProduct = productRepository.findById(theFrankensteinProduct.getId()).get();

        assertNotNull(existingProduct);
        assertEquals("Frankenstein",existingProduct.getName());
    }

    @Test
    @DisplayName("It should update the product with the Autor Isaac Asimov")
    void updateProduct(){

        productRepository.save(theFrankensteinProduct);

        Product existingProduct = productRepository.findById(theFrankensteinProduct.getId()).get();
        existingProduct.setAuthor("Isaac Asimov");

        Product newProduct = productRepository.save(existingProduct);

        assertEquals("Isaac Asimov",newProduct.getAuthor());
        assertEquals("Frankenstein",newProduct.getName());
    }

    @Test
    @DisplayName("It should delete the existing product")
    void deleteProduct(){


        productRepository.save(theFrankensteinProduct);
        long id = theFrankensteinProduct.getId();

        productRepository.save(itProduct);

        productRepository.delete(theFrankensteinProduct);
        Optional<Product> existingProduct = productRepository.findById(id);
        List<Product> list = productRepository.findAll();

        assertEquals(4,list.size());
        assertThat(existingProduct).isEmpty();

    }
}
