package ligafx.builders;

import ligafx.modelos.Arbitro;

public class ArbitroBuilder {

    private final Arbitro arbitro;

    public ArbitroBuilder() {
        arbitro = new Arbitro(0);
    }

    public ArbitroBuilder id(int id) {
        arbitro.setId(id);
        return this;
    }

    public ArbitroBuilder nombre(String nombre) {
        arbitro.setNombre(nombre);
        return this;
    }

    public ArbitroBuilder partidos(int partidos) {
        arbitro.setPartidos(partidos);
        return this;
    }

    public ArbitroBuilder partidosVAR(int partidosVAR) {
        arbitro.setPartidosVAR(partidosVAR);
        return this;
    }

    public ArbitroBuilder amarillas(int amarillas) {
        arbitro.setAmarillas(amarillas);
        return this;
    }

    public ArbitroBuilder rojas(int rojas) {
        arbitro.setRojas(rojas);
        return this;
    }

    public ArbitroBuilder penaltis(int penaltis) {
        arbitro.setPenaltis(penaltis);
        return this;
    }

    public Arbitro build() {
        return arbitro;
    }
}
