package ligafx.mysql;

import ligafx.builders.DatoBuilder;
import ligafx.dao.DAOException;
import ligafx.dao.EstadisticaDAO;
import ligafx.decoradores.EstadisticaDecorator;
import ligafx.modelos.Estadistica;
import ligafx.modelos.TipoParametro;
import ligafx.util.DBConexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadisticaMysql implements EstadisticaDAO {

    private static final String GUARDAR = "INSERT INTO estadistica (puntos, puntos_casa, puntos_fuera, jugados, " +
            "jugados_casa, jugados_fuera, ganados, ganados_casa, ganados_fuera, empatados, empatados_casa, " +
            "emptados_fuera, perdidos, perdidos_casa, perdidos_fuera, goles_favor, goles_favor_casa, " +
            "goles_favor_fuera, goles_contra, goles_contra_casa, goles_contra_fuera, id_equipo, id_jornada) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String CARGAR_ULTIMA_EQUIPO = "SELECT * FROM estadistica WHERE id_equipo = ? " +
            "ORDER BY id_estadistica DESC LIMIT 1";

    private static final String CARGAR_POR_JORNADA = "SELECT * FROM estadisitca WHERE id_jornada = ? ORDER BY " +
            "puntos DESC, (goles_favor-golesc_contra) DESC, goles_favor DESC, goles_contra ASC";

    private static final String CARGAR_ULTIMAS = "SELECT e.nombre as equipo, puntos, jugados, ganados, perdidos, " +
            "empatados, goles_favor, goles_contra, (goles_favor - b.goles_contra) as 'golaverage' " +
            "FROM estadistica es " +
            "INNER JOIN equipo e ON e.id_equipo = es.id_equipo " +
            "WHERE es.id IN (SELECT MAX(id) FROM estadistica GROUP BY id_equipo) " +
            "ORDER BY puntos desc, (goles_favor - goles_contra) desc, goles_favor desc";

    @Override
    public boolean guardar(Estadistica estadistica, int equipo, int jornada) throws DAOException {
        PreparedStatement ps = null;
        int resultado = 0;

        try {
            ps = DBConexion.getConexion().prepareStatement(GUARDAR);
            ps.setInt(1, estadistica.getParametro(TipoParametro.PUNTOS).getTotal());
            ps.setInt(2, estadistica.getParametro(TipoParametro.PUNTOS).getCasa());
            ps.setInt(3, estadistica.getParametro(TipoParametro.PUNTOS).getFuera());
            ps.setInt(4, estadistica.getParametro(TipoParametro.JUGADOS).getTotal());
            ps.setInt(5, estadistica.getParametro(TipoParametro.JUGADOS).getCasa());
            ps.setInt(6, estadistica.getParametro(TipoParametro.JUGADOS).getFuera());
            ps.setInt(7, estadistica.getParametro(TipoParametro.GANADOS).getTotal());
            ps.setInt(8, estadistica.getParametro(TipoParametro.GANADOS).getCasa());
            ps.setInt(9, estadistica.getParametro(TipoParametro.GANADOS).getFuera());
            ps.setInt(10, estadistica.getParametro(TipoParametro.EMPATADOS).getTotal());
            ps.setInt(11, estadistica.getParametro(TipoParametro.EMPATADOS).getCasa());
            ps.setInt(12, estadistica.getParametro(TipoParametro.EMPATADOS).getFuera());
            ps.setInt(13, estadistica.getParametro(TipoParametro.PERDIDOS).getTotal());
            ps.setInt(14, estadistica.getParametro(TipoParametro.PERDIDOS).getCasa());
            ps.setInt(15, estadistica.getParametro(TipoParametro.PERDIDOS).getFuera());
            ps.setInt(16, estadistica.getParametro(TipoParametro.GOLES_FAVOR).getTotal());
            ps.setInt(17, estadistica.getParametro(TipoParametro.GOLES_FAVOR).getCasa());
            ps.setInt(18, estadistica.getParametro(TipoParametro.GOLES_FAVOR).getFuera());
            ps.setInt(19, estadistica.getParametro(TipoParametro.GOLES_CONTRA).getTotal());
            ps.setInt(20, estadistica.getParametro(TipoParametro.GOLES_CONTRA).getCasa());
            ps.setInt(21, estadistica.getParametro(TipoParametro.GOLES_CONTRA).getFuera());
            ps.setInt(22, equipo);
            ps.setInt(23, jornada);

            resultado = ps.executeUpdate();

        } catch (SQLException e) {
            throw new DAOException("ERROR AL GUARDAR LAS ESTADISITCAS", e);
        } finally {
            DBConexion.closeResources(ps, null);
        }

        return resultado == 1;
    }

    @Override
    public Estadistica cargarUltimaEquipo(int equipo) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        /*
          se podria inicializar siempre con un nuevo objeto con todos los paramentros a 0 (new Estadistica(true)), pero
          esto solo sería util solo para la primera jornada (cuando no tenemos todavia estadisticas), en las jornadas
          siguientes siempre va a tener alguna estadistica por lo que nos ahorramos inicializar siempre el objeto a 0
          ahorrandonos un bucle por los parametros por cada vez que se carga la estadistica.
          Haciendo esto es importante comprobar si el valor devuelto es null, si es asi hay que inicializarlo donde se
          ejecute este metodo
         */
        Estadistica estadistica = null;

        try {
            ps = DBConexion.getConexion().prepareStatement(CARGAR_ULTIMA_EQUIPO);
            ps.setInt(1, equipo);

            rs = ps.executeQuery();

            if (rs.next()) {
                estadistica = crear(rs);
            }

        } catch (SQLException e) {
            throw new DAOException("ERROR AL CARGAR LA ESTADISTICA DEL EQUIPO", e);
        } finally {
            DBConexion.closeResources(ps, rs);
        }

        return estadistica;
    }

    @Override
    public List<Estadistica> cargarPorJonada(int jornada) throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Estadistica> estadisticas = new ArrayList<>();

        try {
            ps = DBConexion.getConexion().prepareStatement(CARGAR_POR_JORNADA);
            ps.setInt(1, jornada);

            rs = ps.executeQuery();

            while (rs.next()) {
                estadisticas.add(crear(rs));
            }

        } catch (SQLException e) {
            throw new DAOException("ERROR AL CARGAR LAS ESTADISTICAS DE LA JORNADA", e);
        } finally {
            DBConexion.closeResources(ps, rs);
        }

        return estadisticas;
    }

    @Override
    public List<EstadisticaDecorator> cargarUltima() throws DAOException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<EstadisticaDecorator> estadisticas = new ArrayList<>();

        try {
            ps = DBConexion.getConexion().prepareStatement(CARGAR_ULTIMAS);
            rs = ps.executeQuery();

            while (rs.next()) {
                estadisticas.add(new EstadisticaDecorator(crear(rs), rs.getString("equipo")));
            }

        } catch (SQLException e) {
            throw new DAOException("ERROR AL CARGAR LAS ULTIMAS ESTADISTICAS", e);
        } finally {
            DBConexion.closeResources(ps, rs);
        }

        return estadisticas;
    }

    private Estadistica crear(ResultSet rs) throws SQLException {
        // asi no inicializa cada parametro a 0, no tiene sentido inicializarlos y despues setearlos con los valores
        // del resultado de la consulta
        Estadistica estadistica = new Estadistica(false);

        estadistica.setParametro(TipoParametro.PUNTOS,
                new DatoBuilder()
                        .total(rs.getInt("puntos"))
                        .casa(rs.getInt("puntos_casa"))
                        .fuera(rs.getInt("puntos_fuera"))
                        .build()
        );
        estadistica.setParametro(TipoParametro.JUGADOS,
                new DatoBuilder()
                        .total(rs.getInt("jugados"))
                        .casa(rs.getInt("jugados_casa"))
                        .fuera(rs.getInt("jugados_fuera"))
                        .build()
        );
        estadistica.setParametro(TipoParametro.GANADOS,
                new DatoBuilder()
                        .total(rs.getInt("ganados"))
                        .casa(rs.getInt("ganados_casa"))
                        .fuera(rs.getInt("ganados_fuera"))
                        .build()
        );
        estadistica.setParametro(TipoParametro.EMPATADOS,
                new DatoBuilder()
                        .total(rs.getInt("empatados"))
                        .casa(rs.getInt("empatados_casa"))
                        .fuera(rs.getInt("empatados_fuera"))
                        .build()
        );
        estadistica.setParametro(TipoParametro.PERDIDOS,
                new DatoBuilder()
                        .total(rs.getInt("perdidos"))
                        .casa(rs.getInt("perdidos_casa"))
                        .fuera(rs.getInt("perdidos_fuera"))
                        .build()
        );
        estadistica.setParametro(TipoParametro.GOLES_FAVOR,
                new DatoBuilder()
                        .total(rs.getInt("goles_favor"))
                        .casa(rs.getInt("goles_favor_casa"))
                        .fuera(rs.getInt("goles_favor_fuera"))
                        .build()
        );
        estadistica.setParametro(TipoParametro.GOLES_CONTRA,
                new DatoBuilder()
                        .total(rs.getInt("goles_contra"))
                        .casa(rs.getInt("goles_contra_casa"))
                        .fuera(rs.getInt("goles_contra_fuera"))
                        .build()
        );

        return estadistica;
    }
}
