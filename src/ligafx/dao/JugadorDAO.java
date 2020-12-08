package ligafx.dao;

import ligafx.modelos.Jugador;

import java.util.HashSet;
import java.util.List;

public interface JugadorDAO extends Modificable<Jugador> {

    List<Jugador> cargarPlantilla(int idEquipo) throws DAOException;

    boolean guardar(Jugador jugador, int idEquipo) throws DAOException;

    boolean guardar(List<Jugador> plantilla, int idEquipo) throws DAOException;

    HashSet<Integer> cargarIds() throws DAOException;
}
