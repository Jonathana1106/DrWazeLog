package Estructuras_logica;

import Interfaz.Fabrica_elementos_interfaz;
import Interfaz.Linea_conectora;
import Interfaz.Nodo;
import javafx.scene.shape.Line;

import java.security.cert.TrustAnchor;
import java.util.ArrayList;
import java.util.List;

/**
 * Una clase que representa el grafo, tiene una lista de nodos y de arcos
 */
public class Grafo {
    public static ArrayList<Nodo> nodos = new ArrayList<>();
    public static ArrayList<Linea_conectora> vertices = new ArrayList<>();
    public Grafo(){
        this.nodos = new ArrayList<Nodo>();
        this.vertices = new ArrayList<Linea_conectora>();
    }

    /**
     * Consigue todos los nombres de los nodos excepto uno
     * @param not este es el nombre descartado que no estara en la lista de resultado
     * @return una lista con el nombre de todos los nodos excepto 1
     */
    public static ArrayList<String> get_names(String not){
        ArrayList<String> lista = new ArrayList<>();
        for (int i = 0; i<nodos.size();i++){
            if(!nodos.get(i).getnode_name().equals(not) ){
                lista.add(nodos.get(i).getnode_name());
            }
        }
        return lista;
    }

    /**
     * Esta funcion lo que hace es examinar si un nodo ya existe
     * @param nombre este es el nombre que se quiere comparar
     * @return retorna falso si no encuentra un nodo y true si lo encuentra
     */
    public static boolean existencia(String nombre){
        nombre = nombre.toLowerCase();
        nombre = nombre.replaceAll("\\s+","");
        for (int i = 0; i<nodos.size();i++){
            if (nodos.get(i).getnode_name().equals(nombre)){
                return true;
            }
        }
        return false;
    }
    /**
     * Recibe un nombre y retorna el nodo segun el valor
     * @param nombre es el parametro de busqueda
     * @return retorna un nodo con el nombre anteriormente mencionado
     */
    public static Nodo get_Nodo(String nombre){
        for (int i = 0  ; i != nodos.size() ;i++){
            if(nodos.get(i).getnode_name().equals(nombre)){
                return  nodos.get(i);
            }
        }
        return null;
    }

    /**
     * Retona la calle correspondiente entre dos nodos
     * @param nodo1 este es el nodo en el que inicia el arco/calle
     * @param nodo2 este es el nodo en el que termina el arco/calle
     * @return retorna el arco entre los dos nodos
     */
    public static Linea_conectora sacaarcos(Nodo nodo1 ,Nodo nodo2){
        for (int i = 0;i<vertices.size();i++){
            if(vertices.get(i).origen.equals(nodo1) && vertices.get(i).destino.equals(nodo2)){
                return vertices.get(i);
            }
        }
        return null;

    }

    /**
     * Esta consigue todos los arcos correspondientes a un camino de nodos
     * @param caminos este es una lista de los lugares en orden
     * @return retorna uan lista de arcos que corresponden al camino mencionado
     */
    public static ArrayList<Linea_conectora> get_lineas(ArrayList<Nodo> caminos) {
        int x = caminos.size();
        int y = 0;
        ArrayList<Linea_conectora> conectoras = new ArrayList<>();
        while (y<x-1){
            conectoras.add(sacaarcos(caminos.get(y),caminos.get(y+1)));
            y++;
        }
        return conectoras;
    }

}
