package ligafx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ligafx.dao.DAOException;
import ligafx.dao.DAOManager;
import ligafx.modelos.Equipo;
import ligafx.modelos.Jugador;
import ligafx.modelos.Posicion;
import ligafx.util.Util;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class PlantillaController implements Initializable {

    private static final Logger LOGGER = Logger.getLogger(PlantillaController.class.getName());

    @FXML
    private Label labelEquipo;

    @FXML
    private ImageView imageViewEscudo;

    @FXML
    private VBox vBoxPorteros;

    @FXML
    private VBox vBoxDefensas;

    @FXML
    private VBox vBoxCentrocampistas;

    @FXML
    private VBox vBoxDelanteros;

    @FXML
    private AnchorPane detalles;

    @FXML
    private Label labelNombreCompleto;

    @FXML
    private Label labelGoles;

    @FXML
    private Label labelTarjetas;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        detalles.setVisible(false);
    }


    public void setEquipo(Equipo equipo) {
        labelEquipo.setText(equipo.getNombreClub());
        imageViewEscudo.setImage(new Image("/ligafx/resources/escudos/" + equipo.getEscudo()));

        for (Jugador jugador : equipo.getJugadores()) {
            cargarJugador(jugador);
        }
    }

    /**
     * En función de la posición del jugador lo colocará en su panel correspondiente
     *
     * @param jugador objeto con la información del jugador
     */
    private void cargarJugador(Jugador jugador) {
        /*
         aunque el fxml es siempre el mismo para todos los jugadores, se tiene que usar un nuevo loader para
         cada jugador y su propio controlador
         */
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(("../views/jugadorLista.fxml")));

        try {
            switch (jugador.getPosicion()) {
                case PORTERO:
                    vBoxPorteros.getChildren().add(loader.load());
                    break;
                case DEFENSA:
                    vBoxDefensas.getChildren().add(loader.load());
                    break;
                case CENTROCAMPISTA:
                    vBoxCentrocampistas.getChildren().add(loader.load());
                    break;
                case DELANTERO:
                    vBoxDelanteros.getChildren().add(loader.load());
                    break;
            }

            JugadorListaController controller = loader.getController();
            controller.setJugador(jugador,this);

        } catch (IOException e) {
            Util.mostrarMensaje("Error al cargar un jugador.\n" + e.getMessage(), Alert.AlertType.ERROR);
            LOGGER.severe("ERROR AL CARGAR UN JUGADOR\n" + Util.printStackTrace(e));
        }
    }

    public void cargarDetallesJugador(Jugador jugador) {
        labelNombreCompleto.setText(jugador.getNombre());
        try {
            jugador.getGoles().clear();
            jugador.getGoles().addAll(DAOManager.getGolDAO().cargarTodosPorJugador(jugador.getId()));

            labelGoles.setText("Goles: " + jugador.getGoles().size());

            jugador.getTarjetas().clear();
            jugador.getTarjetas().addAll(DAOManager.getTarjetaDAO().cargarTodasPorJugador(jugador.getId()));
            labelTarjetas.setText("Tarjetas: " + jugador.getTarjetas().size());

        } catch (DAOException e) {
            Util.mostrarMensaje(e.getMensaje(), Alert.AlertType.ERROR);
        }

        detalles.setVisible(true);
    }
}
