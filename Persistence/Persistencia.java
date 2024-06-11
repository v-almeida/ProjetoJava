package ProjetoJava.Persistence;

import java.util.List;

public abstract class Persistencia<T> {
    public Persistencia(String string) {
        //TODO Auto-generated constructor stub
    }
    public abstract void salvar(List<T> objetos);
    public abstract List<T> carregar();
}
