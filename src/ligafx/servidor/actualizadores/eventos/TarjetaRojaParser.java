package ligafx.servidor.actualizadores.eventos;

import com.google.gson.JsonObject;
import ligafx.modelos.TipoTarjeta;

public class TarjetaRojaParser extends TarjetaParser {

    public TarjetaRojaParser(JsonObject tarjeta) {
        super(tarjeta, TipoTarjeta.ROJA);
    }
}
