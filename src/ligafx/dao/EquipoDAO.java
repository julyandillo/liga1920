package ligafx.dao;

import ligafx.modelos.Equipo;

import java.util.List;

public interface EquipoDAO extends Cargable <Equipo, Integer> {

    @Override
    Equipo cargar(Integer id);

    @Override
    List<Equipo> cargarTodos();
}
