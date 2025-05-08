package com.example.flimchos.service;

import com.example.flimchos.repository.GuestRepository;
import org.springframework.stereotype.Service;

@Service
public class GuestService {

    private final GuestRepository guestRepo;

    public GuestService(GuestRepository guestRepo) {
        this.guestRepo = guestRepo;
    }
}
