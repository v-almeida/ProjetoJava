package ProjetoJava;

import ProjetoJava.Controllers.EstacionamentoController;
import ProjetoJava.Views.EstacionamentoView;

public class Programa {
    public static void main(String[] args) {
        EstacionamentoController controller = new EstacionamentoController();
        EstacionamentoView view = new EstacionamentoView(controller);
        view.exibirMenu();
    }
}
