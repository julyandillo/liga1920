package ligafx.builders;

import ligafx.modelos.Dato;

public class DatoBuilder {

    private Dato dato;

    public DatoBuilder() {
        dato = new Dato();
    }

    public DatoBuilder total(int valor) {
        dato.setTotal(valor);
        return this;
    }

    public DatoBuilder casa(int valor) {
        dato.setCasa(valor);
        return this;
    }

    public DatoBuilder fuera(int valor) {
        dato.setFuera(valor);
        return this;
    }

    public Dato build() {
        return dato;
    }
}
