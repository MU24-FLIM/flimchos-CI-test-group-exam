package com.example.flimchos.dto;

import com.example.flimchos.model.Guest;
import com.example.flimchos.model.Restaurant;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingCreationDTO {

    private LocalDate date;
    private LocalTime time;
    private int guests;
    private Long guestId;
    private Long restaurantId;

    public BookingCreationDTO(LocalDate date, LocalTime time, int guests, Long guestId, Long restaurantId) {
        this.date = date;
        this.time = time;
        this.guests = guests;
        this.guestId = guestId;
        this.restaurantId = restaurantId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
