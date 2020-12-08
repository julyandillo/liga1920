package ligafx.servidor.actualizadores;

import com.google.gson.JsonObject;

public class EventoParser {

    private final JsonObject evento;

    public EventoParser(JsonObject evento) {
        this.evento = evento;
    }

    protected String getString(String key) {
        return evento.get(key).getAsString();
    }

    public int getMinuto() {
        return getInt("minuto");
    }

    protected int getInt(String key) {
        return evento.get(key).getAsInt();
    }

    protected boolean getBoolean(String key) {
        return evento.get(key).getAsBoolean();
    }
}
