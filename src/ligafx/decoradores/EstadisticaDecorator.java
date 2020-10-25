package ligafx.decoradores;

import ligafx.modelos.Estadistica;
import ligafx.modelos.TipoParametro;

public class EstadisticaDecorator {

    private final Estadistica estadistica;

    private final String equipo;

    // lugar que ocupa el equipo en la clasificacion de la jornada
    private final Integer posicion;

    public EstadisticaDecorator(Estadistica estadistica, String equipo, Integer posicion) {
        this.estadistica = estadistica;
        this.equipo = equipo;
        this.posicion = posicion;
    }

    public Estadistica getEstadistica() {
        return estadistica;
    }

    public String getEquipo() {
        return equipo;
    }

    public Integer getPuntos() { return estadistica.getParametro(TipoParametro.PUNTOS).getTotal(); }

    public Integer getJugados() { return estadistica.getParametro(TipoParametro.JUGADOS).getTotal(); }

    public Integer getGanados() { return estadistica.getParametro(TipoParametro.GANADOS).getTotal(); }

    public Integer getPerdidos() { return estadistica.getParametro(TipoParametro.PERDIDOS).getTotal(); }

    public Integer getEmpatados() { return estadistica.getParametro(TipoParametro.EMPATADOS).getTotal(); }

    public Integer getGolesFavor() { return estadistica.getParametro(TipoParametro.GOLES_FAVOR).getTotal(); }

    public Integer getGolesContra() { return estadistica.getParametro(TipoParametro.GOLES_CONTRA).getTotal(); }

    public Integer getGolaverage() {
        return estadistica.getParametro(TipoParametro.GOLES_FAVOR).getTotal() -
                estadistica.getParametro(TipoParametro.GOLES_CONTRA).getTotal();
    }

    public Integer getPosicion() {
        return this.posicion;
    }
}
