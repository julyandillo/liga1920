package ligafx.builders;

import ligafx.modelos.Equipo;
import ligafx.modelos.Estadio;
import ligafx.modelos.Estadistica;
import ligafx.modelos.Jugador;

import java.util.List;

public class EquipoBuilder {

    private Equipo equipo;

    public EquipoBuilder() {
        this.equipo = new Equipo(0);
    }

    public EquipoBuilder id(int id) {
        this.equipo.setId(id);

        return this;
    }

    public EquipoBuilder nombre(String nombre) {
        this.equipo.setNombre(nombre);

        return this;
    }

    public EquipoBuilder nombreClub(String nombreClub) {
        this.equipo.setNombreClub(nombreClub);

        return this;
    }

    public EquipoBuilder presidente(String presidente) {
        this.equipo.setPresidente(presidente);

        return this;
    }

    public EquipoBuilder entrenador(String entrenador) {
        this.equipo.setEntrenador(entrenador);

        return this;
    }

    public EquipoBuilder fundacion(int fundacion) {
        this.equipo.setFundacion(fundacion);

        return this;
    }

    public EquipoBuilder escudo(String escudo) {
        equipo.setEscudo(escudo);

        return this;
    }

    public EquipoBuilder telefono(String telefono) {
        equipo.setTelefono(telefono);

        return this;
    }

    public EquipoBuilder direccion(String direccion) {
        equipo.setDireccion(direccion);

        return this;
    }

    public EquipoBuilder web(String web) {
        equipo.setWeb(web);

        return this;
    }

    public EquipoBuilder estadio(Estadio estadio) {
        equipo.setEstadio(estadio);

        return this;
    }

    public EquipoBuilder jugador(Jugador jugador) {
        equipo.getJugadores().add(jugador);

        return this;
    }

    public EquipoBuilder plantilla(List<Jugador> plantilla) {
        equipo.getJugadores().clear();
        equipo.getJugadores().addAll(plantilla);

        return this;
    }

    public EquipoBuilder estadistica(Estadistica estadistica) {
        equipo.setEstadistica(estadistica);

        return this;
    }

    public Equipo build() {
        return this.equipo;
    }
}
