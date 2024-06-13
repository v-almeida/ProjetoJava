package ProjetoJava.Models;

import java.util.Scanner;

public class EntradaUsuario {
    private Scanner scanner;

    public EntradaUsuario() {
        this.scanner = new Scanner(System.in);
    }

    public Carro criarCarro() {
        System.out.print("Digite a placa do carro: ");
        String placa = scanner.nextLine();

        System.out.print("Digite o modelo do carro: ");
        String modelo = scanner.nextLine();

        return new Carro(placa, modelo, modelo);
    }

    public int lerNumeroVaga() {
        System.out.print("Digite o número da vaga: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, digite um número válido.");
            scanner.next(); // descarta a entrada inválida
        }
        return scanner.nextInt();
    }

    public int menuPrincipal() {
        System.out.println("1. Estacionar carro");
        System.out.println("2. Liberar vaga");
        System.out.println("3. Listar vagas");
        System.out.println("4. Sair");
        System.out.print("Escolha uma opção: ");

        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, digite um número válido.");
            scanner.next(); // descarta a entrada inválida
        }
        return scanner.nextInt();
    }
}
