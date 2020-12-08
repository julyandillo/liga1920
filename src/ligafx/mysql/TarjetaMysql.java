package ligafx.mysql;

import ligafx.dao.DAOException;
import ligafx.dao.TarjetaDAO;
import ligafx.modelos.Tarjeta;
import ligafx.util.DBConexion;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class TarjetaMysql implements TarjetaDAO {

    private static final String GUARDAR = "INSERT INTO tarjeta (minuto, tipo, id_jugador, id_partido) " +
            "VALUES (?, ?, ?, ?)";

    @Override
    public Tarjeta cargar(Integer id) throws DAOException {
        return null;
    }

    @Override
    public List<Tarjeta> cargarTodos() throws DAOException {
        return Collections.emptyList();
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
