package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import peaksoft.dto.AssignRequest;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.UserRequest;
import peaksoft.dto.UserResponse;
import peaksoft.exceptions.NotFoundException;
import peaksoft.models.Restaurant;
import peaksoft.models.User;
import peaksoft.repositories.RestaurantRepository;
import peaksoft.repositories.UserRepository;
import peaksoft.service.UserService;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

     private final UserRepository userRepository;
     private final RestaurantRepository restaurantRepository;

     @Override
     public SimpleResponse delete(UserRequest userRequest) {
          User user = userRepository.findUserByEmail(userRequest.getEmail()).orElseThrow(() -> new NotFoundException("User with email: " + userRequest.getEmail() + "not found"));
          Restaurant restaurant = restaurantRepository.findRestaurantByUsers(user);
          restaurant.getUsers().remove(user);
          userRepository.delete(user);
          return new SimpleResponse(HttpStatus.OK, "Deleted successfully");
     }

     @Override
     public UserResponse getProfile() {
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
          String email = authentication.getName();

          User authenticatedUser = userRepository.findUserByEmail(email)
                  .orElseThrow(() -> new NotFoundException("User with email: " + email + " not found"));
               return new UserResponse(
                       authenticatedUser.getFirstName(),
                       authenticatedUser.getLastName(),
                       authenticatedUser.getDateOfBirth(),
                       authenticatedUser.getEmail(),
                       authenticatedUser.getPhoneNumber(),
                       authenticatedUser.getRole(),
                       authenticatedUser.getExperience()
               );
     }

     @Override
     public UserResponse update(UserRequest userRequest) {
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
          String email = authentication.getName();

          User authUser = userRepository.findUserByEmail(email).orElseThrow(() -> new NotFoundException("User with email: " + email + " not found"));
               authUser.setFirstName(userRequest.getFirstName());
               authUser.setLastName(userRequest.getLastName());
               authUser.setDateOfBirth(userRequest.getDateOfBirth());
               authUser.setEmail(userRequest.getEmail());
               authUser.setPhoneNumber(userRequest.getPhoneNumber());
               authUser.setRole(userRequest.getRole());
               authUser.setExperience(userRequest.getExperience());
               userRepository.save(authUser);
          return new UserResponse(authUser.getFirstName(), authUser.getLastName(), authUser.getDateOfBirth(), authUser.getEmail(), authUser.getPhoneNumber(), authUser.getRole(), authUser.getExperience());
     }
     @Override
     public SimpleResponse assign(AssignRequest assignRequest) {
          Restaurant restaurant = restaurantRepository.findById(assignRequest.getRestaurantId()).orElseThrow(()->new NotFoundException("Restaurant with id "+assignRequest.getRestaurantId()+" not found"));
          User user = userRepository.findById(assignRequest.getWorkersId()).orElseThrow(()->new NotFoundException("user with id "+assignRequest.getWorkersId()+" not found"));
          if (restaurant.getNumberOfEmployees() >= 15){
               throw new RuntimeException("no vacancy");
          }else{
               if (user != null && assignRequest.getAcceptOrReject().equalsIgnoreCase("accept")){
                    user.setRestaurant(restaurant);
                    userRepository.save(user);
                    return new SimpleResponse(HttpStatus.OK, "successfully assigned");
               } else if (user != null && assignRequest.getAcceptOrReject().equalsIgnoreCase("reject")) {
                    userRepository.delete(user);
                    return new SimpleResponse(HttpStatus.OK, "Deleted");
               }
          }
          return new SimpleResponse(HttpStatus.OK,"");
     }
}
