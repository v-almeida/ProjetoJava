package ProjetoJava.Views;

import ProjetoJava.Controllers.EstacionamentoController;
import ProjetoJava.Models.Configuracao;
import java.util.Scanner;

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
            System.out.println("Menu Principal:");
            System.out.println("1 - Registrar Entrada de Carro");
            System.out.println("2 - Registrar Saída de Carro");
            System.out.println("3 - Gerar Relatório");
            System.out.println("4 - Configurações");
            System.out.println("5 - Registrar Usuário");
            System.out.println("6 - Remover Usuário");
            System.out.println("7 - Sair");
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
                    break;
                case 4:
                    exibirMenuConfiguracao();
                    break;
                case 5:
                    registrarEntradaUsuario();
                    break;
                case 6:
                    registrarSaidaUsuario();
                    break;
                case 7:
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

        boolean sucesso = controller.registrarEntrada(modelo, placa, cor);
        if (sucesso) {
            System.out.println("Entrada registrada com sucesso!");
        } else {
            System.out.println("Falha ao registrar entrada. Estacionamento lotado.");
        }
    }

    private void registrarSaida() {
        System.out.print("Digite a placa do carro: ");
        String placa = scanner.nextLine();

        boolean sucesso = controller.registrarSaida(placa);
        if (sucesso) {
            System.out.println("Saída registrada com sucesso!");
        } else {
            System.out.println("Falha ao registrar saída. Carro não encontrado.");
        }
    }

    private void gerarRelatorio() {
        String relatorio = controller.gerarRelatorio();
        System.out.println("Relatório:");
        System.out.println(relatorio);
        controller.salvarRelatorio(relatorio);
    }

    private void exibirMenuConfiguracao() {
        Configuracao configuracao = controller.getConfiguracao();
        ConfiguracaoView configuracaoView = new ConfiguracaoView(configuracao);
        configuracaoView.exibirMenu(); // Chamando sem passar Scanner
    }

    private void registrarEntradaUsuario() {
        System.out.print("Nome do usuário: ");
        String nome = scanner.nextLine();
        System.out.print("Email do usuário: ");
        String email = scanner.nextLine();
        System.out.print("Senha do usuário: ");
        String senha = scanner.nextLine();

        boolean sucesso = controller.cadastrarUsuario(nome, email, senha);
        if (sucesso) {
            System.out.println("Usuário registrado com sucesso!");
        } else {
            System.out.println("Falha ao registrar usuário.");
        }
    }

    private void registrarSaidaUsuario() {
        System.out.print("Digite o email do usuário: ");
        String email = scanner.nextLine();

        boolean sucesso = controller.removerUsuario(email);
        if (sucesso) {
            System.out.println("Saída do usuário registrada com sucesso!");
        } else {
            System.out.println("Falha ao registrar saída. Usuário não encontrado.");
        }
    }
}
