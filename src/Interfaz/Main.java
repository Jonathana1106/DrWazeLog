package Interfaz;
import javafx.geometry.Insets;
import Estructuras_logica.Grafo;
import Estructuras_logica.Poblador_grafo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import Prolog.Conexion;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Graph");
        Pane panel = new Pane();
        Fabrica_elementos_interfaz.setCanvas_princ(panel);
        Scene escena_princ = new Scene(panel, 800, 800);
        panel.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        primaryStage.setScene(escena_princ);
        Poblador_grafo.poblar_grafo();
        primaryStage.show();
    }


    public static void main(String[] args)throws IOException {
        launch(args);
    }
}
