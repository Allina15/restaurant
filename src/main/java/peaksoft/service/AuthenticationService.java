package peaksoft.service;

import peaksoft.dto.AuthenticationResponse;
import peaksoft.dto.SignInRequest;
import peaksoft.dto.UserRequest;

public interface AuthenticationService {
    AuthenticationResponse request(UserRequest request);

    AuthenticationResponse singIn(SignInRequest request);
}
