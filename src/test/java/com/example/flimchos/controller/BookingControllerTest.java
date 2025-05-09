package com.example.flimchos.controller;

import com.example.flimchos.model.Booking;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookingControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testPostRequestShouldReturnResponseEntityAndBooking() {
        //Arrange
        LocalDate date = LocalDate.of(2025,11,01);
        LocalTime time = LocalTime.of(15,00);
        Booking booking = new Booking(date, time, 5, 1, 1);

        //Act
        ResponseEntity<Booking> response = restTemplate.postForEntity("http://localhost:"+port+"/booking", booking, Booking.class);

        //Assert
        assertEquals(200, response.getStatusCode().value());
        assertEquals(date, response.getBody().getDate());
        assertEquals(time, response.getBody().getTime());
        assertEquals(5, response.getBody().getGuests());
        assertEquals(1, response.getBody().getGuestId());
        assertEquals(1, response.getBody().getRestaurantId());


    }


}