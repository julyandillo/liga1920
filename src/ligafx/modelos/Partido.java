package ligafx.modelos;

import java.util.Date;

public class Partido extends Entidad {

    private Equipo equipoLocal;

    private Equipo equipoVisitante;

    private int golesLocal;

    private int golesVisitante;

    private Date fecha;

    private boolean disputado;

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
}
