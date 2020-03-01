package ligafx.dao;

public interface Modificable<T> extends Cargable<T, Integer>, Guardable<T>{

    boolean actualizar(T object) throws DAOException;
}
