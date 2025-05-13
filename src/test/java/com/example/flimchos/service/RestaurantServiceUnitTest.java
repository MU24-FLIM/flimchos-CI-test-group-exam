package com.example.flimchos.service;

import com.example.flimchos.model.Booking;
import com.example.flimchos.model.Restaurant;
import com.example.flimchos.repository.RestaurantRepository;
import org.junit.jupiter.api.Disabled;
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

        // Arrange
        // Skapar en tom lista av bokningar för att initialisera restaurangen.
        List<Booking> bookings = new ArrayList<>();
        // Skapar ett nytt Restaurant-objekt med ett specifikt ID, e-post och stad.
        Restaurant restaurant = new Restaurant(1L, "flimchos_teststan@example.com", "Teststan", bookings);

        // Mockar restaurantRepository så att findById returnerar vår skapade restaurang när den anropas med ID 1L.
        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));

        // Act
        // Anropar metoden getRestaurantById med ID 1L och sparar resultatet.
        Restaurant result = restaurantService.getRestaurantById(1L);

        // Assert
        // Verifierar att e-postadressen i det returnerade Restaurant-objektet är korrekt.
        assertEquals("flimchos_teststan@example.com", result.getEmail());
        assertEquals("Teststan", result.getCity());
        // Kontrollerar att findById-metoden i restaurantRepository blev anropad med ID 1L.
        verify(restaurantRepository).findById(1L);

    }
}