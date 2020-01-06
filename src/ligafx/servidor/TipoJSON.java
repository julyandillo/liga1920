package ligafx.servidor;

public enum TipoJSON {
    EQUIPO("equipo"),
    JORNADA("jornada"),
    PARTIDO("partido");

    private String value;

    TipoJSON(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
