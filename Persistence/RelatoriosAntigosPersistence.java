package ProjetoJava.Persistence;

public class RelatoriosAntigosPersistence extends Persistencia {
    public RelatoriosAntigosPersistence() {
        super("relatorios_antigos.txt");
    }

    @Override
    public void salvarConteudo(String conteudo) {
        salvar(conteudo);
    }
}
