package Interfaz;

import javafx.geometry.Insets;
import Logica.ConectorGrafo;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase que ejecuta el programa.
 */
@SuppressWarnings("ALL")
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Graph");
        Pane panel = new Pane();
        FabricaElementosInterfaz.setCanvasPrinc(panel);
        Scene escenaPrinc = new Scene(panel, 800, 800);
        panel.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        primaryStage.setScene(escenaPrinc);
        ConectorGrafo.poblarGrafo();
        primaryStage.show();
    }

    /**
     * Metodo que ejecuta el programa.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
