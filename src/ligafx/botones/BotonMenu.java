package ligafx.botones;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import ligafx.util.Util;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * clase para implementar un boton utilizando el patron COMMAND. Cada boton será una instancia de esta clase
 * y el menu se hará con un invocador que tendra una lista de botones que al hacer click ejecutaran el metodo click.
 *
 * Se hace abstracta porque la lógica para todos los botones es la misma y así nos ahorramos escribir siempre el mismo
 * código en todos los botones, solo cambian los valores nombre, icono y ruta del fmxl
 */

public  class BotonMenu implements OrdenBoton {

    private String ruta = null;
    private String icono = null;
    private String nombre = null;

    public BotonMenu(String fxml, String icono, String nombre) {
        this.icono = icono; // clase css para poner el icono como fondo
        this.nombre = nombre;
        this.ruta = "../views/" + fxml;
    }

    @Override
    public String getIcono() {
        return icono;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void ejecutar(BorderPane mainBorderPane, Logger logger) {
        try {
            mainBorderPane.setCenter(FXMLLoader.load(getClass().getResource(ruta)));
        } catch (IOException | RuntimeException ex) {
            Util.mostrarMensaje("Ha ocurrido un error el menu", Alert.AlertType.ERROR).showAndWait();
            logger.severe(Util.printStackTrace(ex));
        }
    }
}
