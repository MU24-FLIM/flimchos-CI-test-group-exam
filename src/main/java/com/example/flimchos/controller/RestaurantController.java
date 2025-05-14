package com.example.flimchos.controller;

import com.example.flimchos.model.Restaurant;
import com.example.flimchos.repository.RestaurantRepository;
import com.example.flimchos.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, RestaurantRepository restaurantRepository) {
        this.restaurantService = restaurantService;
        this.restaurantRepository = restaurantRepository;

    }

    @PostMapping
    public ResponseEntity<Restaurant> createNewRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant result = restaurantService.createRestaurant(restaurant);
        return ResponseEntity.ok(result);
    }


    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.showAllRestaurants());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRestaurantById(@PathVariable Long id) {
        Optional<Restaurant> restaurant = restaurantService.getRestaurantById(id);
        if (restaurant.isPresent()) {
            return ResponseEntity.ok(restaurant.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurant with id " + id + " not found");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateRestaurant(@PathVariable Long id, @RequestBody Restaurant updatedRestaurant) {
        Optional<Restaurant> restaurant = restaurantService.updateRestaurant(id, updatedRestaurant);
        if (restaurant.isPresent()) {
            return ResponseEntity.ok("Restaurant with id " + id + " updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurant with id " + id + " not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable Long id) {
        Optional<Restaurant> restaurant = restaurantService.getRestaurantById(id);
        if (restaurant.isPresent()) {
            restaurantService.deleteRestaurant(id);
            return ResponseEntity.ok("Restaurant with id " + id + " deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurant with id " + id + " not found");
        }
    }
}

