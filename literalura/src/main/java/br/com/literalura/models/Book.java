package br.com.literalura.models;

import br.com.literalura.dtos.DataBookDTO;
import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;
    private String language;

    @Column(name = "download_count")
    private Integer downloadCount;

    @ManyToOne
    private Author author;

    public Book() {
    }

    public Book(DataBookDTO data) {
        this.title = data.title();
        this.language = data.languages() != null && !data.languages().isEmpty() ? data.languages().get(0) : "Desconhecida";
        this.downloadCount = data.download_count();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "\n ------ LIVRO ------" +
                "\nTítulo: " + title +
                "\nAutor(es): " + (author.getName().isEmpty() ? "Desconhecido" : author.getName()) +
                "\nIdioma: " + language +
                "\nDownloads: " + downloadCount +
                "\n-------------------";
    }
}
