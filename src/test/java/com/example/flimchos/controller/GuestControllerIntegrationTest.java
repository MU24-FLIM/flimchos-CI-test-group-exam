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

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GuestControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateAndGetGuestByHttp(){
        Guest guest = new Guest(null, "Madde", "madde@email.com");

        ResponseEntity<Guest> postResponse = restTemplate.postForEntity("http://localhost:" + port + "/guests", guest, Guest.class);

        Long guestId = postResponse.getBody().getId();

        ResponseEntity<Guest> getResponse = restTemplate.getForEntity("http://localhost:" + port + "/guests/" + guestId, Guest.class);

        assertEquals("Madde", getResponse.getBody().getName());
        assertEquals("madde@email.com", getResponse.getBody().getEmail());
    }

}