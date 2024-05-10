package com.example.BookStore.service;

import com.example.BookStore.models.Booking;
import com.example.BookStore.models.Product;
import com.example.BookStore.models.Users;
import com.example.BookStore.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    @InjectMocks
    private BookingService bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private ProductService productService;

    @Mock
    private UsersService usersService;

    private Booking firstBooking;
    private Booking secondBooking;

    @BeforeEach
    void init() {

        firstBooking = new Booking();
        firstBooking.setId(1l);
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
        firstBooking.setProduct(theFrankensteinProduct);
        firstBooking.setDeliveryAddress("calle 14");
        Date date = new Date();
        firstBooking.setDeliveryDate(date);
        firstBooking.setBookingStatusId(1);
        firstBooking.setQuantity(2);

        secondBooking = new Booking();
        secondBooking.setId(2l);
        secondBooking.setUsers(firstUser);
        secondBooking.setProduct(theFrankensteinProduct);
        secondBooking.setDeliveryAddress("calle 27");
        secondBooking.setDeliveryDate(date);
        secondBooking.setBookingStatusId(2);
        secondBooking.setQuantity(10);
    }

    @Test
    @DisplayName("It Should save the Booking object to the database")
    void save(){

        when(productService.findProductById(anyLong())).thenReturn(firstBooking.getProduct());
        when(usersService.findUsersById(anyLong())).thenReturn(firstBooking.getUsers());
        when(bookingRepository.save(any(Booking.class))).thenReturn(firstBooking);

        Booking newBooking = bookingService.save(firstBooking);

        assertNotNull(newBooking);
        assertThat(newBooking.getDeliveryAddress()).isEqualTo("calle 14");
    }

    @Test
    @DisplayName("It Should return list of Booking with size 2")
    void getBookings(){

        List<Booking> list = new ArrayList<>();
        list.add(firstBooking);
        list.add(secondBooking);

        when(bookingRepository.findAll()).thenReturn(list);

        List<Booking> bookings = bookingService.findAll();

        assertEquals(2,bookings.size());
        assertNotNull(bookings);
    }

    @Test
    @DisplayName("It Should return  the Booking object")
    void findBookingById() {

        when(bookingRepository.findById(anyLong())).thenReturn(Optional.of(firstBooking));

        Booking existingBooking = bookingService.findBookingById(1l);

        assertNotNull(existingBooking);
        assertThat(existingBooking.getId()).isEqualTo(1l);
    }

    @Test
    @DisplayName("It Should throw a Exception")
    void findBookingByIdForException() {

        assertThrows(RuntimeException.class,() -> {
            bookingService.findBookingById(2l);
        });
    }

    @Test
    @DisplayName("It Should update a Booking into the database")
    void updateBooking() {

        when(bookingRepository.findById(anyLong())).thenReturn(Optional.of(firstBooking));
        when(bookingRepository.save(any(Booking.class))).thenReturn(firstBooking);
        firstBooking.setDeliveryAddress("Street 82");

        bookingService.updateBooking(1l,firstBooking);

        Booking updatedBooking = bookingService .findBookingById(1l);

        assertNotNull(updatedBooking);
        assertEquals("Street 82", updatedBooking.getDeliveryAddress());
    }

    @Test
    @DisplayName("It Should delete a Booking from the database")
    void deleteBooking() {

        when(bookingRepository.findById(anyLong())).thenReturn(Optional.of(firstBooking));
        doNothing().when(bookingRepository).delete(any(Booking.class));

        bookingService.deleteBooking(1l);

        verify(bookingRepository,times(1)).delete(firstBooking);
    }

}
