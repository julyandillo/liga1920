package ligafx.botones;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;

import java.util.logging.Logger;

/**
 * para esta aplicacion no es necesario que haya un invocador que sea el que controle los comandos a ejecutar,
 * el mismo boton es el invocador de la orden cuando se hace click sobre él.
 *
 * Se podría haber hecho un inovocador, que no es mas que un set de keys y ordenes, y segun la key en la se haga
 * click se ejecuta una orden
 */

public class BotonInvoker extends Button {

    private static final Logger LOGGER = Logger.getLogger(BotonInvoker.class.getName());

    private OrdenBoton boton; // es el receptor de la orden (patron command)
    private BorderPane borderPane;

    public BotonInvoker(BorderPane borderPane, OrdenBoton boton) {
        super();
        this.boton = boton;
        this.borderPane = borderPane;

        this.setTooltip(new Tooltip(boton.getNombre()));

        this.getStyleClass().addAll("boton-menu:hover", "boton-menu", boton.getIcono());
    }

    @Override
    public void fire() {
        boton.ejecutar(borderPane, LOGGER);
    }
}
