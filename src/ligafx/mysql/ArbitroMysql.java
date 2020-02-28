package ligafx.mysql;

import ligafx.dao.ArbitroDAO;
import ligafx.dao.DAOException;
import ligafx.modelos.Arbitro;
import ligafx.util.DBConexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArbitroMysql implements ArbitroDAO {
    private static final String CARGAR_ID = "select id, nombre from arbitro where id = ?";

    private static final String CARGAR_NOMBRE = "select id, nombre from arbitro where nombre = ?";

    private static final String CARGAR_TODOS = "select id, nombre from arbitro";

    private static final String GUARDAR = "INSERT INTO arbitro (id, nombre) VALUES (?, ?)";

    @Override
    public boolean guardar(Arbitro arbitro) throws DAOException {
        PreparedStatement ps = null;
        int resultado = 0;

        try {
            ps = DBConexion.getConexion().prepareStatement(GUARDAR);
            ps.setInt(1, arbitro.getId());
            ps.setString(2, arbitro.getNombre());

            resultado = ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("ERROR AL GUARDAR EL ARBITRO", e);
        } finally {
            DBConexion.closeResources(ps, null);
        }

        return resultado == 1;
    }

    @Override
    public Arbitro cargar(String nombre) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Arbitro arbitro = null;

        try {
            ps = DBConexion.getConexion().prepareStatement(CARGAR_NOMBRE);
            ps.setString(1, nombre);
            rs = ps.executeQuery();

            if (rs.next()) {
                arbitro = new Arbitro(rs.getInt("id"), rs.getString("nombre"));
            }

        } catch (SQLException e) {
            throw new DAOException("ERROR AL CARGAR EL ARBITRO", e);
        } finally {
            DBConexion.closeResources(ps, rs);
        }

        return arbitro;
    }

    @Override
    public Arbitro cargar(Integer id) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Arbitro arbitro = null;

        try {
            ps = DBConexion.getConexion().prepareStatement(CARGAR_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                arbitro = new Arbitro(rs.getInt("id"), rs.getString("nombre"));
            }

        } catch (SQLException e) {
            throw new DAOException("ERROR AL CARGAR EL ARBITRO", e);
        } finally {
            DBConexion.closeResources(ps, rs);
        }

        return arbitro;
    }

    @Override
    public List<Arbitro> cargarTodos() throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Arbitro> arbitros = new ArrayList<>();

        try {
            ps = DBConexion.getConexion().prepareStatement(CARGAR_TODOS);
            rs = ps.executeQuery();

            if (rs.next()) {
                arbitros.add(new Arbitro(rs.getInt("id"), rs.getString("nombre")));
            }

        } catch (SQLException e) {
            throw new DAOException("ERROR AL CARGAR EL ARBITRO", e);
        } finally {
            DBConexion.closeResources(ps, rs);
        }

        return arbitros;
    }
}
