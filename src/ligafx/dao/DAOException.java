package ligafx.dao;

public class DAOException extends Exception {
    private final String mensaje;
    private final String detalles;
    private final String sql;

    public DAOException(String mensaje, Exception e) {
        this.mensaje = mensaje;
        this.detalles = e.getMessage();
        this.sql = "";
    }

    public DAOException(String mensaje, String sql, Exception e) {
        this.mensaje = mensaje;
        this.detalles = e.getMessage();
        this.sql = sql;
    }

    public String getMensaje() {
        if (this.sql.isEmpty()) {
            return this.mensaje + "\n" + detalles;
        }

        return this.mensaje + "\n" + this.sql + "\n" + detalles;
    }
}
