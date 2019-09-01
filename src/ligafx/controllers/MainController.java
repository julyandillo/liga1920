package ligafx.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import ligafx.util.Util;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class MainController implements Initializable {

    private final static Logger LOGGER = Logger.getLogger(MainController.class.getName());

    @FXML
    private ImageView logoImageView;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private Button botonClasificacion;

    @FXML
    private Button botonCalendario;

    @FXML
    private Button botonEquipos;

    @FXML
    private Button botonArbitros;

    @FXML
    private Button botonEstadisticas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logoImageView.setImage(new Image("file:resources/logo.png"));
    }

    @FXML
    private void buttonAction(ActionEvent event) {
        Object source = event.getSource();
        String url = "";

        if (source.equals(botonClasificacion)) {
            url = "/vistas/clasificacion.xml";
        } else if (source.equals(botonCalendario)) {
            url = "/vistas/calendario.xml";
        } else if (source.equals(botonEquipos)) {
            url = "/vistas/equipos.xml";
        } else if (source.equals(botonArbitros)) {
            url = "/vistas/arbitros.xml";
        } else if (source.equals(botonEstadisticas)) {
            url = "/vistas/estadisticas.xml";
        }

        try {
            mainBorderPane.setCenter(FXMLLoader.load(getClass().getResource(url)));
        } catch (IOException | RuntimeException ex) {
            Util.mostrarMensaje("Ha ocurrido un error al cargar el panel", Alert.AlertType.ERROR).showAndWait();
            LOGGER.severe(Util.printStackTrace(ex));
        }
    }
}
