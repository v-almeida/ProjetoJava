package ProjetoJava;

import ProjetoJava.Controllers.EstacionamentoController;
import ProjetoJava.Models.Configuracao;
import ProjetoJava.Views.EstacionamentoView;
import ProjetoJava.Views.ConfiguracaoView;
import ProjetoJava.Views.UsuarioView;

import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {
       
        Configuracao configuracaoInicial = new Configuracao(10.0, "08:00", "18:00");

        
        EstacionamentoController controller = new EstacionamentoController(configuracaoInicial);
        EstacionamentoView estacionamentoView = new EstacionamentoView(controller);
        ConfiguracaoView configuracaoView = new ConfiguracaoView(configuracaoInicial);
        UsuarioView usuarioView = new UsuarioView(controller);

        
        boolean executando = true;
        Scanner scanner = new Scanner(System.in);
        while (executando) {
            System.out.println("Menu Principal:");
            System.out.println("1 - Menu Estacionamento");
            System.out.println("2 - Menu Configurações");
            System.out.println("3 - Cadastrar Usuário");
            System.out.println("4 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    estacionamentoView.exibirMenu();
                    break;
                case 2:
                    configuracaoView.exibirMenu();
                    break;
                case 3:
                    usuarioView.exibirMenuCadastro();
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
}
