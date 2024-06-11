package ProjetoJava.Persistence;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EntradaCarrosPersistence extends Persistencia<String> {
    private static final String FILE_PATH = "entradas_carros.txt";
    public EntradaCarrosPersistence() {
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
            System.err.println("Erro ao salvar as entradas de carros: " + e.getMessage());
        }
    }

    public void salvarEntradaCarro(String modelo, String placa, String cor) {
        String dataHoraAtual = getDataHoraAtual();
        String novaEntrada = String.format("%s - Modelo: %s, Placa: %s, Cor: %s", dataHoraAtual, modelo, placa, cor);
        List<String> entradasCarros = carregar();

        // Verifica se a entrada já existe antes de adicionar
        boolean entradaExistente = false;
        for (String entrada : entradasCarros) {
            if (entrada.contains("Placa: " + placa)) {
                entradaExistente = true;
                break;
            }
        }

        if (!entradaExistente) {
            // Adiciona a nova entrada
            entradasCarros.add(novaEntrada);

            // Salva todas as entradas novamente no arquivo
            salvar(entradasCarros);
        }
    }

    @Override
    public List<String> carregar() {
        List<String> entradasCarros = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                entradasCarros.add(line);
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar as entradas de carros: " + e.getMessage());
        }
        return entradasCarros;
    }

    private void criarArquivoSeNaoExistir() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Erro ao criar o arquivo de entradas de carros: " + e.getMessage());
            }
        }
    }

    private String getDataHoraAtual() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
