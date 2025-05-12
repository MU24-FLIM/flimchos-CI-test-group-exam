package com.example.flimchos.controller;

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
    @PostMapping("/{guestId}/{restaurantId}")
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking, @PathVariable Long guestId, @PathVariable Long restaurantId) {
        return ResponseEntity.ok(bookingService.createBooking(booking, restaurantId, guestId));
    }

    //Read
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Booking>> getBookingsByRestaurantID(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(bookingService.getBookingsByRestaurantId(restaurantId));
    }

    @GetMapping("/guest/{guestId}")
    public ResponseEntity<List<Booking>> getBookingsByGuestId(@PathVariable Long guestId) {
        return ResponseEntity.ok(bookingService.getBookingsByGuestId(guestId));
    }

}
