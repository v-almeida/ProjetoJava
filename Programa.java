package ProjetoJava;

import java.util.Scanner;
import ProjetoJava.Controllers.EstacionamentoController;

public class Programa {
    public static void main(String[] args) {
        EstacionamentoController controlador = new EstacionamentoController();
        Scanner scanner = new Scanner(System.in);

        boolean executando = true;
        while (executando) {
            System.out.println("Bem-vindo ao sistema de estacionamento!");
            System.out.println("1 - Registrar entrada de carro");
            System.out.println("2 - Registrar saída de carro");
            System.out.println("3 - Gerar relatório e sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner após ler um número

            switch (opcao) {
                case 1: // Registrar entrada de carro
                    System.out.print("Digite o modelo do carro: ");
                    String modelo = scanner.nextLine();

                    System.out.print("Digite a placa do carro: ");
                    String placa = scanner.nextLine();

                    System.out.print("Digite a cor do carro: ");
                    String cor = scanner.nextLine();

                    if (controlador.registrarEntrada(modelo, placa, cor)) {
                        System.out.println("Carro registrado com sucesso!\n");
                    } else {
                        System.out.println("Não há vagas disponíveis no estacionamento.\n");
                    }
                    break;

                case 2: // Registrar saída de carro
                    System.out.print("Digite a placa do carro que está saindo: ");
                    String placaSaida = scanner.nextLine();
                    if (controlador.registrarSaida(placaSaida)) {
                        System.out.println("Carro saiu do estacionamento.\n");
                    } else {
                        System.out.println("O carro com placa '" + placaSaida + "' não foi encontrado no estacionamento.\n");
                    }
                    break;

                case 3: // Gerar relatório e sair
                    String conteudoRelatorio = controlador.gerarRelatorio();
                    controlador.salvarRelatorio(conteudoRelatorio);
                    executando = false;
                    System.out.println("Relatório gerado. Saindo do programa...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.\n");
                    break;
            }
        }

        scanner.close();
    }
}