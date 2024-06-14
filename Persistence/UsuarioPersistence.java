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

    // Salva uma lista de usuários no arquivo, substituindo o conteúdo existente.
    public void salvar(List<Usuario> usuarios) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false))) {
            for (Usuario usuario : usuarios) {
                writer.write(formatarUsuario(usuario));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar os usuários: " + e.getMessage());
        }
    }

    // Carrega os usuários do arquivo e retorna uma lista de objetos Usuario.
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

    // Salva um único usuário no arquivo, mantendo os existentes.
    public boolean salvarUsuario(Usuario usuario) {
        List<Usuario> usuarios = carregar(); // Carregar usuários existentes
        usuarios.add(usuario);
        salvar(usuarios); // Salvar a lista completa de volta
        return true;
    }

    // Remove um usuário pelo email e salva a lista atualizada no arquivo.
    public boolean removerUsuario(String email) {
        List<Usuario> usuarios = carregar();
        boolean usuarioRemovido = usuarios.removeIf(usuario -> usuario.getEmail().equals(email));
        if (usuarioRemovido) {
            salvar(usuarios); // Salva a lista atualizada de volta
        }
        return usuarioRemovido;
    }

    // Formata um objeto Usuario em uma linha de texto para salvar no arquivo.
    private String formatarUsuario(Usuario usuario) {
        return String.format("nome:%s;email:%s;senha:%s;data_hora:%s",
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                LocalDateTime.now().format(DATE_FORMATTER));
    }

    // Converte uma linha de texto em um objeto Usuario.
    private Usuario parseLinhaParaUsuario(String linha) {
        String[] partes = linha.split(";");
        if (partes.length >= 4) { // Assegura que temos todas as partes necessárias
            String nome = partes[0].split(":")[1];
            String email = partes[1].split(":")[1];
            String senha = partes[2].split(":")[1];
            return new Usuario(nome, email, senha);
        }
        return null; // Retorna null se a linha não estiver no formato esperado
    }
}
