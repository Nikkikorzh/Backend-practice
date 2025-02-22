package practise.backend.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="Books")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(unique=true, nullable=false)
    private String title;
    @Column(nullable=false)
    private String description;
    @Column(name="publication_year", nullable=false)
    private Integer publicationYear;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "BOOKS_AUTHORS",
            joinColumns = @JoinColumn(name = "BOOK_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID")
    )
    private List<Author> authors;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "BOOKS_GENRES",
            joinColumns = @JoinColumn(name = "BOOK_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "GENRE_ID", referencedColumnName = "ID")
    )
    private List<Genre> genres;
}
