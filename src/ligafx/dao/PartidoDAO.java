package ligafx.dao;

import ligafx.modelos.Partido;

import java.util.List;

public interface PartidoDAO extends Modificable<Partido>, Cargable<Partido, Integer> {

    List<Partido> cargarTodosPorEquipo(Integer idEquipo) throws DAOException;

    Partido buscar(String local, String visitante) throws DAOException;
}
