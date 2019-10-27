package ligafx.dao;

import ligafx.modelos.Jornada;
import ligafx.mysql.EquipoMysql;
import ligafx.mysql.EstadioMysql;
import ligafx.mysql.JornadaMysql;
import ligafx.mysql.PartidoMysql;

public class DAOManager {

    private static EquipoDAO  equipoDAO = null;

    private static EstadioDAO estadioDAO = null;

    private static PartidoDAO partidoDAO = null;

    private static JornadaDAO jornadaDAO = null;

    private DAOManager() {
        throw new IllegalStateException("Esta clase no puede ser instanciada");
    }

    public static EquipoDAO getEquipoDAO() {
        if (equipoDAO == null) {
            equipoDAO = new EquipoMysql();
        }

        return equipoDAO;
    }

    public static EstadioDAO getEstadioDAO() {
        if (estadioDAO == null) {
            estadioDAO = new EstadioMysql();
        }

        return estadioDAO;
    }

    public static PartidoDAO getPartidoDAO() {
        if (partidoDAO == null) {
            partidoDAO = new PartidoMysql();
        }

        return partidoDAO;
    }

    public static JornadaDAO getJornadaDAO() {
        if (jornadaDAO == null) {
            jornadaDAO = new JornadaMysql();
        }

        return jornadaDAO;
    }
}
