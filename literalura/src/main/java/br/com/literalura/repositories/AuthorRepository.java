package br.com.literalura.repositories;

import br.com.literalura.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT a FROM Author a JOIN FETCH a.book WHERE a.birthYear <= :year AND" +
            " (a.deathYear >= :year OR a.deathYear IS NULL)")
    List<Author> searchLivingAuthorsInYear(Integer year);

    Optional<Author> findByNameContainingIgnoreCase(String name);

    @Query("SELECT a FROM Author a JOIN FETCH a.book")
    List<Author> findAllWithBooks();
}
