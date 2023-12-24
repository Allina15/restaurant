package peaksoft.service;

import peaksoft.dto.CategoryRequest;
import peaksoft.dto.SimpleResponse;

public interface CategoryService {

    SimpleResponse save(CategoryRequest categoryRequest);
    SimpleResponse delete(CategoryRequest categoryRequest);
    SimpleResponse update(CategoryRequest categoryRequest);
}
