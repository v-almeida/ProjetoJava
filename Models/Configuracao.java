package ProjetoJava.Models;

public class Configuracao {
    private double tarifaPorHora;
    private String horarioAbertura;
    private String horarioFechamento;

    
    public Configuracao() {
    }

    
    public Configuracao(double tarifaPorHora, String horarioAbertura, String horarioFechamento) {
        this.tarifaPorHora = tarifaPorHora;
        this.horarioAbertura = horarioAbertura;
        this.horarioFechamento = horarioFechamento;
    }

    public double getTarifaPorHora() {
        return tarifaPorHora;
    }

    public void setTarifaPorHora(double tarifaPorHora) {
        this.tarifaPorHora = tarifaPorHora;
    }

    public String getHorarioAbertura() {
        return horarioAbertura;
    }

    public void setHorarioAbertura(String horarioAbertura) {
        this.horarioAbertura = horarioAbertura;
    }

    public String getHorarioFechamento() {
        return horarioFechamento;
    }

    public void setHorarioFechamento(String horarioFechamento) {
        this.horarioFechamento = horarioFechamento;
    }
}
