package ProjetoJava.Views;

import ProjetoJava.Models.Configuracao;
import ProjetoJava.Persistence.ConfiguracaoPersistence;

import java.util.Scanner;

public class ConfiguracaoView {
    private Configuracao configuracao;
    private ConfiguracaoPersistence configuracaoPersistence;
    private Scanner scanner;

    public ConfiguracaoView(Configuracao configuracao) {
        this.configuracao = configuracao;
        this.configuracaoPersistence = new ConfiguracaoPersistence();
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        boolean executando = true;
        while (executando) {
            System.out.println("Menu de Configurações:");
            System.out.println("1 - Alterar Tarifa por Hora");
            System.out.println("2 - Alterar Horário de Abertura");
            System.out.println("3 - Alterar Horário de Fechamento");
            System.out.println("4 - Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner após ler um número

            switch (opcao) {
                case 1:
                    alterarTarifaPorHora();
                    break;
                case 2:
                    alterarHorarioAbertura();
                    break;
                case 3:
                    alterarHorarioFechamento();
                    break;
                case 4:
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.\n");
                    break;
            }
        }
    }

    private void alterarTarifaPorHora() {
        System.out.print("Digite a nova tarifa por hora: ");
        double novaTarifa = scanner.nextDouble();
        scanner.nextLine(); // Limpar o buffer do scanner após ler um número
        configuracao.setTarifaPorHora(novaTarifa);
        salvarConfiguracao();
        System.out.println("Tarifa por hora alterada para: " + novaTarifa);
    }

    private void alterarHorarioAbertura() {
        System.out.print("Digite o novo horário de abertura: ");
        String novoHorario = scanner.nextLine();
        configuracao.setHorarioAbertura(novoHorario);
        salvarConfiguracao();
        System.out.println("Horário de abertura alterado para: " + novoHorario);
    }

    private void alterarHorarioFechamento() {
        System.out.print("Digite o novo horário de fechamento: ");
        String novoHorario = scanner.nextLine();
        configuracao.setHorarioFechamento(novoHorario);
        salvarConfiguracao();
        System.out.println("Horário de fechamento alterado para: " + novoHorario);
    }

    private void salvarConfiguracao() {
        if (configuracaoPersistence.salvarConfiguracao(configuracao)) {
            System.out.println("Configuração salva com sucesso.");
        } else {
            System.out.println("Erro ao salvar a configuração.");
        }
    }
}
