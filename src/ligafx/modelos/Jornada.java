package ligafx.modelos;

import java.util.Date;

public class Jornada  extends Entidad {

    private Date inicio;

    private Date fin;

    public Jornada() {
        super();
    }

    public Jornada(int id, Date inicio, Date fin) {
        super(id);

        this.inicio = inicio;
        this.fin = fin;
    }

    public Date getInicio() {
        return inicio;
    }

    public Date getFin() {
        return fin;
    }
}
