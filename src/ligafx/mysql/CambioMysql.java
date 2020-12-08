package ligafx.mysql;

import ligafx.dao.CambioDAO;
import ligafx.dao.DAOException;
import ligafx.modelos.Cambio;
import ligafx.util.DBConexion;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class CambioMysql implements CambioDAO {

    private static final String GUARDAR = "INSERT INTO cambio (minuto, id_partido, id_jugador_sale, id_jugador_entra) " +
            "VALUES (?, ?, ?, ?)";

    private static final String CARGAR = "SELECT minuto, id_partido, id_jugador_sale, id_jugador_sale FROM cambio " +
            "WHERE id_cambio = ?";

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
}
