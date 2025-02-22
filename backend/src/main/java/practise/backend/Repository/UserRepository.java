package practise.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practise.backend.Models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
