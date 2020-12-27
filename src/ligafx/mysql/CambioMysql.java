package ligafx.mysql;

import ligafx.builders.CambioBuilder;
import ligafx.dao.CambioDAO;
import ligafx.dao.DAOException;
import ligafx.decoradores.CambioDecorator;
import ligafx.modelos.Cambio;
import ligafx.modelos.TipoEquipo;
import ligafx.util.DBConexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CambioMysql implements CambioDAO {

    private static final String GUARDAR = "INSERT INTO cambio (minuto, id_partido, id_jugador_sale, id_jugador_entra) " +
            "VALUES (?, ?, ?, ?)";

    private static final String CARGAR = "SELECT minuto, id_partido, id_jugador_sale, id_jugador_sale FROM cambio " +
            "WHERE id_cambio = ?";

    private static final String CARGAR_POR_PARTIDO = "select c.minuto, j.apodo as sale, j2.apodo as entra, " +
            "case when p.id_equipo_local = j.id_equipo then 'LOCAL' else 'VISITANTE' end as equipo " +
            "from cambio c " +
            "inner join jugador j on c.id_jugador_sale = j.id_jugador " +
            "inner join jugador j2 on j2.id_jugador = c.id_jugador_entra " +
            "inner join partido p on p.id_partido = c.id_partido " +
            "where c.id_partido = ?";

    @Override
    public int guardarCambios(List<Cambio> cambios, int idPartido) throws DAOException {
        int filasInsertadas = 0;

        for (Cambio cambio : cambios) {
            if (guardar(cambio, idPartido)) {
                filasInsertadas++;
            }
        }

        return filasInsertadas;
    }

    public boolean guardar(Cambio cambio, int idPartido) throws DAOException {
        PreparedStatement ps = null;
        int filasInsertadas = 0;

        try {
            ps = DBConexion.getConexion().prepareStatement(GUARDAR);
            ps.setInt(1, cambio.getMinuto());
            ps.setInt(2, idPartido);
            ps.setInt(3, cambio.getIdJugadorSale());
            ps.setInt(4, cambio.getIdJugadorEntra());

            filasInsertadas = ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("ERROR AL GUARDAR UN CAMBIO", GUARDAR, e);
        } finally {
            DBConexion.closeResources(ps, null);
        }

        return filasInsertadas == 1;
    }

    @Override
    public Cambio cargar(Integer id) throws DAOException {
        return null;
    }

    @Override
    public List<Cambio> cargarTodos() throws DAOException {
        return Collections.emptyList();
    }

    @Override
    public Map<TipoEquipo, List<CambioDecorator>> cargarTodosPorPartido(int idPartido) throws DAOException {
        Map<TipoEquipo, List<CambioDecorator>> cambios = new EnumMap<>(TipoEquipo.class);
        cambios.put(TipoEquipo.LOCAL, new ArrayList<>());
        cambios.put(TipoEquipo.VISITANTE, new ArrayList<>());

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = DBConexion.getConexion().prepareStatement(CARGAR_POR_PARTIDO);
            ps.setInt(1, idPartido);
            rs = ps.executeQuery();

            while (rs.next()) {
                CambioBuilder cambioBuilder = new CambioBuilder();
                cambioBuilder.minuto(rs.getInt("minuto"));

                if (rs.getString("equipo").equals("LOCAL")) {
                    cambios.get(TipoEquipo.LOCAL).add(new CambioDecorator(cambioBuilder.build(),
                            rs.getString("entra"), rs.getString("sale")));
                } else {
                    cambios.get(TipoEquipo.VISITANTE).add(new CambioDecorator(cambioBuilder.build(),
                            rs.getString("entra"), rs.getString("sale")));
                }
            }

        } catch (SQLException e) {
            throw new DAOException("ERROR AL CARGAR LOS CAMBIOS DEL PARTIDO " + idPartido, e);
        } finally {
            DBConexion.closeResources(ps, rs);
        }

        return cambios;
    }
}
