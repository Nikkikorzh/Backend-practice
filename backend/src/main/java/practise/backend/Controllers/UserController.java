package practise.backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import practise.backend.Models.Book;
import practise.backend.Models.User;
import practise.backend.Services.UserService;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/{userId}/favorites/add/{bookId}")
    public ResponseEntity<User> addFavorite(@PathVariable Integer userId, @PathVariable Integer bookId, Principal principal) {

        String currentUserEmail = principal.getName();


        if (!userService.isUserAuthorized(userId, currentUserEmail)) {
            throw new RuntimeException("You can only add books to your own favorites.");
        }

        return ResponseEntity.ok(userService.addFavoriteBook(userId, bookId));
    }

    @DeleteMapping("/{userId}/favorites/delete/{bookId}")
    public ResponseEntity<User> removeFavorite(@PathVariable Integer userId, @PathVariable Integer bookId, Principal principal) {

        String currentUserEmail = principal.getName();

        if (!userService.isUserAuthorized(userId, currentUserEmail)) {
            throw new RuntimeException("You can only remove books from your own favorites.");
        }

        return ResponseEntity.ok(userService.removeFavoriteBook(userId, bookId));
    }

    @GetMapping("/{userId}/favorites")
    public ResponseEntity<Set<Book>> getFavorites(@PathVariable Integer userId, Principal principal) {
        String currentUserEmail = principal.getName();

        if (!userService.isUserAuthorized(userId, currentUserEmail)) {
            throw new RuntimeException("You can only view your own favorite books.");
        }

        return ResponseEntity.ok(userService.getFavoriteBooks(userId));
    }
}

