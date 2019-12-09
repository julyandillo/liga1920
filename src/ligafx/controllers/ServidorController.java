package ligafx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import ligafx.core.Servidor;
import ligafx.util.Util;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class ServidorController implements Initializable {

    private static final Logger LOGGER = Logger.getLogger(ServidorController.class.getName());

    @FXML
    Label labelEstado;

    @FXML
    Button buttonIniciar;

    @FXML
    Button buttonDetener;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Servidor servidor = Servidor.getInstance();

            if (servidor.getState() == Thread.State.RUNNABLE) {
                this.labelEstado.setText("INICIADO");
                this.labelEstado.getStyleClass().addAll("estado-iniciado");
                this.labelEstado.getStyleClass().removeAll("estado-detenido");

                this.buttonIniciar.setDisable(true);
                this.buttonDetener.setDisable(false);
            } else {
                this.labelEstado.setText("DETENIDO");
                this.labelEstado.getStyleClass().addAll("estado-detenido");
                this.labelEstado.getStyleClass().removeAll("estado-iniciado");

                this.buttonDetener.setDisable(true);
                this.buttonIniciar.setDisable(false);
            }

        } catch (IOException e) {
            LOGGER.severe("ERROR: ha ocurrido un error en el servidor.\n" + Util.printStackTrace(e));
            Util.mostrarMensaje("Ha ocurrido un error en el servidor", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void iniciarServidor() {
        try {
            Servidor.getInstance().start();
        } catch (IOException e) {
            LOGGER.severe("ERROR: ha ocurrido un error al iniciar el servidor.\n" + Util.printStackTrace(e));
            Util.mostrarMensaje("Ha ocurrido un error al iniciar el servidor", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void detenerServidor() {
        try {
            Servidor.getInstance().closeServer();
        } catch (IOException e) {
            LOGGER.severe("ERROR: ha ocurrido un error al detener el servidor.\n" + Util.printStackTrace(e));
            Util.mostrarMensaje("Ha ocurrido un error al detener el servidor", Alert.AlertType.ERROR);
        }
    }
}
