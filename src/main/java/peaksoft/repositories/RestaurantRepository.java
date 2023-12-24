package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.models.Restaurant;
import peaksoft.models.User;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {

    Restaurant findByName(String name);
    Restaurant findRestaurantByUsers(User user);
}
