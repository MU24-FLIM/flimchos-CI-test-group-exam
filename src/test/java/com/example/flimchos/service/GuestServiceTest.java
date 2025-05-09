package com.example.flimchos.service;

import com.example.flimchos.model.Guest;
import com.example.flimchos.repository.GuestRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class GuestServiceTest {

    @Mock
    private GuestRepository guestRepo;

    @InjectMocks
    private GuestService guestService;

    @Test //test för att det går och hämta rätt id
    public void testToGetGuestById(){
        Guest guest = new Guest(1L, "Madde", "madde@email.com");
        when(guestRepo.findById(1L)).thenReturn(Optional.of(guest));

        Guest result = guestService.getGuest(1L).orElse(null);

        assertEquals(1L, result.getId());
        verify(guestRepo).findById(1L);
    }

    @Test //test för att kolla att det går att radera utifrån id
    public void testToDeleteGuestById(){
        doNothing().when(guestRepo).deleteById(1L);

        guestService.deleteGuestById(1L);

        verify(guestRepo).deleteById(1L);

    }
}