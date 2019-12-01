package ligafx.dao;

import ligafx.decoradores.GolDecorator;
import ligafx.modelos.Gol;
import ligafx.modelos.TipoEquipo;

import java.util.EnumMap;
import java.util.List;

public interface GolDAO extends Cargable<Gol, Integer> {

    public EnumMap<TipoEquipo, List<GolDecorator>> cargarTodosPorPartido(Integer idPartido, Integer idEquipoLocal,
                                                                         Integer idEquipoVisitante) throws DAOException;
}
