package ligafx.mysql;

import ligafx.builders.PartidoBuilder;
import ligafx.dao.DAOException;
import ligafx.dao.DAOManager;
import ligafx.dao.PartidoDAO;
import ligafx.modelos.Partido;
import ligafx.util.DBConexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartidoMysql implements PartidoDAO {

    private static final String GET = "SELECT id_equipo_local, id_equipo_visitante, goles_local, goles_visitante," +
            "fecha, disputado, id_jornada FROM partido WHERE id_partido = ?";

    private static final String BUSCAR = "select id_partido " +
            "from partido p " +
            "inner join equipo el on el.id_equipo = p.id_equipo_local and el.nombre = ? " +
            "inner join equipo ev on ev.id_equipo = p.id_equipo_visitante and ev.nombre = ? ";

    private static final String ACTUALIZA = "UPDATE partido SET goles_local=?, goles_visitante=?, " +
            "fecha=?, disputado=1, id_arbitro=?, id_arbitro_var=?, asistencia=? WHERE id_partido=?";

    @Override
    public boolean guardar(Partido object) {
        return false;
    }

    @Override
    public boolean actualizar(Partido partido) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = DBConexion.getConexion().prepareStatement(ACTUALIZA);
            ps.setInt(1, partido.getGolesLocal());
            ps.setInt(2, partido.getGolesVisitante());
            ps.setTimestamp(3, new Timestamp(partido.getFecha().getTime()));
            ps.setInt(4, partido.getArbitro().getId());
            ps.setInt(5, partido.getArbitroVar().getId());
            ps.setInt(6, partido.getAsistencia());
            ps.setInt(7, partido.getId());

            ps.executeUpdate();

        } catch (SQLException ex) {
            throw new DAOException("ERROR AL ACTUALIZAR EL PARTIDO", ex);
        } finally {
            DBConexion.closeResources(ps, rs);
        }

        return true;
    }

    @Override
    public Partido cargar(Integer idPartido) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Partido partido = null;

        try {
            ps = DBConexion.getConexion().prepareStatement(GET);
            ps.setInt(1, idPartido);
            rs = ps.executeQuery();

            if (rs.next()) {
                partido = new PartidoBuilder()
                        .id(idPartido)
                        .local(DAOManager.getEquipoDAO().cargar(rs.getInt(1)))
                        .visitante(DAOManager.getEquipoDAO().cargar(rs.getInt(2)))
                        .golesLocal(rs.getInt(3))
                        .golesVisitante(rs.getInt(4))
                        .fecha(rs.getTimestamp(5))
                        .disputado(rs.getBoolean(6))
                        .goles(DAOManager.getGolDAO().cargarTodosPorPartido(idPartido, rs.getInt(1),
                                rs.getInt(2)))
                        .jornada(rs.getInt(7))
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
    public Partido buscar(String local, String visitante) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Partido partido = null;

        try {
            ps = DBConexion.getConexion().prepareStatement(BUSCAR);
            ps.setString(1, local);
            ps.setString(2, visitante);

            rs = ps.executeQuery();

            if (rs.next()) {
                partido = this.cargar(rs.getInt(1));
            }

        } catch (SQLException ex) {
            throw new DAOException("ERROR AL BUSCAR EL PARTIDO", ex);
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
