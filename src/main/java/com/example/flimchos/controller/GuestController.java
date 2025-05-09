package com.example.flimchos.controller;

import com.example.flimchos.model.Guest;
import com.example.flimchos.service.GuestService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/guest")
public class GuestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @PostMapping
    public ResponseEntity<Guest> createGuest(@RequestBody Guest guest){
        Guest result = guestService.createGuest(guest);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public Guest getGuestById(@PathVariable Long id){
        return guestService.getGuest(id).orElseThrow(() -> new EntityNotFoundException("No guest with that id"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuestById(@PathVariable Long id){
        guestService.getGuest(id).orElseThrow(() -> new EntityNotFoundException("No guest with that id"));
        guestService.deleteGuestById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Guest> updateGuestEmail(@PathVariable Long id, @RequestBody Guest newGuestEmail){
        Guest guest = guestService.getGuest(id).orElseThrow(()-> new EntityNotFoundException("No guest with that id"));
        guest.setEmail(newGuestEmail.getEmail());
        guestService.updateGuestEmail(guest);
        return ResponseEntity.ok(guest);
    }
}
