package ligafx.dao;

import ligafx.modelos.Equipo;

import java.util.List;

public interface EquipoDAO extends Cargable <Equipo, Integer> {

    List<String> cargarNombres() throws DAOException;

    Equipo cargar(String nombre) throws DAOException;
}
