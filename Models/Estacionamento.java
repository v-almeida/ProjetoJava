package ProjetoJava.Models;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
    private List<Carro> vagas;
    private final int capacidadeMax = 10; 

    public Estacionamento() {
        this.vagas = new ArrayList<>();
    }

   
    public boolean adicionarCarro(Carro carro) {
        if (vagas.size() < capacidadeMax) {
            vagas.add(carro);
            return true;
        } else {
            return false; 
        }
    }

    
    public Carro buscarCarroPorPlaca(String placa) {
        for (Carro carro : vagas) {
            if (carro.getPlaca().equals(placa)) {
                return carro;
            }
        }
        return null; 
    }

    
    public int getTotalCarrosEstacionados() {
        return vagas.size();
    }

   
    public void liberarVaga(Carro carro) {
        vagas.remove(carro);
    }

    
    public boolean temVaga() {
        return vagas.size() < capacidadeMax;
    }

     
    public boolean carroEstacionado(String placaSaida) {
        return vagas.stream().anyMatch(carro -> carro.getPlaca().equals(placaSaida));
    }

    
    public void removerCarro(String placaSaida) {
        vagas.removeIf(carro -> carro.getPlaca().equals(placaSaida));
    }
}