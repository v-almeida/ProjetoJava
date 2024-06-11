package ProjetoJava.Views;

import ProjetoJava.Controllers.EstacionamentoController;

import java.util.Scanner;

public class UsuarioView {
    private EstacionamentoController controller;
    private Scanner scanner;

    public UsuarioView(EstacionamentoController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenuCadastro() {
        System.out.print("Digite o nome do usuário: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o email do usuário: ");
        String email = scanner.nextLine();
        System.out.print("Digite a senha do usuário: ");
        String senha = scanner.nextLine();

        boolean sucesso = controller.cadastrarUsuario(nome, email, senha);
        if (sucesso) {
            System.out.println("Usuário cadastrado com sucesso.");
        } else {
            System.out.println("Erro ao cadastrar o usuário.");
        }
    }
}
