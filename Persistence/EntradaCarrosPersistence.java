package ProjetoJava.Persistence;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EntradaCarrosPersistence extends Persistencia {
    public EntradaCarrosPersistence() {
        super("entrada_carros.txt");
    }

    @Override
    public void salvarConteudo(String conteudo) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        String registro = String.format("%s - %s%n", formattedDateTime, conteudo);
        salvar(registro);
    }

    public void salvarEntradaCarro(String modelo, String placa, String cor) {
        String conteudo = String.format("Modelo: %s, Placa: %s, Cor: %s", modelo, placa, cor);
        salvarConteudo(conteudo);
    }
}
