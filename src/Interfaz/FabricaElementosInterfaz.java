package Interfaz;

import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

import java.util.Random;

/**
 * Clase que se encarga de fabricas los elementos de la Interfaz.
 */
@SuppressWarnings("ALL")
public class FabricaElementosInterfaz {

    /**
     *
     */
    public static Pane canvasPrinc;

    /**
     * Metodo que se encarga de crear el canvas.
     *
     * @param canvas
     */
    public static void setCanvasPrinc(Pane canvas) {
        canvasPrinc = canvas;
    }

    /**
     * Esta metodo crea un nodo del grafo y le da todas sus caracteristicas.
     *
     * @param nombre Nombre que tiene el nodo.
     * @param coordX Coordenada inicial en x del nodo.
     * @param coordY Coordenada inicial en y del nodo.
     * @return Retorna el nodo.
     */
    public static Nodo createNodo(String nombre, int coordX, int coordY) {
        Random rand = new Random();
        Circle circle = new Circle(coordX, coordY, 50, Color.rgb(rand.nextInt(100), rand.nextInt(100),
                rand.nextInt(100)));
        circle.setStroke(Color.rgb(rand.nextInt(100), rand.nextInt(100), rand.nextInt(100)));
        circle.setStrokeWidth(4);
        Label label = new Label(nombre);
        label.setTextFill(Color.WHITE);
        DropShadow ds = new DropShadow();
        ds.setOffsetY(7.0f);
        ds.setColor(Color.BLACK);
        label.setEffect(ds);
        circle.setEffect(ds);
        canvasPrinc.getChildren().addAll(circle, label);
        label.setLabelFor(circle);
        label.relocate(coordX - 30, coordY);
        Nodo nodo = new Nodo(nombre, circle, label);
        return nodo;
    }

    /**
     * Esta metodo es exactamente igual al anterior, lo que hace es crear un nodo y asigna por default un 0,0 de
     * posicion.
     *
     * @param nombre Nombre del nodo.
     * @return Retorna el nodo.
     */
    public static Nodo createNodo(String nombre) {
        Random rand = new Random();
        Circle circle = new Circle(100, 100, 50, Color.rgb(rand.nextInt(100), rand.nextInt(100), rand.nextInt(100)));
        circle.setStroke(Color.rgb(rand.nextInt(100), rand.nextInt(100), rand.nextInt(100)));
        circle.setStrokeWidth(4);
        Label label = new Label(nombre);
        label.setTextFill(Color.WHITE);
        DropShadow ds = new DropShadow();
        ds.setOffsetY(7.0f);
        ds.setColor(Color.BLACK);
        label.setEffect(ds);
        circle.setEffect(ds);
        canvasPrinc.getChildren().addAll(circle, label);
        label.setLabelFor(circle);
        label.relocate(70, 100);

        Nodo nodo = new Nodo(nombre, circle, label);
        return nodo;
    }

    /**
     * Esta funcion lo que hace es crear una linea en medio de los 2 nodos, le da su valor de peso y asigna textos,
     * lo añade al grafo.
     *
     * @param origen  Ndo de origen de la linea.
     * @param destino Nodo del destino.
     * @param peso    Peso que tiene la linea (por el grafo).
     * @return Retorna una linea conectora.
     */
    public static LineaConectora crearLinea(Nodo origen, Nodo destino, int peso) {
        Label label = new Label(Integer.toString(peso));
        Line line = new Line();
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.WHITE);
        label.setEffect(ds);
        line.startXProperty().bind(origen.circulo.centerXProperty());
        line.startYProperty().bind(origen.circulo.centerYProperty());
        line.endXProperty().bind(destino.circulo.centerXProperty());
        line.endYProperty().bind(destino.circulo.centerYProperty());
        line.setStrokeWidth(6);
        line.setStrokeLineCap(StrokeLineCap.BUTT);
        canvasPrinc.getChildren().addAll(label, line);
        line.toBack();
        label.toFront();
        LineaConectora lineaConectora = new LineaConectora(origen, destino, peso, label, line);
        return lineaConectora;
    }
}
