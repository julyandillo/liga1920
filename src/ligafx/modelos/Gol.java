package ligafx.modelos;

public class Gol extends Evento {

    private boolean penalti;

    private boolean propiaMeta;

    public Gol() {
        super(0, 0);

        this.penalti = false;
        this.propiaMeta = false;
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
}
