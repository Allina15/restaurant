package peaksoft.controllers;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.MenuRequest;
import peaksoft.dto.SimpleResponse;
import peaksoft.models.MenuItem;
import peaksoft.service.MenuItemService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@EnableMethodSecurity
@RequestMapping("/api/menuItems")
public class MenuItemApi {

    private final MenuItemService menuItemService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF')")
    public SimpleResponse save(@RequestBody @Valid  MenuRequest menuRequest){
        return menuItemService.save(menuRequest);
    }

    @GetMapping("/search")
    @PermitAll
    public List<MenuItem>search(@RequestParam String name){
        return menuItemService.search(name);
    }

    @PermitAll
    @GetMapping("/sort")
    public List<MenuItem>sortByPrice(@RequestParam String ascOrDesc){
        return menuItemService.sortByPrice(ascOrDesc);
    }

    @PermitAll
    @GetMapping("/isVegan")
    public List<MenuItem>isVegan(@RequestParam boolean vegan){
        return menuItemService.filterByIsVegetarian(vegan);
    }
}
