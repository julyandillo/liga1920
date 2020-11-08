package ligafx.modelos;

public class Tarjeta extends Evento {

    private TipoTarjeta tipo;

    public Tarjeta(int minuto, int idJugador, TipoTarjeta tipo) {
        super(minuto, idJugador);

        this.tipo = tipo;
    }

    public Tarjeta(int id, int minuto, int idJugador, TipoTarjeta tipo) {
        super(id, minuto, idJugador);

        this.tipo = tipo;
    }

    public void setTipo(TipoTarjeta tipo) {
        this.tipo = tipo;
    }

    public TipoTarjeta getTipo() {
        return this.tipo;
    }
}
