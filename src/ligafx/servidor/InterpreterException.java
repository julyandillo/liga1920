package ligafx.servidor;

public class InterpreterException extends Exception {
    private final String mensaje;

    public InterpreterException(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return this.mensaje;
    }
}
