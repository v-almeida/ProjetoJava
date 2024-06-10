package ProjetoJava;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Programa {
    public static void main(String[] args) {
        Estacionamento estacionamento = new Estacionamento();
        Relatorio relatorio = new Relatorio();

        // Exemplo de uso:
        Carro carro1 = new Carro("Fiat", "ABC1234", "Preto");
        Carro carro2 = new Carro("Chevrolet", "DEF5678", "Prata");

        if (estacionamento.adicionarCarro(carro1)) {
            System.out.println("Carro adicionado ao estacionamento.");
            relatorio.adicionarCarroEntrou();
        } else {
            System.out.println("Estacionamento lotado.");
        }

        if (estacionamento.adicionarCarro(carro2)) {
            System.out.println("Carro adicionado ao estacionamento.");
            relatorio.adicionarCarroEntrou();
        } else {
            System.out.println("Estacionamento lotado.");
        }

        estacionamento.liberarVaga(carro1);
        relatorio.adicionarCarroSaiu();

        // Gerar relatório e escrever em arquivo
        relatorio.calcularValorPagamentos();
        gerarRelatorio(relatorio);
    }

    public static void gerarRelatorio(Relatorio relatorio) {
        try (FileWriter writer = new FileWriter("relatorio.txt")) {
            writer.write("Total de carros que entraram: " + relatorio.getTotalCarrosEntraram() + "\n");
            writer.write("Total de carros que saíram: " + relatorio.getTotalCarrosSairam() + "\n");
            writer.write("Valor de pagamentos: R$ " + relatorio.getValorPagamentos() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}