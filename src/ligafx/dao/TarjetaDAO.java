package ligafx.dao;

import ligafx.decoradores.TarjetaDecorator;
import ligafx.modelos.Tarjeta;
import ligafx.modelos.TipoEquipo;

import java.util.List;
import java.util.Map;

public interface TarjetaDAO extends Cargable<Tarjeta, Integer>{

    int guardarTarjetas(List<Tarjeta> tarjetas, int idPartido) throws DAOException;

    Map<TipoEquipo, List<TarjetaDecorator>> cargarTodasPorPartido(int idPartido) throws DAOException;
}
