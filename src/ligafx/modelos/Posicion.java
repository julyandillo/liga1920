package ligafx.modelos;

public enum Posicion {
    PORTERO("POR"),
    DEFENSA("DEF"),
    CENTROCAMPISTA("MED"),
    DELANTERO("DEL");

    private String valor;

    Posicion(String valor) { this.valor = valor; }

    public String getValor() { return this.valor; }
}
