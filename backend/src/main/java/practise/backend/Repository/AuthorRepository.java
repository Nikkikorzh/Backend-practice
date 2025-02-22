package practise.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practise.backend.Models.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
