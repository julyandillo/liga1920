package ligafx.modelos;

public class Gol extends Evento {

    private boolean penalti;

    private boolean propiaMeta;

    public Gol(int minuto, boolean penalti, boolean propiaMeta) {
        super(minuto);

        this.penalti = penalti;
        this.propiaMeta = propiaMeta;
    }

    public Gol(int id, int minuto, boolean penalti, boolean propiaMeta) {
        super(id, minuto);

        this.penalti = penalti;
        this.propiaMeta = propiaMeta;
    }

    public boolean isPenalti() {
        return this.penalti;
    }

    public boolean isPropiaMeta() {
        return this.propiaMeta;
    }
}
