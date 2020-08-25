package ligafx.builders;

import ligafx.decoradores.GolDecorator;
import ligafx.decoradores.TarjetaDecorator;
import ligafx.modelos.Arbitro;
import ligafx.modelos.Equipo;
import ligafx.modelos.Partido;
import ligafx.modelos.TipoEquipo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class PartidoBuilder {

    private Partido partido;

    public PartidoBuilder() {
        partido = new Partido(0);
    }
    
    public PartidoBuilder id(int id) {
        partido.setId(id);

        return this;
    }

    public PartidoBuilder local(Equipo equipo) {
        partido.setEquipoLocal(equipo);

        return this;
    }

    public PartidoBuilder visitante(Equipo equipo) {
        partido.setEquipoVisitante(equipo);

        return this;
    }

    public PartidoBuilder fecha(Date fecha) {
        partido.setFecha(fecha);

        return this;
    }

    public PartidoBuilder disputado(boolean disputado) {
        partido.setDisputado(disputado);

        return this;
    }

    public PartidoBuilder arbitro(Arbitro arbitro) {
        partido.setArbitro(arbitro);

        return this;
    }

    public PartidoBuilder arbitroVar(Arbitro arbitro) {
        partido.setArbitroVar(arbitro);

        return this;
    }

    public PartidoBuilder golesLocal(int goles) {
        partido.setGolesLocal(goles);

        return this;
    }

    public PartidoBuilder golesVisitante(int goles) {
        partido.setGolesVisitante(goles);

        return this;
    }

    public PartidoBuilder gol(GolDecorator gol, TipoEquipo equipo) {
        partido.agregarGol(gol, equipo);

        return this;
    }

    public PartidoBuilder tarjeta(TarjetaDecorator tarjeta, TipoEquipo equipo) {
        partido.agregarTarjeta(tarjeta, equipo);

        return this;
    }

    public PartidoBuilder tarjetas(Map<TipoEquipo, List<TarjetaDecorator>> tarjetas) {
        partido.setTarjetas(tarjetas);

        return this;
    }

    public PartidoBuilder goles(Map<TipoEquipo, List<GolDecorator>> goles) {
        partido.setGoles(goles);

        return this;
    }

    public Partido build() {
        return partido;
    }
}
