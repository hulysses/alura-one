package br.com.literalura.main;

import br.com.literalura.dtos.DataBookDTO;
import br.com.literalura.services.ConvertDataService;
import br.com.literalura.services.GutendexAPIService;

import java.util.Scanner;

public class Main {

    private static final GutendexAPIService gutendexAPIService = new GutendexAPIService();
    private static final ConvertDataService convertDataService = new ConvertDataService();

    public static void showMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("|-----------------------------------------|");
        System.out.println("|              LITERALURA MENU            |");
        System.out.println("|-----------------------------------------|");
        System.out.println("|  Escolha uma opção:                     |");
        System.out.println("| 0. Sair do programa;                    |");
        System.out.println("| 1. Buscar livro pelo título;            |");
        System.out.println("| 2. Listar livros;                       |");
        System.out.println("| 3. Listar autores;                      |");
        System.out.println("| 4. Listar autores vivos por ano;        |");
        System.out.println("| 5. Listar livros por idioma.            |");
        System.out.println("|-----------------------------------------|");

        var oprtion = scanner.nextInt();
        scanner.nextLine();

        switch (oprtion) {
            case 0:
                System.out.println("Saindo do programa...");
                break;
            case 1:
                System.out.println(" Informe o título do livro:");
                String title = scanner.nextLine();

                getBookByTitle(title);
                break;
            case 2:
                System.out.println("Listar livros...");
                // Implementar lógica de listagem de livros
                break;
            case 3:
                System.out.println("Listar autores...");
                // Implementar lógica de listagem de autores
                break;
            case 4:
                System.out.println("Listar autores vivos por ano...");
                // Implementar lógica de listagem de autores vivos por ano
                break;
            case 5:
                System.out.println("Listar livros por idioma...");
                // Implementar lógica de listagem de livros por idioma
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void getBookByTitle(String title) {
        var data = gutendexAPIService.consumeAPI(title);
        DataBookDTO bookDTO = convertDataService.convert(data.results, DataBookDTO.class);
        System.out.println(bookDTO);

    }
}
