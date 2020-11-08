package ligafx.modelos;

public class Cambio extends Entidad {

    private int idJugadorSale;

    private int idJugadorEntra;

    private int minuto;

    public Cambio(int minuto, int idJugadorSale, int idJugadorEntra) {
        this.minuto = minuto;
        this.idJugadorEntra = idJugadorEntra;
        this.idJugadorSale = idJugadorSale;
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
