package ProjetoJava.Persistence;

import ProjetoJava.Models.Usuario;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UsuarioPersistence {
    private static final String FILE_PATH = "usuarios.txt";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void salvar(List<Usuario> usuarios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            for (Usuario usuario : usuarios) {
                writer.write(formatarUsuario(usuario));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar os usuários: " + e.getMessage());
        }
    }

    public List<Usuario> carregar() {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (!linha.trim().isEmpty()) {
                    Usuario usuario = parseLinhaParaUsuario(linha);
                    if (usuario != null) {
                        usuarios.add(usuario);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar os usuários: " + e.getMessage());
        }
        return usuarios;
    }

    public boolean salvarUsuario(Usuario usuario) {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario);
        salvar(usuarios);
        return true; // ou outra condição para indicar se o salvamento foi bem-sucedido
    }

    private String formatarUsuario(Usuario usuario) {
        return "nome: " + usuario.getNome() +
                " email: " + usuario.getEmail() +
                " senha: " + usuario.getSenha() +
                " data_hora: " + LocalDateTime.now().format(DATE_FORMATTER);
    }

    private Usuario parseLinhaParaUsuario(String linha) {
        String[] partes = linha.split(" ");
        if (partes.length >= 6) { // Garantir que haja pelo menos 6 partes ( fatores (`nome`, `email`, `senha` e `data_hora`)
            String nome = partes[1];
            String email = partes[3];
            String senha = partes[5];
            // Podem haver campos adicionais, então vamos ignorar os últimos campos
            return new Usuario(nome, email, senha);
        }
        return null;
    }
}