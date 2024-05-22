package com.example.BookStore.service;

import com.example.BookStore.models.BookingStatus;
import com.example.BookStore.repository.BookingStatusRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BookingStatusService {

    private final BookingStatusRepository bookingStatusRepository;

    public BookingStatusService(BookingStatusRepository bookingStatusRepository) {
        this.bookingStatusRepository = bookingStatusRepository;
    }

    public BookingStatus findBookingStatustById(Long idBookingStatus) {

        return bookingStatusRepository.findById(idBookingStatus)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid BookingStatus id "+idBookingStatus));
    }
}
