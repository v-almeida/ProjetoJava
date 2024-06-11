package ProjetoJava.Persistence;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EntradaCarrosPersistence {
    private static final String FILE_NAME = "entrada_carros.txt";

    public static void salvarEntradaCarro(String modelo, String placa, String cor) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        String conteudo = String.format("%s - Modelo: %s, Placa: %s, Cor: %s%n", formattedDateTime, modelo, placa, cor);

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(conteudo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar dados de entrada de carro.");
            e.printStackTrace();
        }
    }
}
