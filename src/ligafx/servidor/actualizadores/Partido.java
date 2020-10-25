package ligafx.servidor.actualizadores;

import com.google.gson.JsonObject;
import javafx.scene.control.Alert;
import ligafx.dao.DAOException;
import ligafx.dao.DAOManager;
import ligafx.servidor.Actualizable;
import ligafx.util.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

public class Partido implements Actualizable {

    private static final Logger LOGGER = Logger.getLogger(Partido.class.getName());

    private JsonObject json = null;

    public Partido(JsonObject json) {
        this.json = json;
    }

    @Override
    public boolean actualiza() {
        String local = this.json.get("local").getAsString();
        String visitante = this.json.get("visitante").getAsString();

        // por recomendacion de SonarLint en los logger no se deben crear string concantenando variables
        // desde Java 8 se puede usar un Supplier que evalua las variables cuando se usan (lazyily)
        //LOGGER.info(() -> "Actualizando informacion de partido " + local + " - " + visitante);
        System.out.println("Actualizando informacion de partido " + local + " - " + visitante);
        try {
            ligafx.modelos.Partido partido = DAOManager.getPartidoDAO().buscar(local, visitante);
            JsonObject info = json.getAsJsonObject("data");
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");

            partido.setGolesLocal(info.get("goles_local").getAsInt());
            partido.setGolesVisitante(info.get("goles_visitante").getAsInt());
            partido.setAsistencia(info.get("asistencia").getAsInt());
            partido.setArbitro(DAOManager.getArbitroDAO().cargar(info.get("arbitro").getAsString()));
            partido.setArbitroVar(DAOManager.getArbitroDAO().cargar(info.get("arbitro_var").getAsString()));
            partido.setFecha(formatoFecha.parse(info.get("fecha").getAsString()));

        } catch (DAOException e) {
            Util.mostrarMensaje(e.getMensaje(), Alert.AlertType.ERROR);
            LOGGER.severe("ERROR ACTUALIZADOR DE PARTIDO\n" + e.getMensaje());
        } catch (ParseException e) {
            LOGGER.severe("ERROR AL PARSEAR LA FECHA DE PARTIDO\n" + e.getMessage());
        }

        return true;
    }
}
