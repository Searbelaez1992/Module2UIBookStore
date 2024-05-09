package com.example.BookStore.models;

import jakarta.persistence.*;

@Entity
@Table(name="BOOKSTORE")
public class BookStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="BOOKSTORE_ID")
    private long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name="AVAILABLE_QTY")
    private int avaibleQty;
    @Column(name="BOOKED_QTY")
    private int bookedQty;
    @Column(name="SOLD_QTY")
    private int soldQty;

    public BookStore(Product product, int avaibleQty, int soldQty, int bookedQty) {
        this.product = product;
        this.avaibleQty = avaibleQty;
        this.soldQty = soldQty;
        this.bookedQty = bookedQty;
    }

    public BookStore(Product product, long id, int avaibleQty, int bookedQty, int soldQty) {
        this.product = product;
        this.id = id;
        this.avaibleQty = avaibleQty;
        this.bookedQty = bookedQty;
        this.soldQty = soldQty;
    }

    public BookStore() {
        super();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAvaibleQty() {
        return avaibleQty;
    }

    public void setAvaibleQty(int avaibleQty) {
        this.avaibleQty = avaibleQty;
    }

    public int getBookedQty() {
        return bookedQty;
    }

    public void setBookedQty(int bookedQty) {
        this.bookedQty = bookedQty;
    }

    public int getSoldQty() {
        return soldQty;
    }

    public void setSoldQty(int soldQty) {
        this.soldQty = soldQty;
    }
}
