package ligafx.servidor;

import com.google.gson.JsonObject;
import ligafx.servidor.actualizadores.Equipo;
import ligafx.servidor.actualizadores.Jugador;

public class JsonInterpreter {

    Actualizable actualizador = null;

    public JsonInterpreter(JsonObject json) throws InterpreterException {
        // se obtiene el tipo de objeto que hay que interpretar para actualizar la base de datos
        String tipo = json.get("type").getAsString();

        switch (tipo) {
            case "equipo":
                this.actualizador = new Equipo(json);
                break;
            case "plantilla":
                this.actualizador = new Jugador(json);
                break;
            case "partido":
                // TODO actrualizador para un partido
                break;
            case "jornada":
                // TODO actualizador para una jornada completa
                break;
            default:
                throw new InterpreterException("El tipo de objeto para interpretar no es válido");
        }
    }

    public boolean actualiza() {
        return this.actualizador.actualiza();
    }
}
