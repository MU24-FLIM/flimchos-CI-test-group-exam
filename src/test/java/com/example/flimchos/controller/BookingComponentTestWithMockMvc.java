package com.example.flimchos.controller;

import com.example.flimchos.dto.BookingCreationDTO;
import com.example.flimchos.model.Booking;
import com.example.flimchos.model.Guest;
import com.example.flimchos.model.Restaurant;
import com.example.flimchos.repository.BookingRepository;
import com.example.flimchos.repository.GuestRepository;
import com.example.flimchos.repository.RestaurantRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
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

    @MockitoBean
    private BookingRepository bookingRepository;

    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private GuestRepository guestRepository;
    @MockitoBean
    private RestaurantRepository restaurantRepository;

    @Test
    public void testCreateBookingShouldReturnStatusOkAndBookingDTO() throws Exception {
        //Arrange
        Guest guest = new Guest(1L, "Madde", "madde@guest.flim");
        Restaurant restaurant = new Restaurant(1L, "info@flimchos.flim", "Storstan", new ArrayList<>());
        LocalDate date = LocalDate.of(2025, 11, 1);
        LocalTime time = LocalTime.of(15, 0);
        BookingCreationDTO bookingCreationDTO = new BookingCreationDTO(date, time, 5, 1L, 1L);
        Booking booking = new Booking(date, time, 5, guest, restaurant);
        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));
        when(guestRepository.findById(1L)).thenReturn(Optional.of(guest));
        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);

        //Act & Assert
        mockMvc.perform(post("http://localhost:8080/bookings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookingCreationDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date", is("2025-11-01")))
                .andExpect(jsonPath("$.time", is("15:00:00")))
                .andExpect(jsonPath("$.guests", is(5)))
                .andExpect(jsonPath("$.guestName", is("Madde")))
                .andExpect(jsonPath("$.restaurantCity", is("Storstan")))
                .andDo(print());
    }

}