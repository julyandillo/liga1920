package ligafx.menu;

import java.util.HashMap;
import java.util.Map;

public class Invocador {

    private Map<String, OrdenBoton> botones;

    public Invocador() {
        botones = new HashMap<>();
    }

    public void add(OrdenBoton boton) {
        this.botones.put(boton.getNombre(), boton);
    }

    public String[] getList() {
        return this.botones.keySet().toArray(new String[0]);
    }

}
