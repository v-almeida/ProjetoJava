package ProjetoJava.Controllers;

import java.io.FileWriter;
import java.io.IOException;
import ProjetoJava.Models.Carro;
import ProjetoJava.Models.Estacionamento;
import ProjetoJava.Models.Relatorio;
import ProjetoJava.Persistence.EntradaCarrosPersistence;
import ProjetoJava.Persistence.SaidaCarrosPersistence;
import ProjetoJava.Persistence.RelatoriosAntigosPersistence;
import ProjetoJava.Persistence.LogPersistence;

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
            EntradaCarrosPersistence.salvarEntradaCarro(modelo, placa, cor);
            LogPersistence.salvarLog("Entrada registrada para o carro com placa: " + placa);
            return true;
        } else {
            LogPersistence.salvarLog("Tentativa de entrada falhou, estacionamento lotado.");
            return false;
        }
    }

    public boolean registrarSaida(String placa) {
        if (estacionamento.carroEstacionado(placa)) {
            estacionamento.removerCarro(placa);
            relatorio.registrarSaida();
            SaidaCarrosPersistence.salvarSaidaCarro(placa);
            LogPersistence.salvarLog("Saída registrada para o carro com placa: " + placa);
            return true;
        } else {
            LogPersistence.salvarLog("Tentativa de saída falhou, carro com placa: " + placa + " não encontrado.");
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
        RelatoriosAntigosPersistence.salvarRelatorioAntigo(conteudoRelatorio);
        try (FileWriter writer = new FileWriter("relatorio.txt")) {
            writer.write(conteudoRelatorio);
            System.out.println("Relatório salvo com sucesso em 'relatorio.txt'.");
            LogPersistence.salvarLog("Relatório gerado e salvo.");
        } catch (IOException e) {
            System.err.println("Ocorreu um erro ao salvar o relatório.");
            LogPersistence.salvarLog("Erro ao salvar o relatório.");
            e.printStackTrace();
        }
    }
}
