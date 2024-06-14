package ProjetoJava.Controllers;

import ProjetoJava.Models.Carro;
import ProjetoJava.Models.Configuracao;
import ProjetoJava.Models.Estacionamento;
import ProjetoJava.Models.Relatorio;
import ProjetoJava.Models.Usuario;
import ProjetoJava.Persistence.ConfiguracaoPersistence;
import ProjetoJava.Persistence.EntradaCarrosPersistence;
import ProjetoJava.Persistence.SaidaCarrosPersistence;
import ProjetoJava.Persistence.RelatoriosAntigosPersistence;
import ProjetoJava.Persistence.LogPersistence;
import ProjetoJava.Persistence.UsuarioPersistence;
import ProjetoJava.Persistence.EntradaUsuariosPersistence;
import ProjetoJava.Persistence.SaidaUsuariosPersistence;

import java.io.FileWriter;
import java.io.IOException;

public class EstacionamentoController {
    private Estacionamento estacionamento;
    private Relatorio relatorio;
    private EntradaCarrosPersistence entradaCarrosPersistence;
    private SaidaCarrosPersistence saidaCarrosPersistence;
    private RelatoriosAntigosPersistence relatoriosAntigosPersistence;
    private LogPersistence logPersistence;
    private UsuarioPersistence usuarioPersistence;
    private ConfiguracaoPersistence configuracaoPersistence;
    private EntradaUsuariosPersistence entradaUsuariosPersistence;
    private SaidaUsuariosPersistence saidaUsuariosPersistence;

    public EstacionamentoController(Configuracao configuracao) {
        this.estacionamento = new Estacionamento();
        this.relatorio = new Relatorio();
        this.entradaCarrosPersistence = new EntradaCarrosPersistence();
        this.saidaCarrosPersistence = new SaidaCarrosPersistence();
        this.relatoriosAntigosPersistence = new RelatoriosAntigosPersistence();
        this.logPersistence = new LogPersistence();
        this.usuarioPersistence = new UsuarioPersistence();
        this.configuracaoPersistence = new ConfiguracaoPersistence();
        this.entradaUsuariosPersistence = new EntradaUsuariosPersistence();
        this.saidaUsuariosPersistence = new SaidaUsuariosPersistence();
        this.configuracaoPersistence.salvarConfiguracao(configuracao); // Salva a configuração no construtor
    }

    public boolean registrarEntrada(String modelo, String placa, String cor) {
        if (estacionamento.temVaga()) {
            Carro carro = new Carro(modelo, placa, cor);
            estacionamento.adicionarCarro(carro);
            relatorio.registrarEntrada();
            entradaCarrosPersistence.salvarEntradaCarro(modelo, placa, cor);
            logPersistence.logEntradaCarro(modelo, placa, cor);
            return true;
        } else {
            logPersistence.salvarConteudo("Tentativa de entrada falhou, estacionamento lotado.");
            return false;
        }
    }

    public boolean registrarSaida(String placa) {
        if (estacionamento.carroEstacionado(placa)) {
            estacionamento.removerCarro(placa);
            relatorio.registrarSaida();
            saidaCarrosPersistence.salvarSaidaCarro(placa);
            logPersistence.logSaidaCarro(placa);
            return true;
        } else {
            logPersistence.salvarConteudo("Tentativa de saída falhou, carro com placa: " + placa + " não encontrado.");
            return false;
        }
    }

    public String gerarRelatorio() {
        String relatorioGerado = String.format(
            "Total de carros que entraram: %d%n" +
            "Total de carros que saíram: %d%n" +
            "Valor de pagamentos: R$ %.2f%n",
            relatorio.getTotalCarrosEntraram(),
            relatorio.getTotalCarrosSairam(),
            relatorio.getValorPagamentos()
        );
        logPersistence.logRelatorio(relatorioGerado);
        return relatorioGerado;
    }

    public void salvarRelatorio(String conteudoRelatorio) {
        relatoriosAntigosPersistence.salvarConteudo(conteudoRelatorio);
        try (FileWriter writer = new FileWriter("relatorio.txt")) {
            writer.write(conteudoRelatorio);
            System.out.println("Relatório salvo com sucesso em 'relatorio.txt'.");
            logPersistence.salvarConteudo("Relatório gerado e salvo.");
        } catch (IOException e) {
            System.err.println("Ocorreu um erro ao salvar o relatório.");
            logPersistence.salvarConteudo("Erro ao salvar o relatório.");
            e.printStackTrace();
        }
    }

    public boolean cadastrarUsuario(String nome, String email, String senha) {
        Usuario novoUsuario = new Usuario(nome, email, senha);
        boolean sucesso = usuarioPersistence.salvarUsuario(novoUsuario);
        if (sucesso) {
            entradaUsuariosPersistence.salvarEntradaUsuario(nome, email);
            logPersistence.logEntradaUsuario(nome, email);
        }
        return sucesso;
    }

    public boolean removerUsuario(String email) {
        boolean sucesso = usuarioPersistence.removerUsuario(email);
        if (sucesso) {
            saidaUsuariosPersistence.salvarSaidaUsuario(email);
            logPersistence.logSaidaUsuario(email);
        }
        return sucesso;
    }

    public boolean setConfiguracao(Configuracao configuracao) {
        boolean sucesso = configuracaoPersistence.salvarConfiguracao(configuracao);
        if (sucesso) {
            logPersistence.logConfiguracao(configuracao.toString(), "admin"); // Supondo que o usuário atual é "admin"
        }
        return sucesso;
    }

    public Configuracao getConfiguracao() {
        return configuracaoPersistence.carregarConfiguracao();
    }
}
