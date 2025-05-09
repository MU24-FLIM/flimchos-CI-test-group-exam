package com.example.flimchos.service;

import com.example.flimchos.model.Booking;
import com.example.flimchos.repository.BookingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingService bookingService;

    @Test
    public void testCreateBooking() {
        //Arrange
        LocalDate date = LocalDate.of(2025,11,01);
        LocalTime time = LocalTime.of(15,00);
        Booking booking = new Booking(date, time, 5, 1, 1);
        when(bookingRepository.save(any())).thenReturn(booking);

        //Act
        Booking response = bookingService.createBooking(booking);

        //Assert
        assertEquals(date, response.getDate());
        assertEquals(time, response.getTime());
        assertEquals(5, response.getGuests());
        assertEquals(1, response.getGuestId());
        assertEquals(1, response.getRestaurantId());

    }

}