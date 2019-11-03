package ligafx.dao;

import ligafx.decoradores.GolDecorator;
import ligafx.modelos.Gol;
import ligafx.modelos.TipoEquipo;

import java.util.List;
import java.util.Map;

public interface GolDAO extends Cargable<Gol, Integer> {

    public Map<TipoEquipo, List<GolDecorator>> cargarTodosPorPartido(Integer idPartido, Integer idEquipoLocal,
                                                                     Integer idEquipoVisitante) throws DAOException;
}
