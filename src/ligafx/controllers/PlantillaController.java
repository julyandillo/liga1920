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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

    @FXML
    private Label vecesCambiado;

    @FXML
    private Label vecesEntra;

    @FXML
    private Label paisNacimiento;

    @FXML
    private Label nacionalidad;

    @FXML
    private Label peso;

    @FXML
    private Label altura;

    @FXML
    private Label dorsal;

    @FXML
    private Label fechaNacimiento;

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

    public void cargarDetallesJugador(int idJugador) {
        try {
            Jugador jugador = DAOManager.getJugadorDAO().cargar(idJugador);

            labelNombreCompleto.setText(jugador.getNombre());
            dorsal.setText("Dorsal: " + jugador.getDorsal());

            SimpleDateFormat formatoFecha = new SimpleDateFormat("d-MM-yyyy");
            fechaNacimiento.setText("Fecha de nacimiento: " + formatoFecha.format(jugador.getFechaNacimiento()));

            paisNacimiento.setText("Pais de nacimiento: " + jugador.getPaisNacimiento());
            nacionalidad.setText("Nacionalidad: " + jugador.getNacionalidad());
            peso.setText("Peso: " + jugador.getPeso() + " kg");
            altura.setText("Altura: " + jugador.getAltura() + " cm");

            labelGoles.setText("Goles: " + jugador.getGoles().size() + " (" + jugador.getNumeroGolesDePenalti()
                    + " penaltis)");

            labelTarjetas.setText("Tarjetas: " + jugador.getTarjetas().size());

            vecesCambiado.setText("Número de veces cambiado: " + jugador.getVecesCambiado());
            vecesEntra.setText("Número de veces que entra al campo: " + jugador.getVecesEntra());

        } catch (DAOException e) {
            Util.mostrarMensaje(e.getMensaje(), Alert.AlertType.ERROR);
        }

        detalles.setVisible(true);
    }
}
