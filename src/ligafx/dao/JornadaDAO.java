package ligafx.dao;

import ligafx.modelos.Jornada;

public interface JornadaDAO {
    Jornada cargar(Integer id) throws DAOException;
}
