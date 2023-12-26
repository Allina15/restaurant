package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.models.Restaurant;
import peaksoft.models.User;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {

    Optional<Restaurant>findByName(String name);
    Restaurant findRestaurantByUsers(User user);
}
