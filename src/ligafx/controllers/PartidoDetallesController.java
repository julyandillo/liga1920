package ligafx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ligafx.dao.DAOException;
import ligafx.dao.DAOManager;
import ligafx.decoradores.CambioDecorator;
import ligafx.decoradores.GolDecorator;
import ligafx.decoradores.TarjetaDecorator;
import ligafx.modelos.Gol;
import ligafx.modelos.Partido;
import ligafx.modelos.TipoEquipo;
import ligafx.modelos.TipoTarjeta;
import ligafx.util.Util;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class PartidoDetallesController implements Initializable {

    private static final Logger LOGGER = Logger.getLogger(PartidoDetallesController.class.getName());

    @FXML
    Label fecha;

    @FXML
    Label equipoLocal;

    @FXML
    Label equipoVisitante;

    @FXML
    Label jornada;

    @FXML
    ImageView escudoLocal;

    @FXML
    Label arbitro;

    @FXML
    Label arbitroVAR;

    @FXML
    Label golesLocal;

    @FXML
    Label golesVisitante;

    @FXML
    ImageView escudoVisitante;

    @FXML
    VBox boxGolesLocal;

    @FXML
    VBox boxGolesVisitante;

    @FXML
    VBox tarjetasLocal;

    @FXML
    VBox tarjetasVisitante;

    @FXML
    VBox cambiosLocal;

    @FXML
    VBox cambiosVisitante;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // no es necesario que haga nada al cargar
    }

    public void cargarPartido(Partido partido) {
        SimpleDateFormat formato = new SimpleDateFormat("EEEE d 'de' MMMMM, H:mm");
        fecha.setText(formato.format(partido.getFecha()));

        jornada.setText("JORNADA " + partido.getJornada());
        arbitro.setText("Árbitro: " + partido.getArbitro().getNombre());
        arbitroVAR.setText("VAR: " + partido.getArbitroVar().getNombre());

        equipoLocal.setText(partido.getEquipoLocal().getNombre());
        escudoLocal.setImage(new Image("/ligafx/resources/escudos/" + partido.getEquipoLocal().getEscudo()));;

        equipoVisitante.setText(partido.getEquipoVisitante().getNombre());
        escudoVisitante.setImage(new Image("/ligafx/resources/escudos/" + partido.getEquipoVisitante().getEscudo()));

        golesLocal.setText(String.valueOf(partido.getGolesLocal()));
        golesVisitante.setText(String.valueOf(partido.getGolesVisitante()));

        try {
            Map<TipoEquipo, List<GolDecorator>> goles = DAOManager.getGolDAO().cargarTodosPorPartido(partido.getId(),
                    partido.getEquipoLocal().getId(), partido.getEquipoVisitante().getId());
            for (Map.Entry<TipoEquipo, List<GolDecorator>> golesEquipo : goles.entrySet()) {
                cargarGoles(golesEquipo.getKey(), golesEquipo.getValue());
            }

            Map<TipoEquipo, List<TarjetaDecorator>> tarjetas = DAOManager.getTarjetaDAO().cargarTodasPorPartido(partido.getId());
            for (Map.Entry<TipoEquipo, List<TarjetaDecorator>> tarjetasEquipo : tarjetas.entrySet()) {
                cargarTarjetas(tarjetasEquipo.getKey(), tarjetasEquipo.getValue());
            }

            Map<TipoEquipo, List<CambioDecorator>> cambios = DAOManager.getCambioDAO().cargarTodosPorPartido(partido.getId());
            for (Map.Entry<TipoEquipo, List<CambioDecorator>> cambiosEquipo : cambios.entrySet()) {
                cargarCambios(cambiosEquipo.getKey(), cambiosEquipo.getValue());
            }

        } catch (DAOException exception) {
            Util.mostrarMensaje(exception.getMensaje(), Alert.AlertType.ERROR);
        }
    }

    private void cargarGoles(TipoEquipo equipo, List<GolDecorator> goles) {
        for (GolDecorator gol : goles) {
            ImageView icono = creaIcono("/ligafx/resources/icons/balon.png");
            if ((equipo == TipoEquipo.LOCAL && !gol.getGol().isPropiaMeta()) ||
                    (equipo == TipoEquipo.VISITANTE && gol.getGol().isPropiaMeta())) {
                boxGolesLocal.getChildren().add(
                        creaDetalle(new Label(gol.infoGolConJugadorAntes()), Pos.CENTER_RIGHT, icono));
            } else {
                boxGolesVisitante.getChildren().add(
                        creaDetalle(new Label(gol.infoGolConJugadorDespues()), Pos.CENTER_LEFT, icono));
            }
        }
    }

    private ImageView creaIcono(String ruta) {
        ImageView icono = new ImageView(new Image(ruta));
        icono.setFitHeight(15);
        icono.setFitWidth(15);

        return icono;
    }

    private HBox creaDetalle(Label contenido, Pos alineacion, ImageView icono) {
        HBox box = new HBox();
        box.setAlignment(alineacion);

        if (alineacion == Pos.CENTER_LEFT) {
            box.getChildren().add(icono);
        }

        box.getChildren().add(contenido);

        if (alineacion == Pos.CENTER_RIGHT) {
            box.getChildren().add(icono);
        }

        return box;
    }

    private void cargarTarjetas(TipoEquipo equipo, List<TarjetaDecorator> tarjetas) {
        for (TarjetaDecorator tarjeta : tarjetas) {
            ImageView iconoTarjeta = tarjeta.getTarjeta().getTipo() == TipoTarjeta.AMARILLA ?
                    creaIcono("/ligafx/resources/icons/tarjeta_amarilla.png") :
                    creaIcono("/ligafx/resources/icons/tarjeta_roja.png");

            if (equipo == TipoEquipo.LOCAL) {
                tarjetasLocal.getChildren().add(
                        creaDetalle(new Label(tarjeta.getInfoTarjetaConJugadorAntes()), Pos.CENTER_RIGHT, iconoTarjeta)
                );
            } else {
                tarjetasVisitante.getChildren().add(
                        creaDetalle(new Label(tarjeta.getInfoTarjetaConJugadorDespues()), Pos.CENTER_LEFT, iconoTarjeta)
                );
            }
        }
    }

    private void cargarCambios(TipoEquipo equipo, List<CambioDecorator> cambios) {
        for (CambioDecorator cambio : cambios) {
            if (equipo == TipoEquipo.LOCAL) {
                cambiosLocal.getChildren().add(detalleCambio(cambio.getCambio().getMinuto(), cambio.getEntra(),
                        cambio.getSale()));
            } else {
                cambiosVisitante.getChildren().add(detalleCambio(cambio.getCambio().getMinuto(), cambio.getEntra(),
                        cambio.getSale()));
            }
        }
    }

    private HBox detalleCambio(int minuto, String entra, String sale) {
        HBox hbox = new HBox();
        hbox.getChildren().add(new Label(entra));
        hbox.getChildren().add(creaIcono("/ligafx/resources/icons/cambio.png"));
        hbox.getChildren().add(new Label(sale));
        hbox.getChildren().add(new Label(" " + minuto + "'"));

        return hbox;
    }
}
