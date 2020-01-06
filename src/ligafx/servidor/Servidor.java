package ligafx.servidor;

import ligafx.util.ReadIni;
import ligafx.util.Util;

import java.io.IOException;
import java.net.ServerSocket;
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

    public static void startServer() throws IOException {
        if (instancia == null) {
            instancia = new Servidor("Servidor liga 19/20");
        }

        instancia.start();
    }

    /**
     * Despues de cerrar el socket, si se quisiera abrir de nuevo una conexion daria error si no
     * se borra la instancia siempre devolveria la misma de antes pero con el socket cerrado,
     * asi se puede abrir un nuevo socket la proxima vez que se inicie el servidor despues de cerrarse
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

        LOGGER.info("SERVIDOR INICIADO");

        // espera a que se realice una conexión
        // socket = server.accept(); -> esto haría un servidor dedicado

        // espera a que un cliente solicite una conexion y crea un nuevo hilo para tratar la petición
        // siguiendo escuchando mas peticiones de nuevos clientes
        while (!server.isClosed()) {
            try {
                HiloCliente cliente = new HiloCliente(server.accept());

                Thread hilo = new Thread(cliente);
                hilo.start();
            } catch (IOException e) {
                /*
                 * Si se usara Util.printStackTrace(e) para el log, como esto es un nuevo hilo y la clase
                 * Util es un singleton creado en otro hilo, lanzaria una excepcion al usar ese recurso
                 */
                LOGGER.severe(e.getMessage());
            }
        }

        LOGGER.info("SERVIDOR DETENIDO");
    }

    public void close() throws IOException {
        if (!server.isClosed()) {
            server.close();
        }
    }
}
