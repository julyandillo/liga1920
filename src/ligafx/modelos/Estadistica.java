package ligafx.modelos;

import java.util.EnumMap;
import java.util.Map;

public class Estadistica {
    private final Map<TipoParametro, Dato> parametros;
    
    public Estadistica(boolean inicialilzar) {
        parametros = new EnumMap<>(TipoParametro.class);

        if (inicialilzar) {
            for (TipoParametro parametro :
                    TipoParametro.values()) {
                parametros.put(parametro, new Dato());
            }
        }
    }
    
    public void incrementar(TipoParametro parametro, int valor, TipoEquipo equipo) {
        if (parametros.containsKey(parametro)) {
            parametros.get(parametro).incrementar(valor, equipo);
        } else {
            Dato dato = new Dato();
            dato.incrementar(valor, equipo);
            
            parametros.put(parametro, dato);
        }
    }
    
    public Dato getParametro(TipoParametro parametro) {
        if (parametros.containsKey(parametro)) {
            return parametros.get(parametro);
        }
        
        return null;
    }

    public void setParametro(TipoParametro parametro, Dato dato) {
        parametros.put(parametro, dato);
    }
}
