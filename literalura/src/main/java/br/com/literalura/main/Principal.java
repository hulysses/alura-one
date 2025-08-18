package br.com.literalura.main;

import br.com.literalura.dtos.DataAuthorDTO;
import br.com.literalura.dtos.DataBookDTO;
import br.com.literalura.dtos.DataGutendexDTO;
import br.com.literalura.models.Author;
import br.com.literalura.models.Book;
import br.com.literalura.repositories.AuthorRepository;
import br.com.literalura.repositories.BookRepository;
import br.com.literalura.services.CatalogService;
import br.com.literalura.services.ConvertData;
import br.com.literalura.services.GutendexAPIService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Principal {
    private final Scanner scanner = new Scanner(System.in);

    private GutendexAPIService gutendexAPIService;
    private ConvertData convertData;
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private CatalogService catalogService;

    public Principal(GutendexAPIService gutendexAPIService, ConvertData convertData, BookRepository bookRepository,
                     AuthorRepository authorRepository, CatalogService catalogService) {
        this.gutendexAPIService = gutendexAPIService;
        this.convertData = convertData;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.catalogService = catalogService;
    }

    public void showMenu() {
        int opcao = -1;
        while (opcao != 0) {
            String menu = """
                    
                    *********************************************
                    *                 LiterAlura                *
                    *********************************************
                    
                     Escolha uma opção:
                    
                     0 - Sair
                     1 - Buscar livro pelo título
                     2 - Listar livros
                     3 - Listar autores
                     4 - Listar autores vivos por ano
                     5 - Listar livros em determinado idioma
                    
                    *********************************************
                    """;

            System.out.println(menu);
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 0:
                    System.out.println(" Saindo do programa...");
                    break;
                case 1:
                    System.out.println(" Informe o título do livro:");
                    String title = scanner.nextLine();

                    getBookByTitle(title);
                    break;
                case 2:
                    listBooks();
                    break;
                case 3:
                    listAuthors();
                    break;
                case 4:
                    listLivingAuthorsByYear();
                    break;
                case 5:
                    listBooksByLanguage();
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void getBookByTitle(String title) {
        try {
            Optional<Book> bookAlreadyExists = bookRepository.findByTitleContainingIgnoreCase(title);
            if (bookAlreadyExists.isPresent()) {
                System.out.println(bookAlreadyExists.get());
                return;
            }

            String json = gutendexAPIService.consumeAPI(title);
            DataGutendexDTO dataGutendexDTO = convertData.convert(json, DataGutendexDTO.class);

            Optional<DataBookDTO> dataBookDTO = dataGutendexDTO.results().stream()
                    .filter(b -> b.title().equalsIgnoreCase(title))
                    .findFirst();

            if (dataBookDTO.isPresent()) {
                DataBookDTO bookDTO = dataBookDTO.get();
                Author newAuthor;
                if (!bookDTO.authors().isEmpty()) {
                    DataAuthorDTO dataAuthorDTO = bookDTO.authors().get(0);
                    Optional<Author> authorAlreadyExist = authorRepository.findByNameContainingIgnoreCase(dataAuthorDTO.name());
                    newAuthor = authorAlreadyExist.orElseGet(() -> new Author(dataAuthorDTO));
                } else {
                    newAuthor = authorRepository.findByNameContainingIgnoreCase("Desconhecido")
                            .orElseGet(() -> {
                                Author authorUnknown = new Author();
                                authorUnknown.setName("Desconhecido");
                                return authorUnknown;
                            });
                }

                Author author = authorRepository.save(newAuthor);

                Book newBook = new Book(bookDTO);
                newBook.setAuthor(author);

                bookRepository.save(newBook);
                System.out.println(newBook);
            } else {
                System.out.println("\n Livro não encontrado. Verifique a ortografia ou tente um título diferente.");
            }
        } catch (Exception e) {
            System.out.println(" Ocorreu um erro durante a busca: " + e.getMessage());
        }
    }

    private void listBooks() {
        System.out.println("\n --- LIVROS ---");
        List<Book> books = catalogService.listBooks();
        if (books.isEmpty()) {
            System.out.println(" Nenhum livro cadastrado.");
        } else {
            books.forEach(System.out::println);
        }
    }

    private void listAuthors() {
        System.out.println("\n --- AUTORES ---");
        List<Author> authors = catalogService.listAuthors();
        if (authors.isEmpty()) {
            System.out.println(" Nenhum autor cadastrado.");
        } else {
            authors.forEach(System.out::println);
        }
    }

    private void listLivingAuthorsByYear() {
        System.out.println(" Informe o ano:");
        Integer year = scanner.nextInt();
        scanner.nextLine();
        List<Author> authors = catalogService.listLivingAuthorsByYear(year);

        if (authors.isEmpty()) {
            System.out.println("\n Nenhum autor encontrado vivo para o ano de " + year + ".");
        } else {
            System.out.println("\n--- AUTORES VIVOS EM " + year + " ---");
            authors.forEach(System.out::println);
        }
    }

    private void listBooksByLanguage() {
        System.out.println("""
                Digite o idioma para a busca:
                es - espanhol
                en - inglês
                fr - francês
                pt - português
                """);
        String language = scanner.nextLine();
        List<Book> books = catalogService.listBookByLanguage(language);

        if (books.isEmpty()) {
            System.out.println("\n Nenhum livro encontrado para o idioma '" + language + "'.");
        } else {
            System.out.println("\n--- LIVROS NO IDIOMA: " + language + " ---");
            books.forEach(System.out::println);
        }
    }
}



