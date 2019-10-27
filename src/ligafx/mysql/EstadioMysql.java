package ligafx.mysql;

import ligafx.dao.DAOException;
import ligafx.dao.EstadioDAO;
import ligafx.modelos.Estadio;
import ligafx.util.DBConexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EstadioMysql implements EstadioDAO {

    private final static String GET = "SELECT * FROM estadio WHERE id_estadio=?";

    @Override
    public Estadio cargar(Integer id) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Estadio estadio = null;

        try {
            ps = DBConexion.getConexion().prepareStatement(GET);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            if (rs.next()) {
                estadio = new Estadio(id, rs.getString("nombre"), rs.getInt("capacidad"),
                        rs.getString("dimensiones"), rs.getInt("construccion"),
                        rs.getString("imagen"));
            }

        } catch (SQLException e) {
            throw new DAOException("ERROR AL CARGAR EL ESTADIO", e);
        } finally {
            DBConexion.closeResources(ps, rs);
        }

        return estadio;
    }

    @Override
    public List<Estadio> cargarTodos() throws DAOException {
        return null;
    }
}
