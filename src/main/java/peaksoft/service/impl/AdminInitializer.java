package peaksoft.service.impl;


import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import peaksoft.enums.Role;
import peaksoft.models.User;
import peaksoft.repositories.UserRepository;

@Component
@RequiredArgsConstructor
public class AdminInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        if (!userRepository.existsUserByRole(Role.ADMIN)) {
            User admin = User.builder()
                    .email("admin@example.com")
                    .password(passwordEncoder.encode("adminPassword"))
                    .role(Role.ADMIN)
                    .build();
            userRepository.save(admin);
        }
    }
}
