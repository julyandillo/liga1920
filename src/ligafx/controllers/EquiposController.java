package ligafx.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import ligafx.dao.DAOException;
import ligafx.dao.DAOManager;
import ligafx.modelos.Equipo;
import ligafx.util.Util;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EquiposController implements Initializable {

    private static final Logger LOGGER = Logger.getLogger(EquiposController.class.getName());

    @FXML
    private ListView<String> listViewEquipos;

    @FXML
    private Label labelNombreClub;

    @FXML
    private Label labelPresidente;

    @FXML
    private Label labelEntrenador;

    @FXML
    private Label labelFundacion;

    @FXML
    private Label labelTelefono;

    @FXML
    private Label labelDireccion;

    @FXML
    private Label labelWeb;

    @FXML
    private FlowPane flowPaneDetalles;

    @FXML
    private ImageView escudo;

    @FXML
    private Label labelNombreEstadio;

    @FXML
    private Label labelCapacidadEstadio;

    @FXML
    private Label labelDimensionesEstadio;

    @FXML
    private Label labelConstruccionEstadio;

    @FXML
    private ImageView imageviewEstadio;

    private Equipo equipo;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            flowPaneDetalles.setVisible(false);

            ObservableList<String> equipos = FXCollections.observableArrayList(
                    DAOManager.getEquipoDAO().cargarNombres());

            listViewEquipos.setItems(equipos);

            listViewEquipos.getSelectionModel().selectedItemProperty().addListener(
                    ((observable, old, newValue) -> cargarDetalles(newValue))
            );

        } catch (DAOException e) {
            Util.mostrarMensaje(e.getMensaje(), Alert.AlertType.ERROR);
            LOGGER.log(Level.SEVERE, e.getMensaje());
        }
    }

    private void cargarDetalles(String nombre) {
        try {
            equipo = DAOManager.getEquipoDAO().cargar(nombre);
            equipo.getJugadores().addAll(DAOManager.getJugadorDAO().cargarPlantilla(equipo.getId()));

            labelNombreClub.setText(equipo.getNombreClub());
            labelDireccion.setText(equipo.getDireccion());
            labelEntrenador.setText(equipo.getEntrenador());
            labelFundacion.setText(String.valueOf(equipo.getFundacion()));
            labelPresidente.setText(equipo.getPresidente());
            labelTelefono.setText(equipo.getTelefono());
            labelWeb.setText(equipo.getWeb());

            escudo.setImage(new Image("/ligafx/resources/escudos/" + equipo.getEscudo()));

            labelNombreEstadio.setText(equipo.getEstadio().getNombre());
            labelCapacidadEstadio.setText(String.valueOf(equipo.getEstadio().getCapacidad()));
            labelDimensionesEstadio.setText(equipo.getEstadio().getDimensiones());
            labelConstruccionEstadio.setText(String.valueOf(equipo.getEstadio().getConstruccion()));
            imageviewEstadio.setImage(new Image("/ligafx/resources/estadios/" + equipo.getEstadio().getImagen()));

            flowPaneDetalles.setVisible(true);
        } catch (DAOException e) {
            Util.mostrarMensaje(e.getMensaje(), Alert.AlertType.ERROR);
            LOGGER.log(Level.SEVERE, e.getMensaje());
            LOGGER.log(Level.SEVERE, Util.printStackTrace(e));
        }
    }

    @FXML
    private void cargarPlantilla() {
        try {
            // si se hace asi no se puede obtener el controlador de la vista, y es necesario para pasarle el equipo
            //Parent root = FXMLLoader.load(getClass().getResource("../views/plantilla.fxml"));
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../views/plantilla.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Plantilla de " + equipo.getNombre());
            stage.getIcons().add(new Image(getClass().getResource("../resources/logo2.png").toExternalForm()));
            stage.setScene(new Scene(loader.load()));

            PlantillaController controller = loader.getController();
            controller.setEquipo(equipo);

            stage.showAndWait();


        } catch (IOException e) {
            Util.mostrarMensaje(e.getMessage(), Alert.AlertType.ERROR);
            LOGGER.severe("ERROR AL GENERAR LA VISTA DE LA PLANTILLA" + Util.printStackTrace(e));
        }

    }
}
