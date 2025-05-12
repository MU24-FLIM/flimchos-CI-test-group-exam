package com.example.flimchos.service;

import com.example.flimchos.dto.BookingCreationDTO;
import com.example.flimchos.dto.BookingDTO;
import com.example.flimchos.mapper.BookingMapper;
import com.example.flimchos.model.Booking;
import com.example.flimchos.model.Guest;
import com.example.flimchos.model.Restaurant;
import com.example.flimchos.repository.BookingRepository;
import com.example.flimchos.repository.GuestRepository;
import com.example.flimchos.repository.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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

    //Create
    public BookingDTO createBooking(BookingCreationDTO bookingCreationDTO){
        Restaurant restaurant = restaurantRepository.findById(bookingCreationDTO.getRestaurantId()).orElseThrow(EntityNotFoundException::new);
        Guest guest = guestRepository.findById(bookingCreationDTO.getGuestId()).orElseThrow(EntityNotFoundException::new);
        Booking booking = BookingMapper.INSTANCE.bookingCreationDTOToBooking(bookingCreationDTO);
        booking.setRestaurant(restaurant);
        booking.setGuest(guest);

        bookingRepository.save(booking);
        return BookingMapper.INSTANCE.bookingToBookingDTO(booking);
    }

    //Read
    public List<Booking> getAllBookings(){
        return bookingRepository.findAll();
    }

    public BookingDTO getBookingById(Long id){
        Booking booking = bookingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return BookingMapper.INSTANCE.bookingToBookingDTO(booking);
    }

    public List<Booking> getBookingsByGuestId(Long id){
        return bookingRepository.findAllByGuestId(id);
    }

    public List<Booking> getBookingsByRestaurantId(Long id){
        return bookingRepository.findAllByRestaurantId(id);
    }

}
