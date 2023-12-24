package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import peaksoft.models.MenuItem;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem,Long> {

    MenuItem findMenuItemByName(String name);

    @Query("select m from MenuItem m where m.name like %:name% ")
    List<MenuItem> searchMenuItem(@Param("name") String name);

    @Query("select m from MenuItem m where m.name in :names and m.stopList is null ")
    List<MenuItem>searchMenu(@Param("names") List<String>names);

    @Query("SELECT m FROM MenuItem m ORDER BY m.price ASC")
    List<MenuItem> sortByPriceAsc();

    @Query("SELECT m FROM MenuItem m ORDER BY m.price DESC")
    List<MenuItem> sortByPriceDesc();

    @Query("SELECT m FROM MenuItem m WHERE m.isVegetarian = :vegan")
    List<MenuItem> findVeganItems(@Param("vegan") boolean vegan);

}
