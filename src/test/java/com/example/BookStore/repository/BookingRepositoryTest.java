package com.example.BookStore.repository;

import com.example.BookStore.models.Booking;
import com.example.BookStore.models.BookingStatus;
import com.example.BookStore.models.Product;
import com.example.BookStore.models.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class BookingRepositoryTest {

    @Autowired
    private BookingRepository bookingRepository;

    private Booking firstBooking;
    private Booking secondBooking;

    @BeforeEach
    void init() {
        firstBooking = new Booking();
        Users firstUser = new Users();
        firstUser.setId(1l);
        firstUser.setName("simon");
        firstUser.setAddress("Street 55");
        firstUser.setEmail("simon@gmail.com");
        firstUser.setPhone("3109999999");
        firstUser.setRoleId(1);
        firstUser.setLogin("simon332");
        firstUser.setPassword("Testrre1");
        firstBooking.setUsers(firstUser);
        Product theFrankensteinProduct = new Product();
        theFrankensteinProduct.setId(1l);
        theFrankensteinProduct.setName("Frankenstein");
        theFrankensteinProduct.setDescription("One of BBC's 100 Novels That Shaped Our World");
        theFrankensteinProduct.setAuthor("Mary Shelley");
        theFrankensteinProduct.setPrice(70);
        theFrankensteinProduct.setImagePath("https://ik.imagekit.io/panmac/9781509827756.jpg");
        BookingStatus bookingStatus = new BookingStatus(1L, "SUBMITTED");
        firstBooking.setProduct(theFrankensteinProduct);
        firstBooking.setDeliveryAddress("calle 14");
        firstBooking.setDeliveryDate("2024-04-29");
        firstBooking.setBookingStatus(bookingStatus);
        firstBooking.setQuantity(2);

        secondBooking = new Booking();

        secondBooking.setUsers(firstUser);
        secondBooking.setProduct(theFrankensteinProduct);
        secondBooking.setDeliveryAddress("calle 27");
        secondBooking.setDeliveryDate("2024-04-29");
        secondBooking.setBookingStatus(bookingStatus);
        secondBooking.setQuantity(10);
    }

    @Test
    @DisplayName("It Should save the Booking to the database")
    void save(){
        //Arrange

        //Act
        Booking newBooking = bookingRepository.save(firstBooking);
        //Assert
        assertNotNull(newBooking);
        assertThat(newBooking.getId()).isNotEqualTo(null);
    }

    @Test
    @DisplayName("It should return the Booking list with size of 4")
    void getAllBookings(){

        bookingRepository.save(firstBooking);
        bookingRepository.save(secondBooking);

        List<Booking>  list = bookingRepository.findAll();

        assertNotNull(list);
        assertThat(list).isNotNull();
        assertEquals(4,list.size());
    }

    @Test
    @DisplayName("It should return the Booking by its id")
    void getBookingById(){

        bookingRepository.save(firstBooking);

        Booking existingBooking = bookingRepository.findById(firstBooking.getId()).get();

        assertNotNull(existingBooking);
        assertEquals("calle 14",existingBooking.getDeliveryAddress());
    }

    @Test
    @DisplayName("It should updated the Booking with the Delivery Address Street 43")
    void updateBooking(){

        bookingRepository.save(firstBooking);

        Booking existingBooking = bookingRepository.findById(firstBooking.getId()).get();
        existingBooking.setDeliveryAddress("Street 43");

        Booking newBooking = bookingRepository.save(existingBooking);

        assertEquals("Street 43",newBooking.getDeliveryAddress());
        assertEquals(2,newBooking.getQuantity());
    }

    @Test
    @DisplayName("It should delete the existing Booking")
    void deletetBooking(){


        bookingRepository.save(firstBooking);
        long id = firstBooking.getId();

        bookingRepository.save(secondBooking);

        bookingRepository.delete(firstBooking);
        Optional<Booking> existingBooking = bookingRepository.findById(id);
        List<Booking> list = bookingRepository.findAll();

        assertEquals(3,list.size());
        assertThat(existingBooking).isEmpty();

    }

}
