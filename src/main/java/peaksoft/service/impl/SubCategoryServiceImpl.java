package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.SubCategoryRequest;
import peaksoft.dto.SubCategoryResponse;
import peaksoft.models.Category;
import peaksoft.models.SubCategory;
import peaksoft.repositories.CategoryRepository;
import peaksoft.repositories.SubCategoryRepository;
import peaksoft.service.SubCategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public SimpleResponse save(SubCategoryRequest request) {
        SubCategory subCategory = new SubCategory();
        Category category = categoryRepository.findByName(request.getCategory());
        subCategory.setCategory(category);
        subCategory.setName(request.getName());
        subCategoryRepository.save(subCategory);
        return new SimpleResponse(HttpStatus.OK,"successfully saved");
    }

    @Override
    public SimpleResponse delete(SubCategoryRequest request) {
        SubCategory subCategory = subCategoryRepository.findByName(request.getName());
        Category category = categoryRepository.findCategoryBySubCategory(subCategory);
        category.getSubCategories().remove(subCategory);
        subCategoryRepository.delete(subCategory);
        return new SimpleResponse(HttpStatus.OK,"successfully deleted");
    }

    @Override
    public SimpleResponse update(SubCategoryRequest request) {
        SubCategory subCategory = subCategoryRepository.findByName(request.getName());
        subCategory.setName(request.getNewName());
        subCategoryRepository.save(subCategory);
        return new SimpleResponse(HttpStatus.OK,"successfully updated");
    }

    @Override
    public SubCategoryResponse getByCategory(String category) {
        List<SubCategory> subCategories = subCategoryRepository.getSubCategoriesByCategoryName(category);
        return new SubCategoryResponse(subCategories);
    }
}
