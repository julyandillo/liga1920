package ligafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ligafx.util.DBConexion;
import ligafx.util.ReadIni;
import ligafx.util.Util;

import java.io.IOException;
import java.util.logging.*;

public class Main extends Application {

    private static final Logger LOG_RAIZ = Logger.getLogger("ligafx");

    /*
    cuando se escriba en este logger, como no tiene ningun handler ascenderá hasta que encuentre un logger
    que tenga, por eso se crea el LOG_RAIZ, todos los demás son hijos de este así que siempre se utilizarán
    los mismos handler para todos los logger de las clases
     */
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/main.fxml"));

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("views/style.css").toExternalForm());

        primaryStage.setTitle("La Liga 2019/2020");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("file:resources/logo.png"));
        primaryStage.show();
    }


    public static void main(String[] args) {
        try {
            Handler consoleHandler = new ConsoleHandler();
            Handler fileHandler = new FileHandler("./ligafx.log", false);
            fileHandler.setFormatter(new SimpleFormatter());

            LOG_RAIZ.addHandler(consoleHandler);
            LOG_RAIZ.addHandler(fileHandler);

            if (ReadIni.getDebugMode().equals("true")) {
                consoleHandler.setLevel(Level.ALL);
                fileHandler.setLevel(Level.ALL);
                LOGGER.log(Level.INFO, "MODO DEBUG ACTIVADO");
            } else {
                consoleHandler.setLevel(Level.SEVERE);
                fileHandler.setLevel(Level.SEVERE);
            }

            DBConexion.getConexion();
            launch(args);

        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, Util.printStackTrace(ex));
        }
    }
}
