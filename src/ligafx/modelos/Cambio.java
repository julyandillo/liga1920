package ligafx.modelos;

public class Cambio extends Entidad {

    private int idJugadorSale;

    private int idJugadorEntra;

    private int minuto;

    public Cambio() {
        super(0);

        minuto = 0;
        idJugadorEntra = 0;
        idJugadorSale = 0;
    }

    public int getIdJugadorSale() {
        return idJugadorSale;
    }

    public void setIdJugadorSale(int idJugadorSale) {
        this.idJugadorSale = idJugadorSale;
    }

    public int getIdJugadorEntra() {
        return idJugadorEntra;
    }

    public void setIdJugadorEntra(int idJugadorEntra) {
        this.idJugadorEntra = idJugadorEntra;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }
}
