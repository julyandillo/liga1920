package ligafx.dao;

public interface Modificable<T> {

    boolean guardar(T object);

    boolean actualizar(T object);
}
