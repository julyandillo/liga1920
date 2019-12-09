package ligafx.modelos;

public class Arbitro extends Entidad {

    private String nombre;

    public Arbitro(int id) {
        super(id);

        this.nombre = "";
    }

    public Arbitro(int id, String nombre) {
        super(id);
        this.nombre = nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }
}
