package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.CategoryRequest;
import peaksoft.dto.SimpleResponse;
import peaksoft.models.Category;
import peaksoft.repositories.CategoryRepository;
import peaksoft.service.CategoryService;
@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

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
        categoryRepository.delete(category);
        return new SimpleResponse(HttpStatus.OK, "successfully deleted");
    }

    @Override
    public SimpleResponse update(CategoryRequest categoryRequest) {
        Category category = categoryRepository.findByName(categoryRequest.getName());
        category.setName(categoryRequest.getNewName());
        categoryRepository.save(category);
        return new SimpleResponse(HttpStatus.OK, "category updated");
    }
}
