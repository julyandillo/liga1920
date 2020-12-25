package ligafx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ligafx.modelos.Partido;
import ligafx.util.Util;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class PartidoListaController implements Initializable {

    private static final Logger LOGGER = Logger.getLogger(PartidoListaController.class.getName());

    @FXML
    Label labelEquipoLocal;

    @FXML
    Label labelEquipoVisitante;

    @FXML
    Label labelGolesLocal;

    @FXML
    Label labelGolesVisitante;

    @FXML
    ImageView imageViewLocal;

    @FXML
    ImageView imageViewVisitante;

    @FXML
    AnchorPane anchorPane;

    private Partido partido;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // no es necesario que haga nada cuando se carga
    }

    public void setPartido(Partido partido, String fondo) {
        anchorPane.getStyleClass().add(fondo);

        labelEquipoLocal.setText(partido.getEquipoLocal().getNombre().toUpperCase());
        labelEquipoLocal.getStyleClass().add("label-amarillo");
        imageViewLocal.setImage(new Image("/ligafx/resources/escudos/" + partido.getEquipoLocal().getEscudo()));

        labelEquipoVisitante.setText(partido.getEquipoVisitante().getNombre().toUpperCase());
        labelEquipoVisitante.getStyleClass().add("label-amarillo");
        imageViewVisitante.setImage(new Image("/ligafx/resources/escudos/" +
                partido.getEquipoVisitante().getEscudo()));


        if (partido.disputado()) {
            labelGolesLocal.setText(String.valueOf(partido.getGolesLocal()));
            labelGolesVisitante.setText(String.valueOf(partido.getGolesVisitante()));
        } else {
            labelGolesVisitante.setText("");
            labelGolesLocal.setText("");
        }

        labelGolesLocal.getStyleClass().add("label-azul-claro");
        labelGolesVisitante.getStyleClass().add("label-azul-claro");

        this.partido = partido;

    }

    public void cargarPartido() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../views/partidoDetalles.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Partido " + partido.getId());
            stage.getIcons().add(new Image(getClass().getResource("../resources/logo2.png").toExternalForm()));
            stage.setScene(new Scene(loader.load()));

            PartidoDetallesController controller = loader.getController();
            controller.cargarPartido(partido);

            stage.showAndWait();

        } catch (IOException e) {
            Util.mostrarMensaje(e.getMessage(), Alert.AlertType.ERROR);
            LOGGER.severe("ERROR AL GENERAR LA VISTA DE LA PLANTILLA" + Util.printStackTrace(e));
        }

    }
}
