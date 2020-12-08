package ligafx.servidor.actualizadores.eventos;

import com.google.gson.JsonObject;
import ligafx.modelos.TipoTarjeta;
import ligafx.servidor.actualizadores.EventoParser;

public abstract class TarjetaParser extends EventoParser {

    protected final TipoTarjeta tipoTarjeta;

    protected TarjetaParser(JsonObject tarjeta, TipoTarjeta tipoTarjeta) {
        super(tarjeta);
        this.tipoTarjeta = tipoTarjeta;
    }

    public int getIdJugador() {
        return getInt("jugador");
    }

    public TipoTarjeta getTipoTarjeta() {
        return tipoTarjeta;
    }
}
