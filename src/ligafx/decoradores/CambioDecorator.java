package ligafx.decoradores;

import ligafx.modelos.Cambio;

public class CambioDecorator {

    private final Cambio cambio;

    private final String sale;

    private final String entra;

    public CambioDecorator(Cambio cambio, String entra, String sale) {
        this.cambio = cambio;
        this.sale = sale;
        this.entra = entra;
    }

    public String getSale() {
        return sale;
    }

    public String getEntra() {
        return entra;
    }

    public Cambio getCambio() {
        return cambio;
    }
}
