package com.example.BookStore.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="BOOKINGSTATUS")
@Data
public class BookingStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="BOOKINGSTATUS_ID")
    private Long id;
    @Column(name="NAME")
    private String name;

    @Override
    public String toString() {
        return "BookingStatus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public BookingStatus() {
    }

    public BookingStatus(Long id) {
        this.id = id;
    }

    public BookingStatus(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}


