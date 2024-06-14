package ProjetoJava.Persistence;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogPersistence extends Persistencia<String> {
    private static final String FILE_PATH = "logs.txt";

    public LogPersistence() {
        super(FILE_PATH);
        criarArquivoSeNaoExistir();
    }

    @Override
    public void salvar(List<String> objetos) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (String log : objetos) {
                writer.println(log);
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar o log: " + e.getMessage());
        }
    }

    @Override
    public List<String> carregar() {
        List<String> logs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                logs.add(line);
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar o log: " + e.getMessage());
        }
        return logs;
    }

    public void salvarConteudo(String conteudo) {
        String dataHoraAtual = getDataHoraAtual();
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            writer.println(dataHoraAtual + " - " + conteudo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar conteúdo no log: " + e.getMessage());
        }
    }

    public void logEntradaCarro(String modelo, String placa, String cor) {
        String conteudo = String.format("Entrada de carro - Modelo: %s, Placa: %s, Cor: %s", modelo, placa, cor);
        salvarConteudo(conteudo);
    }

    public void logSaidaCarro(String placa) {
        String conteudo = String.format("Saída de carro - Placa: %s", placa);
        salvarConteudo(conteudo);
    }

    public void logConfiguracao(String alteracao, String usuario) {
        String conteudo = String.format("Configuração alterada - Usuário: %s, Alteração: %s", usuario, alteracao);
        salvarConteudo(conteudo);
    }

    public void logEntradaUsuario(String nome, String email) {
        String conteudo = String.format("Entrada de usuário - Nome: %s, Email: %s", nome, email);
        salvarConteudo(conteudo);
    }

    public void logSaidaUsuario(String email) {
        String conteudo = String.format("Saída de usuário - Email: %s", email);
        salvarConteudo(conteudo);
    }

    public void logRelatorio(String relatorio) {
        String conteudo = "Relatório gerado - " + relatorio;
        salvarConteudo(conteudo);
    }

    private void criarArquivoSeNaoExistir() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Erro ao criar o arquivo de logs: " + e.getMessage());
            }
        }
    }

    private String getDataHoraAtual() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
