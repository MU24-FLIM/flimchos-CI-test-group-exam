package com.example.flimchos.integration;

import com.example.flimchos.FlimchosApplication;
import com.example.flimchos.model.Restaurant;
import com.example.flimchos.repository.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;


import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = FlimchosApplication.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
class RestaurantIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @BeforeEach
    public void setUp() {
        //Rensa databasen innan varje test för att säkerställa att testerna är isolerade
        restaurantRepository.deleteAll();
    }

    @Test
    public void testCreateAndGetRestaurant() throws Exception {

        //Arrange
        // Skapa en ny restaurang
        Restaurant restaurant = new Restaurant();
        restaurant.setEmail("flimchos_teststan@example.com");
        restaurant.setCity("Teststan");

        //Act & Assert
        //Skicka en POST-förfrågan för att skapa en restaurang och få svar
        ResponseEntity<Restaurant> postResponse = restTemplate.postForEntity("http://localhost:" + port + "/restaurants", restaurant, Restaurant.class);

        //Kontrollera att svaret har status OK
        assertEquals(HttpStatus.OK, postResponse.getStatusCode());

        //Act & Assert
        // Hämta resturangen som just skapades med hjälp av dess id
        Long restaurantId = postResponse.getBody().getId();
        ResponseEntity<Restaurant> getResponse = restTemplate.getForEntity("http://localhost:" + port + "/restaurants/" + restaurantId, Restaurant.class);

        //Kontrollera att Get- förfrågan också har status OK
        assertEquals(HttpStatus.OK, getResponse.getStatusCode());

        //Assert
        //Verifiera att den hämtade restaurangens information är korrekt
        assertEquals("flimchos_teststan@example.com", getResponse.getBody().getEmail());
        assertEquals("Teststan", getResponse.getBody().getCity());
    }

    }
