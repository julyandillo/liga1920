package ligafx.decoradores;

import ligafx.modelos.Gol;

public class GolDecorator {

    private Gol gol;

    private String info;

    public GolDecorator(Gol gol, String info) {
        this.gol = gol;
        this.info = info;
    }

    public Gol getGol() {
        return this.gol;
    }

    public String detalle(boolean prefijo) {
        if (prefijo) {
            return this.gol.detalle() + " " + this.info;
        }
        else {
            return this.info + " " + this.gol.detalle();
        }
    }
}
