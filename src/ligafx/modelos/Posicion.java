package ligafx.modelos;

public enum Posicion {
    PORTERO("POR"),
    DEFENSA("DEF"),
    CENTROCAMPISTA("MED"),
    DELANTERO("DEL"),
    DESCONOCIDA("SP");

    private final String valor;

    Posicion(String valor) { this.valor = valor; }

    public String getValor() { return this.valor; }
}
