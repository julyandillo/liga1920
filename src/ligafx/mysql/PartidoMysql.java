package ligafx.mysql;

import ligafx.dao.PartidoDAO;
import ligafx.modelos.Partido;

import java.util.List;

public class PartidoMysql implements PartidoDAO {

    @Override
    public boolean guardar(Partido object) {
        return false;
    }

    @Override
    public boolean actualizar(Partido object) {
        return false;
    }

    @Override
    public Partido cargar(Integer idPartido) {
        return null;
    }

    @Override
    public List<Partido> cargarTodos() {
        return null;
    }

    @Override
    public List<Partido> cargarTodosPorEquipo(Integer idEquipo) {
        return null;
    }
}
