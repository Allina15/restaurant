package peaksoft.service;

import peaksoft.dto.RestaurantRequest;
import peaksoft.dto.RestaurantResponse;
import peaksoft.dto.SimpleResponse;

public interface RestaurantService {

    SimpleResponse save(RestaurantRequest restaurantRequest);
    SimpleResponse delete(long id);
    RestaurantResponse getProfile(String name);
    RestaurantResponse update(RestaurantRequest restaurantRequest);
}
