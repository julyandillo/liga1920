package ligafx.dao;

import ligafx.decoradores.CambioDecorator;
import ligafx.modelos.Cambio;
import ligafx.modelos.TipoEquipo;

import java.util.List;
import java.util.Map;

public interface CambioDAO extends Cargable<Cambio, Integer> {

    int guardarCambios(List<Cambio> cambios, int idPartido) throws DAOException;

    Map<TipoEquipo, List<CambioDecorator>> cargarTodosPorPartido(int idPartido) throws DAOException;
}
