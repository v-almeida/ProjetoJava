package ProjetoJava.Views;

import java.util.Scanner;
import ProjetoJava.Controllers.EstacionamentoController;

public class EstacionamentoView {
    private EstacionamentoController controller;
    private Scanner scanner;

    public EstacionamentoView(EstacionamentoController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
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
                case 1:
                    registrarEntrada();
                    break;
                case 2:
                    registrarSaida();
                    break;
                case 3:
                    gerarRelatorio();
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.\n");
                    break;
            }
        }
    }

    private void registrarEntrada() {
        System.out.print("Digite o modelo do carro: ");
        String modelo = scanner.nextLine();

        System.out.print("Digite a placa do carro: ");
        String placa = scanner.nextLine();

        System.out.print("Digite a cor do carro: ");
        String cor = scanner.nextLine();

        if (controller.registrarEntrada(modelo, placa, cor)) {
            System.out.println("Carro registrado com sucesso!\n");
        } else {
            System.out.println("Não há vagas disponíveis no estacionamento.\n");
        }
    }

    private void registrarSaida() {
        System.out.print("Digite a placa do carro que está saindo: ");
        String placaSaida = scanner.nextLine();

        if (controller.registrarSaida(placaSaida)) {
            System.out.println("Carro saiu do estacionamento.\n");
        } else {
            System.out.println("O carro com placa '" + placaSaida + "' não foi encontrado no estacionamento.\n");
        }
    }

    private void gerarRelatorio() {
        String relatorio = controller.gerarRelatorio();
        controller.salvarRelatorio(relatorio);
        System.out.println("Relatório gerado. Saindo do programa...");
    }
}
