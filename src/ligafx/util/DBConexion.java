package ligafx.util;

import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
                Util.mostrarMensaje("No se puede conectar con la base de datos", Alert.AlertType.ERROR);
                LOGGER.log(Level.SEVERE, Util.printStackTrace(e));
            }
        }

        return conexion;
    }
}
