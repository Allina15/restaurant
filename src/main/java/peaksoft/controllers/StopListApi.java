package peaksoft.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.StopListRequest;
import peaksoft.service.StopListService;

@RequiredArgsConstructor
@RestController
@EnableMethodSecurity
@RequestMapping("/api/stopLists")
public class StopListApi {

    private final StopListService service;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN','CHEF')")
    public SimpleResponse save(@RequestBody StopListRequest stopListRequest){
        return service.save(stopListRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'CHEF')")
    @DeleteMapping("/{id}")
    public SimpleResponse delete(@PathVariable long id){
        return service.delete(id);
    }
}
