package com.example.flimchos.service;

import com.example.flimchos.model.Restaurant;
import com.example.flimchos.repository.BookingRepository;
import com.example.flimchos.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    //private final BookingService bookingService;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;

}
public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
}
public List<Restaurant> showAllRestaurants() {
        return restaurantRepository.findAll();
}
    public Optional<Restaurant> getRestaurantById(Long id) {
        return restaurantRepository.findById(id);
    }
    public Optional<Restaurant> updateRestaurant(Long id, Restaurant updatedRestaurant) {
        return restaurantRepository.findById(id).map(restaurant -> {
            restaurant.setEmail(updatedRestaurant.getEmail());
            restaurant.setCity(updatedRestaurant.getCity());
            return restaurantRepository.save(restaurant);
        });
    }
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }
}