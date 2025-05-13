package com.example.flimchos.controller;

import com.example.flimchos.dto.BookingCreationDTO;
import com.example.flimchos.model.Guest;
import com.example.flimchos.model.Restaurant;
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
import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
class BookingComponentTestWithMockMvc {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateBookingShouldReturnStatusOkAndBookingDTO() throws Exception {
        //Arrange
        Guest guest = new Guest(1L, "Madde", "madde@guest.flim");
        Restaurant restaurant = new Restaurant(1L, "info@flimchos.flim", "Storstan", new ArrayList<>());
        LocalDate date = LocalDate.of(2025,11,01);
        LocalTime time = LocalTime.of(15,00);
        BookingCreationDTO bookingDTO = new BookingCreationDTO(date, time, 5, 1L, 1L);

        //Act & Assert
        mockMvc.perform(post("http://localhost:8080/restaurants")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(restaurant)))
                .andExpect(status().isOk());

        mockMvc.perform(post("http://localhost:8080/guests")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(guest)))
                .andExpect(status().isOk());

        mockMvc.perform(post("http://localhost:8080/bookings")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(bookingDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date", is("2025-11-01")))
                .andExpect(jsonPath("$.guests", is(5)))
                .andExpect(jsonPath("$.guestName", is("Madde")))
                .andExpect(jsonPath("$.restaurantCity", is("Storstan")))
                .andDo(print());
    }

}