package peaksoft.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.AssignRequest;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.UserRequest;
import peaksoft.dto.UserResponse;
import peaksoft.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserApi {

    private final UserService userService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping
    public SimpleResponse delete(@RequestBody UserRequest userRequest){
        return userService.delete(userRequest);
    }

    @GetMapping
    public UserResponse getProfile(){
        return userService.getProfile();
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public UserResponse update(@RequestBody UserRequest userRequest){
        return userService.update(userRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public SimpleResponse assign(@RequestBody AssignRequest assignRequest){
        return userService.assign(assignRequest);
    }
}
