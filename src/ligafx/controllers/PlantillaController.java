package ligafx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import ligafx.modelos.Equipo;
import ligafx.modelos.Jugador;
import ligafx.modelos.Posicion;

import java.net.URL;
import java.util.ResourceBundle;

public class PlantillaController implements Initializable {

    @FXML
    private Label labelEquipo;

    @FXML
    private ImageView imageViewEscudo;

    @FXML
    private ScrollPane scrollPanePorteros;

    @FXML
    private VBox vBoxPorteros;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

    }
}
