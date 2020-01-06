package ligafx.servidor;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.scene.control.Alert;
import ligafx.util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Logger;

public class HiloCliente implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(HiloCliente.class.getName());

    private Socket socket;

    public HiloCliente(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            /* como el socket está continuamente escuchando, si no se usa directamente el reader
             * como fuente para parsear en lugar de un string, lanzaria una
             * exception al parsear un null y cerraria el hilo pero la instancia (singleton)
             * seguiria creada y no se podría volver a crear (nunca sería null)
             * por este motivo se parsea desde un string (cuando se reciba algo)
             */
            String info = reader.readLine();
            if (info != null) {
                JsonObject json = JsonParser.parseString(info).getAsJsonObject();

                JsonInterpreter interpreter = new JsonInterpreter(json);
                if (!interpreter.actualiza()) {
                    LOGGER.severe("ERROR: ha ocurrido un error al actualizar con los datos enviados.\n" +
                            json.getAsString());
                }
            }

            socket.close();

        } catch (IOException ex) {
            LOGGER.severe("ERROR: ha ocurrido un error en el servidor.\n" + Util.printStackTrace(ex));
        } catch (InterpreterException e) {
            Util.mostrarMensaje(e.getMensaje(), Alert.AlertType.ERROR);
            LOGGER.severe("ERROR: " + e.getMensaje());
        }

    }
}
