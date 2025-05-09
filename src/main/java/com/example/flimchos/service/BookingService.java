package com.example.flimchos.service;

import com.example.flimchos.model.Booking;
import com.example.flimchos.model.Guest;
import com.example.flimchos.model.Restaurant;
import com.example.flimchos.repository.BookingRepository;
import com.example.flimchos.repository.GuestRepository;
import com.example.flimchos.repository.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final RestaurantRepository restaurantRepository;
    private final GuestRepository guestRepository;

    public BookingService(BookingRepository bookingRepository, RestaurantRepository restaurantRepository, GuestRepository guestRepository) {
        this.bookingRepository = bookingRepository;
        this.restaurantRepository = restaurantRepository;
        this.guestRepository = guestRepository;
    }

    public Booking createBooking(Booking booking, Long restaurantId, Long guestId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(EntityNotFoundException::new);
        Guest guest = guestRepository.findById(guestId).orElseThrow(EntityNotFoundException::new);
        booking.setGuest(guest);
        booking.setRestaurant(restaurant);
        return bookingRepository.save(booking);
    }

}
