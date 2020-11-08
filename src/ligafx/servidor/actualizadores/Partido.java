package ligafx.servidor.actualizadores;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.scene.control.Alert;
import ligafx.dao.DAOException;
import ligafx.dao.DAOManager;
import ligafx.modelos.*;
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

            final int golesLocal = info.get("goles_local").getAsInt();
            final int golesVisitante = info.get("goles_visitante").getAsInt();

            partido.setGolesLocal(golesLocal);
            partido.setGolesVisitante(golesVisitante);
            partido.setAsistencia(info.get("asistencia").getAsInt());
            partido.setArbitro(DAOManager.getArbitroDAO().cargar(info.get("arbitro").getAsString()));
            partido.setArbitroVar(DAOManager.getArbitroDAO().cargar(info.get("arbitro_var").getAsString()));
            partido.setFecha(formatoFecha.parse(info.get("fecha").getAsString()));

            JsonArray eventos = info.getAsJsonArray("eventos");
            for (JsonElement elemento : eventos) {
                JsonObject evento = elemento.getAsJsonObject();

                if (evento.get("tipo").getAsString().equals("tarjeta_amarilla")) {
                    partido.agregarTarjeta(new Tarjeta(evento.get("minuto").getAsInt(),
                            evento.get("jugador").getAsInt(), TipoTarjeta.AMARILLA));
                } else if (evento.get("tipo").getAsString().equals("tarjeta_roja")) {
                    partido.agregarTarjeta(new Tarjeta(evento.get("minuto").getAsInt(),
                            evento.get("jugador").getAsInt(), TipoTarjeta.ROJA));
                }
                // TODO faltan los goles y los cambios (tambien hay que hacer el modelo para los cambios)
                // (meter eventos en la partido en lugar de separar por tarjeta gol y cambio)
            }

            if (partido.getEquipoLocal().getEstadistica() == null) {
                // si el equipo no tiene estadisticas
                partido.getEquipoLocal().setEstadistica(new Estadistica(true));
            }
            if (partido.getEquipoVisitante().getEstadistica() == null) {
                // si el equipo no tiene estadisticas
                partido.getEquipoVisitante().setEstadistica(new Estadistica(true));
            }

            partido.getEquipoLocal().getEstadistica().incrementar(TipoParametro.JUGADOS, 1, TipoEquipo.LOCAL);
            partido.getEquipoLocal().getEstadistica().incrementar(
                    TipoParametro.GOLES_FAVOR, golesLocal, TipoEquipo.LOCAL);
            partido.getEquipoLocal().getEstadistica().incrementar(
                    TipoParametro.GOLES_CONTRA, golesVisitante, TipoEquipo.LOCAL);

            partido.getEquipoVisitante().getEstadistica().incrementar(
                    TipoParametro.JUGADOS, 1, TipoEquipo.VISITANTE);
            partido.getEquipoVisitante().getEstadistica().incrementar(
                    TipoParametro.GOLES_CONTRA, golesLocal, TipoEquipo.VISITANTE);
            partido.getEquipoVisitante().getEstadistica().incrementar(
                    TipoParametro.GOLES_FAVOR, golesVisitante, TipoEquipo.VISITANTE);

            if (golesLocal > golesVisitante) {
                partido.getEquipoLocal().getEstadistica().incrementar(TipoParametro.PUNTOS, 3, TipoEquipo.LOCAL);
                partido.getEquipoLocal().getEstadistica().incrementar(TipoParametro.GANADOS, 1, TipoEquipo.LOCAL);

                partido.getEquipoVisitante().getEstadistica().incrementar(TipoParametro.PERDIDOS, 1, TipoEquipo.VISITANTE);
            } else if (golesLocal == golesVisitante) {
                partido.getEquipoLocal().getEstadistica().incrementar(TipoParametro.PUNTOS, 1, TipoEquipo.LOCAL);
                partido.getEquipoLocal().getEstadistica().incrementar(TipoParametro.EMPATADOS, 1, TipoEquipo.LOCAL);

                partido.getEquipoVisitante().getEstadistica().incrementar(TipoParametro.PUNTOS, 1, TipoEquipo.VISITANTE);
                partido.getEquipoVisitante().getEstadistica().incrementar(TipoParametro.EMPATADOS, 1, TipoEquipo.VISITANTE);
            } else {
                partido.getEquipoLocal().getEstadistica().incrementar(TipoParametro.PERDIDOS, 1, TipoEquipo.LOCAL);

                partido.getEquipoVisitante().getEstadistica().incrementar(TipoParametro.GANADOS, 1, TipoEquipo.VISITANTE);
                partido.getEquipoVisitante().getEstadistica().incrementar(TipoParametro.PUNTOS, 3, TipoEquipo.VISITANTE);
            }

            if (!partido.disputado()) {
                DAOManager.getPartidoDAO().actualizar(partido);
                DAOManager.getEstadisticaDAO().guardar(partido.getEquipoLocal().getEstadistica(),
                        partido.getEquipoLocal().getId(), partido.getJornada());
                DAOManager.getEstadisticaDAO().guardar(partido.getEquipoVisitante().getEstadistica(),
                            partido.getEquipoVisitante().getId(), partido.getJornada());
            }

        } catch (DAOException e) {
            LOGGER.severe("ERROR ACTUALIZADOR DE PARTIDO\n" + e.getMensaje());
        } catch (ParseException e) {
            LOGGER.severe("ERROR AL PARSEAR LA FECHA DE PARTIDO\n" + e.getMessage());
        }

        return true;
    }
}
