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
import java.util.stream.Collectors;

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

        return BookingMapper.INSTANCE.bookingToBookingDTO(bookingRepository.save(booking));
    }

    //Read
    public List<BookingDTO> getAllBookings(){
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream().map(BookingMapper.INSTANCE::bookingToBookingDTO).collect(Collectors.toList());
    }

    public BookingDTO getBookingById(Long id){
        Booking booking = bookingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return BookingMapper.INSTANCE.bookingToBookingDTO(booking);
    }

    public List<BookingDTO> getBookingsByGuestId(Long id){
        List<Booking> bookings = bookingRepository.findAllByGuestId(id);
        return bookings.stream().map(BookingMapper.INSTANCE::bookingToBookingDTO).collect(Collectors.toList());
    }

    public List<BookingDTO> getBookingsByRestaurantId(Long id){
        List<Booking> bookings = bookingRepository.findAllByRestaurantId(id);
        return bookings.stream().map(BookingMapper.INSTANCE::bookingToBookingDTO).collect(Collectors.toList());
    }

    //Update
    public BookingDTO updateBookingByID(BookingCreationDTO bookingCreationDTO, Long id){
        Restaurant restaurant = restaurantRepository.findById(bookingCreationDTO.getRestaurantId()).orElseThrow(EntityNotFoundException::new);
        Guest guest = guestRepository.findById(bookingCreationDTO.getGuestId()).orElseThrow(EntityNotFoundException::new);
        Booking bookingToUpdate = bookingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        bookingToUpdate.setRestaurant(restaurant);
        bookingToUpdate.setGuest(guest);
        bookingToUpdate.setTime(bookingCreationDTO.getTime());
        bookingToUpdate.setDate(bookingCreationDTO.getDate());

        return BookingMapper.INSTANCE.bookingToBookingDTO(bookingRepository.save(bookingToUpdate));
    }

}
