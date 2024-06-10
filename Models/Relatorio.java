package ProjetoJava.Models;

public class Relatorio {
    private int totalCarrosEntraram;
    private int totalCarrosSairam;
    private double valorPagamentos;

    public void adicionarCarroEntrou() {
        totalCarrosEntraram++;
    }

    public void adicionarCarroSaiu() {
        totalCarrosSairam++;
    }

    public void calcularValorPagamentos() {
        valorPagamentos = totalCarrosSairam * 5.0; // Cada saída custa R$ 5,00
    }

    // Getters
}