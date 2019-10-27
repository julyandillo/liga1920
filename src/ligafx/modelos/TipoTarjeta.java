package ligafx.modelos;

public enum TipoTarjeta {
    AMARILLA(1),
    ROJA(2);

    private int valor;

    TipoTarjeta(int valor) { this.valor = valor; }

    int getValor() { return this.valor; }
}
