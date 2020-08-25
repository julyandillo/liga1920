package ligafx.dao;

import ligafx.modelos.Equipo;

import java.util.List;
import java.util.Map;

public interface EquipoDAO extends Modificable<Equipo> {

    List<String> cargarNombres() throws DAOException;

    Equipo cargar(String nombre) throws DAOException;

    Map<String, String> cargarNombresEscudos() throws DAOException;

}
