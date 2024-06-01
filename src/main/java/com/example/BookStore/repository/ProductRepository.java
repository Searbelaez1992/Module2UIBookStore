package com.example.BookStore.repository;

import com.example.BookStore.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE (p.name LIKE %?1%"
            + " OR p.description LIKE %?1%"
            + " OR p.author LIKE %?1%"
            + " OR cast(p.id as String) LIKE %?1%)"
            + " AND (p.name LIKE %?2%"
            + " OR p.description LIKE %?2%"
            + " OR p.author LIKE %?2%"
            + " OR cast(p.id as String) LIKE %?2%)"
            + " AND (p.name LIKE %?3%"
            + " OR p.description LIKE %?3%"
            + " OR p.author LIKE %?3%"
            + " OR cast(p.id as String) LIKE %?3%)"
            + " AND (p.name LIKE %?4%"
            + " OR p.description LIKE %?4%"
            + " OR p.author LIKE %?4%"
            + " OR cast(p.id as String) LIKE %?4%)")
    public List<Product> findAll(String keyWord1, String keyWord2, String keyWord3, String keyWord4);
}
