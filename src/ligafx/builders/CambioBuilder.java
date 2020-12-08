package ligafx.builders;

import ligafx.modelos.Cambio;

public class CambioBuilder {

    private final Cambio cambio;

    public CambioBuilder() {
        cambio = new Cambio();
    }

    public CambioBuilder minuto(int minuto) {
        cambio.setMinuto(minuto);

        return this;
    }

    public CambioBuilder jugadorSale(int idJugador) {
        cambio.setIdJugadorSale(idJugador);

        return this;
    }

    public CambioBuilder jugadorEntra(int idJugador) {
        cambio.setIdJugadorEntra(idJugador);

        return this;
    }

    public CambioBuilder id(int id) {
        cambio.setId(id);

        return this;
    }

    public Cambio build() {
        return this.cambio;
    }
}
