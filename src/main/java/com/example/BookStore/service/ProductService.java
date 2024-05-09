package com.example.BookStore.service;

import com.example.BookStore.models.Product;
import com.example.BookStore.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public Product addProduct(Product product){

        Product entity = productRepository.save(product);
        return entity;
    }

    public Product findProductById(Long idProduct) {

        Product product = productRepository.findById(idProduct)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid product id "+idProduct));
        return product;
    }

    public Product updateProduct(Long id,  Product product){
        productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid product id"+id));

        product.setId(id);

        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid product id"+id));

        productRepository.delete(product);
    }



}