package com.example.flimchos.service;

import com.example.flimchos.model.Booking;
import com.example.flimchos.repository.BookingRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking createBooking(Booking booking){
        return bookingRepository.save(booking);
    }

}
