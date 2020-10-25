package ligafx.controllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import ligafx.dao.DAOException;
import ligafx.dao.DAOManager;
import ligafx.decoradores.EstadisticaDecorator;
import ligafx.modelos.Estadistica;
import ligafx.util.Util;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClasificacionController implements Initializable {

    private static final Logger LOGGER = Logger.getLogger(ClasificacionController.class.getName());

    @FXML
    TableView<EstadisticaDecorator> tableViewClasificacion;

    @FXML
    TableColumn<String, EstadisticaDecorator> tableColumnEquipo;

    @FXML
    TableColumn<String, EstadisticaDecorator> tableColumnPuntos;

    @FXML
    TableColumn<String, EstadisticaDecorator> tableColumnJugados;

    @FXML
    TableColumn<String, EstadisticaDecorator> tableColumnGanados;

    @FXML
    TableColumn<String, EstadisticaDecorator> tableColumnEmpatados;

    @FXML
    TableColumn<String, EstadisticaDecorator> tableColumnPerdidos;

    @FXML
    TableColumn<String, EstadisticaDecorator> tableColumnGolesFavor;

    @FXML
    TableColumn<String, EstadisticaDecorator> tableColumnGolesContra;

    @FXML
    TableColumn<String, EstadisticaDecorator> tableColumnGolaverage;

    @FXML
    TableColumn<String, Integer> tableColumnPosicion;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            tableColumnEquipo.setCellValueFactory(new PropertyValueFactory<>("equipo"));
            tableColumnPuntos.setCellValueFactory(new PropertyValueFactory<>("puntos"));
            tableColumnJugados.setCellValueFactory(new PropertyValueFactory<>("jugados"));
            tableColumnGanados.setCellValueFactory(new PropertyValueFactory<>("ganados"));
            tableColumnPerdidos.setCellValueFactory(new PropertyValueFactory<>("empatados"));
            tableColumnEmpatados.setCellValueFactory(new PropertyValueFactory<>("perdidos"));
            tableColumnGolesFavor.setCellValueFactory(new PropertyValueFactory<>("golesFavor"));
            tableColumnGolesContra.setCellValueFactory(new PropertyValueFactory<>("golesContra"));
            tableColumnGolaverage.setCellValueFactory(new PropertyValueFactory<>("golaverage"));
            tableColumnPosicion.setCellValueFactory(new PropertyValueFactory<>("posicion"));

            /*tableColumnPosicion.setCellValueFactory(column->
                    new ReadOnlyObjectWrapper<>(
                            tableViewClasificacion.getItems().indexOf(
                                    column.getValue())+1));*/

            tableColumnPosicion.setSortable(false);

            List<EstadisticaDecorator> clasificacion = DAOManager.getEstadisticaDAO().cargarUltima();

            if (clasificacion.isEmpty()) {
                clasificacion = new ArrayList<>();
                List<String> equipos = DAOManager.getEquipoDAO().cargarNombres();

                int posicion = 1;
                for(String equipo : equipos) {
                    clasificacion.add(new EstadisticaDecorator(new Estadistica(true), equipo, posicion));
                    posicion++;
                }
            }

            tableViewClasificacion.getItems().addAll(clasificacion);

        } catch (DAOException e) {
            Util.mostrarMensaje(e.getMensaje(), Alert.AlertType.ERROR);
            LOGGER.log(Level.SEVERE, e.getMensaje());
        }
    }
}
