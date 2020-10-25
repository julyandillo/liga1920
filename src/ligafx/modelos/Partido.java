package ligafx.modelos;

import ligafx.decoradores.GolDecorator;
import ligafx.decoradores.TarjetaDecorator;

import java.util.*;

public class Partido extends Entidad {

    private Equipo equipoLocal;

    private Equipo equipoVisitante;

    private int golesLocal;

    private int golesVisitante;

    private Date fecha;

    private boolean disputado;

    private Arbitro arbitro;

    private Arbitro arbitroVar;

    private int asistencia;

    private Map<TipoEquipo, List<GolDecorator>> goles;

    private Map<TipoEquipo, List<TarjetaDecorator>> tarjetas;

    public Partido(int id) {

        super(id);
    }

    public Partido(int id, Equipo equipoLocal, Equipo equipoVisitante, int golesLocal, int golesVisitante, Date fecha,
                   boolean disputado) {
        super(id);

        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.golesLocal = golesLocal;
        this.golesVisitante = golesVisitante;
        this.fecha = fecha;
        this.disputado = disputado;

        this.goles = new EnumMap<>(TipoEquipo.class);
        this.tarjetas = new EnumMap<>(TipoEquipo.class);
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(Equipo equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(Equipo equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesVisitante(int golesVisitante) {
        this.golesVisitante = golesVisitante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean disputado() {
        return disputado;
    }

    public void setDisputado(boolean disputado) {
        this.disputado = disputado;
    }

    public void setArbitro(Arbitro arbitro) {
        this.arbitro = arbitro;
    }

    public Arbitro getArbitro() {
        return this.arbitro;
    }

    public Arbitro getArbitroVar() {
        return arbitroVar;
    }

    public void setArbitroVar(Arbitro arbitroVar) {
        this.arbitroVar = arbitroVar;
    }

    public int getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(int asistencia) {
        this.asistencia = asistencia;
    }

    public void agregarTarjeta(TarjetaDecorator tarjeta, TipoEquipo equipo) {
        if (!this.tarjetas.containsKey(equipo)) {
            this.tarjetas.put(equipo, new ArrayList<>());
        }

        this.tarjetas.get(equipo).add(tarjeta);
    }

    public void setTarjetas(List<TarjetaDecorator> tarjetas, TipoEquipo equipo) {
        this.tarjetas.put(equipo, tarjetas);
    }

    public void setTarjetas(Map<TipoEquipo, List<TarjetaDecorator>> tarjetas) {
        this.tarjetas = tarjetas;
    }

    public List<TarjetaDecorator> getTarjetas(TipoEquipo equipo) {
        return this.tarjetas.get(equipo);
    }

    public void agregarGol(GolDecorator gol, TipoEquipo equipo) {
        if (!this.goles.containsKey(equipo)) {
            this.goles.put(equipo, new ArrayList<GolDecorator>());
        }

        this.goles.get(equipo).add(gol);
    }

    public void setGoles(List<GolDecorator> goles, TipoEquipo equipo) {
        this.goles.put(equipo, goles);
    }

    public void setGoles(Map<TipoEquipo, List<GolDecorator>> goles) {
        this.goles = goles;
    }

    public List<GolDecorator> getGoles(TipoEquipo equipo) {
        return this.goles.get(equipo);
    }
}
