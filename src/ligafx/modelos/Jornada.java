package ligafx.modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Jornada  extends Entidad {

    private Date inicio;

    private Date fin;

    private List<Partido> partidos;

    public Jornada() {
        super();
    }

    public Jornada(int id) {
        super(id);

        this.inicio = null;
        this.fin = null;

        partidos = new ArrayList<>();
    }

    public Jornada(int id, Date inicio, Date fin) {
        super(id);

        this.inicio = inicio;
        this.fin = fin;

        partidos = new ArrayList<>();
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public Date getInicio() {
        return inicio;
    }

    public Date getFin() {
        return fin;
    }

    public List<Partido> getPartidos() { return partidos; }
}
