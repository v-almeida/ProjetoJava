package ProjetoJava.Persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RelatoriosAntigosPersistence extends Persistencia<String> {
    private static final String FILE_PATH = "relatorios_antigos.txt";

    public RelatoriosAntigosPersistence() {
        super(FILE_PATH);
        criarArquivoSeNaoExistir();
    }

    @Override
    public void salvar(List<String> objetos) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (String conteudo : objetos) {
                writer.println(conteudo);
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar os relatórios antigos: " + e.getMessage());
        }
    }

    public void salvarConteudo(String conteudo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            writer.println(conteudo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar conteúdo: " + e.getMessage());
        }
    }

    @Override
    public List<String> carregar() {
        List<String> relatoriosAntigos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                relatoriosAntigos.add(line);
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar os relatórios antigos: " + e.getMessage());
        }
        return relatoriosAntigos;
    }

    private void criarArquivoSeNaoExistir() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Erro ao criar o arquivo de relatórios antigos: " + e.getMessage());
            }
        }
    }
}
