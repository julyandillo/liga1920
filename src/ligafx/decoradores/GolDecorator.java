package ligafx.decoradores;

import ligafx.modelos.Gol;

public class GolDecorator {

    private final Gol gol;

    private final String jugador;

    public GolDecorator(Gol gol, String jugador) {
        this.gol = gol;
        this.jugador = jugador;
    }

    public Gol getGol() {
        return this.gol;
    }

    public String infoGolConJugadorDespues() {
        return this.detalle() + " " + this.jugador;
    }

    public String infoGolConJugadorAntes() {
        return this.jugador + " " + this.detalle();
    }

    public String detalle() {
        return this.gol.getMinuto() + (this.gol.isPenalti() ? " (p)" : "") + (this.gol.isPropiaMeta() ? " (pp)" : "");
    }
}
