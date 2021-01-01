package ligafx.dao;

import ligafx.modelos.Arbitro;

import java.util.List;
import java.util.Map;

public interface ArbitroDAO extends Cargable<Arbitro, Integer> {

    boolean guardar(Arbitro arbitro) throws DAOException;

    Arbitro cargar(String nombre) throws DAOException;

    List<String> cargarNombres() throws DAOException;
}
