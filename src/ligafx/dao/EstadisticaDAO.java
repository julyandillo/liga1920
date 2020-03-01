package ligafx.dao;

import ligafx.decoradores.EstadisticaDecorator;
import ligafx.modelos.Estadistica;

import java.util.List;

public interface EstadisticaDAO  {

    boolean guardar(Estadistica estadistica, int equipo, int jornada) throws DAOException;

    Estadistica cargarUltimaEquipo(int equipo) throws DAOException;

    List<Estadistica> cargarPorJonada(int jornada) throws DAOException;

    List<EstadisticaDecorator> cargarUltima() throws DAOException;
}
