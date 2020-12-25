package ligafx.mysql;

import ligafx.builders.TarjetaBuilder;
import ligafx.dao.DAOException;
import ligafx.dao.TarjetaDAO;
import ligafx.decoradores.TarjetaDecorator;
import ligafx.modelos.Tarjeta;
import ligafx.modelos.TipoEquipo;
import ligafx.modelos.TipoTarjeta;
import ligafx.util.DBConexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TarjetaMysql implements TarjetaDAO {

    private static final String GUARDAR = "INSERT INTO tarjeta (minuto, tipo, id_jugador, id_partido) " +
            "VALUES (?, ?, ?, ?)";

    private static final String CARGAR_POR_PARTIDO = "select t.minuto, t.id_jugador, j.apodo as jugador, t.tipo," +
            "case when j.id_equipo = p.id_equipo_local then 'LOCAL' else 'VISITANTE' end as equipo " +
            "from tarjeta t " +
            "inner join jugador j on j.id_jugador = t.id_jugador " +
            "inner join partido p on p.id_partido = t.id_partido " +
            "where t.id_partido = ?";

    @Override
    public Tarjeta cargar(Integer id) throws DAOException {
        return null;
    }

    @Override
    public List<Tarjeta> cargarTodos() throws DAOException {
        return Collections.emptyList();
    }

    @Override
    public Map<TipoEquipo, List<TarjetaDecorator>> cargarTodasPorPartido(int idPartido) throws DAOException {
        Map<TipoEquipo, List<TarjetaDecorator>> tarjetas = new EnumMap<>(TipoEquipo.class);
        tarjetas.put(TipoEquipo.LOCAL, new ArrayList<>());
        tarjetas.put(TipoEquipo.VISITANTE, new ArrayList<>());

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = DBConexion.getConexion().prepareStatement(CARGAR_POR_PARTIDO);
            ps.setInt(1, idPartido);
            rs = ps.executeQuery();

            while (rs.next()) {
                TarjetaBuilder tarjetaBuilder = new TarjetaBuilder();
                tarjetaBuilder.jugador(rs.getInt("id_jugador"));
                tarjetaBuilder.minuto(rs.getInt("minuto"));
                tarjetaBuilder.tipo(rs.getInt("tipo") == 1 ? TipoTarjeta.AMARILLA : TipoTarjeta.ROJA);

                TipoEquipo equipo = rs.getString("equipo").equals("LOCAL") ? TipoEquipo.LOCAL : TipoEquipo.VISITANTE;

                tarjetas.get(equipo).add(new TarjetaDecorator(tarjetaBuilder.build(), rs.getString("jugador")));
            }

        } catch (SQLException e) {
            throw new DAOException("ERROR AL OBTENER LAS TARJETAS DEL PARTIDO", e);
        } finally {
            DBConexion.closeResources(ps, rs);
        }

        return tarjetas;
    }

    @Override
    public int guardarTarjetas(List<Tarjeta> tarjetas, int idPartido) throws DAOException {
        int guardadas = 0;

        for (Tarjeta tarjeta : tarjetas) {
            if (guardarTarjeta(tarjeta, idPartido)) {
                guardadas++;
            }
        }

        return guardadas;
    }

    public boolean guardarTarjeta(Tarjeta tarjeta, int idPartido) throws DAOException {
        PreparedStatement ps = null;
        int filasInsertadas = 0;

        try {
            ps = DBConexion.getConexion().prepareStatement(GUARDAR);
            ps.setInt(1, tarjeta.getMinuto());
            ps.setInt(2, tarjeta.getTipo().getValor());
            ps.setInt(3, tarjeta.getIdJugador());
            ps.setInt(4, idPartido);

            filasInsertadas = ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("ERROR AL GUARDAR LA TARJETA", GUARDAR, e);
        } finally {
            DBConexion.closeResources(ps, null);
        }

        return filasInsertadas == 1;
    }
}
