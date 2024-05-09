package com.example.BookStore.service;

import com.example.BookStore.models.Booking;
import com.example.BookStore.repository.BookingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ProductService productService;
    private final UsersService usersService;

    public BookingService(BookingRepository bookingRepository, ProductService productService, UsersService usersService) {
        this.bookingRepository = bookingRepository;
        this.productService = productService;
        this.usersService = usersService;
    }

    public List<Booking> findAll(){
        return bookingRepository.findAll();

    }

    public Booking save(Booking booking){

        Booking entity = bookingRepository.save(booking);
        booking.setProduct(productService.findProductById(booking.getProduct().getId()));
        booking.setUsers(usersService.findUsersById(booking.getUsers().getId()));
        return entity;
    }

    public Booking findBookingById(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid booking id "+bookingId));
        return booking;
    }

    public Booking updateBooking(Long id,  Booking booking){
        bookingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid booking id"+id));

        booking.setId(id);

        return bookingRepository.save(booking);
    }

    public void deleteBooking( Long id){
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid booking id"+id));
        bookingRepository.delete(booking);
    }
}
