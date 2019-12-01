package ligafx.mysql;

import ligafx.builders.GolBuilder;
import ligafx.dao.DAOException;
import ligafx.dao.GolDAO;
import ligafx.decoradores.GolDecorator;
import ligafx.modelos.Gol;
import ligafx.modelos.TipoEquipo;
import ligafx.util.DBConexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class GolMysql implements GolDAO {

    private static final String GOLES_POR_PARTIDO = "select g.id_gol, g.minuto, g.penalti, g.propia_meta, j.apodo, " +
            "j.id_equipo " +
            "from gol g " +
            "inner join jugador j on j.id_jugador=g.id_jugador " +
            "where g.id_partido = ?";

    @Override
    public EnumMap<TipoEquipo, List<GolDecorator>> cargarTodosPorPartido(Integer idPartido, Integer idEquipoLocal,
                                                                         Integer idEquipoVisitante) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        EnumMap<TipoEquipo, List<GolDecorator>> goles = new EnumMap<>(TipoEquipo.class);
        goles.put(TipoEquipo.LOCAL, new ArrayList<>());
        goles.put(TipoEquipo.VISITANTE, new ArrayList<>());

        try {
            ps = DBConexion.getConexion().prepareStatement(GOLES_POR_PARTIDO);
            rs = ps.executeQuery();

            while (rs.next()) {
                TipoEquipo equipo = rs.getInt(6) == idEquipoLocal ? TipoEquipo.LOCAL : TipoEquipo.VISITANTE;

                goles.get(equipo).add(new GolDecorator(new GolBuilder().id(rs.getInt(1))
                        .minuto(rs.getInt(2))
                        .penalti(rs.getBoolean(3))
                        .propiaMeta(rs.getBoolean(4))
                        .build()
                        , rs.getString(5)));
            }

        } catch (SQLException e) {
            throw new DAOException("ERROR AL CARGAR LOS GOLES DEL PARTIDO", e);
        } finally {
            DBConexion.closeResources(ps, rs);
        }

        return goles;
    }

    @Override
    public Gol cargar(Integer id) throws DAOException {
        return null;
    }

    @Override
    public List<Gol> cargarTodos() throws DAOException {
        return new ArrayList<>();
    }
}
