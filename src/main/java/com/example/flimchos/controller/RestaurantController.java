package com.example.flimchos.controller;

import com.example.flimchos.model.Restaurant;
import com.example.flimchos.repository.RestaurantRepository;
import com.example.flimchos.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Restaurant createNewRestaurant (@RequestBody Restaurant restaurant) {
        return restaurantService.createRestaurant(restaurant);
    }
   @GetMapping
   public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
   }
    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id, @RequestBody Restaurant updatedRestaurant) {
        Restaurant restaurant = restaurantService.updateRestaurant(id, updatedRestaurant);
        if (restaurant != null) {
            return ResponseEntity.ok(restaurant);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
    }
   }

