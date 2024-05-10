package com.example.BookStore.controller;

import com.example.BookStore.models.Booking;
import com.example.BookStore.models.Product;
import com.example.BookStore.models.Users;
import com.example.BookStore.service.BookingService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig
@WebMvcTest(controllers = BookingController.class)
public class BookingControllerTest {

    @MockBean
    private BookingService bookingService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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
        firstBooking.setProduct(theFrankensteinProduct);
        firstBooking.setDeliveryAddress("calle 14");
        Date date = new Date();
        firstBooking.setDeliveryDate(date);
        firstBooking.setBookingStatusId(1);
        firstBooking.setQuantity(2);

        secondBooking = new Booking();

        secondBooking.setUsers(firstUser);
        secondBooking.setProduct(theFrankensteinProduct);
        secondBooking.setDeliveryAddress("calle 27");
        secondBooking.setDeliveryDate(date);
        secondBooking.setBookingStatusId(2);
        secondBooking.setQuantity(10);
    }

    @Test
    @DisplayName("It Should save the Booking object to the database")
    void shouldCreateNewBooking() throws Exception {

        when(bookingService.save(any(Booking.class))).thenReturn(firstBooking);

        this.mockMvc.perform(post("/api/booking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(firstBooking)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.deliveryAddress",is(firstBooking.getDeliveryAddress())))
                .andExpect(jsonPath("$.deliveryTime",is(firstBooking.getDeliveryTime())))
                .andExpect(jsonPath("$.deliveryTime",is(firstBooking.getDeliveryTime())))
                .andExpect(jsonPath("$.quantity",is(firstBooking.getQuantity())));
    }

    @Test
    @DisplayName("It Should Return all the Booking object from the database")
    void ShouldFetchAllBookings() throws Exception {

        List<Booking> list = new ArrayList<>();
        list.add(firstBooking);
        list.add(secondBooking);

        when(bookingService.findAll()).thenReturn(list);

        this.mockMvc.perform(get("/api/booking"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",is(list.size())));
    }

    @Test
    @DisplayName("It Should Return one  Booking by id from the database")
    void ShouldFetchOneBookingById() throws Exception {

        when(bookingService.findBookingById(anyLong())).thenReturn(firstBooking);

        this.mockMvc.perform(get("/api/booking/{id}",1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deliveryAddress",is(firstBooking.getDeliveryAddress())))
                .andExpect(jsonPath("$.deliveryTime",is(firstBooking.getDeliveryTime())))
                .andExpect(jsonPath("$.deliveryTime",is(firstBooking.getDeliveryTime())))
                .andExpect(jsonPath("$.quantity",is(firstBooking.getQuantity())));
    }

    @Test
    @DisplayName("It Should delete a Booking object from the database")
    void ShouldDeleteBooking() throws Exception {

        doNothing().when(bookingService).deleteBooking(anyLong());

        this.mockMvc.perform(delete("/api/booking/{id}",1l))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("It Should update a Booking from the database")
    void ShouldUpdateBooking() throws Exception {

        when(bookingService.updateBooking(anyLong(),any(Booking.class))).thenReturn(firstBooking);

        this.mockMvc.perform(post("/api/booking/{id}",1l)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(firstBooking)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.deliveryAddress",is(firstBooking.getDeliveryAddress())))
                .andExpect(jsonPath("$.deliveryTime",is(firstBooking.getDeliveryTime())))
                .andExpect(jsonPath("$.deliveryTime",is(firstBooking.getDeliveryTime())))
                .andExpect(jsonPath("$.quantity",is(firstBooking.getQuantity())));
    }
}
