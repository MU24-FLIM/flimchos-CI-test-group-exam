package com.example.flimchos.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class BookingDTO {

    private Long id;
    private LocalDate date;
    private LocalTime time;
    private int guests;
    private String guestName;
    private String restaurantCity;

    public BookingDTO(LocalDate date, LocalTime time, int guests, String guestName, String restaurantCity) {
        this.date = date;
        this.time = time;
        this.guests = guests;
        this.guestName = guestName;
        this.restaurantCity = restaurantCity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getRestaurantCity() {
        return restaurantCity;
    }

    public void setRestaurantCity(String restaurantCity) {
        this.restaurantCity = restaurantCity;
    }
}
