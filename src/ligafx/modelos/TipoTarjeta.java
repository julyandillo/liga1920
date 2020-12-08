package ligafx.modelos;

public enum TipoTarjeta {
    AMARILLA(1),
    ROJA(2);

    private final int valor;

    TipoTarjeta(int valor) { this.valor = valor; }

    public int getValor() { return this.valor; }
}
