package Interfaz;

import Logica.Grafo;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;

/**
 * Esta clase alamcena un nodo de destino y de origen, junto al camino que hay entre ellos
 * para de esta forma "pintar" el camino anteriormente dicho.
 */
@SuppressWarnings("ALL")
public class PintaNodos {
    public static Nodo destino;
    public static Nodo origen;
    public static ArrayList<Nodo> caminos = new ArrayList<>();
    public static ArrayList<LineaConectora> lineasConectan;

    /**
     * Metodo que recibe es una linea de strings indicando un camino,
     * obtiene todos los nodos y caminos para colorearlos.
     *
     * @param camino una lista de nombres de los nodos que se quieren colorear.
     */
    public static void pintar_camino(ArrayList<String> camino) {
        if (lineasConectan != null && !lineasConectan.contains(null)) {
            reset();
        }
        caminos = new ArrayList<>();
        for (int i = 0; i < camino.size(); i++) {
            String objetivo = camino.get(i);
            objetivo = objetivo.replaceAll("\\s+", "");
            Nodo nodografo = Grafo.getNodo(objetivo);
            nodografo.circulo.setFill(Color.DEEPSKYBLUE);
            caminos.add(nodografo);
        }
        origen = caminos.get(0);
        destino = caminos.get(caminos.size() - 1);
        ArrayList<LineaConectora> lineas = Grafo.getLineas(caminos);
        lineasConectan = lineas;
        System.out.println("El largo fue" + caminos.size());
        paintLine(lineas);
    }

    /**
     * Metodo que toma la lista de nodos y de caminos y las vuelve a un color que no indica el camino.
     */
    public static void reset() {
        if (lineasConectan == null) {
            System.out.println("LOLOL");
            return;
        }
        Random rand = new Random();
        for (int i = 0; i < caminos.size(); i++) {
            caminos.get(i).circulo.setFill(Color.rgb(rand.nextInt(200), rand.nextInt(200), rand.nextInt(200)));
        }
        for (int i = 0; i < lineasConectan.size(); i++) {
            lineasConectan.get(i).linea.setStroke(Color.rgb(rand.nextInt(200), rand.nextInt(200), rand.nextInt(200)));
        }
        lineasConectan = null;
    }

    /**
     * Metodo que recibe es una lista de lineas, de esta forma "coloreando" cada una de ellas para indicar que son un
     * camino.
     *
     * @param linea
     */
    public static void paintLine(ArrayList<LineaConectora> linea) {
        System.out.println("El largo lineas fue" + linea.size());
        for (int i = 0; i < linea.size(); i++) {
            linea.get(i).linea.setStroke(Color.DEEPSKYBLUE);
            linea.get(i).linea.toFront();
            linea.get(i).destino.circulo.toFront();
            linea.get(i).origen.circulo.toFront();
            linea.get(i).origen.label.toFront();
            linea.get(i).destino.label.toFront();
        }
    }
}