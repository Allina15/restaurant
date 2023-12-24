package peaksoft.controllers;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.dto.AuthenticationResponse;
import peaksoft.dto.SignInRequest;
import peaksoft.dto.UserRequest;
import peaksoft.service.AuthenticationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationApi {

    private final AuthenticationService authenticationService;

    @PermitAll
    @PostMapping("/request")
    AuthenticationResponse request(@RequestBody @Valid UserRequest request){
        return authenticationService.request(request);
    }

    @PermitAll
    @PostMapping("/signIn")
    AuthenticationResponse signIn(@RequestBody SignInRequest request){
        return authenticationService.singIn(request);
    }

}
