package ligafx.servidor.actualizadores.eventos;

import com.google.gson.JsonObject;
import ligafx.servidor.actualizadores.EventoParser;

public class CambioParser extends EventoParser {

    public CambioParser(JsonObject cambio) {
        super(cambio);
    }

    public int getIdJugadorSale() {
        return getInt("sale");
    }

    public int getIdJugadorEntra() {
        return getInt("entra");
    }
}
