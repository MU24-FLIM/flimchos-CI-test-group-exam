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
        Guest guest = new Guest("Madde", "<EMAIL>");
        Restaurant restaurant = new Restaurant("<EMAIL>", "Linda", new ArrayList<>());
        LocalDate date = LocalDate.of(2025,11,01);
        LocalTime time = LocalTime.of(15,00);
        BookingCreationDTO bookingDTO = new BookingCreationDTO(date, time, 5, 1L, 1L);
        Booking booking = BookingMapper.INSTANCE.bookingCreationDTOToBooking(bookingDTO);
        when(bookingRepository.save(any())).thenReturn(booking);
        when(restaurantRepository.findById(any())).thenReturn(Optional.of(restaurant));
        when(guestRepository.findById(any())).thenReturn(Optional.of(guest));

        //Act
        BookingDTO response = bookingService.createBooking(bookingDTO);

        //Assert
        assertEquals(date, response.getDate());
        assertEquals(time, response.getTime());
        assertEquals(5, response.getGuests());
        assertEquals("Madde", response.getGuestName());
        assertEquals("Linda", response.getRestaurantCity());

    }

}