package practise.backend.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practise.backend.Models.User;
import practise.backend.Repository.BookRepository;
import practise.backend.Repository.UserRepository;

import practise.backend.Models.Book;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;


    public boolean isUserAuthorized(Integer userId, String currentUserEmail) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getEmail().equals(currentUserEmail);
    }

    public User addFavoriteBook(Integer userId, Integer bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        user.getFavoriteBooks().add(book);
        return userRepository.save(user);
    }

    public User removeFavoriteBook(Integer userId, Integer bookId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        user.getFavoriteBooks().remove(book);
        return userRepository.save(user);
    }

    public Set<Book> getFavoriteBooks(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getFavoriteBooks();
    }
}
