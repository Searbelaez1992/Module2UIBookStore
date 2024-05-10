package com.example.BookStore.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="PRODUCT")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="PRODUCT_ID")
    private Long id;
    @Column(name="NAME")
    private String name;
    @Column(name="DESCRIPTION")
    private String description;
    @Column(name="AUTHOR")
    private String author;
    @Column(name="PRICE")
    private float price;
    @Column(name="IMAGE_PATH")
    private String imagePath;



    public Product(String description, Long id, String name, String author, float price, String imagePath) {
        this.description = description;
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.imagePath = imagePath;
    }

    public Product(){}

    public Product(Long id) {
        this.id = id;
    }

    public Product(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
