package ligafx.dao;

import java.util.List;

public interface Cargable<T, K> {

    T cargar(K id);

    List<T> cargarTodos();

}
