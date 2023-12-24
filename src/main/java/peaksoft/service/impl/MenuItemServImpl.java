package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.MenuRequest;
import peaksoft.dto.SimpleResponse;
import peaksoft.exceptions.NotFoundException;
import peaksoft.models.Category;
import peaksoft.models.MenuItem;
import peaksoft.models.Restaurant;
import peaksoft.repositories.CategoryRepository;
import peaksoft.repositories.MenuItemRepository;
import peaksoft.repositories.RestaurantRepository;
import peaksoft.service.MenuItemService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MenuItemServImpl implements MenuItemService {
    private final CategoryRepository categoryRepository;
    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public SimpleResponse save(MenuRequest menuRequest) {
        Category category = categoryRepository.findByName(menuRequest.getCategory());
        MenuItem menuItem = new MenuItem();
        menuItem.setName(menuRequest.getName());
        menuItem.setImage(menuRequest.getImage());
        menuItem.setImage(menuRequest.getImage());
        try {
            double price = menuRequest.getPrice();
            if (price < 0) {
                throw new IllegalArgumentException("Price must be non-negative");
            }
            menuItem.setPrice(price);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        menuItem.setDescription(menuRequest.getDescription());
        menuItem.setVegetarian(menuRequest.isVegetarian());
        menuItem.setCategory(category);
        Restaurant restaurant = restaurantRepository.findById(menuRequest.getRestId()).orElseThrow(()->new NotFoundException("restaurant not found"));
        menuItem.setRestaurant(restaurant);
        menuItemRepository.save(menuItem);
        return new SimpleResponse(HttpStatus.OK, "Menu item saved");
    }

    @Override
    public SimpleResponse delete(String name) {
    MenuItem menuItem = menuItemRepository.findMenuItemByName(name);

        return null;
    }

    @Override
    public List<MenuItem> search(String name) {
        return menuItemRepository.searchMenuItem(name);
    }

    @Override
    public List<MenuItem> sortByPrice(String ascOrDesc) {
        if (ascOrDesc.equalsIgnoreCase("asc")){
            return menuItemRepository.sortByPriceAsc();
        }
        return menuItemRepository.sortByPriceDesc();
    }

    @Override
    public List<MenuItem> filterByIsVegetarian(boolean vegetarian) {
        return menuItemRepository.findVeganItems(vegetarian);
    }
}
