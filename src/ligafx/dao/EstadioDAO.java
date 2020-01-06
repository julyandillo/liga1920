package ligafx.dao;

import ligafx.modelos.Estadio;

public interface EstadioDAO extends Modificable <Estadio> {

    Estadio cargarPorIdEquipo(int idEquipo) throws DAOException;

}
