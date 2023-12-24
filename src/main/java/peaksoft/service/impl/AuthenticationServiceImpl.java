package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.config.JwtService;
import peaksoft.dto.AuthenticationResponse;
import peaksoft.dto.SignInRequest;
import peaksoft.dto.UserRequest;
import peaksoft.enums.Role;
import peaksoft.exceptions.AlreadyExistsException;
import peaksoft.exceptions.BadCredentialsException;
import peaksoft.exceptions.NotFoundException;
import peaksoft.models.User;
import peaksoft.repositories.UserRepository;
import peaksoft.service.AuthenticationService;

import java.time.LocalDate;
import java.time.Period;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthenticationResponse request(UserRequest request) {
        if (userRepository.existsUserByEmail(request.getEmail())) {
            throw new AlreadyExistsException(
                    "user with userName: " + request.getEmail()+ " already exists!"
            );
        }
        User user = User.builder().firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dateOfBirth(request.getDateOfBirth())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhoneNumber())
                .role(request.getRole())
                .experience(request.getExperience())
                .build();

        int age = Period.between(request.getDateOfBirth(), LocalDate.now()).getYears();
        if (request.getRole()==Role.CHEF){
            if (age < 25 || age > 45){
                throw new RuntimeException("Ваш возраст не подходит!");
            }
            if (request.getExperience() < 2){
                throw new RuntimeException("У вас недостаточно опыта!");
            }
        } else if (request.getRole()==Role.WAITER) {
            if (age < 18 || age > 30){
                throw new RuntimeException("Ваш возраст не подходит!");
            }
            if (request.getExperience() < 1){
                throw new RuntimeException("У вас недостаточно опыта!");
            }
        }
        userRepository.save(user);

        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder().
                token(token)
                .userName(user.getUsername())
                .build();
    }

    @Override
    public AuthenticationResponse singIn(SignInRequest request) {
        User user = userRepository.findUserByEmail(request.username()).orElseThrow(
                () -> new NotFoundException(
                        "user with email: " + request.username() + " not fount"));
        if (request.username().isBlank()) {
            throw new BadCredentialsException("email is blank");
        }
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BadCredentialsException("wrong password");
        }
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().
                token(jwtToken)
                .userName(user.getUsername())
                .build();
    }
}
