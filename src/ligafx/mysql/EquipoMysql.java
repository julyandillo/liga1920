package ligafx.mysql;

import ligafx.dao.DAOException;
import ligafx.dao.DAOManager;
import ligafx.dao.EquipoDAO;
import ligafx.modelos.Equipo;
import ligafx.util.DBConexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipoMysql implements EquipoDAO {

    private static final String GET_TODOS = "SELECT * FROM equipo";

    private static final String GET = "SELECT * FROM equipo WHERE id_equipo = ?";

    private static final String GET_POR_NOMBRE = "SELECT * FROM equipo WHERE nombre LIKE ?";

    private static final String GET_NOMBRES = "SELECT nombre FROM equipo ORDER BY nombre";

    @Override
    public Equipo cargar(Integer id) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Equipo equipo = null;

        try {
            ps = DBConexion.getConexion().prepareStatement(GET);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                equipo = setValores(rs);
            }
        } catch (SQLException ex) {
            throw new DAOException("ERROR AL CARGAR EL EQUIPO", ex);
        } finally {
            DBConexion.closeResources(ps, rs);
        }

        return equipo;
    }

    @Override
    public Equipo cargar(String nombre) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Equipo equipo = null;

        try {
            ps = DBConexion.getConexion().prepareStatement(GET_POR_NOMBRE);
            ps.setString(1, nombre);

            rs = ps.executeQuery();

            if (rs.next()) {
                equipo = setValores(rs);
            }
        } catch (SQLException ex) {
            throw new DAOException("ERROR AL CARGAR EL EQUIPO POR NOMBRE", ex);
        } finally {
            DBConexion.closeResources(ps, rs);
        }

        return equipo;
    }

    @Override
    public List<Equipo> cargarTodos() throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Equipo> equipos = new ArrayList<>();

        try {
            ps = DBConexion.getConexion().prepareStatement(GET_TODOS);
            rs = ps.executeQuery();

            while(rs.next()) {
                equipos.add(setValores(rs));
            }

        } catch (SQLException e) {
            throw new DAOException("ERROR AL CARGAR TODOS LOS EQUIPOS", e);
        } finally {
            DBConexion.closeResources(ps, rs);
        }

        return equipos;
    }

    @Override
    public List<String> cargarNombres() throws DAOException {
        List<String> equipos = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = DBConexion.getConexion().prepareStatement(GET_NOMBRES);
            rs = ps.executeQuery();

            while (rs.next()) {
                equipos.add(rs.getString(1));
            }
        } catch (SQLException e) {
            throw new DAOException("ERROR AL CARGAR LA LISTA DE EQUIPOS", e);
        } finally {
            DBConexion.closeResources(ps, rs);
        }

        return equipos;
    }

    private Equipo setValores(ResultSet rs) throws SQLException, DAOException {
        Equipo equipo = new Equipo(rs.getInt("id_equipo"));
        equipo.setDireccion(rs.getString("direccion"));
        equipo.setEntrenador(rs.getString("entrenador"));
        equipo.setEscudo(rs.getString("escudo"));
        equipo.setFundacion(rs.getInt("fundacion"));
        equipo.setNombre(rs.getString("nombre"));
        equipo.setNombreClub(rs.getString("nombre_completo"));
        equipo.setPresidente(rs.getString("presidente"));
        equipo.setTelefono(rs.getString("telefono"));
        equipo.setWeb(rs.getString("web"));

        equipo.setEstadio(DAOManager.getEstadioDAO().cargar(rs.getInt("id_estadio")));

        return equipo;
    }
}
