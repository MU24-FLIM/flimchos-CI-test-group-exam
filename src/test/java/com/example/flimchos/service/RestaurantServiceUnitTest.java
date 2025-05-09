package com.example.flimchos.service;

import com.example.flimchos.model.Booking;
import com.example.flimchos.model.Restaurant;
import com.example.flimchos.repository.RestaurantRepository;
import org.mockito.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RestaurantServiceUnitTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RestaurantService restaurantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGetRestaurantByIdReturnsRestaurant() {

        //arrange
        List<Booking> bookings = new ArrayList<>();
        Restaurant restaurant = new Restaurant(1L, "flimchos_teststan@example.com", "Teststan", bookings);

        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));

        //act
        Restaurant result = restaurantService.getRestaurantById(1L);

        //assert
        assertEquals("flimchos_teststan@example.com", result.getEmail());
        assertEquals("Teststan", result.getCity());
        verify(restaurantRepository).findById(1L);

    }

}