package com.example.BookStore.repository;

import com.example.BookStore.models.BookStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookStoreRepository extends JpaRepository<BookStore, Long> {

    @Query("SELECT B"
            + " FROM BookStore B"
            + " INNER JOIN Product P"
            + " ON B.id =P.id"
            + " WHERE (P.name LIKE %?1%"
            + " OR P.author LIKE %?1%"
            + " OR cast(P.id as String) LIKE %?1%"
            + " OR cast(B.avaibleQty as String) LIKE %?1%"
            + " OR cast(B.bookedQty as String) LIKE %?1%"
            + " OR cast(B.soldQty as String) LIKE %?1%)"
            + " AND (P.name LIKE %?2%"
            + " OR P.author LIKE %?2%"
            + " OR cast(P.id as String) LIKE %?2%"
            + " OR cast(B.avaibleQty as String) LIKE %?2%"
            + " OR cast(B.bookedQty as String) LIKE %?2%"
            + " OR cast(B.soldQty as String) LIKE %?2%)"
            + " AND (P.name LIKE %?3%"
            + " OR P.author LIKE %?3%"
            + " OR cast(P.id as String) LIKE %?3%"
            + " OR cast(B.avaibleQty as String) LIKE %?3%"
            + " OR cast(B.bookedQty as String) LIKE %?3%"
            + " OR cast(B.soldQty as String) LIKE %?3%)"
            + " AND (P.name LIKE %?4%"
            + " OR P.author LIKE %?4%"
            + " OR cast(P.id as String) LIKE %?4%"
            + " OR cast(B.avaibleQty as String) LIKE %?4%"
            + " OR cast(B.bookedQty as String) LIKE %?4%"
            + " OR cast(B.soldQty as String) LIKE %?4%)"
            + " AND (P.name LIKE %?5%"
            + " OR P.author LIKE %?5%"
            + " OR cast(P.id as String) LIKE %?5%"
            + " OR cast(B.avaibleQty as String) LIKE %?5%"
            + " OR cast(B.bookedQty as String) LIKE %?5%"
            + " OR cast(B.soldQty as String) LIKE %?5%)"
            + " AND (P.name LIKE %?6%"
            + " OR P.author LIKE %?6%"
            + " OR cast(P.id as String) LIKE %?6%"
            + " OR cast(B.avaibleQty as String) LIKE %?6%"
            + " OR cast(B.bookedQty as String) LIKE %?6%"
            + " OR cast(B.soldQty as String) LIKE %?6%)"
    )
    public List<BookStore> findAll(String keyWord1, String keyWord2, String keyWord3, String keyWord4, String keyWord5, String keyWord6);
}
