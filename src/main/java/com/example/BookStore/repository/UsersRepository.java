package com.example.BookStore.repository;

import com.example.BookStore.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query("SELECT u FROM Users u WHERE (u.name LIKE %?1%"
            + " OR u.address LIKE %?1%"
            + " OR u.login LIKE %?1%"
            + " OR cast(u.id as String) LIKE %?1%"
            + " OR u.email LIKE %?1%)"
            + " AND (u.name LIKE %?2%"
            + " OR u.address LIKE %?2%"
            + " OR u.login LIKE %?2%"
            + " OR cast(u.id as String) LIKE %?2%"
            + " OR u.email LIKE %?2%)"
            + " AND (u.name LIKE %?3%"
            + " OR u.address LIKE %?3%"
            + " OR u.login LIKE %?3%"
            + " OR cast(u.id as String) LIKE %?3%"
            + " OR u.email LIKE %?3%)"
            + " AND (u.name LIKE %?4%"
            + " OR u.address LIKE %?4%"
            + " OR u.login LIKE %?4%"
            + " OR cast(u.id as String) LIKE %?4%"
            + " OR u.email LIKE %?4%)"
            + " AND (u.name LIKE %?5%"
            + " OR u.address LIKE %?5%"
            + " OR u.login LIKE %?5%"
            + " OR cast(u.id as String) LIKE %?5%"
            + " OR u.email LIKE %?5%)")
    public List<Users> findAll(String keyWord1, String keyWord2, String keyWord3, String keyWord4, String keyWord5);
}
