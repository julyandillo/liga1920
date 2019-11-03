package ligafx.mysql;

import ligafx.dao.DAOException;
import ligafx.dao.DAOManager;
import ligafx.dao.PartidoDAO;
import ligafx.modelos.Partido;
import ligafx.util.DBConexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PartidoMysql implements PartidoDAO {

    private static final String GET = "SELECT id_equipo_local, id_equipo_visitante, goles_local, goles_visitante," +
            "fecha, disputado FROM partido WHERE id_partido = ?";

    @Override
    public boolean guardar(Partido object) {
        return false;
    }

    @Override
    public boolean actualizar(Partido object) {
        return false;
    }

    @Override
    public Partido cargar(Integer idPartido) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Partido partido = null;

        try {
            ps = DBConexion.getConexion().prepareStatement(GET);
            rs = ps.executeQuery();

            partido = new Partido(idPartido, DAOManager.getEquipoDAO().cargar(rs.getInt(1)),
                    DAOManager.getEquipoDAO().cargar(rs.getInt(2)), rs.getInt(3),
                    rs.getInt(4), rs.getDate(5), rs.getBoolean(6));

            partido.setGoles(DAOManager.getGolDAO().cargarTodosPorPartido(idPartido, rs.getInt(1),
                    rs.getInt(2)));

        } catch (SQLException ex) {
            throw new DAOException("ERROR AL CARGAR EL PARTIDO", ex);
        } finally {
            DBConexion.closeResources(ps, rs);
        }

        return partido;
    }

    @Override
    public List<Partido> cargarTodos() {
        return new ArrayList<>();
    }

    @Override
    public List<Partido> cargarTodosPorEquipo(Integer idEquipo) {
        return new ArrayList<>();
    }
}
