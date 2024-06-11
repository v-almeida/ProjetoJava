package ProjetoJava.Persistence;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Persistencia {
    protected String fileName;

    public Persistencia(String fileName) {
        this.fileName = fileName;
    }

    protected void salvar(String conteudo) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(conteudo);
        } catch (IOException e) {
            System.err.println("Erro ao salvar no arquivo: " + fileName);
            e.printStackTrace();
        }
    }

    public abstract void salvarConteudo(String conteudo);
}
