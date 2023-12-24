package peaksoft.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.CategoryRequest;
import peaksoft.dto.SimpleResponse;
import peaksoft.service.CategoryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryApi {

    private final CategoryService categoryService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    public SimpleResponse save(@RequestBody CategoryRequest categoryRequest){
        return categoryService.save(categoryRequest);
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    public SimpleResponse update(@RequestBody CategoryRequest categoryRequest){
        return categoryService.update(categoryRequest);
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    public SimpleResponse delete(@RequestBody CategoryRequest categoryRequest){
        return categoryService.delete(categoryRequest);
    }
}
