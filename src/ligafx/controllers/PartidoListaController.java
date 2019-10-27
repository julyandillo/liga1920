package ligafx.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import ligafx.modelos.Partido;

import java.net.URL;
import java.util.ResourceBundle;

public class PartidoListaController implements Initializable {

    @FXML
    Label labelEquipoLocal;

    @FXML
    Label labelEquipoVisitante;

    @FXML
    Label labelGolesLocal;

    @FXML
    Label labelGolesVisitante;

    @FXML
    ImageView imageViewLocal;

    @FXML
    ImageView imageViewVisitante;

    @FXML
    AnchorPane anchorPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // no es necesario que haga nada cuando se carga
    }

    public void setPartido(Partido partido, String fondo) {
        anchorPane.getStyleClass().add(fondo);

        labelEquipoLocal.setText(partido.getEquipoLocal().getNombre().toUpperCase());
        labelEquipoLocal.getStyleClass().add("label-amarillo");
        imageViewLocal.setImage(new Image("/ligafx/resources/escudos/" + partido.getEquipoLocal().getEscudo()));

        labelEquipoVisitante.setText(partido.getEquipoVisitante().getNombre().toUpperCase());
        labelEquipoVisitante.getStyleClass().add("label-amarillo");
        imageViewVisitante.setImage(new Image("/ligafx/resources/escudos/" +
                partido.getEquipoVisitante().getEscudo()));


        if (partido.disputado()) {
            labelGolesLocal.setText(String.valueOf(partido.getGolesLocal()));
            labelGolesVisitante.setText(String.valueOf(partido.getGolesVisitante()));
        } else {
            labelGolesVisitante.setText("");
            labelGolesLocal.setText("");
        }

        labelGolesLocal.getStyleClass().add("label-azul-claro");
        labelGolesVisitante.getStyleClass().add("label-azul-claro");
    }
}
