package ligafx.mysql;

import ligafx.dao.DAOException;
import ligafx.dao.DAOManager;
import ligafx.dao.JornadaDAO;
import ligafx.modelos.Jornada;
import ligafx.modelos.Partido;
import ligafx.util.DBConexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JornadaMysql implements JornadaDAO {

    private static final String CARGAR_JORNADA = "select p.id_partido, p.id_equipo_local, p.goles_local, " +
            "p.id_equipo_visitante, p.goles_visitante, disputado, fecha " +
            "from partido p " +
            "where id_jornada = ?";

    @Override
    public Jornada cargar(Integer id) throws DAOException {
        Jornada jornada = new Jornada(id);

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = DBConexion.getConexion().prepareStatement(CARGAR_JORNADA);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                Partido partido = new Partido(rs.getInt(1));
                partido.setDisputado(rs.getBoolean(6));
                partido.setEquipoLocal(DAOManager.getEquipoDAO().cargar(rs.getInt(2)));
                partido.setEquipoVisitante(DAOManager.getEquipoDAO().cargar(rs.getInt(4)));
                partido.setFecha(rs.getTimestamp(7));
                partido.setGolesLocal(rs.getInt(3));
                partido.setGolesVisitante(rs.getInt(5));

                jornada.getPartidos().add(partido);
            }

        } catch (SQLException e) {
            throw new DAOException("ERROR al cargar la jornada", e);
        } finally {
            DBConexion.closeResources(ps, rs);
        }

        return jornada;
    }
}
