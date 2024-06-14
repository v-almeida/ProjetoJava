package ProjetoJava.Persistence;

import ProjetoJava.Models.Usuario;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UsuarioPersistence extends Persistencia<Usuario> {
    private static final String FILE_PATH = "usuarios.txt";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public UsuarioPersistence() {
        super(FILE_PATH);
        criarArquivoSeNaoExistir();
    }

    @Override
    public void salvar(List<Usuario> objetos) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Usuario usuario : objetos) {
                writer.println(formatarUsuario(usuario));
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar o usuário: " + e.getMessage());
        }
    }

    @Override
    public List<Usuario> carregar() {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                try {
                    usuarios.add(parseLinhaParaUsuario(linha));
                } catch (IllegalArgumentException e) {
                    System.err.println("Erro ao analisar a linha: " + linha + ". Detalhes: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar os usuários: " + e.getMessage());
        }
        return usuarios;
    }

    
    public boolean salvarUsuario(Usuario usuario) {
        List<Usuario> usuarios = carregar();
        usuarios.add(usuario);
        salvar(usuarios);
        return true;
    }


    public boolean removerUsuario(String email) {
        List<Usuario> usuarios = carregar();
        boolean encontrado = usuarios.removeIf(usuario -> usuario.getEmail().equals(email));
        salvar(usuarios);
        return encontrado;
    }


    private String formatarUsuario(Usuario usuario) {
        return "nome:" + usuario.getNome() +
                " email:" + usuario.getEmail() +
                " senha:" + usuario.getSenha() +
                " data_hora:" + LocalDateTime.now().format(DATE_FORMATTER);
    }


    private Usuario parseLinhaParaUsuario(String linha) {
        String[] partes = linha.split(" ");
        if (partes.length >= 4) { // Garantir que haja pelo menos 4 partes (nome, email, senha e data_hora)
            String nome = partes[0].split(":")[1];
            String email = partes[1].split(":")[1];
            String senha = partes[2].split(":")[1];
            // Podem haver campos adicionais, então vamos ignorar os últimos campos
            return new Usuario(nome, email, senha);
        }
        return null;
    }

    private void criarArquivoSeNaoExistir() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Erro ao criar o arquivo de usuários: " + e.getMessage());
            }
        }
    }
}
