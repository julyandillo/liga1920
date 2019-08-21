package ligafx.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class ReadIni {

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

            }
        }
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
}

