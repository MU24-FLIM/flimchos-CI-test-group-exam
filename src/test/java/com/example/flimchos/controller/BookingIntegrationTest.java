package com.example.flimchos.controller;

import com.example.flimchos.dto.BookingCreationDTO;
import com.example.flimchos.dto.BookingDTO;
import com.example.flimchos.model.Guest;
import com.example.flimchos.model.Restaurant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookingIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void testCreateBookingShouldReturnStatusOkAndBookingDTO() {
        //Arrange
        Guest guest = new Guest("Madde", "madde@guest.flim");
        Restaurant restaurant = new Restaurant("info@flimchos.flim", "Storstan", new ArrayList<>());
        LocalDate date = LocalDate.of(2025,11,01);
        LocalTime time = LocalTime.of(15, 00);
        ResponseEntity<Guest> guestResponse = testRestTemplate.postForEntity("http://localhost:" + port + "/guests", guest, Guest.class);
        ResponseEntity<Restaurant> restaurantResponse = testRestTemplate.postForEntity("http://localhost:" + port + "/restaurants", restaurant, Restaurant.class);
        assertEquals(HttpStatusCode.valueOf(200), guestResponse.getStatusCode());
        assertEquals(HttpStatusCode.valueOf(200), restaurantResponse.getStatusCode());
        BookingCreationDTO booking = new BookingCreationDTO(date, time, 5, guestResponse.getBody().getId(), restaurantResponse.getBody().getId());

        //Act
        ResponseEntity<BookingDTO> response = testRestTemplate.postForEntity("http://localhost:" + port + "/bookings", booking, BookingDTO.class);

        //Assert
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals(LocalDate.of(2025,11,01), response.getBody().getDate());
        assertEquals(LocalTime.of(15, 00), response.getBody().getTime());
        assertEquals("Madde", response.getBody().getGuestName());
        assertEquals("Storstan", response.getBody().getRestaurantCity());


    }
}