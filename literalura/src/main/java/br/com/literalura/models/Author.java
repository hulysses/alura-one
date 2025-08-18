package br.com.literalura.models;

import br.com.literalura.dtos.DataAuthorDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(name = "birth_year")
    private Integer birthYear;

    @Column(name = "death_year")
    private Integer deathYear;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> book = new ArrayList<>();

    public Author() {
    }

    public Author(DataAuthorDTO data) {
        this.name = data.name();
        this.birthYear = data.birthDate();
        this.deathYear = data.deathDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "\n ------ AUTOR ------" +
                "\nNome: " + name +
                "\nAno de Nascimento: " + birthYear +
                "\nAno de Falecimento: " + deathYear +
                "\nLivros: " + (book.isEmpty() ? "Nenhum livro cadastrado" : book.stream()
                .map(Book::getTitle)
                .collect(Collectors.joining(", "))) +
                "\n-------------------";
    }
}
