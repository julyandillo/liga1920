package ligafx.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConexion {

    private static Connection conexion = null;

    private final static Logger LOGGER = Logger.getLogger(DBConexion.class.getName());

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

                conexion = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database, user, password);
            }  catch (SQLException e) {
                LOGGER.log(Level.SEVERE, Util.printStackTrace(e));
            }
        }

        return conexion;
    }
}
