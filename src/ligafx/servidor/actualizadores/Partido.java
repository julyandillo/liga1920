package ligafx.servidor.actualizadores;

import com.google.gson.*;
import ligafx.builders.*;
import ligafx.dao.DAOException;
import ligafx.dao.DAOManager;
import ligafx.modelos.*;
import ligafx.servidor.Actualizable;
import ligafx.servidor.actualizadores.eventos.*;
import ligafx.util.JugadoresIDs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

public class Partido implements Actualizable {

    private static final Logger LOGGER = Logger.getLogger(Partido.class.getName());

    private ligafx.modelos.Partido partidoParaActualizar;

    private final JsonObject infoPartido;

    private int golesLocal;

    private int golesVisitante;

    public Partido(JsonObject json) {
        infoPartido = json.getAsJsonObject("data");

        String local = json.get("local").getAsString();
        String visitante = json.get("visitante").getAsString();

        cargarPartidoParaActualizar(local, visitante);
    }

    private void cargarPartidoParaActualizar(String local, String visitante) {
        try {
            partidoParaActualizar = DAOManager.getPartidoDAO().buscar(local, visitante);
        } catch (DAOException e) {
            LOGGER.severe("ERROR AL CARGAR EL PARTIDO PARA ACTUALIZAR ANTES DE PARSEAR\n" + e.getMensaje());
        }
    }

    @Override
    public boolean actualiza() {
        if (!partidoParaActualizar.disputado()) {

            parsearDetallesPartido();
            parsearEventos();

            actualizarEstadisticasEquipos();

            try {
                DAOManager.getPartidoDAO().actualizar(partidoParaActualizar);
                if (partidoParaActualizar.getModeloGoles().size() != DAOManager.getGolDAO().
                        guardarGoles(partidoParaActualizar.getModeloGoles(), partidoParaActualizar.getId())) {
                    LOGGER.severe("NO SE HAN GUARDADO TODOS LOS GOLES DEL PARTIDO");
                }

                if (partidoParaActualizar.getModeloTarjetas().size() != DAOManager.getTarjetaDAO().guardarTarjetas(
                        partidoParaActualizar.getModeloTarjetas(), partidoParaActualizar.getId()
                )) {
                    LOGGER.severe("NO SE HAN GUARDADO TODAS LAS TARJETAS DEL PARTIDO");
                }

                if (partidoParaActualizar.getModeloCambios().size() != DAOManager.getCambioDAO()
                        .guardarCambios(partidoParaActualizar.getModeloCambios(), partidoParaActualizar.getId())) {
                    LOGGER.severe("NO SE HAN GUARDADO TODAS LOS CAMBIOS DEL PARTIDO");
                }

                DAOManager.getEstadisticaDAO().guardar(partidoParaActualizar.getEquipoLocal().getEstadistica(),
                        partidoParaActualizar.getEquipoLocal().getId(), partidoParaActualizar.getJornada());
                DAOManager.getEstadisticaDAO().guardar(partidoParaActualizar.getEquipoVisitante().getEstadistica(),
                        partidoParaActualizar.getEquipoVisitante().getId(), partidoParaActualizar.getJornada());


            } catch (DAOException e) {
                LOGGER.severe("ERROR ACTUALIZADOR DE PARTIDO\n" + e.getMensaje());
            }
        }

        return true;
    }

    private void parsearDetallesPartido() {
        golesLocal = infoPartido.get("goles_local").getAsInt();
        golesVisitante = infoPartido.get("goles_visitante").getAsInt();

        try {
            partidoParaActualizar.setGolesLocal(golesLocal);
            partidoParaActualizar.setGolesVisitante(golesVisitante);
            partidoParaActualizar.setAsistencia(infoPartido.get("asistencia").getAsInt());
            partidoParaActualizar.setArbitro(DAOManager.getArbitroDAO().cargar(infoPartido.get("arbitro").getAsString()));
            partidoParaActualizar.setArbitroVar(DAOManager.getArbitroDAO().cargar(infoPartido.get("arbitro_var").getAsString()));

            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            partidoParaActualizar.setFecha(formatoFecha.parse(infoPartido.get("fecha").getAsString()));

        } catch (DAOException e) {
            LOGGER.severe("ERROR AL CARGAR EL PARTIDO PARA ANTES DE PARSEAR\n" + e.getMensaje());
        } catch (ParseException e) {
            LOGGER.severe("ERROR AL PARSEAR LA FECHA DE PARTIDO\n" + e.getMessage());
        }
    }

    private void parsearEventos() {
        JsonArray eventos = infoPartido.getAsJsonArray("eventos");

        for (JsonElement elemento : eventos) {
            EventoParserFactory eventoFactory = new EventoParserFactory(elemento.getAsJsonObject());
            EventoParser evento = eventoFactory.getEvento();

            if (evento instanceof TarjetaParser) {
                agregarTarjetaAlPartido((TarjetaParser) evento);
            } else if (evento instanceof GolParser) {
                agregarGolAlPartido((GolParser) evento);
            } else if (evento instanceof CambioParser) {
                agregarCambioAlPartido((CambioParser) evento);
            }
        }
    }

    private void agregarTarjetaAlPartido(TarjetaParser tarjeta) {
        TarjetaBuilder tarjetaBuilder = new TarjetaBuilder()
                .jugador(tarjeta.getIdJugador())
                .minuto(tarjeta.getMinuto())
                .tipo(tarjeta.getTipoTarjeta());

        if (!JugadoresIDs.check(tarjeta.getIdJugador())) {
            agregarNuevoJugador(tarjeta.getIdJugador());
        }

        partidoParaActualizar.agregarTarjeta(tarjetaBuilder.build());
    }

