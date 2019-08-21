package ligafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ligafx.util.DBConexion;
import ligafx.util.Util;

import java.io.IOException;
import java.util.logging.*;

public class Main extends Application {

    private static final Logger LOG_RAIZ = Logger.getLogger("ligafx");

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("liga1920/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        try {
            Handler consoleHandler = new ConsoleHandler();
            Handler fileHandler = new FileHandler("./ligafx.log", false);
            fileHandler.setFormatter(new SimpleFormatter());

            LOG_RAIZ.addHandler(consoleHandler);
            LOG_RAIZ.addHandler(fileHandler);

            consoleHandler.setLevel(Level.ALL);
            fileHandler.setLevel(Level.ALL);

            launch(args);

            DBConexion.getConexion();
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, Util.printStackTrace(ex));
        }
    }
}
