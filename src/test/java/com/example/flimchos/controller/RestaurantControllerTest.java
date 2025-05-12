package com.example.flimchos.controller;

import com.example.flimchos.model.Booking;
import com.example.flimchos.model.Restaurant;
import com.example.flimchos.repository.RestaurantRepository;
import com.example.flimchos.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = RestaurantController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RestaurantService restaurantService;

    @MockitoBean
    private RestaurantRepository restaurantRepository;

    @Test
    public void getRestaurantById() throws Exception {
        //arrange
        List<Booking> bookings = new ArrayList<>();
        Restaurant restaurant = new Restaurant(1L,"flimchos_teststan@example.com", "Teststan", bookings);
        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));

        // Act & Assert
        mockMvc.perform(get("/restaurants/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("flimchos_teststan@example.com"))
                .andExpect(jsonPath("$.city").value("Teststan"));
    }
    @Test
    public void getRestaurantByIdNotFound() throws Exception {
        when(restaurantRepository.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/restaurants/999"))
                .andExpect(status().isNotFound());
    }

}