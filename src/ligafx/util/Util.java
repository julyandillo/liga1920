package ligafx.util;

import javafx.scene.control.Alert;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Util {

    private static Alert alert = new Alert(Alert.AlertType.NONE);

    /**
     * Constructor privado de la clase, se añade para que java no cree uno por defecto vacio.
     * Esta clase no se puede instanciar
     */
    private Util() {
        throw new IllegalStateException("La clase 'Util' no se puede instanciar");
    }

    /**
     * Metodo que convierte a String el stack trace de una exception, el stack trace solo se puede imprimir, con este
     * metodo se puede utilizar para mostrarlo por pantalla o almacenarlo en un log por ejemplo
     * @param e Exception
     * @return String con el stack trace de la excepcion
     */
    public static String printStackTrace(Exception e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        e.printStackTrace(printWriter);

        return stringWriter.toString();
    }

    public static Alert mostrarMensaje(String mensaje, Alert.AlertType tipo) {
        alert.setTitle("La Liga 2020/2021");
        alert.setAlertType(tipo);
        alert.setContentText(mensaje);

        return alert;
    }
}
