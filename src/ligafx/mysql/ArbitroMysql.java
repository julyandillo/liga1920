package ligafx.mysql;

import ligafx.builders.ArbitroBuilder;
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
    private static final String CARGAR_ID = "select id_arbitro, nombre, " +
            "(select count(*) from partido where id_arbitro=a.id_arbitro) as partidos," +
            "(select count(*) from partido where id_arbitro_var=a.id_arbitro) as partidosVAR," +
            "(select count(*) from tarjeta " +
            "where tipo = 1 and id_partido in (select id_partido from partido where id_arbitro=a.id_arbitro)) as amarillas," +
            "(select count(*) from tarjeta " +
            "where tipo = 2 and id_partido in (select id_partido from partido where id_arbitro=a.id_arbitro)) as rojas," +
            "(select count(*) from gol " +
            "where penalti = 1 and id_partido in (select id_partido from partido where id_arbitro=a.id_arbitro)) as penaltis " +
            "from arbitro a " +
            "where a.id_arbitro = ?";

    private static final String CARGAR_NOMBRE = "select id_arbitro, nombre," +
            "(select count(*) from partido where id_arbitro=a.id_arbitro) as partidos," +
            "(select count(*) from partido where id_arbitro_var=a.id_arbitro) as partidosVAR," +
            "(select count(*) from tarjeta " +
            "where tipo = 1 and id_partido in (select id_partido from partido where id_arbitro=a.id_arbitro)) as amarillas," +
            "(select count(*) from tarjeta " +
            "where tipo = 2 and id_partido in (select id_partido from partido where id_arbitro=a.id_arbitro)) as rojas," +
            "(select count(*) from gol " +
            "where penalti = 1 and id_partido in (select id_partido from partido where id_arbitro=a.id_arbitro)) as penaltis " +
            "from arbitro a " +
            "where a.nombre = ?";

    private static final String CARGAR_NOMBRES = "select nombre from arbitro order by nombre";

    private static final String CARGAR_TODOS = "select id_arbitro, nombre from arbitro";

    private static final String GUARDAR = "INSERT INTO arbitro (nombre) VALUES (?)";

    @Override
    public boolean guardar(Arbitro arbitro) throws DAOException {
        PreparedStatement ps = null;
        int resultado = 0;

        try {
            ps = DBConexion.getConexion().prepareStatement(GUARDAR);
            ps.setString(1, arbitro.getNombre());

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
                arbitro = cargarArbitro(rs);
            } else {
                // si no existe el arbitro se guarda uno nuevo y despues se vuelve a cargar por el nombre
                this.guardar(new Arbitro(0, nombre));
                arbitro = this.cargar(nombre);
            }

        } catch (SQLException e) {
            throw new DAOException("ERROR AL CARGAR EL ARBITRO", e);
        } finally {
            DBConexion.closeResources(ps, rs);
        }

        return arbitro;
    }

    private Arbitro cargarArbitro(ResultSet rs) throws SQLException {
        ArbitroBuilder arbitroBuilder = new ArbitroBuilder()
                .id(rs.getInt("id_arbitro"))
                .nombre(rs.getString("nombre"))
                .partidos(rs.getInt("partidos"))
                .partidosVAR(rs.getInt("partidosVAR"))
                .amarillas(rs.getInt("amarillas"))
                .rojas(rs.getInt("rojas"))
                .penaltis(rs.getInt("penaltis"));

        return arbitroBuilder.build();
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
                arbitro = cargarArbitro(rs);
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

    @Override
    public List<String> cargarNombres() throws DAOException {
        List<String> arbitros = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = DBConexion.getConexion().prepareStatement(CARGAR_NOMBRES);
            rs = ps.executeQuery();

            while (rs.next()) {
                arbitros.add(rs.getString("nombre"));
            }

        } catch (SQLException e) {
            throw new DAOException("ERROR AL CARGAR LOS NOMBRES DE LOS ARBITROS", CARGAR_NOMBRES, e);
        } finally {
            DBConexion.closeResources(ps, rs);
        }

        return arbitros;
    }
}
