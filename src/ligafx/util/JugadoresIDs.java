package ligafx.util;

import ligafx.dao.DAOException;
import ligafx.dao.DAOManager;

import java.util.HashSet;
import java.util.logging.Logger;

/**
 * Clase que mantiene un singleton con los ids de todos los jugadores
 * para comprobar si un id de un jugador existe en la base de datos
 */
public class JugadoresIDs {

    private static final Logger LOGGER = Logger.getLogger(JugadoresIDs.class.getName());

    private static JugadoresIDs instance = null;

    private HashSet<Integer> jugadores;

    private JugadoresIDs() {
        try {
            jugadores = DAOManager.getJugadorDAO().cargarIds();
        } catch (DAOException e) {
            LOGGER.severe("ERROR AL CARGAR LOS IDS DE LOS JUGADORES");
        }
    }

    private boolean exists(int idJugador) {
        return jugadores.contains(idJugador);
    }

    private void addId(int idJugador) {
        jugadores.add(idJugador);
    }

    public static boolean check(int idJugador) {
        if (instance == null) {
            instance = new JugadoresIDs();
        }

        return instance.exists(idJugador);
    }

    public static void add(int idJugador) {
        if (instance == null) {
            instance = new JugadoresIDs();
        }

        instance.addId(idJugador);
    }
}
