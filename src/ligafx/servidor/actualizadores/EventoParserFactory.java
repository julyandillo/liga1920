package ligafx.servidor.actualizadores;

import com.google.gson.JsonObject;
import ligafx.servidor.actualizadores.eventos.CambioParser;
import ligafx.servidor.actualizadores.eventos.GolParser;
import ligafx.servidor.actualizadores.eventos.TarjetaAmarillaParser;
import ligafx.servidor.actualizadores.eventos.TarjetaRojaParser;

public class EventoParserFactory {

    private final JsonObject evento;

    public EventoParserFactory(JsonObject evento) {
        this.evento = evento;
    }

    public EventoParser getEvento() {
        if (getTipoEvento().equals("cambio")) {
            return new CambioParser(evento);
        } else if (getTipoEvento().startsWith("gol")) {
            return new GolParser(evento);
        } else if (getTipoEvento().equals("tarjeta_amarilla")) {
            return new TarjetaAmarillaParser(evento);
        } else if (getTipoEvento().equals("tarjeta_roja")) {
            return new TarjetaRojaParser(evento);
        }

        return null;
    }

    private String getTipoEvento() {
        return evento.get("tipo").getAsString();
    }
}
