package peaksoft.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.ChequeRequest;
import peaksoft.dto.ChequeResponse;
import peaksoft.dto.SimpleResponse;
import peaksoft.service.ChequeService;

@RestController
@RequiredArgsConstructor
@EnableMethodSecurity
@RequestMapping("/api/cheques")
public class ChequeApi {

    private final ChequeService chequeService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','WAITER')")
    public ChequeResponse save(@RequestBody ChequeRequest chequeRequest){
        return chequeService.save(chequeRequest);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','WAITER')")
    public String countCheque(@RequestParam String userName){
        return chequeService.countCheque(userName);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable long id){
        return chequeService.delete(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/restaurantCheques")
    public String restaurantCheques(@RequestParam String name){
        return chequeService.restaurantCheque(name);
    }
}
