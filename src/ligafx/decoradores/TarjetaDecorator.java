package ligafx.decoradores;

import ligafx.modelos.Tarjeta;

public class TarjetaDecorator {

    private final Tarjeta tarjeta;

    private final String jugador;

    public TarjetaDecorator(Tarjeta tarjeta, String jugador) {
        this.jugador = jugador;
        this.tarjeta = tarjeta;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public String getInfoTarjetaConJugadorAntes() {
        return jugador + " " + detalle();
    }

    public String getInfoTarjetaConJugadorDespues() {
        return detalle() + " " + jugador;
    }

    private String detalle() {
        return tarjeta.getMinuto() + "'";
    }
}
