package ligafx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ligafx.modelos.Jugador;

import java.net.URL;
import java.util.ResourceBundle;

public class JugadorListaController implements Initializable {

    @FXML
    private Label labelDorsal;

    @FXML
    private Label labelNombre;

    @FXML
    private ImageView imageView;

    private Jugador jugador;

    private PlantillaController parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // no es necesario hacer nada cuando se crea, el jugador lo carga el controlador
    }

    public void setJugador(Jugador jugador, PlantillaController parent) {
        this.jugador = jugador;
        this.parent = parent;

        this.labelDorsal.setText(String.valueOf(jugador.getDorsal()));
        this.labelNombre.setText(jugador.getApodo());
        this.imageView.setImage(new Image(jugador.getImagen()));
    }

    @FXML
    private void click() {
        parent.cargarDetallesJugador(jugador);
    }
}
