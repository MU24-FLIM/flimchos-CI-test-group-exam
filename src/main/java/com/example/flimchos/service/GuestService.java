package com.example.flimchos.service;

import com.example.flimchos.model.Guest;
import com.example.flimchos.repository.GuestRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GuestService {

    private final GuestRepository guestRepo;

    public GuestService(GuestRepository guestRepo) {
        this.guestRepo = guestRepo;
    }

    public Guest createGuest(Guest guest){
        return guestRepo.save(guest);
    }

    public Optional<Guest> getGuest(Long id){
        return guestRepo.findById(id);
    }

    public void deleteGuestById(Long id){
        guestRepo.deleteById(id);
    }

    public Guest updateGuestEmail(Guest newGuestEmail){
        return guestRepo.findById(newGuestEmail.getId()).map(guest -> {
            guest.setEmail(newGuestEmail.getEmail());
            return guestRepo.save(guest);
        }).orElse(null);
    }

}
