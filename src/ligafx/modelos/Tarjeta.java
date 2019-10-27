package ligafx.modelos;

public class Tarjeta extends Evento {

    private TipoTarjeta tipo;

    public Tarjeta(int minuto, TipoTarjeta tipo) {
        super(minuto);

        this.tipo = tipo;
    }

    public Tarjeta(int id, int minuto, TipoTarjeta tipo) {
        super(id, minuto);

        this.tipo = tipo;
    }

    public TipoTarjeta getTipo() {
        return this.tipo;
    }
}
