package com.example.BookStore.repository;

import com.example.BookStore.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT B"
            + " FROM Booking B"
            + " INNER JOIN Product P"
            + " ON B.id =P.id"
            + " INNER JOIN Users U"
            + " ON B.id =U.id"
            + " INNER JOIN BookingStatus S"
            + " ON B.id =S.id"
            + " WHERE (U.name LIKE %?1%"
            + " OR P.name LIKE %?1%"
            + " OR cast(B.deliveryDate as String) LIKE %?1%"
            + " OR B.deliveryAddress LIKE %?1%)"
            + " AND (U.name LIKE %?2%"
            + " OR P.name LIKE %?2%"
            + " OR cast(B.deliveryDate as String) LIKE %?2%"
            + " OR B.deliveryAddress LIKE %?2%)"
            + " AND (U.name LIKE %?3%"
            + " OR P.name LIKE %?3%"
            + " OR cast(B.deliveryDate as String) LIKE %?3%"
            + " OR B.deliveryAddress LIKE %?3%)"
            + " AND (U.name LIKE %?4%"
            + " OR P.name LIKE %?4%"
            + " OR cast(B.deliveryDate as String) LIKE %?4%"
            + " OR B.deliveryAddress LIKE %?4%)"
            + " AND (U.name LIKE %?5%"
            + " OR P.name LIKE %?5%"
            + " OR cast(B.deliveryDate as String) LIKE %?5%"
            + " OR B.deliveryAddress LIKE %?5%)")
    public List<Booking> findAll(String keyWord1, String keyWord2, String keyWord3, String keyWord4, String keyWord5);
}
