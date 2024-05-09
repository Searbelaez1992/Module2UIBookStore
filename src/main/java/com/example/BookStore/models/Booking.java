package com.example.BookStore.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name="BOOKING")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="BOOKING_ID")
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_Id")
    private Users users;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name="DELIVERY_ADDRESS")
    private String deliveryAddress;
    @Column(name="DELIVERY_DATE")
    private Date deliveryDate;
    @Column(name="DELIVERY_TIME")
    private Time deliveryTime;
    @Column(name="BOOKINGSTATUS_ID")
    private long bookingStatusId;
    @Column(name="QUANTITY")
    private int quantity;






    public Booking(Users users, String deliveryAddress, Date deliveryDate, Time deliveryTime, long bookingStatusId, int quantity, Product product) {
        this.users = users;
        this.deliveryAddress = deliveryAddress;
        this.deliveryDate = deliveryDate;
        this.deliveryTime = deliveryTime;
        this.bookingStatusId = bookingStatusId;
        this.quantity = quantity;
        this.product = product;
    }

    public Booking() {
        super();
    }

    public Booking(String deliveryAddress, long id, Users users, Date deliveryDate, Time deliveryTime, long bookingStatusId, int quantity, Product product) {
        this.deliveryAddress = deliveryAddress;
        this.id = id;
        this.users = users;
        this.deliveryDate = deliveryDate;
        this.deliveryTime = deliveryTime;
        this.bookingStatusId = bookingStatusId;
        this.quantity = quantity;
        this.product = product;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Time getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Time deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public long getBookingStatusId() {
        return bookingStatusId;
    }

    public void setBookingStatusId(long bookingStatusId) {
        this.bookingStatusId = bookingStatusId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", users=" + users +
                ", product=" + product +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", deliveryDate=" + deliveryDate +
                ", deliveryTime=" + deliveryTime +
                ", bookingStatusId=" + bookingStatusId +
                ", quantity=" + quantity +
                '}';
    }
}
