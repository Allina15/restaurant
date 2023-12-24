package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.SubCategoryRequest;
import peaksoft.dto.SubCategoryResponse;

public interface SubCategoryService {
    SimpleResponse save(SubCategoryRequest request);
    SimpleResponse delete(SubCategoryRequest request);
    SimpleResponse update(SubCategoryRequest request);
    SubCategoryResponse getByCategory(String category);
}
