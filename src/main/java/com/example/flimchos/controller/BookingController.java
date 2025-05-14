package com.example.flimchos.controller;

import com.example.flimchos.dto.BookingCreationDTO;
import com.example.flimchos.dto.BookingDTO;
import com.example.flimchos.model.Booking;
import com.example.flimchos.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    //Create
    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingCreationDTO booking) {
        return ResponseEntity.ok(bookingService.createBooking(booking));
    }

    //Read
    @GetMapping
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<BookingDTO>> getBookingsByRestaurantID(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(bookingService.getBookingsByRestaurantId(restaurantId));
    }

    @GetMapping("/guest/{guestId}")
    public ResponseEntity<List<BookingDTO>> getBookingsByGuestId(@PathVariable Long guestId) {
        return ResponseEntity.ok(bookingService.getBookingsByGuestId(guestId));
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<BookingDTO> updateBookingById(@PathVariable Long id, @RequestBody BookingCreationDTO booking) {
        return ResponseEntity.ok(bookingService.updateBookingByID(booking, id));
    }

}
