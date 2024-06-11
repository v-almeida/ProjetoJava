package ProjetoJava.Persistence;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogPersistence {
    private static final String FILE_NAME = "logs.txt";

    public static void salvarLog(String mensagem) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        String conteudo = String.format("%s - %s%n", formattedDateTime, mensagem);

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(conteudo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar log.");
            e.printStackTrace();
        }
    }
}
