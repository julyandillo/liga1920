package ligafx.core;

import ligafx.util.ReadIni;
import ligafx.util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
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
                // recibe el json del cliente como string
                String entrada = reader.readLine();

                System.out.println(entrada);
            }

            System.out.println("SERVIDOR DETENIDO");

        } catch (IOException ex) {
            LOGGER.severe("ERROR: ha ocurrido un error en el servidor.\n" + Util.printStackTrace(ex));
        }
    }

    public void closeServer() throws IOException{
        if (!server.isClosed()) {
            server.close();
        }
    }
}
