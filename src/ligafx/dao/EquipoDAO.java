package ligafx.dao;

import ligafx.modelos.Equipo;

import java.util.List;

public interface EquipoDAO extends Modificable<Equipo> {

    List<String> cargarNombres() throws DAOException;

    Equipo cargar(String nombre) throws DAOException;
}
