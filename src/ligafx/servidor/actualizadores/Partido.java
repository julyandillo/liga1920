package ligafx.servidor.actualizadores;

import com.google.gson.JsonObject;
import ligafx.servidor.Actualizable;

import java.util.logging.Logger;

public class Partido implements Actualizable {

    private static final Logger LOGGER = Logger.getLogger(Partido.class.getName());

    private JsonObject json = null;

    public Partido(JsonObject json) {
        this.json = json;
    }

    @Override
    public boolean actualiza() {
        String local = this.json.get("local").getAsString();
        String visitante = this.json.get("visitante").getAsString();

        // por recomendacion de SonarLint en los logger no se deben crear string concantenando variables
        // desde Java 8 se puede usar un Supplier que evalua las variables cuando se usan (lazyily)
        //LOGGER.info(() -> "Actualizando informacion de partido " + local + " - " + visitante);
        System.out.println("Actualizando informacion de partido " + local + " - " + visitante);

        return true;
    }
}
