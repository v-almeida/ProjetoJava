package ProjetoJava.Controllers;


import java.io.FileWriter;
import java.io.IOException;
import ProjetoJava.Models.Carro;
import ProjetoJava.Models.Estacionamento;
import ProjetoJava.Models.Relatorio;

public class EstacionamentoController {
    private Estacionamento estacionamento;
    private Relatorio relatorio;

    public EstacionamentoController() {
        this.estacionamento = new Estacionamento();
        this.relatorio = new Relatorio();
    }

    public boolean registrarEntrada(String modelo, String placa, String cor) {
        if (estacionamento.temVaga()) {
            Carro carro = new Carro(modelo, placa, cor);
            estacionamento.adicionarCarro(carro);
            relatorio.registrarEntrada();
            return true;
        } else {
            return false;
        }
    }

    public boolean registrarSaida(String placa) {
        if (estacionamento.carroEstacionado(placa)) {
            estacionamento.removerCarro(placa);
            relatorio.registrarSaida();
            return true;
        } else {
            return false;
        }
    }

    public String gerarRelatorio() {
        return String.format(
            "Total de carros que entraram: %d%n" +
            "Total de carros que saíram: %d%n" +
            "Valor de pagamentos: R$ %.2f%n",
            relatorio.getTotalCarrosEntraram(),
            relatorio.getTotalCarrosSairam(),
            relatorio.getValorPagamentos()
        );
    }

    public void salvarRelatorio(String conteudoRelatorio) {
        try (FileWriter writer = new FileWriter("relatorio.txt")) {
            writer.write(conteudoRelatorio);
            System.out.println("Relatório salvo com sucesso em 'relatorio.txt'.");
        } catch (IOException e) {
            System.err.println("Ocorreu um erro ao salvar o relatório.");
            e.printStackTrace();
        }
    }
}
