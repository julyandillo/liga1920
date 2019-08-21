package ligafx.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Util {

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
}
