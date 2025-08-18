package br.com.literalura.repositories;

import br.com.literalura.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByTitleContainingIgnoreCase(String title);

    @Query("SELECT l FROM Book l JOIN FETCH l.author WHERE l.language = :language")
    List<Book> findByLanguage(String language);

    @Query("SELECT l FROM Book l JOIN FETCH l.author")
    List<Book> findAllWithAuthor();
}
