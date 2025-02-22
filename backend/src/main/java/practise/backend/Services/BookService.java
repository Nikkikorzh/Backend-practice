package practise.backend.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import practise.backend.Repository.BookRepository;

import practise.backend.Models.Book;
import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Integer id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void deleteById(Integer id) {
        bookRepository.deleteById(id);
    }

    public ByteArrayInputStream exportBooksToCSV() {
        List<Book> books = findAll();
        StringBuilder sb = new StringBuilder("ID,Title,Description,PublicationYear\n");
        for(Book book : books){
            sb.append(book.getId()).append(",");
            sb.append(book.getTitle()).append(",");
            sb.append(book.getDescription()).append(",");
            sb.append(book.getPublicationYear()).append("\n");
        }
        return new ByteArrayInputStream(sb.toString().getBytes());
    }
}
