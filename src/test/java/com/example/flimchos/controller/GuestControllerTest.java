package com.example.flimchos.controller;

import com.example.flimchos.model.Guest;
import com.example.flimchos.repository.GuestRepository;
import com.example.flimchos.service.GuestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


//COMPONENT-TEST

@SpringBootTest
@AutoConfigureMockMvc
class GuestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GuestService guestService;

    @MockitoBean
    private GuestRepository guestRepo;


    @Test
    public void testToGetGuestById() throws Exception{
        Guest guest = new Guest(1L, "Madde", "madde@email.com");
        when(guestRepo.findById(1L)).thenReturn(Optional.of(guest));

        mockMvc.perform(get("/guests/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("madde@email.com"));
    }
}