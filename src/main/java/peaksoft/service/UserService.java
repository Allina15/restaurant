package peaksoft.service;

import peaksoft.dto.AssignRequest;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.UserRequest;
import peaksoft.dto.UserResponse;

public interface UserService {
    SimpleResponse delete(UserRequest userRequest);
    UserResponse getProfile();
    UserResponse update(UserRequest userRequest);
    SimpleResponse assign(AssignRequest assignRequest);
}
