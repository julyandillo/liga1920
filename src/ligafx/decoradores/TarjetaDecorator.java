package ligafx.decoradores;

import ligafx.modelos.Tarjeta;

public class TarjetaDecorator {

    private Tarjeta tarjeta;

    private String jugador;

    public TarjetaDecorator(Tarjeta tarjeta, String jugador) {
        this.jugador = jugador;
        this.tarjeta = tarjeta;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public String detalle(boolean prefijo) {
        if (prefijo) {
            return jugador + " " + tarjeta.getMinuto() + "'";
        } else {
            return tarjeta.getMinuto() + "'" + " " + jugador;
        }
    }
}
