package com.example.flimchos.repository;

import com.example.flimchos.model.Booking;
import com.example.flimchos.model.Guest;
import com.example.flimchos.model.Restaurant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookingJPATest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private BookingRepository bookingRepository;

    //Two JpaTests to test the connection between repository and database

    //A test validating the save method, verifying that it returns what is expected.
    @Test
    public void testSaveBooking(){
        //Arrange
        Restaurant restaurant = new Restaurant(1L, "lillstan@flimchos.com", "Lillstan", new ArrayList<>());
        Guest guest = new Guest(1L, "Madde", "madde@flimchos.com");
        LocalDate date = LocalDate.of(2025,11,01);
        LocalTime time = LocalTime.of(15,00);
        Booking booking  = new Booking(date, time, 5, guest, restaurant);

        //Act
        Booking savedBooking = bookingRepository.save(booking);

        //Assert
        assertEquals("Lillstan", savedBooking.getRestaurant().getCity());
        assertEquals("Madde", savedBooking.getGuest().getName());
    }

    //Testing that a Booking can be found by Restaurant ID. Using TestRestTemplate to save the Booking to the database,
    //I make sure that only the fyndByRestaurantId method and not the save method is tested.
    @Test
    public void testFindBookingByRestaurantId(){
        //Arrange
        Restaurant restaurant = new Restaurant(1L, "Lillstan", "lillstan@flimchos.com", new ArrayList<>());
        Guest guest = new Guest(1L, "Madde", "madde@flimchos.com");
        LocalDate date = LocalDate.of(2025,11,01);
        LocalTime time = LocalTime.of(15,00);
        Booking booking  = new Booking(date, time, 5, guest, restaurant);
        testEntityManager.persistAndFlush(booking);

        //Act
        List<Booking> savedBooking = bookingRepository.findAllByRestaurantId(restaurant.getId());

        //Assert
        assertTrue(savedBooking.contains(booking));
    }

}