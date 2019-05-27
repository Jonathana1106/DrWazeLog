package Interfaz;

import Estructuras_logica.Grafo;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.Random;

/**
 * Esta es la que funciona como un arco, contiene el nodo de origen y el nodo de destino, un label que
 * tiene el peso y una linea que conecta los dos nodos anteriormente mencionados
 */
public class Linea_conectora  {
    public int peso;
    public Nodo origen;
    public Nodo destino;
    public Label peso_texto;
    public Line linea;

    /**
     * Este es el constructor, recibe el nodo de inicio y el de destino
     * su peso y un label con el peso, al mismo tiempo una linea para conectar todos
     * y enlazar sus coordenadas
     * @param inicio es el nodo donde arranca la linea
     * @param destino es el nodo donde termina la linea
     * @param peso es el kilometraje de la "Calle"
     * @param peso_texto es un label conteniendo el numero anterior
     * @param linea es una linea
     */
    public Linea_conectora(Nodo inicio, Nodo destino, int peso, Label peso_texto, Line linea){
        this.origen = inicio;
        this.destino = destino;
        this.peso = peso;
        this.peso_texto = peso_texto;
        this.linea=linea;
        Grafo.vertices.add(this);
        origen.adjacencia.add(destino);
        Random rand = new Random();
        linea.setStroke(Color.rgb(rand.nextInt(100),rand.nextInt(100),rand.nextInt(100)));
        if(Grafo.sacaarcos(destino,inicio)!=null) {
            peso_texto.layoutYProperty().bind(origen.circulo.centerYProperty().add(destino.circulo.centerYProperty()).divide(2).add(-20));
            peso_texto.layoutXProperty().bind(origen.circulo.centerXProperty().add(destino.circulo.centerXProperty()).divide(2).add(-20));
        }
        else{    peso_texto.layoutYProperty().bind(origen.circulo.centerYProperty().add(destino.circulo.centerYProperty()).divide(2).add(20));
            peso_texto.layoutXProperty().bind(origen.circulo.centerXProperty().add(destino.circulo.centerXProperty()).divide(2).add(20));

        }
    }

}
