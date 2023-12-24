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
    public double countCheque(@RequestParam String userName){
        return chequeService.countCheque(userName);
    }
}
