package com.example.flimchos.controller;

import com.example.flimchos.model.Booking;
import com.example.flimchos.repository.BookingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
class BookingControllerComponentTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateBookingShouldReturnStatusOkAndBooking() throws Exception {
        //Arrange
        LocalDate date = LocalDate.of(2025,11,01);
        LocalTime time = LocalTime.of(15, 00);
        Booking booking = new Booking(date, time, 5, 1, 1);

        //Act & Assert
        mockMvc.perform(post("http://localhost:8080/booking")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(booking)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date", is("2025-11-01")))
                .andExpect(jsonPath("$.guestId", is(1)))
                .andDo(print());
    }

}