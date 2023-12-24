package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.enums.Role;
import peaksoft.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User>findUserByEmail(String username);

    boolean existsUserByRole(Role role);

    boolean existsUserByEmail(String email);

    Optional<User>findUserByFirstNameAndLastName(String firstName, String lastName);

    Optional<User>findUserByFirstName(String name);
}
