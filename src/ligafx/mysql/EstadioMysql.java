package ligafx.mysql;

import ligafx.dao.DAOException;
import ligafx.dao.EstadioDAO;
import ligafx.modelos.Estadio;
import ligafx.util.DBConexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadioMysql implements EstadioDAO {

    private final static String GET = "SELECT * FROM estadio WHERE id_estadio=?";

    private final static String GET_POR_EQUIPO = "select * from estadio where id_estadio = " +
            "(select id_estadio from equipo where id_equipo = ?)";

    private final static String UPDATE = "update estadio set capacidad=?, dimensiones=?, construccion=? where id_estadio=?";

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
    public Estadio cargarPorIdEquipo(int idEquipo) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Estadio estadio = null;

        try {
            ps = DBConexion.getConexion().prepareStatement(GET_POR_EQUIPO);
            ps.setInt(1, idEquipo);

            rs = ps.executeQuery();
            if (rs.next()) {
                estadio = new Estadio(rs.getInt("id_estadio"), rs.getString("nombre"),
                        rs.getInt("capacidad"), rs.getString("dimensiones"),
                        rs.getInt("construccion"), rs.getString("imagen"));
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
        return new ArrayList<>();
    }

    @Override
    public boolean guardar(Estadio object) {
        return false;
    }

    @Override
    public boolean actualizar(Estadio estadio) throws DAOException {
        PreparedStatement ps = null;

        try {
            ps = DBConexion.getConexion().prepareStatement(UPDATE);
            ps.setInt(1, estadio.getCapacidad());
            ps.setString(2, estadio.getDimensiones());
            ps.setInt(3, estadio.getConstruccion());
            ps.setInt(4, estadio.getId());

            return ps.executeUpdate() == 1;

        }catch (SQLException e) {
            throw new DAOException("ERROR AL ACTUALIZAR EL ESTADIO", e);
        } finally {
            DBConexion.closeResources(ps, null);
        }
    }
}