    private void agregarNuevoJugador(int id) {
        ligafx.modelos.Jugador jugador = new JugadorBuilder()
                .id(id)
                .nombre("Sin definir")
                .apodo("Desconocido")
                .posicion(Posicion.DESCONOCIDA)
                .build();

        try {
            DAOManager.getJugadorDAO().guardar(jugador, 0);
            JugadoresIDs.add(id);
        } catch (DAOException e) {
            LOGGER.severe("ERROR AL GUARDAR UN NUEVO JUGADOR DESCONOCIDO");
        }
    }

    private void agregarGolAlPartido(GolParser gol) {
        GolBuilder golBuilder = new GolBuilder()
                .minuto(gol.getMinuto())
                .idJugador(gol.getIdJugador())
                .penalti(gol.esPenalti())
                .propiaMeta(gol.esPropiaMeta());

        if (!JugadoresIDs.check(gol.getIdJugador())) {
            agregarNuevoJugador(gol.getIdJugador());
        }

        partidoParaActualizar.agregarGol(golBuilder.build());
    }

    private void agregarCambioAlPartido(CambioParser cambio) {
        CambioBuilder cambioBuilder = new CambioBuilder()
                .minuto(cambio.getMinuto())
                .jugadorSale(cambio.getIdJugadorSale())
                .jugadorEntra(cambio.getIdJugadorEntra());

        if (!JugadoresIDs.check(cambio.getIdJugadorSale())) {
            agregarNuevoJugador(cambio.getIdJugadorSale());
        }
        if (!JugadoresIDs.check(cambio.getIdJugadorEntra())) {
            agregarNuevoJugador(cambio.getIdJugadorEntra());
        }

        partidoParaActualizar.agregarCambio(cambioBuilder.build());
    }

    private void actualizarEstadisticasEquipos() {
        inicializarEstadisticasSiNoExisten();

        partidoParaActualizar
                .getEquipoLocal()
                .getEstadistica()
                .incrementar(TipoParametro.JUGADOS, 1, TipoEquipo.LOCAL);
        partidoParaActualizar
                .getEquipoLocal()
                .getEstadistica()
                .incrementar(TipoParametro.GOLES_FAVOR, golesLocal, TipoEquipo.LOCAL);
        partidoParaActualizar
                .getEquipoLocal()
                .getEstadistica()
                .incrementar(TipoParametro.GOLES_CONTRA, golesVisitante, TipoEquipo.LOCAL);

        partidoParaActualizar
                .getEquipoVisitante()
                .getEstadistica()
                .incrementar(TipoParametro.JUGADOS, 1, TipoEquipo.VISITANTE);
        partidoParaActualizar
                .getEquipoVisitante()
                .getEstadistica().incrementar(TipoParametro.GOLES_CONTRA, golesLocal, TipoEquipo.VISITANTE);
        partidoParaActualizar
                .getEquipoVisitante()
                .getEstadistica()
                .incrementar(TipoParametro.GOLES_FAVOR, golesVisitante, TipoEquipo.VISITANTE);

        if (golesLocal > golesVisitante) {
            partidoParaActualizar.getEquipoLocal().getEstadistica().incrementar(TipoParametro.PUNTOS, 3, TipoEquipo.LOCAL);
            partidoParaActualizar.getEquipoLocal().getEstadistica().incrementar(TipoParametro.GANADOS, 1, TipoEquipo.LOCAL);

            partidoParaActualizar.getEquipoVisitante().getEstadistica().incrementar(TipoParametro.PERDIDOS, 1, TipoEquipo.VISITANTE);
        } else if (golesLocal == golesVisitante) {
            partidoParaActualizar.getEquipoLocal().getEstadistica().incrementar(TipoParametro.PUNTOS, 1, TipoEquipo.LOCAL);
            partidoParaActualizar.getEquipoLocal().getEstadistica().incrementar(TipoParametro.EMPATADOS, 1, TipoEquipo.LOCAL);

            partidoParaActualizar.getEquipoVisitante().getEstadistica().incrementar(TipoParametro.PUNTOS, 1, TipoEquipo.VISITANTE);
            partidoParaActualizar.getEquipoVisitante().getEstadistica().incrementar(TipoParametro.EMPATADOS, 1, TipoEquipo.VISITANTE);
        } else {
            partidoParaActualizar.getEquipoLocal().getEstadistica().incrementar(TipoParametro.PERDIDOS, 1, TipoEquipo.LOCAL);

            partidoParaActualizar.getEquipoVisitante().getEstadistica().incrementar(TipoParametro.GANADOS, 1, TipoEquipo.VISITANTE);
            partidoParaActualizar.getEquipoVisitante().getEstadistica().incrementar(TipoParametro.PUNTOS, 3, TipoEquipo.VISITANTE);
        }
    }

    private void inicializarEstadisticasSiNoExisten() {
        if (partidoParaActualizar.getEquipoLocal().getEstadistica() == null) {
            // si el equipo no tiene estadisticas
            partidoParaActualizar.getEquipoLocal().setEstadistica(new Estadistica(true));
        }
        if (partidoParaActualizar.getEquipoVisitante().getEstadistica() == null) {
            // si el equipo no tiene estadisticas
            partidoParaActualizar.getEquipoVisitante().setEstadistica(new Estadistica(true));
        }
    }
}
