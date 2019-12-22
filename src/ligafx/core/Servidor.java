package ligafx.core;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ligafx.util.ReadIni;
import ligafx.util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

/**
 * Otra forma de hacer el hilo seria que la clase en lugar de heredar de Thread, implementara la
 * interfaz Runnable, así para crear el hilo en el lugar donde se invoque habria que crear un
 * objeto de la clase Thread y pasarle esta clase al constructor y lanzar el hilo con el metodo
 * start del objeto creado. Eso se hace cuando la clase ya hereda de otra, no es posible la
 * herecia multiple (solo se puede heredar de una clase)
 */
public class Servidor extends Thread {

    private static final Logger LOGGER = Logger.getLogger(Servidor.class.getName());

    private static Servidor instancia = null;

    private ServerSocket server;

    /**
     * Se usa el patron singleton para que la instancia del servidor sea la misma en toda la aplicaion.
     * Asi cada vez que se cargue la plnatilla del estado del servidor no creara una nueva instancia y
     * se puede controlar el estado del hilo
     *
     * @return Servidor instancia unica del servidor
     */
    public static Servidor getInstance() throws IOException {
        if (instancia == null) {
            instancia = new Servidor("Servidor liga 19/20");
        }

        return instancia;
    }

    /**
     * Despues de cerrar el socket, si se quisiera abrir de nuevo una conexion daria error si no
     * se borra la instancia siempre devolveria la misma ed antes pero con el socket cerrado
     * asi se abre un nuevo socket la proxima vez que se inicie el servidor despues de cerrarse
     *
     * @throws IOException exception
     */
    public static void closeServer() throws IOException {
        if (instancia != null) {
            instancia.close();
        }

        instancia = null;
    }

    private Servidor(String name) throws IOException {
        super(name);

        server = new ServerSocket(ReadIni.getPort());
    }

    @Override
    public void run() {
        Socket socket = null;
        BufferedReader reader = null;

        try {

            System.out.println("SERVIDOR INICIADO");

            // espera a que se realice una conexión
            socket = server.accept();
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // mientras el servidor no se cierre el hilo permanecera en ejecucion
            while (!server.isClosed()) {
                /* como el socket está continuamente escuchando, si no se usa directamente el reader
                 * como fuente para parsear en lugar de un string, lanzaria una
                 * exception al parsear un null y cerraria el hilo pero la instancia (singleton)
                 * seguiria creada y no se podría volver a crear (nunca sería null)
                 * por este motivo se parsea desde un string (cuando se reciba algo)
                 */
                String info = reader.readLine();
                if (info != null) {
                    JsonObject json = JsonParser.parseString(info).getAsJsonObject();
                    System.out.println(json.get("type"));
                }
            }

            System.out.println("SERVIDOR DETENIDO");

        } catch (IOException ex) {
            LOGGER.severe("ERROR: ha ocurrido un error en el servidor.\n" + Util.printStackTrace(ex));
        }
    }

    public void close() throws IOException {
        if (!server.isClosed()) {
            server.close();
        }
    }
}
