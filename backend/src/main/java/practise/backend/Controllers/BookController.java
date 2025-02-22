package practise.backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import practise.backend.Services.BookService;

import practise.backend.Models.Book;
import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping("/lib")
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok(bookService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Integer id){
        return ResponseEntity.ok(bookService.findById(id));
    }


    @PostMapping("/add-new-book")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        return ResponseEntity.ok(bookService.save(book));
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteBook(@PathVariable Integer id){
        bookService.deleteById(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/export/csv")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<InputStreamResource> exportBooksToCSV() {
        ByteArrayInputStream bais = bookService.exportBooksToCSV();
        InputStreamResource file = new InputStreamResource(bais);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=books.csv")
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }
}
