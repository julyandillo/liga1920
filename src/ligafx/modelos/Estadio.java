package ligafx.modelos;

public class Estadio extends Entidad {

    private String nombre;

    private String dimensiones;

    private int capacidad;

    private int construccion;

    private String imagen;

    public Estadio(int id, String nombre, int capacidad, String dimensiones, int construccion, String imagen) {
        super(id);

        this.nombre = nombre;
        this.capacidad = capacidad;
        this.dimensiones = dimensiones;
        this.construccion = construccion;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getConstruccion() {
        return construccion;
    }

    public void setConstruccion(int construccion) {
        this.construccion = construccion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
