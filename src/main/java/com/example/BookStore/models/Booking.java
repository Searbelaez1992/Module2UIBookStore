package com.example.BookStore.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name="BOOKING")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="BOOKING_ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_Id")
    private Users users;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name="DELIVERY_ADDRESS")
    private String deliveryAddress;
    @JsonFormat(pattern = "yyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @Column(name="DELIVERY_DATE")
    private String deliveryDate;
    @JsonFormat(pattern = "HH:mm", shape = JsonFormat.Shape.STRING)
    @Column(name="DELIVERY_TIME")
    private String deliveryTime;
    @ManyToOne
    @JoinColumn(name = "bookingstatus_id")
    private BookingStatus bookingStatus;
    @Column(name="QUANTITY")
    private int quantity;

    public Booking(Users users, String deliveryAddress, String deliveryDate, String deliveryTime, BookingStatus bookingStatus, int quantity, Product product) {
        this.users = users;
        this.deliveryAddress = deliveryAddress;
        this.deliveryDate = deliveryDate;
        this.deliveryTime = deliveryTime;
        this.bookingStatus = bookingStatus;
        this.quantity = quantity;
        this.product = product;
    }

    public Booking() {
        super();
    }

    public Booking(String deliveryAddress, Long id, Users users, String deliveryDate, String deliveryTime, BookingStatus bookingStatus, int quantity, Product product) {
        this.deliveryAddress = deliveryAddress;
        this.id = id;
        this.users = users;
        this.deliveryDate = deliveryDate;
        this.deliveryTime = deliveryTime;
        this.bookingStatus = bookingStatus;
        this.quantity = quantity;
        this.product = product;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
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
                ", bookingStatus=" + bookingStatus +
                ", quantity=" + quantity +
                '}';
    }
}
