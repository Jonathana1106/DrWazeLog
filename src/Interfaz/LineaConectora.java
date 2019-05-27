package Interfaz;

import Logica.Grafo;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.Random;

/**
 * Clase que funciona como un arco, contiene el nodo de origen y el nodo de destino, un label que
 * tiene el peso y una linea que conecta los dos nodos anteriormente mencionados.
 */
@SuppressWarnings("ALL")
public class LineaConectora {
    public int peso;
    public Nodo origen;
    public Nodo destino;
    public Label peso_texto;
    public Line linea;

    /**
     * Este es el constructor, recibe el nodo de inicio y el de destino
     * su peso y un label con el peso, al mismo tiempo una linea para conectar todos
     * y enlazar sus coordenadas.
     *
     * @param inicio    Nodo donde arranca la linea.
     * @param destino   Nodo donde termina la linea.
     * @param peso      Kilometraje de la "Calle".
     * @param pesoTexto Es un label conteniendo el numero anterior.
     * @param linea     Es una linea.
     */
    public LineaConectora(Nodo inicio, Nodo destino, int peso, Label pesoTexto, Line linea) {
        this.origen = inicio;
        this.destino = destino;
        this.peso = peso;
        this.peso_texto = pesoTexto;
        this.linea = linea;
        Grafo.vertices.add(this);
        origen.adjacencia.add(destino);
        Random rand = new Random();
        linea.setStroke(Color.rgb(rand.nextInt(100), rand.nextInt(100), rand.nextInt(100)));
        if (Grafo.sacaArcos(destino, inicio) != null) {
            pesoTexto.layoutYProperty().bind(origen.circulo.centerYProperty().add(destino.circulo.centerYProperty()).divide(2).add(-20));
            pesoTexto.layoutXProperty().bind(origen.circulo.centerXProperty().add(destino.circulo.centerXProperty()).divide(2).add(-20));
        } else {
            pesoTexto.layoutYProperty().bind(origen.circulo.centerYProperty().add(destino.circulo.centerYProperty()).divide(2).add(20));
            pesoTexto.layoutXProperty().bind(origen.circulo.centerXProperty().add(destino.circulo.centerXProperty()).divide(2).add(20));
        }
    }
}