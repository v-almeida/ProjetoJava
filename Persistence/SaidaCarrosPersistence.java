package ProjetoJava.Persistence;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SaidaCarrosPersistence extends Persistencia {
    public SaidaCarrosPersistence() {
        super("saida_carros.txt");
    }

    @Override
    public void salvarConteudo(String conteudo) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        String registro = String.format("%s - %s%n", formattedDateTime, conteudo);
        salvar(registro);
    }

    public void salvarSaidaCarro(String placa) {
        String conteudo = String.format("Placa: %s", placa);
        salvarConteudo(conteudo);
    }
}
