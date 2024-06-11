package ProjetoJava.Persistence;

import ProjetoJava.Models.Estacionamento;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EstacionamentoPersistence extends Persistencia<Estacionamento> {
    private static final String FILE_PATH = "estacionamento.txt";

    public EstacionamentoPersistence() {
        super(FILE_PATH);
    }

    @Override
    public void salvar(List<Estacionamento> objetos) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            outputStream.writeObject(objetos);
        } catch (IOException e) {
            System.err.println("Erro ao salvar o estacionamento: " + e.getMessage());
        }
    }

    @Override
    public List<Estacionamento> carregar() {
        List<Estacionamento> estacionamentos = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            Object obj = inputStream.readObject();
            if (obj instanceof List) {
                estacionamentos = (List<Estacionamento>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar o estacionamento: " + e.getMessage());
        }
        return estacionamentos;
    }
}
