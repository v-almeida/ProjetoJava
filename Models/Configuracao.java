package ProjetoJava.Models;

public class Configuracao {
    private double tarifaPorHora;
    private String horarioAbertura;
    private String horarioFechamento;

    public Configuracao(double tarifaPorHora, String horarioAbertura, String horarioFechamento) {
        this.tarifaPorHora = tarifaPorHora;
        this.horarioAbertura = horarioAbertura;
        this.horarioFechamento = horarioFechamento;
    }

    public double getTarifaPorHora() {
        return tarifaPorHora;
    }

    public String getHorarioAbertura() {
        return horarioAbertura;
    }

    public String getHorarioFechamento() {
        return horarioFechamento;
    }
}
