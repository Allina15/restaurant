package peaksoft.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import peaksoft.enums.Role;
import peaksoft.validations.Password;
import peaksoft.validations.PhoneNumber;
import peaksoft.validations.UniqueEmail;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private LocalDate dateOfBirth;
    @Email
    @UniqueEmail
    private String email;
    @Password
    private String password;
    @PhoneNumber
    private String phoneNumber;
    @NotBlank
    private Role role;
    @NotBlank
    private int experience;
    private String restName;

    public UserRequest(String firstName, String lastName, LocalDate dateOfBirth, String email, String password, String phoneNumber, Role role, int experience) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.experience = experience;
    }
}
