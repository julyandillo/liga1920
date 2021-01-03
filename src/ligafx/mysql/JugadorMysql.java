package ligafx.mysql;

import ligafx.builders.JugadorBuilder;
import ligafx.dao.DAOException;
import ligafx.dao.DAOManager;
import ligafx.dao.JugadorDAO;
import ligafx.modelos.Jugador;
import ligafx.util.DBConexion;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class JugadorMysql implements JugadorDAO {

    private static final String GUARDAR = "INSERT INTO jugador (id_jugador, nombre, dorsal, posicion, apodo, peso, " +
            "altura, fecha_nacimiento, nacionalidad, pais_nacimiento, id_equipo, imagen) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String CARGAR = "select j.*, count(distinct c.id_cambio) as sale, " +
            "count(distinct c1.id_cambio) as entra " +
            "from jugador j " +
            "left join cambio c on c.id_jugador_sale=j.id_jugador " +
            "left join cambio c1 on c1.id_jugador_entra=j.id_jugador " +
            "where j.id_jugador = ?";

    private static final String CARGAR_TODOS = "select * from jugador order by nombre";

    private static final String CARGAR_TODOS_IDS = "select id_jugador from jugador order by nombre";

    private static final String CARGAR_PLANTILLA = "select * from jugador where id_equipo = ?";

    @Override
    public boolean guardar(Jugador jugador) throws DAOException {
        throw new UnsupportedOperationException("Método no disponible, para guardar un jugador es necesario un equipo");
    }

    @Override
    public boolean guardar(Jugador jugador, int idEquipo) throws DAOException {
        PreparedStatement ps = null;

        try {
            ps = DBConexion.getConexion().prepareStatement(GUARDAR);
            ps.setInt(1, jugador.getId());
            ps.setString(2,jugador.getNombre());
            ps.setInt(3, jugador.getDorsal());
            ps.setString(4, jugador.getPosicion().getValor());
            ps.setString(5, jugador.getApodo());
            ps.setInt(6, jugador.getPeso());
            ps.setInt(7, jugador.getAltura());

            if (jugador.getFechaNacimiento() != null) {
                ps.setDate(8, new Date(jugador.getFechaNacimiento().getTime()));
            } else {
                ps.setDate(8, null);
            }

            ps.setString(9, jugador.getNacionalidad());
            ps.setString(10, jugador.getPaisNacimiento());
            ps.setInt(11, idEquipo);
            ps.setString(12, jugador.getImagen());

            return ps.executeUpdate() == 1;

        } catch (SQLException ex) {
            throw new DAOException("ERROR AL GUARDAR EL JUGADOR", ex);
        } finally {
            DBConexion.closeResources(ps, null);
        }

    }

    @Override
    public boolean guardar(List<Jugador> plantilla, int idEquipo) throws DAOException {
        PreparedStatement ps = null;

        try {
            ps = DBConexion.getConexion().prepareStatement(GUARDAR);

            for(Jugador jugador : plantilla) {
                ps.setInt(1, jugador.getId());
                ps.setString(2,jugador.getNombre());
                ps.setInt(3, jugador.getDorsal());
                ps.setString(4, jugador.getPosicion().getValor());
                ps.setString(5, jugador.getApodo());
                ps.setInt(6, jugador.getPeso());
                ps.setInt(7, jugador.getAltura());
                ps.setString(9, jugador.getNacionalidad());
                ps.setString(10, jugador.getPaisNacimiento());
                ps.setInt(11, idEquipo);
                ps.setString(12, jugador.getImagen());

                if (jugador.getFechaNacimiento() != null) {
                    ps.setDate(8, new Date(jugador.getFechaNacimiento().getTime()));
                } else {
                    ps.setDate(8, null);
                }

                ps.addBatch();
            }

            return ps.executeBatch().length == plantilla.size();

        } catch (SQLException ex) {
            throw new DAOException("ERROR AL GUARDAR LA PLANTILLA", ex);
        } finally {
            DBConexion.closeResources(ps, null);
        }
    }

    @Override
    public boolean actualizar(Jugador jugador) throws DAOException {
        return false;
    }

    /**
     * Carga todos los datos de un jugador dado su id, también carga los goles, tarjetas y cambios
     *
     * @param id id del jugador  para cargar
     * @return objeto Jugador
     * @throws DAOException descripción del error en la carga del jugador
     */
    @Override
    public Jugador cargar(Integer id) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Jugador jugador = null;

        try {
            ps = DBConexion.getConexion().prepareStatement(CARGAR);
            ps.setInt(1, id);

            rs = ps.executeQuery();
            if (rs.next()) {
                jugador = creaJugador(rs);
                jugador.setVecesEntra(rs.getInt("entra"));
                jugador.setVecesCambiado(rs.getInt("sale"));
                jugador.getGoles().addAll(DAOManager.getGolDAO().cargarTodosPorJugador(id));
                jugador.getTarjetas().addAll(DAOManager.getTarjetaDAO().cargarTodasPorJugador(id));
            }

        } catch (SQLException ex) {
            throw new DAOException("ERROR AL CARGAR EL JUGADOR", ex);
        } finally {
            DBConexion.closeResources(ps, rs);
        }

        return jugador;
    }

    @Override
    public List<Jugador> cargarPlantilla(int idEquipo) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Jugador> plantilla = new ArrayList<>();

        try {
            ps = DBConexion.getConexion().prepareStatement(CARGAR_PLANTILLA);
            ps.setInt(1, idEquipo);

            rs = ps.executeQuery();

            while(rs.next()) {
                plantilla.add(creaJugador(rs));
            }

        } catch (SQLException ex) {
            throw new DAOException("ERROR AL CARGAR LA PLANTILLA", ex);
        } finally {
            DBConexion.closeResources(ps, rs);
        }

        return plantilla;
    }

    @Override
    public List<Jugador> cargarTodos() throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Jugador> jugadores = new ArrayList<>();

        try {
            ps = DBConexion.getConexion().prepareStatement(CARGAR_TODOS);
            rs = ps.executeQuery();

            while(rs.next()) {
                jugadores.add(creaJugador(rs));
            }

        } catch (SQLException ex) {
            throw new DAOException("ERROR AL CARGAR TODOS LOS JUGADORES", ex);
        } finally {
            DBConexion.closeResources(ps, rs);
        }

        return jugadores;
    }

    private Jugador creaJugador(ResultSet rs) throws SQLException {
        return new JugadorBuilder().id(rs.getInt("id_jugador"))
                .nombre(rs.getString("nombre"))
                .apodo(rs.getString("apodo"))
                .paisNacimiento(rs.getString("pais_nacimiento"))
                .nacionalidad((rs.getString("nacionalidad")))
                .fechaNacimiento(new java.util.Date(rs.getDate("fecha_nacimiento").getTime()))
                .dorsal(rs.getInt("dorsal"))
                .posicion(rs.getString("posicion"))
                .peso(rs.getInt("peso"))
                .altura(rs.getInt("altura"))
                .imagen(rs.getString("imagen"))
                .build();
    }

    @Override
    public HashSet<Integer> cargarIds() throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        HashSet<Integer> idJugadores = new HashSet<>();

        try {
            ps = DBConexion.getConexion().prepareStatement(CARGAR_TODOS_IDS);
            rs = ps.executeQuery();

            while (rs.next()) {
                idJugadores.add(rs.getInt("id_jugador"));
            }

        } catch (SQLException e) {
            throw new DAOException("ERROR AL CARGAR LOS IDS DE LOS JUGADORES", CARGAR_TODOS_IDS, e);
        } finally {
            DBConexion.closeResources(ps, rs);
        }


        return idJugadores;
    }
}
