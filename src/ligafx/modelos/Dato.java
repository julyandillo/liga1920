package ligafx.modelos;

public class Dato {

    Integer total;
    Integer casa;
    Integer fuera;

    public Dato() {
        casa = 0;
        fuera = 0;
        total = 0;
    }

    public Integer getTotal() {
        return total;
    }

    public Integer getCasa() {
        return casa;
    }

    public Integer getFuera() {
        return fuera;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setCasa(Integer casa) {
        this.casa = casa;
        total = this.casa + this.fuera;
    }

    public void setFuera(Integer fuera) {
        this.fuera = fuera;
        total = this.casa + this.fuera;
    }

    public void incrementar(int valor, TipoEquipo equipo) {
        total += valor;

        if (equipo == TipoEquipo.LOCAL) {
            casa += valor;
        } else {
            fuera += valor;
        }
    }
}
