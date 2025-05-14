package com.example.flimchos.service;

import com.example.flimchos.dto.BookingCreationDTO;
import com.example.flimchos.dto.BookingDTO;
import com.example.flimchos.model.Booking;
import com.example.flimchos.model.Guest;
import com.example.flimchos.model.Restaurant;
import com.example.flimchos.repository.BookingRepository;
import com.example.flimchos.repository.GuestRepository;
import com.example.flimchos.repository.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class BookingUnitTest {

    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private RestaurantRepository restaurantRepository;
    @Mock
    private GuestRepository guestRepository;

    @InjectMocks
    private BookingService bookingService;

    @Test
    public void testCreateBooking() {
        //Arrange
        LocalDate date = LocalDate.of(2025,11,1);
        LocalTime time = LocalTime.of(15, 0);
        Guest guest = new Guest(1L, "Madde", "<EMAIL>");
        Restaurant restaurant = new Restaurant(1L, "<EMAIL>", "Linda", new ArrayList<>());
        BookingCreationDTO bookingCreationDTO = new BookingCreationDTO(date, time, 5, 1L, 1L);
        Booking booking = new Booking(date, time, 5, guest, restaurant);
        when(bookingRepository.save(any())).thenReturn(booking);
        when(restaurantRepository.findById(any())).thenReturn(Optional.of(restaurant));
        when(guestRepository.findById(any())).thenReturn(Optional.of(guest));

        //Act
        BookingDTO response = bookingService.createBooking(bookingCreationDTO);

        //Assert
        assertEquals(LocalDate.of(2025,11,1), response.getDate());
        assertEquals(LocalTime.of(15,0), response.getTime());
        assertEquals(5, response.getGuests());
        assertEquals("Madde", response.getGuestName());
        assertEquals("Linda", response.getRestaurantCity());
        verify(bookingRepository).save(any());

    }

    @Test
    public void testCreateBookingWithInvalidRestaurantIdThrowsEntityNotFoundException() {
        //Arrange
        LocalDate date = LocalDate.of(2025,11,1);
        LocalTime time = LocalTime.of(15, 0);
        BookingCreationDTO bookingCreationDTO = new BookingCreationDTO(date, time, 5, 1L, 1L);
        when(restaurantRepository.findById(any())).thenReturn(Optional.empty()); // First operation  in service class

        //Act

        //Assert
        assertThrows(EntityNotFoundException.class, () -> bookingService.createBooking(bookingCreationDTO));

    }

    @Test
    public void testCreateBookingWithInvalidGuestIdThrowsEntityNotFoundException() {
        //Arrange
        LocalDate date = LocalDate.of(2025,11,1);
        LocalTime time = LocalTime.of(15, 0);
        Restaurant restaurant = new Restaurant(1L, "<EMAIL>", "Linda", new ArrayList<>());
        BookingCreationDTO bookingCreationDTO = new BookingCreationDTO(date, time, 5, 1L, 1L);
        when(restaurantRepository.findById(any())).thenReturn(Optional.of(restaurant)); // First operation  in service class
        when(guestRepository.findById(any())).thenReturn(Optional.empty()); // Second operation in service class

        //Act

        //Assert
        assertThrows(EntityNotFoundException.class, () -> bookingService.createBooking(bookingCreationDTO));

    }

}