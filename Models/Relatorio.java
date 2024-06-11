package ProjetoJava.Models;

public class Relatorio {
    private int totalCarrosEntraram;
    private int totalCarrosSairam;
    private double valorPagamentos;

    public Relatorio() {
        this.totalCarrosEntraram = 0;
        this.totalCarrosSairam = 0;
        this.valorPagamentos = 0.0;
    }

    public void registrarEntrada() {
        totalCarrosEntraram++;
    }

    public void registrarSaida() {
        totalCarrosSairam++;
        valorPagamentos += calcularPagamento(); 
    }

    public int getTotalCarrosEntraram() {
        return totalCarrosEntraram;
    }

    public int getTotalCarrosSairam() {
        return totalCarrosSairam;
    }

    public double getValorPagamentos() {
        return valorPagamentos;
    }

    private double calcularPagamento() {
        
        
        return 10.0; 
    }
}
