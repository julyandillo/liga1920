package ligafx.dao;

import java.util.List;

public interface Cargable<T, K> {

    T cargar(K id) throws DAOException;

    List<T> cargarTodos() throws DAOException;

}
