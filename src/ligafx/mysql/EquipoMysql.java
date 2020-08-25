package ligafx.mysql;

import ligafx.builders.EquipoBuilder;
import ligafx.dao.DAOException;
import ligafx.dao.DAOManager;
import ligafx.dao.EquipoDAO;
import ligafx.modelos.Equipo;
import ligafx.util.DBConexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EquipoMysql implements EquipoDAO {

    private static final String GET_TODOS = "SELECT * FROM equipo";

    private static final String GET = "SELECT * FROM equipo WHERE id_equipo = ?";

    private static final String GET_POR_NOMBRE = "SELECT * FROM equipo WHERE nombre LIKE ?";

    private static final String GET_NOMBRES = "SELECT nombre FROM equipo ORDER BY nombre";

    private static final String GET_NOMBRES_ESCUDOS = "SELECT nombre, escudo FROM equipo ORDER BY nombre";

    private static final String UPDATE_1 = "update equipo set entrenador=?, presidente=? where id_equipo=?";

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

    public Map<String, String> cargarNombresEscudos() throws DAOException {
        Map<String, String> equipos = new HashMap<>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = DBConexion.getConexion().prepareStatement(GET_NOMBRES_ESCUDOS);
            rs = ps.executeQuery();

            while (rs.next()) {
                equipos.put(rs.getString(1), rs.getString(2));
            }
        } catch (SQLException e) {
            throw new DAOException("ERROR AL CARGAR LA LISTA DE EQUIPOS", e);
        } finally {
            DBConexion.closeResources(ps, rs);
        }

        return equipos;
    }

    @Override
    public boolean guardar(Equipo object) throws DAOException {
        return false;
    }

    @Override
    public boolean actualizar(Equipo equipo) throws DAOException {
        PreparedStatement ps = null;

        try {
            ps = DBConexion.getConexion().prepareStatement(UPDATE_1);
            ps.setString(1, equipo.getEntrenador());
            ps.setString(2, equipo.getPresidente());
            ps.setInt(3, equipo.getId());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new DAOException("ERROR AL ACTUALIZAR EL PRESIDENTE Y ENTRENADOR", e);
        } finally {
            DBConexion.closeResources(ps, null);
        }
    }

    private Equipo setValores(ResultSet rs) throws SQLException, DAOException {
        return new EquipoBuilder().id(rs.getInt("id_equipo"))
                .direccion(rs.getString("direccion"))
                .entrenador(rs.getString("entrenador"))
                .escudo(rs.getString("escudo"))
                .fundacion(rs.getInt("fundacion"))
                .nombre(rs.getString("nombre"))
                .nombreClub(rs.getString("nombre_completo"))
                .presidente(rs.getString("presidente"))
                .telefono(rs.getString("telefono"))
                .web(rs.getString("web"))
                .estadio(DAOManager.getEstadioDAO().cargar(rs.getInt("id_estadio")))
                .build();
    }
}
