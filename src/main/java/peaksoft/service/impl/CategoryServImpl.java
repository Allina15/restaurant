package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.CategoryRequest;
import peaksoft.dto.SimpleResponse;
import peaksoft.exceptions.NotFoundException;
import peaksoft.models.Category;
import peaksoft.models.MenuItem;
import peaksoft.repositories.CategoryRepository;
import peaksoft.repositories.MenuItemRepository;
import peaksoft.service.CategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final MenuItemRepository menuItemRepository;

    @Override
    public SimpleResponse save(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        categoryRepository.save(category);
        return new SimpleResponse(HttpStatus.OK, "successfully saved");
    }

    @Override
    public SimpleResponse delete(CategoryRequest categoryRequest) {
        Category category = categoryRepository.findByName(categoryRequest.getName());
        List<MenuItem> menuItems = menuItemRepository.findMenuItemByCategoryName(categoryRequest.getName());
        for (MenuItem m: menuItems) {
            m.setCategory(null);
        }
        categoryRepository.delete(category);
        return new SimpleResponse(HttpStatus.OK, "successfully deleted");
    }

    @Override
    public SimpleResponse update(CategoryRequest categoryRequest) {
        Category category = categoryRepository.findByName(categoryRequest.getName());
        if (category==null){
            throw new NotFoundException("Category not found");
        }else {
            category.setName(categoryRequest.getNewName());
            categoryRepository.save(category);
        }
        return new SimpleResponse(HttpStatus.OK, "category updated");
    }
}
