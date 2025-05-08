package com.example.flimchos.controller;

import com.example.flimchos.repository.GuestRepository;
import com.example.flimchos.service.GuestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class GuestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }
}
