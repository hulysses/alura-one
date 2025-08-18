package br.com.literalura.services;

import br.com.literalura.models.Author;
import br.com.literalura.models.Book;
import br.com.literalura.repositories.AuthorRepository;
import br.com.literalura.repositories.BookRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public CatalogService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Transactional(readOnly = true)
    public List<Book> listBooks() {
        return bookRepository.findAllWithAuthor();
    }

    @Transactional(readOnly = true)
    public List<Author> listAuthors() {
        return authorRepository.findAllWithBooks();
    }

    @Transactional(readOnly = true)
    public List<Author> listLivingAuthorsByYear(Integer year) {
        return authorRepository.searchLivingAuthorsInYear(year);
    }

    @Transactional(readOnly = true)
    public List<Book> listBookByLanguage(String language) {
        return bookRepository.findByLanguage(language);
    }
}
