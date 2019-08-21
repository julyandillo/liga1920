package ligafx.modelos;

public class Evento extends Entidad {

    protected int minuto;

    public Evento(int minuto) {
        super();
        this.minuto = minuto;
    }

    public Evento(int id, int minuto) {
        super(id);
        this.minuto = minuto;
    }

    public int getMinuto() {
        return this.minuto;
    }
}
