package Logica;

import Interfaz.FabricaElementosInterfaz;
import Interfaz.Nodo;
import Prolog.Conexion;

import java.util.ArrayList;

/**
 * Clase que se encarga de conectar el grafo.
 */
@SuppressWarnings("ALL")
public class ConectorGrafo {

    public static Conexion conexion = new Conexion();

    /**
     * Este metodo se conecta con la clase que se conecta con la base de datos de prolog
     * toma la lista de elementos y pobla el grafo con estos.
     */
    public static void poblarGrafo() {

        ArrayList<Integer> ints = new ArrayList<>();
        ints.add(5);
        ints.add(10);
        ints.add(15);
        ints.add(20);
        ints.add(25);
        ints.add(30);
        ArrayList<String> elementos = conexion.getLugares();

        int coordX = 50;
        int coordY = 50;
        for (int i = 0; i < elementos.size(); i++) {
            if (ints.contains(i)) {
                coordX = 50;
                coordY = coordY + 200;
            }
            String nombre = elementos.get(i);
            nombre = nombre.replaceAll("\\s+", "");
            System.out.println(elementos.get(i));
            FabricaElementosInterfaz.createNodo(nombre, coordX, coordY);
            coordX = coordX + 150;
        }
        poblarGrafoLineas(conexion.getArcos());

    }

    /**
     * Metodo que toma todas las lineas del grafo leidas desde el archivo de prolog.
     * y crea todas las calles, poblando el grafo.
     *
     * @param arcos estos son todos los arcos en forma de string.
     */
    private static void poblarGrafoLineas(ArrayList<ArrayList<String>> arcos) {
        for (int i = 0; i < arcos.size(); i++) {
            String origenName = arcos.get(i).get(0);
            origenName = origenName.replaceAll("\\s+", "");
            Nodo origen = Grafo.getNodo(origenName);
            String destinoN = arcos.get(i).get(1);
            destinoN = destinoN.replaceAll("\\s+", "");
            Nodo destino = Grafo.getNodo(destinoN);
            int peso = Integer.parseInt(arcos.get(i).get(2));
            FabricaElementosInterfaz.crearLinea(origen, destino, peso);
        }
    }
}
