package ProjetoJava.Models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import ProjetoJava.Models.Carro;

public class Persistence {
    private static final String ARQUIVO_CARROS = "carros.txt";
    private static final String ARQUIVO_VAGAS = "vagas.txt";

    public void salvarCarros(List<Carro> carros) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_CARROS))) {
            for (Carro carro : carros) {
                writer.write(carro.getPlaca() + "," + carro.getModelo());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Carro> carregarCarros() {
        List<Carro> carros = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_CARROS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 2) {
                    carros.add(new Carro(partes[0], partes[1], linha));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return carros;
    }

    public void salvarVagas(List<Vaga> vagas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_VAGAS))) {
            for (Vaga vaga : vagas) {
                writer.write(vaga.getNumero() + "," + vaga.isDisponivel());
                if (!vaga.isDisponivel() && vaga.getNumero() != null) {

                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Vaga> carregarVagas(List<Carro> carros) {
        List<Vaga> vagas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_VAGAS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length >= 2) {
                    int numero = Integer.parseInt(partes[0]);
                    boolean disponivel = Boolean.parseBoolean(partes[1]);
                    Vaga vaga = new Vaga(numero);
                    if (!disponivel && partes.length == 4) {
                        String placa = partes[2];
                        String modelo = partes[3];
                        Carro carro = new Carro(placa, modelo, modelo);
                        vaga.estacionarCarro(carro);
                        carros.add(carro);
                    }
                    vagas.add(vaga);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vagas;
    }
}
