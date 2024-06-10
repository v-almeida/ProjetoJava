import java.util.ArrayList;
import java.util.List;

public class Estacionamento {
    private List<Carro> vagas;

    public Estacionamento() {
        this.vagas = new ArrayList<>();
    }

    public boolean adicionarCarro(Carro carro) {
        if (vagas.size() < 10) {
            vagas.add(carro);
            return true;
        } else {
            return false; // Estacionamento lotado
        }
    }

    public Carro buscarCarroPorPlaca(String placa) {
        for (Carro carro : vagas) {
            if (carro.getPlaca().equals(placa)) {
                return carro;
            }
        }
        return null; // Carro não encontrado
    }

    public int getTotalCarrosEstacionados() {
        return vagas.size();
    }

    public void liberarVaga(Carro carro) {
        vagas.remove(carro);
    }
}