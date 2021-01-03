package ligafx.builders;

import javafx.geometry.Pos;
import ligafx.modelos.Gol;
import ligafx.modelos.Jugador;
import ligafx.modelos.Posicion;
import ligafx.modelos.Tarjeta;

import java.util.Date;

public class JugadorBuilder {

    private Jugador jugador;

    public JugadorBuilder() {
        this.jugador = new Jugador(0);
    }

    public JugadorBuilder(int id) {
        this.jugador = new Jugador(id);
    }

    public JugadorBuilder id(int id) {
        this.jugador.setId(id);

        return this;
    }

    public JugadorBuilder nombre(String nombre) {
        this.jugador.setNombre(nombre);

        return this;
    }

    public JugadorBuilder apodo(String apodo) {
        this.jugador.setApodo(apodo);

        return this;
    }

    public JugadorBuilder paisNacimiento(String paisNacimiento) {
        this.jugador.setPaisNacimiento(paisNacimiento);

        return this;
    }

    public JugadorBuilder nacionalidad(String nacionalidad) {
        this.jugador.setNacionalidad(nacionalidad);

        return this;
    }

    public JugadorBuilder fechaNacimiento(Date fechaNacimiento) {
        this.jugador.setFechaNacimiento(fechaNacimiento);

        return this;
    }

    public JugadorBuilder dorsal(int dorsal) {
        this.jugador.setDorsal(dorsal);

        return this;
    }

    public JugadorBuilder posicion(String valor) {
        Posicion posicion;

        switch (valor) {
            case "MED":
                posicion = Posicion.CENTROCAMPISTA;
                break;
            case "DEF":
                posicion = Posicion.DEFENSA;
                break;
            case "DEL":
                posicion = Posicion.DELANTERO;
                break;
            case "POR":
                posicion = Posicion.PORTERO;
                break;
            default:
                posicion = null;
                break;
        }

        this.jugador.setPosicion(posicion);

        return this;
    }

    public JugadorBuilder posicion(Posicion posicion) {
        this.jugador.setPosicion(posicion);

        return this;
    }

    public JugadorBuilder altura(int altura) {
        this.jugador.setAltura(altura);

        return this;
    }

    public JugadorBuilder peso(int peso) {
        this.jugador.setPeso(peso);

        return this;
    }

    public JugadorBuilder imagen(String imagen) {
        this.jugador.setImagen(imagen);

        return this;
    }

    public JugadorBuilder gol(Gol gol) {
        this.jugador.getGoles().add(gol);

        return this;
    }

    public JugadorBuilder tarjeta(Tarjeta tarjeta) {
        this.jugador.getTarjetas().add(tarjeta);

        return this;
    }

    public JugadorBuilder vecesCambiado(int veces) {
        this.jugador.setVecesCambiado(veces);

        return this;
    }

    public JugadorBuilder vecesEntra(int veces) {
        this.jugador.setVecesEntra(veces);

        return this;
    }

    public Jugador build() {
        return this.jugador;
    }
}
