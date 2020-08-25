package ligafx.botones;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ligafx.controllers.EquiposController;

public class BotonEquipoInvoker extends Button {

    private final EquiposController controller;
    private final String equipo;

    public BotonEquipoInvoker(EquiposController controller, String equipo, String escudo) {
        super();
        this.controller = controller;
        this.equipo = equipo;

        this.setTooltip(new Tooltip(equipo));
        this.getStyleClass().addAll("boton-equipo:hover", "boton-equipo");

        ImageView icono = new ImageView(new Image("/ligafx/resources/escudos/" + escudo));
        icono.setFitHeight(28);
        icono.setFitWidth(28);

        this.setGraphic(icono);
    }

    @Override
    public void fire() {
        this.controller.cargarDetalles(this.equipo);
    }
}
