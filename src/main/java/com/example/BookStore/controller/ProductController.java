package com.example.BookStore.controller;

import com.example.BookStore.models.Product;
import com.example.BookStore.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        return productService.findProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product)
    {
        return productService.updateProduct(id,product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id)
    {
        productService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }

}
