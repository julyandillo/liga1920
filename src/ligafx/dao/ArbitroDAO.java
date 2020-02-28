package ligafx.dao;

import ligafx.modelos.Arbitro;

public interface ArbitroDAO extends Cargable<Arbitro, Integer> {

    boolean guardar(Arbitro arbitro) throws DAOException;

    Arbitro cargar(String nombre) throws DAOException;
}
