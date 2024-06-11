package ProjetoJava.Persistence;

import ProjetoJava.Models.Configuracao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ConfiguracaoPersistence {

    private static final String CONFIG_FILE = "configuracao.txt";

    public Configuracao carregarConfiguracao() {
        Configuracao configuracao = new Configuracao();
        try (Scanner scanner = new Scanner(new java.io.File(CONFIG_FILE))) {
            configuracao.setTarifaPorHora(Double.parseDouble(scanner.nextLine()));
            configuracao.setHorarioAbertura(scanner.nextLine());
            configuracao.setHorarioFechamento(scanner.nextLine());
        } catch (IOException e) {
            System.err.println("Erro ao carregar a configuração: " + e.getMessage());
        }
        return configuracao;
    }

    public boolean salvarConfiguracao(Configuracao configuracao) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CONFIG_FILE))) {
            writer.println(configuracao.getTarifaPorHora());
            writer.println(configuracao.getHorarioAbertura());
            writer.println(configuracao.getHorarioFechamento());
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao salvar a configuração: " + e.getMessage());
            return false;
        }
    }
}

