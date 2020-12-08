package ligafx.builders;

import ligafx.modelos.Gol;

public class GolBuilder {
    private Gol gol = null;

    public GolBuilder() {
        this.gol = new Gol();
    }

    public GolBuilder minuto(int minuto) {
        this.gol.setMinuto(minuto);

        return this;
    }

    public GolBuilder idJugador(int id) {
        this.gol.setIdJugador(id);

        return this;
    }

    public GolBuilder penalti(boolean penalti) {
        this.gol.setPenalti(penalti);

        return this;
    }

    public GolBuilder propiaMeta(boolean propiaMeta) {
        this.gol.setPropiaMeta(propiaMeta);

        return this;
    }

    public GolBuilder id(int id) {
        this.gol.setId(id);

        return this;
    }

    public Gol build() {
        return this.gol;
    }
}
