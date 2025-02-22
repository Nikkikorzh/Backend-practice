package practise.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import practise.backend.Models.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
}