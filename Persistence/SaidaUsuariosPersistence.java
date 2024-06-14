package ProjetoJava.Persistence;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaidaUsuariosPersistence {
    private static final String FILE_PATH = "saida_usuarios.txt";

    public SaidaUsuariosPersistence() {
        criarArquivoSeNaoExistir();
    }

    public void salvarSaidaUsuario(String email) {
        String dataHoraAtual = getDataHoraAtual();
        String conteudo = String.format("%s - Email: %s", dataHoraAtual, email);
        List<String> saidasUsuarios = carregarSaidas();
        saidasUsuarios.add(conteudo);
        salvar(saidasUsuarios);
    }

    private void salvar(List<String> saidas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String saida : saidas) {
                writer.write(saida);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar as saídas de usuários: " + e.getMessage());
        }
    }

    public List<String> carregarSaidas() {
        List<String> saidasUsuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                saidasUsuarios.add(linha);
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar as saídas de usuários: " + e.getMessage());
        }
        return saidasUsuarios;
    }

    private void criarArquivoSeNaoExistir() {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH, true);
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Erro ao criar o arquivo de saídas de usuários: " + e.getMessage());
        }
    }

    private String getDataHoraAtual() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}