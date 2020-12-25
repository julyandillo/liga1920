package ligafx.mysql;

import ligafx.builders.PartidoBuilder;
import ligafx.dao.DAOException;
import ligafx.dao.DAOManager;
import ligafx.dao.JornadaDAO;
import ligafx.modelos.Jornada;
import ligafx.modelos.Partido;
import ligafx.util.DBConexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JornadaMysql implements JornadaDAO {

    private static final String CARGAR_JORNADA = "select p.id_partido, p.id_equipo_local, p.goles_local, " +
            "p.id_equipo_visitante, p.goles_visitante, disputado, fecha, a.id_arbitro as arbitro, a2.id_arbitro as var " +
            "from partido p " +
            "left join arbitro a on p.id_arbitro = a.id_arbitro " +
            "left join arbitro a2 on p.id_arbitro_var = a2.id_arbitro " +
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
                PartidoBuilder partidoBuilder = new PartidoBuilder()
                        .id(rs.getInt(1))
                        .disputado(rs.getBoolean(6))
                        .local(DAOManager.getEquipoDAO().cargar(rs.getInt(2)))
                        .visitante(DAOManager.getEquipoDAO().cargar(rs.getInt(4)))
                        .golesLocal(rs.getInt(3))
                        .golesVisitante(rs.getInt(5))
                        .fecha(rs.getTimestamp(7))
                        .arbitro(DAOManager.getArbitroDAO().cargar(rs.getInt("arbitro")))
                        .arbitroVar(DAOManager.getArbitroDAO().cargar(rs.getInt("var")))
                        .jornada(id);

                jornada.getPartidos().add(partidoBuilder.build());
            }

        } catch (SQLException e) {
            throw new DAOException("ERROR al cargar la jornada", e);
        } finally {
            DBConexion.closeResources(ps, rs);
        }

        return jornada;
    }

    @Override
    public List<Jornada> cargarTodos() throws DAOException {
        return new ArrayList<>();
    }


}
