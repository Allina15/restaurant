package peaksoft.controllers;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.Request;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.SubCategoryRequest;
import peaksoft.dto.SubCategoryResponse;
import peaksoft.service.SubCategoryService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subcategories")
@EnableMethodSecurity
public class SubCategoryApi {

    private final SubCategoryService service;

    @PermitAll
    @GetMapping
    public SubCategoryResponse getByCategory(@RequestParam String category){
        return service.getByCategory(category);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @PostMapping
    public SimpleResponse save(@RequestBody SubCategoryRequest subCategoryRequest){
        return service.save(subCategoryRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @PutMapping
    public SimpleResponse update(@RequestBody SubCategoryRequest subCategoryRequest){
        return service.update(subCategoryRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    @DeleteMapping
    public SimpleResponse delete(@RequestBody SubCategoryRequest subCategoryRequest){
        return service.delete(subCategoryRequest);
    }
}
