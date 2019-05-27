package Logica;

import Interfaz.LineaConectora;
import Interfaz.Nodo;

import java.util.ArrayList;

/**
 * Una clase que representa el grafo, tiene una lista de nodos y de arcos.
 */
@SuppressWarnings("ALL")
public class Grafo {
    public static ArrayList<Nodo> nodos = new ArrayList<>();
    public static ArrayList<LineaConectora> vertices = new ArrayList<>();

    /**
     *
     */
    public Grafo() {
        this.nodos = new ArrayList<Nodo>();
        this.vertices = new ArrayList<LineaConectora>();
    }

    /**
     * Metodo que consigue todos los nombres de los nodos excepto uno.
     *
     * @param not Este es el nombre descartado que no estara en la lista de resultado.
     * @return Retorna una lista con el nombre de todos los nodos excepto 1.
     */
    public static ArrayList<String> getNames(String not) {
        ArrayList<String> lista = new ArrayList<>();
        for (int i = 0; i < nodos.size(); i++) {
            if (!nodos.get(i).getNodeName().equals(not)) {
                lista.add(nodos.get(i).getNodeName());
            }
        }
        return lista;
    }

    /**
     * Este metodo examina si un nodo ya existe.
     *
     * @param nombre Este es el nombre que se quiere comparar.
     * @return Retorna falso si no encuentra un nodo y true si lo encuentra.
     */
    public static boolean existencia(String nombre) {
        nombre = nombre.toLowerCase();
        nombre = nombre.replaceAll("\\s+", "");
        for (int i = 0; i < nodos.size(); i++) {
            if (nodos.get(i).getNodeName().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Metodo que recibe un nombre y retorna el nodo segun el valor.
     *
     * @param nombre Este es el parametro de busqueda.
     * @return Retorna un nodo con el nombre anteriormente mencionado.
     */
    public static Nodo getNodo(String nombre) {
        for (int i = 0; i != nodos.size(); i++) {
            if (nodos.get(i).getNodeName().equals(nombre)) {
                return nodos.get(i);
            }
        }
        return null;
    }

    /**
     * Metodo que retona la calle correspondiente entre dos nodos.
     *
     * @param nodo1 Este es el nodo en el que inicia el arco/calle.
     * @param nodo2 Este es el nodo en el que termina el arco/calle
     * @return Retorna el arco entre los dos nodos.
     */
    public static LineaConectora sacaArcos(Nodo nodo1, Nodo nodo2) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).origen.equals(nodo1) && vertices.get(i).destino.equals(nodo2)) {
                return vertices.get(i);
            }
        }
        return null;
    }

    /**
     * Este metodo consigue todos los arcos correspondientes a un camino de nodos.
     *
     * @param caminos Este es una lista de los lugares en orden.
     * @return Retorna uan lista de arcos que corresponden al camino mencionado.
     */
    public static ArrayList<LineaConectora> getLineas(ArrayList<Nodo> caminos) {
        int x = caminos.size();
        int y = 0;
        ArrayList<LineaConectora> conectoras = new ArrayList<>();
        while (y < x - 1) {
            conectoras.add(sacaArcos(caminos.get(y), caminos.get(y + 1)));
            y++;
        }
        return conectoras;
    }
}