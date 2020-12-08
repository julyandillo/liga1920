package ligafx.dao;

import ligafx.modelos.Tarjeta;

import java.util.List;

public interface TarjetaDAO extends Cargable<Tarjeta, Integer>{

    int guardarTarjetas(List<Tarjeta> tarjetas, int idPartido) throws DAOException;
}
