package ligafx.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public final class ReadIni {

    private static final Logger LOGGER = Logger.getLogger(ReadIni.class.getName());

    private static Properties config = null;

    private static final String FILENAME = "config.ini";

    /**
     * se crea el constructor privado porque si no se crea ninguno, java automáticamente creará uno por defecto
     * público que puede ser instanciado, haciendolo así evitamos que esta clase se pueda instanciar
     */
    private ReadIni() {
        throw new IllegalStateException("Utility class");
    }

    private static void loadConfig() {
        if (config == null) {
            config = new Properties();
            try {
                config.load(new FileInputStream(ReadIni.FILENAME));
            } catch (IOException e) {
                LOGGER.severe("ERROR: no se puede cargar la configuracion.\n" +
                        Util.printStackTrace(e));
            }
        }
    }

    public static int getPort() {
        ReadIni.loadConfig();

        return Integer.parseInt(config.getProperty("port"));
    }

    public static String getDbUser() {
        ReadIni.loadConfig();

        return config.getProperty("user");
    }

    public static String getDbPassword() {
        ReadIni.loadConfig();

        return config.getProperty("password");
    }

    public static String getDbName() {
        ReadIni.loadConfig();

        return config.getProperty("database");
    }

    public static String getServer() {
        ReadIni.loadConfig();

        return config.getProperty("server");
    }

    public static String getDebugMode() {
        ReadIni.loadConfig();

        return config.getProperty("log");
    }
}

