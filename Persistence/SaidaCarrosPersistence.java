package ProjetoJava.Persistence;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SaidaCarrosPersistence extends Persistencia<String> {
    private static final String FILE_PATH = "saidas_carros.txt";

    public SaidaCarrosPersistence() {
        super(FILE_PATH);
        criarArquivoSeNaoExistir();
    }

    @Override
    public void salvar(List<String> objetos) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (String saida : objetos) {
                writer.println(saida);
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar a saída de carro: " + e.getMessage());
        }
    }

    @Override
    public List<String> carregar() {
        List<String> saidasCarros = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                saidasCarros.add(line);
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar as saídas de carros: " + e.getMessage());
        }
        return saidasCarros;
    }

    public void salvarSaidaCarro(String placa) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime dataHoraSaida = LocalDateTime.now();
        String dataHoraFormatada = dataHoraSaida.format(formatter);
        String saidaCarro = placa + " - " + dataHoraFormatada;
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            writer.println(saidaCarro);
        } catch (IOException e) {
            System.err.println("Erro ao salvar saída de carro: " + e.getMessage());
        }
    }

    private void criarArquivoSeNaoExistir() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Erro ao criar o arquivo de saídas de carros: " + e.getMessage());
            }
        }
    }
}

