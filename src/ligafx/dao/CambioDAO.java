package ligafx.dao;

import ligafx.modelos.Cambio;

import java.util.List;

public interface CambioDAO extends Cargable<Cambio, Integer> {

    int guardarCambios(List<Cambio> cambios, int idPartido) throws DAOException;
}
