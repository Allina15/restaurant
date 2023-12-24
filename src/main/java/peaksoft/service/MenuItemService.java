package peaksoft.service;

import peaksoft.dto.MenuRequest;
import peaksoft.dto.SimpleResponse;
import peaksoft.models.MenuItem;

import java.util.List;

public interface MenuItemService {
    SimpleResponse save(MenuRequest menuRequest);
    SimpleResponse delete(String name);
    List<MenuItem> search(String name);
    List<MenuItem> sortByPrice(String ascOrDesc);
    List<MenuItem> filterByIsVegetarian(boolean vegetarian);
}
