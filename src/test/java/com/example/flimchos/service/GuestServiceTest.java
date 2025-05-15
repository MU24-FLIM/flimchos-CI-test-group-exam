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

//UNIT-TEST


@ExtendWith(MockitoExtension.class)
class GuestServiceTest {

    @Mock
    private GuestRepository guestRepo;

    @InjectMocks
    private GuestService guestService;

    @Test //test för att det går och hämta rätt id
    public void testToGetGuestById(){
        //Arrange
        Guest guest = new Guest(1L, "Madde", "madde@email.com");
        when(guestRepo.findById(1L)).thenReturn(Optional.of(guest));

        //Act
        Guest result = guestService.getGuest(1L).orElse(null);

        //Assert
        assertEquals(1L, result.getId());
        verify(guestRepo).findById(1L);
    }

    @Test //test för att kolla att det går att radera utifrån id
    public void testToDeleteGuestById(){
        //Arrange (doNothing, för att den inte ska göra något när den kallar på deleteById,
        // eftersom den returnerar void kan man inte ha when direkt)
        doNothing().when(guestRepo).deleteById(1L);

        //Act
        guestService.deleteGuestById(1L);

        //Assert
        verify(guestRepo).deleteById(1L);

    }
}