package com.example.flimchos.controller;

import com.example.flimchos.model.Guest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;


//INTEGRATION-TEST

@ActiveProfiles("test") //test databasen, som ligger i application-test.properties
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GuestControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test //ett test som kollar kontakten mellan databas och skapa en gäst
    public void testCreateAndGetGuestByHttp(){
        //Arrange
        Guest guest = new Guest(null, "Madde", "madde@email.com");

        //Act
        ResponseEntity<Guest> postResponse = restTemplate.postForEntity("http://localhost:" + port + "/guests", guest, Guest.class);

        Long guestId = postResponse.getBody().getId();

        ResponseEntity<Guest> getResponse = restTemplate.getForEntity("http://localhost:" + port + "/guests/" + guestId, Guest.class);

        //Assert
        assertEquals("Madde", getResponse.getBody().getName());
        assertEquals("madde@email.com", getResponse.getBody().getEmail());
    }

}