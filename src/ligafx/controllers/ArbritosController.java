package ligafx.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.FlowPane;
import ligafx.dao.DAOException;
import ligafx.dao.DAOManager;
import ligafx.modelos.Arbitro;
import ligafx.util.Util;

import java.net.URL;
import java.util.ResourceBundle;

public class ArbritosController implements Initializable {

    @FXML
    private ListView<String> listaArbitros;

    @FXML
    private Label partidos;

    @FXML
    private Label partidosVAR;

    @FXML
    private Label tarjetasAmarillas;

    @FXML
    private Label tarjetasRojas;

    @FXML
    private Label penaltis;

    @FXML
    private FlowPane detalles;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            detalles.setVisible(false);

            ObservableList<String> arbitros = FXCollections.observableList(
                    DAOManager.getArbitroDAO().cargarNombres()
            );

            listaArbitros.setItems(arbitros);
            listaArbitros.getSelectionModel().selectedItemProperty().addListener(
                    ((observable, old, newValue) -> mostrarDetalles(newValue))
            );

        } catch (DAOException e) {
            Util.mostrarMensaje(e.getMensaje(), Alert.AlertType.ERROR);
        }
    }

    private void mostrarDetalles(String nombreArbitro) {
        try {
            Arbitro arbitro = DAOManager.getArbitroDAO().cargar(nombreArbitro);

            partidos.setText("Partidos arbitrados: " + arbitro.getPartidos());
            partidosVAR.setText("Partidos en VAR: " + arbitro.getPartidosVAR());
            tarjetasAmarillas.setText("amarillas: " + arbitro.getTarjetasAmarillas());
            tarjetasRojas.setText("rojas: " + arbitro.getTarjetasRojas());
            penaltis.setText("Penaltis señalados: " + arbitro.getPenaltis());

            detalles.setVisible(true);

        } catch (DAOException e) {
            Util.mostrarMensaje(e.getMensaje(), Alert.AlertType.ERROR);
        }
    }
}
