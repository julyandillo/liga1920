package ligafx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import ligafx.dao.DAOException;
import ligafx.dao.DAOManager;
import ligafx.modelos.Jornada;
import ligafx.modelos.Partido;
import ligafx.util.Util;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class CalendarioController implements Initializable {

    private static final Logger LOGGER = Logger.getLogger(CalendarioController.class.getName());

    @FXML
    private ComboBox<Integer> comboBoxJornada;

    @FXML
    private VBox vBoxPartidos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i=0; i<38; i++) {
            comboBoxJornada.getItems().add(i+1);
        }

        comboBoxJornada.getSelectionModel().selectedItemProperty().addListener(
                ((observableValue, integer, t1) -> cargarPartidos(t1))
        );

    }

    private void cargarPartidos(int jornada) {
        try {
            vBoxPartidos.getChildren().clear();

            Jornada jonada = DAOManager.getJornadaDAO().cargar(jornada);
            int posicion = 1;

            for (Partido partido : jonada.getPartidos()) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../views/partidoLista.fxml"));
                vBoxPartidos.getChildren().add(loader.load());

                posicion++;
                String fondo = (posicion % 2 == 0 ? "fondo-par" : "fondo-impar");

                PartidoListaController controller = loader.getController();
                controller.setPartido(partido, fondo);
            }

        } catch (DAOException e) {
            Util.mostrarMensaje(e.getMensaje(), Alert.AlertType.ERROR);
            LOGGER.severe(Util.printStackTrace(e));
        } catch (IOException e) {
            Util.mostrarMensaje("Ha ocurrido un error al cargar un partido", Alert.AlertType.ERROR);
            LOGGER.severe(Util.printStackTrace(e));
        }
    }
}
