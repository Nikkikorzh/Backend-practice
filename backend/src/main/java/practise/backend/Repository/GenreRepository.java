package practise.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import practise.backend.Models.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
