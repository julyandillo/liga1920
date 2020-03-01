package ligafx.decoradores;

import ligafx.modelos.Estadistica;

public class EstadisticaDecorator {

    private Estadistica estadistica;

    private String equipo;

    public EstadisticaDecorator(Estadistica estadistica, String equipo) {
        this.estadistica = estadistica;
        this.equipo = equipo;
    }

    public Estadistica getEstadistica() {
        return estadistica;
    }

    public String getEquipo() {
        return equipo;
    }
}
