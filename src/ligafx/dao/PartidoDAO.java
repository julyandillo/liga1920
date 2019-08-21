package ligafx.dao;

import ligafx.modelos.Partido;

import java.util.List;

public interface PartidoDAO extends Modificable<Partido>, Cargable<Partido, Integer> {
    @Override
    boolean guardar(Partido object);

    @Override
    boolean actualizar(Partido object);

    @Override
    Partido cargar(Integer idPartido);

    @Override
    List<Partido> cargarTodos();

    List<Partido> cargarTodosPorJornada(Integer idJornada);

    List<Partido> cargarTodosPorEquipo(Integer idEquipo);
}
