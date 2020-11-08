package ligafx.builders;

import ligafx.modelos.Tarjeta;
import ligafx.modelos.TipoTarjeta;

public class TarjetaBuilder {
    private Tarjeta tarjeta = null;

    public TarjetaBuilder() {
        this.tarjeta = new Tarjeta(0, 0, TipoTarjeta.AMARILLA);
    }

    public TarjetaBuilder minuto(int minuto) {
        this.tarjeta.setMinuto(minuto);

        return this;
    }

    public TarjetaBuilder tipo(TipoTarjeta tipo) {
        this.tarjeta.setTipo(tipo);

        return this;
    }

    public TarjetaBuilder jugador(int idJugador) {
        this.tarjeta.setIdJugador(idJugador);

        return this;
    }

    public Tarjeta build() {
        return this.tarjeta;
    }
}
