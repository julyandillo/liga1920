package ligafx.dao;

public interface Modificable<T> extends Cargable<T, Integer>{

    boolean guardar(T object) throws DAOException;

    boolean actualizar(T object) throws DAOException;
}
