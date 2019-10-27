package ligafx.menu;

import javafx.scene.layout.BorderPane;

import java.util.logging.Logger;

public interface OrdenBoton {
    String getNombre();
    String getIcono();
    void ejecutar(BorderPane borderPane, Logger logger);
}
