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

    private int jornada;

    // para cuando se carga el partido para mostrar los detalles se usan los decoradores
    // cuando se usan para el actulizador se usan los modelos (goles, tarjetas y cambios)
    private Map<TipoEquipo, List<GolDecorator>> goles;

    private Map<TipoEquipo, List<TarjetaDecorator>> tarjetas;

    private final List<Tarjeta> modeloTarjetas;

    private final List<Gol> modeloGoles;

    private final List<Cambio> modeloCambios;

    public Partido(int id) {
        super(id);

        this.goles = new EnumMap<>(TipoEquipo.class);
        this.tarjetas = new EnumMap<>(TipoEquipo.class);

        this.modeloTarjetas = new ArrayList<>();
        this.modeloGoles = new ArrayList<>();
        this.modeloCambios = new ArrayList<>();
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

    public int getJornada() {
        return jornada;
    }

    public void setJornada(int jornada) {
        this.jornada = jornada;
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
            this.goles.put(equipo, new ArrayList<>());
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

    public void agregarTarjeta(Tarjeta tarjeta) {
        this.modeloTarjetas.add(tarjeta);
    }

    public List<Tarjeta> getModeloTarjetas() {
        return this.modeloTarjetas;
    }

    public void agregarGol(Gol gol) {
        this.modeloGoles.add(gol);
    }
    public List<Gol> getModeloGoles() {
        return this.modeloGoles;
    }

    public void agregarCambio(Cambio cambio) {
        this.modeloCambios.add(cambio);
    }

    public List<Cambio> getModeloCambios() {
        return this.modeloCambios;
    }
}
