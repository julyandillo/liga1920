package ligafx.servidor.actualizadores.eventos;

import com.google.gson.JsonObject;
import ligafx.modelos.TipoTarjeta;

public class TarjetaAmarillaParser extends TarjetaParser {

    public TarjetaAmarillaParser(JsonObject tarjeta) {
        super(tarjeta, TipoTarjeta.AMARILLA);
    }
}
