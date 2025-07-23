import Services.ConvertCurrency;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option = 0;

        System.out.println("|---------------------------------------------|");
        System.out.println("|             Conversor de Moedas             |");

        while(option != 7) {
            try {
                showMenu();
                option = scanner.nextInt();
                convertCurrency(option);
            } catch (InputMismatchException e) {
                System.out.println("| Opção inválida! Por favor, insira um número |");
            } catch (Exception e) {
                System.out.println("| Ocorreu um erro inesperado: " + e.getMessage());
                System.out.println("|---------------------------------------------|");
            } finally {
                scanner.nextLine();
            }
        }
    }

    public static void showMenu() {
        System.out.println("|---------------------------------------------|");
        System.out.println("| 1 - Dólar para Peso argentino               |");
        System.out.println("| 2 - Peso argentino para Dólar               |");
        System.out.println("| 3 - Dólar para Real                         |");
        System.out.println("| 4 - Real para Dólar                         |");
        System.out.println("| 5 - Dólar para Peso Colombiano              |");
        System.out.println("| 6 - Peso Colombiano para Dólar              |");
        System.out.println("| 7 - Sair                                    |");
        System.out.println("|---------------------------------------------|");
        System.out.println("| Escolha uma opção:                          |");
    }

    public static void convertCurrency(int option) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("| Informe o valor:                            |");

        try {
            double value = scanner.nextDouble();
            String from = "", to = "";

            switch (option) {
                case 1: from = "USD"; to = "ARS"; break;
                case 2: from = "ARS"; to = "USD"; break;
                case 3: from = "USD"; to = "BRL"; break;
                case 4: from = "BRL"; to = "USD"; break;
                case 5: from = "USD"; to = "COP"; break;
                case 6: from = "COP"; to = "USD"; break;
                case 7:
                    System.out.println("| Obrigado por usar o Conversor de Moedas!   |");
                    System.out.println("|---------------------------------------------|");
                    return;
                default:
                    System.out.println("| Opção inválida!                             |");
                    return;
            }

            ConvertCurrency converter = new ConvertCurrency();
            Double result = converter.convert(from, to, value);

            System.out.println("| Convertendo...                              |");
            System.out.println("|---------------------------------------------|");

            if (result != null) {
                System.out.printf("| %.2f %s equivalem a %.2f %s        |\n", value, from, result, to);
            } else {
                System.out.println("| Erro ao converter moeda. Tente novamente.   |");
            }

        } catch (InputMismatchException e) {
            System.out.println("| Valor inválido! Por favor, insira um número.|");
            scanner.nextLine();
        }
    }
}
