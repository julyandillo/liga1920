package ligafx.modelos;


public class Arbitro extends Entidad {

    private String nombre;

    private int partidos;

    private int partidosVAR;

    private int amarillas;

    private int rojas;

    private int penaltis;

    public Arbitro(int id) {
        super(id);

        this.nombre = "";
        partidos = 0;
        partidosVAR = 0;
        amarillas = 0;
        rojas = 0;
        penaltis = 0;
    }

    public Arbitro(int id, String nombre) {
        super(id);
        this.nombre = nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setPartidos(int partidos) {
        this.partidos = partidos;
    }

    public void setPartidosVAR(int partidosVAR) {
        this.partidosVAR = partidosVAR;
    }

    public void setAmarillas(int amarillas) {
        this.amarillas = amarillas;
    }

    public void setRojas(int rojas) {
        this.rojas = rojas;
    }

    public int getPartidos() {
        return partidos;
    }

    public int getPartidosVAR() {
        return partidosVAR;
    }

    public int getTarjetasAmarillas() {
        return amarillas;
    }

    public int getTarjetasRojas() {
        return rojas;
    }

    public int getPenaltis() {
        return penaltis;
    }

    public void setPenaltis(int penaltis) {
        this.penaltis = penaltis;
    }
}
