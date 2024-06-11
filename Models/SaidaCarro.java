package ProjetoJava.Models;

import java.io.Serializable;

public class SaidaCarro implements Serializable {
    private String placa;
    private String dataHoraSaida;

    public SaidaCarro(String placa, String dataHoraSaida) {
        this.placa = placa;
        this.dataHoraSaida = dataHoraSaida;
    }

    public String getPlaca() {
        return placa;
    }

    public String getDataHoraSaida() {
        return dataHoraSaida;
    }
}
