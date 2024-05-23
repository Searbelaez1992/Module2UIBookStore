package com.example.BookStore.controller;


import com.example.BookStore.models.Booking;
import com.example.BookStore.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public List<Booking> getBookings() {
        return bookingService.findAll();
    }

    @GetMapping("/{id}")
    public Booking getBooking(@PathVariable Long id) {
        return bookingService.findBookingById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Booking saveBooking(@RequestBody Booking booking) {
        return bookingService.save(booking);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Booking updateBooking(@PathVariable long id, @RequestBody Booking booking) throws Exception {
        return bookingService.updateBooking(id,booking);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id)
    {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}
