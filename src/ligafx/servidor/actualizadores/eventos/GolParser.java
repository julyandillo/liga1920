package ligafx.servidor.actualizadores.eventos;

import com.google.gson.JsonObject;
import ligafx.servidor.actualizadores.EventoParser;

public class GolParser extends EventoParser {

    public GolParser(JsonObject gol) {
        super(gol);
    }

    public int getIdJugador() {
        return getInt("jugador");
    }

    public boolean esPenalti() {
        return getString("tipo").equals("gol_penalti");
    }

    public boolean esPropiaMeta() {
        return getString("tipo").equals("gol_pp");
    }
}
