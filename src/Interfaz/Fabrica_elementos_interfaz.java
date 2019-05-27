package Interfaz;

import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;

import java.util.Random;

public   class Fabrica_elementos_interfaz {
    public static Pane canvas_princ;

    public static void setCanvas_princ(Pane canvas){
        canvas_princ = canvas;
    }

    /**
     * Esta funcion crea un nodo del grafo y le da todas sus caracteristicas
     * @param nombre este es el nombre que tiene el nodo
     * @param coordx es la coordenada inicial en x del nodo
     * @param coordy es la coordenada inicial en y del nodo
     * @return retorna el nodo
     */
    public static Nodo create_Nodo(String nombre, int coordx , int coordy){
        Random rand = new Random();
        Circle circle = new Circle(coordx,coordy,50,Color.rgb(rand.nextInt(100),rand.nextInt(100),rand.nextInt(100)));
        circle.setStroke(Color.rgb(rand.nextInt(100),rand.nextInt(100),rand.nextInt(100)));
        circle.setStrokeWidth(4);
        Label label = new Label(nombre);
        label.setTextFill(Color.WHITE);
        DropShadow ds = new DropShadow();
        ds.setOffsetY(7.0f);
        ds.setColor(Color.BLACK);
        label.setEffect(ds);
        circle.setEffect(ds);
        canvas_princ.getChildren().addAll(circle,label);
        label.setLabelFor(circle);
        label.relocate(coordx-30,coordy);
        Nodo nodo = new Nodo(nombre,circle, label);
        return nodo;
    }

    /**
     * Esta es exactamente la misma funcion anterior, lo que hace es crear un nodo y asigna por default un 0,0 de posicion
     * @param nombre este es el nombre del nodo
     * @return retorna el nodo
     */
    public static Nodo create_Nodo(String nombre){
        Random rand = new Random();
        Circle circle = new Circle(100,100,50,Color.rgb(rand.nextInt(100),rand.nextInt(100),rand.nextInt(100)));
        circle.setStroke(Color.rgb(rand.nextInt(100),rand.nextInt(100),rand.nextInt(100)));
        circle.setStrokeWidth(4);
        Label label = new Label(nombre);
        label.setTextFill(Color.WHITE);
        DropShadow ds = new DropShadow();
        ds.setOffsetY(7.0f);
        ds.setColor(Color.BLACK);
        label.setEffect(ds);
        circle.setEffect(ds);
        canvas_princ.getChildren().addAll(circle,label);
        label.setLabelFor(circle);
        label.relocate(70,100);

        Nodo nodo = new Nodo(nombre,circle, label);
        return nodo;
    }

    /**
     * Esta funcion lo que hace es crear una linea en medio de los 2 nodos, le da su valor de peso y asigna textos, lo añade al grafo
     * @param origen este es el nodo de origen de la linea
     * @param destino este es el nodo de destino
     * @param peso este es el peso que tiene la linea(por el grafo)
     * @return retorna una Linea conectora
     */
    public static Linea_conectora crear_linea(Nodo origen , Nodo destino, int peso ){
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
        canvas_princ.getChildren().addAll(label,line);
        line.toBack();
        label.toFront();
        Linea_conectora linea_conectora = new Linea_conectora(origen,destino,peso,label,line);
    return linea_conectora;
    }
}
