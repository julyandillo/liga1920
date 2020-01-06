package ligafx.servidor.actualizadores;

import com.google.gson.JsonObject;
import ligafx.dao.DAOException;
import ligafx.dao.DAOManager;
import ligafx.modelos.Estadio;
import ligafx.servidor.Actualizable;

import java.util.logging.Logger;

public class Equipo implements Actualizable {

    private static final Logger LOGGER = Logger.getLogger(Equipo.class.getName());

    private JsonObject json = null;

    public Equipo(JsonObject json) {
        this.json = json;
    }

    @Override
    public boolean actualiza() {
        LOGGER.info("Actualizando info de " + this.json.get("name").getAsString());

        int idEquipo = json.get("id").getAsInt();

        JsonObject data = json.getAsJsonObject("data");
        String entrenador = data.get("entrenador").getAsString();
        String presidente = data.get("presidente").getAsString();

        JsonObject estadioObj = data.getAsJsonObject("estadio");
        int capacidad = estadioObj.get("capacidad").getAsInt();
        int construccion = estadioObj.get("construccion").getAsInt();
        String dimensiones = estadioObj.get("dimensiones").getAsString();

        try {
            ligafx.modelos.Equipo equipo = DAOManager.getEquipoDAO().cargar(idEquipo);
            equipo.setEntrenador(entrenador);
            equipo.setPresidente(presidente);

            boolean resultado = DAOManager.getEquipoDAO().actualizar(equipo);

            Estadio estadio = DAOManager.getEstadioDAO().cargarPorIdEquipo(idEquipo);
            estadio.setCapacidad(capacidad);
            estadio.setConstruccion(construccion);
            estadio.setDimensiones(dimensiones);

            return resultado && DAOManager.getEstadioDAO().actualizar(estadio);

        } catch (DAOException e) {
            LOGGER.severe("ERROR AL ACTUALIZAR LA INFORMACION DEL EQUIPO\n" + e.getMensaje());
        }

        return true;
    }
}
