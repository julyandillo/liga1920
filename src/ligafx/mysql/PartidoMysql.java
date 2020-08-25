package ligafx.mysql;

import ligafx.builders.PartidoBuilder;
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

            if (rs.next()) {
                partido = new PartidoBuilder()
                        .id(idPartido)
                        .local(DAOManager.getEquipoDAO().cargar(rs.getInt(1)))
                        .visitante(DAOManager.getEquipoDAO().cargar(rs.getInt(2)))
                        .golesLocal(rs.getInt(3))
                        .golesVisitante(rs.getInt(4))
                        .fecha(rs.getDate(5))
                        .disputado(rs.getBoolean(6))
                        .goles(DAOManager.getGolDAO().cargarTodosPorPartido(idPartido, rs.getInt(1),
                                rs.getInt(2)))
                        .build();
            }

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
