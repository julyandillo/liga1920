package ligafx.dao;

public interface Guardable<T> {

    boolean guardar(T object) throws DAOException;
}
