package ligafx.servidor.actualizadores;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ligafx.builders.JugadorBuilder;
import ligafx.dao.DAOException;
import ligafx.dao.DAOManager;
import ligafx.servidor.Actualizable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Jugador implements Actualizable {

    private static final Logger LOGGER = Logger.getLogger(Jugador.class.getName());

    private JsonObject json = null;

    public Jugador(JsonObject json) {
        this.json = json;
    }

    @Override
    public boolean actualiza() {
        int idEquipo = json.get("id").getAsInt();
        List<ligafx.modelos.Jugador> plantilla = new ArrayList<>();

        LOGGER.log(Level.INFO, "Actualizando la plantilla del equipo {0}", json.get("id").getAsString());

        JsonArray jugadores = json.getAsJsonArray("data");

        try {
            for (JsonElement elemento : jugadores) {
                JsonObject jugador = elemento.getAsJsonObject();

                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
                JugadorBuilder jugadorBuilder = new JugadorBuilder(jugador.get("id_resultados_futbol").getAsInt())
                        .nombre(jugador.get("nombre_completo").getAsString())
                        .apodo(jugador.get("nombre").getAsString())
                        .fechaNacimiento(formatoFecha.parse(jugador.get("fecha_nacimiento").getAsString()))
                        .nacionalidad(jugador.get("nacionalidad").getAsString())
                        .paisNacimiento(jugador.get("pais").getAsString())
                        .dorsal(jugador.get("dorsal").getAsInt())
                        .altura(jugador.get("altura").getAsInt())
                        .peso(jugador.get("peso").getAsInt())
                        .posicion(jugador.get("posicion").getAsString())
                        .imagen(jugador.get("imagen").getAsString());

                plantilla.add(jugadorBuilder.build());
            }

            LOGGER.log(Level.INFO, "encontrados {0} jugadores", plantilla.size());

            return DAOManager.getJugadorDAO().guardar(plantilla, idEquipo);

        } catch (ParseException e) {
            LOGGER.severe("ERROR AL CREAR LA FECHA DE NACIMIENTO\n" + e.getMessage());
        } catch (DAOException e) {
            LOGGER.severe("ERROR AL GUARDAR LA PLANTILLA\n" + e.getMensaje());
        }

        return false;
    }
}
