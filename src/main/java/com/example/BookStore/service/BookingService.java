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
    private final BookingStatusService bookingStatusService;

    public BookingService(BookingRepository bookingRepository, ProductService productService, UsersService usersService, BookingStatusService bookingStatusService) {
        this.bookingRepository = bookingRepository;
        this.productService = productService;
        this.usersService = usersService;
        this.bookingStatusService = bookingStatusService;
    }

    public List<Booking> findAll(){
        return bookingRepository.findAll();
    }


    public List<Booking> getBookings(String keyWord1,String keyWord2,String keyWord3,String keyWord4,String keyWord5){

        if(keyWord1 != null || keyWord2 != null || keyWord3 != null || keyWord4 != null || keyWord5 != null)
            return bookingRepository.findAll(keyWord1, keyWord2, keyWord3, keyWord4, keyWord5);
        return bookingRepository.findAll();
    }

    public Booking save(Booking booking){
        if(booking.getId() !=null )
            booking.setId(null);
        Booking entity = bookingRepository.save(booking);
        booking.setBookingStatus(bookingStatusService.findBookingStatustById(booking.getBookingStatus().getId()));
        booking.setProduct(productService.findProductById(booking.getProduct().getId()));
        booking.setUsers(usersService.findUsersById(booking.getUsers().getId()));
        return entity;
    }

    public Booking findBookingById(Long bookingId) {

        return bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid booking id "+bookingId));
    }

    public Booking updateBooking(Long id,  Booking booking){
        bookingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid booking id"+id));

        booking.setId(id);

        return bookingRepository.save(booking);
    }

    public Booking updateStatusBooking(Long id,Long status){
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid booking id"+id));


        booking.setBookingStatus(bookingStatusService.findBookingStatustById(status));
        return bookingRepository.save(booking);
    }

    public void deleteBooking( Long id){
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid booking id"+id));
        bookingRepository.delete(booking);
    }
}
