package ligafx.util;

import javafx.scene.control.Alert;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConexion {

    private static Connection conexion = null;

    private static final Logger LOGGER = Logger.getLogger(DBConexion.class.getName());

    private DBConexion() {
        throw new IllegalStateException("La clase 'Conexion' no se puede instanciar. Utiliza 'getConexion' para" +
                "obtener la conexión a la base de datos");
    }

    public static Connection getConexion() {
        if (conexion == null) {
            try {
                String server = ReadIni.getServer();
                String user = ReadIni.getDbUser();
                String password = ReadIni.getDbPassword();
                String database = ReadIni.getDbName();
                String url = "jdbc:mysql://" + server + "/" + database + "?serverTimezone=UTC";

                conexion = DriverManager.getConnection(url, user, password);

            }  catch (SQLException e) {
                Util.mostrarMensaje("ERROR: NO SE PUEDE CONECTAR CON LA BASE DE DATOS", Alert.AlertType.ERROR);
                LOGGER.log(Level.SEVERE, Util.printStackTrace(e));
            }
        }

        return conexion;
    }

    public static void closeResources(PreparedStatement ps, ResultSet rs)  {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                Util.mostrarMensaje("ERROR AL CERRAR UN RECURSO: PreparedStatement", Alert.AlertType.ERROR);
                LOGGER.log(Level.SEVERE, Util.printStackTrace(e));
            }
        }

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                Util.mostrarMensaje("ERROR AL CERRAR UN RECURSO: ResultSet", Alert.AlertType.ERROR);
                LOGGER.log(Level.SEVERE, Util.printStackTrace(e));
            }
        }
    }
}
