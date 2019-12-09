package ligafx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import ligafx.menu.BotonInvoker;
import ligafx.menu.BotonMenu;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class MainController implements Initializable {

    private static final Logger LOGGER = Logger.getLogger(MainController.class.getName());

    @FXML
    private ImageView logoImageView;

    @FXML
    private VBox vboxMenu;

    @FXML
    private BorderPane mainBorderPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logoImageView.setImage(new Image("/ligafx/resources/logo.png"));

        vboxMenu.getChildren().add(new BotonInvoker(mainBorderPane,
                new BotonMenu("clasificacion.fxml", "boton-clasificacion", "Clasificacion")));
        vboxMenu.getChildren().add(new BotonInvoker(mainBorderPane,
                new BotonMenu("calendario.fxml", "boton-calendario", "Calendario")));
        vboxMenu.getChildren().add(new BotonInvoker(mainBorderPane,
                new BotonMenu("equipos.fxml", "boton-equipos", "Equipos")));
        vboxMenu.getChildren().add(new BotonInvoker(mainBorderPane,
                new BotonMenu("arbitros.fxml", "boton-arbitros", "Arbitros")));
        vboxMenu.getChildren().add(new BotonInvoker(mainBorderPane,
                new BotonMenu("estadisticas.fxml", "boton-estadisticas", "Estadisticas")));
        vboxMenu.getChildren().add(new BotonInvoker(mainBorderPane,
                new BotonMenu("servidor.fxml", "boton-servidor", "Control del servidor")));
    }

    /*
    @FXML
    private void buttonAction(ActionEvent event) {
        Object source = event.getSource();
        String url = "";

        if (source.equals(botonClasificacion)) {
            url = "../views/clasificacion.fxml";
        } else if (source.equals(botonCalendario)) {
            url = "../views/calendario.fxml";
        } else if (source.equals(botonEquipos)) {
            url = "../views/equipos.fxml";
        } else if (source.equals(botonArbitros)) {
            url = "../views/arbitros.fxml";
        } else if (source.equals(botonEstadisticas)) {
            url = "../views/estadisticas.fxml";
        }

        try {
            mainBorderPane.setCenter(FXMLLoader.load(getClass().getResource(url)));
        } catch (IOException | RuntimeException ex) {
            Util.mostrarMensaje("Ha ocurrido un error al cargar el panel", Alert.AlertType.ERROR).showAndWait();
            LOGGER.severe(Util.printStackTrace(ex));
        }
    }
    */
}
