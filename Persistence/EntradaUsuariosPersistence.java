package ProjetoJava.Persistence;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EntradaUsuariosPersistence {
    private static final String FILE_PATH = "entrada_usuarios.txt";

    public EntradaUsuariosPersistence() {
        criarArquivoSeNaoExistir();
    }

    public void salvarEntradaUsuario(String nome, String email) {
        String dataHoraAtual = getDataHoraAtual();
        String conteudo = String.format("%s - Nome: %s, Email: %s", dataHoraAtual, nome, email);
        List<String> entradasUsuarios = carregarEntradas();
        entradasUsuarios.add(conteudo);
        salvar(entradasUsuarios);
    }

    private void salvar(List<String> entradas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String entrada : entradas) {
                writer.write(entrada);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar as entradas de usuários: " + e.getMessage());
        }
    }

    public List<String> carregarEntradas() {
        List<String> entradasUsuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                entradasUsuarios.add(linha);
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar as entradas de usuários: " + e.getMessage());
        }
        return entradasUsuarios;
    }

    private void criarArquivoSeNaoExistir() {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH, true);
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Erro ao criar o arquivo de entradas de usuários: " + e.getMessage());
        }
    }

    private String getDataHoraAtual() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
