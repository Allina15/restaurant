package peaksoft.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.RestaurantRequest;
import peaksoft.dto.RestaurantResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.service.RestaurantService;

@RestController
@RequiredArgsConstructor
@EnableMethodSecurity
@RequestMapping("/api/restaurant")
public class RestaurantApi {

    private final RestaurantService restaurantService;

    @PostMapping
    public SimpleResponse save(@RequestBody RestaurantRequest restaurantRequest){
        return restaurantService.save(restaurantRequest);
    }

    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable long id){
        return restaurantService.delete(id);
    }

    @GetMapping
    public RestaurantResponse getProfile(@RequestParam String name){
        return restaurantService.getProfile(name);
    }


}
