package ligafx.modelos;

public class Evento extends Entidad {

    protected int minuto;

    protected int idJugador;

    public Evento(int minuto, int idJugador) {
        super();
        this.minuto = minuto;
        this.idJugador = idJugador;
    }

    public Evento(int id, int minuto, int idJugador) {
        super(id);
        this.minuto = minuto;
        this.idJugador = idJugador;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public int getMinuto() {
        return this.minuto;
    }

    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }
}
