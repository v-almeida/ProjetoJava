package ProjetoJava.Persistence;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SaidaCarrosPersistence {
    private static final String FILE_NAME = "saida_carros.txt";

    public static void salvarSaidaCarro(String placa) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        String conteudo = String.format("%s - Placa: %s%n", formattedDateTime, placa);

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(conteudo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados de saída de carro.");
            e.printStackTrace();
        }
    }
}
