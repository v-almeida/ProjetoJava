package ProjetoJava.Persistence;

import java.io.FileWriter;
import java.io.IOException;

public class RelatoriosAntigosPersistence {
    private static final String FILE_NAME = "relatorios_antigos.txt";

    public static void salvarRelatorioAntigo(String conteudoRelatorio) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(conteudoRelatorio);
        } catch (IOException e) {
            System.err.println("Erro ao salvar relatório antigo.");
            e.printStackTrace();
        }
    }
}
