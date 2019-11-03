package ligafx.modelos;

public class Gol extends Evento {

    private boolean penalti;

    private boolean propiaMeta;

    public Gol() {
        super(0, 0);

        this.penalti = false;
        this.propiaMeta = false;
    }

    public Gol(int minuto) {
        super(minuto);

        this.penalti = false;
        this.propiaMeta = false;
    }

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

    public void setPenalti(boolean penalti) {
        this.penalti = penalti;
    }

    public void setPropiaMeta(boolean propiaMeta) {
        this.propiaMeta = propiaMeta;
    }

    public String detalle() {
        return this.minuto + (this.penalti ? " (p)" : "") + (this.propiaMeta ? " (pp)" : "");
    }
}
