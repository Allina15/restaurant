package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.RestaurantRequest;
import peaksoft.dto.RestaurantResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.exceptions.NotFoundException;
import peaksoft.models.Restaurant;
import peaksoft.repositories.RestaurantRepository;
import peaksoft.service.RestaurantService;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Override
    public SimpleResponse save(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantRequest.getName());
        restaurant.setLocation(restaurantRequest.getLocation());
        restaurant.setRestType(restaurantRequest.getRestType());
        restaurant.setService(restaurantRequest.getService());
        restaurantRepository.save(restaurant);
        return new SimpleResponse(HttpStatus.OK, "Restaurant successfully saved!");
    }

    @Override
    public SimpleResponse delete(long id) {
        restaurantRepository.deleteById(id);
        return new SimpleResponse(HttpStatus.OK, "Restaurant successfully deleted!");
    }

    @Override
    public RestaurantResponse getProfile(String name) {
        Restaurant restaurant = restaurantRepository.findByName(name).orElseThrow(()->new NotFoundException("Restaurant not found"));
            return new RestaurantResponse(restaurant.getName(), restaurant.getLocation(), restaurant.getRestType(), restaurant.getNumberOfEmployees(), restaurant.getService());
    }

    @Override
    public RestaurantResponse update(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = restaurantRepository.findByName(restaurantRequest.getName()).orElseThrow(()->new NotFoundException("Restaurant not found"));
        restaurant.setName(restaurantRequest.getName());
        restaurant.setLocation(restaurantRequest.getLocation());
        restaurant.setRestType(restaurantRequest.getRestType());
        restaurant.setService(restaurantRequest.getService());
        restaurantRepository.save(restaurant);
        return new RestaurantResponse(restaurant.getName(), restaurant.getLocation(), restaurant.getRestType(), restaurant.getNumberOfEmployees(), restaurant.getService());
    }
}
