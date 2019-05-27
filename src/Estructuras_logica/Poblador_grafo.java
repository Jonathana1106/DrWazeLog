package Estructuras_logica;

import Interfaz.Fabrica_elementos_interfaz;
import Interfaz.Nodo;
import Prolog.Conexion;

import java.util.ArrayList;

public class Poblador_grafo {

    public static Conexion conexiom = new Conexion();

    /**
     * Esta funcion se conecta con la clase que se conecta con la base de datos de prolog
     * toma la lista de elementos y pobla el grafo con estos
     */
    public static void poblar_grafo(){

        ArrayList<Integer> ints = new ArrayList<>();
        ints.add(5);
        ints.add(10);
        ints.add(15);
        ints.add(20);
        ints.add(25);
        ints.add(30);
        ArrayList<String> elementos =  conexiom.getLugares();

        int coord_x = 50;
        int coord_y=50;
        for(int i = 0; i<elementos.size();i++){
            if(ints.contains(i)){
                coord_x = 50;
                coord_y = coord_y + 200;
            }
            String nombre =elementos.get(i);
                    nombre = nombre.replaceAll("\\s+","");
            System.out.println(elementos.get(i));
            Fabrica_elementos_interfaz.create_Nodo(nombre,coord_x,coord_y);
            coord_x = coord_x+150;
        }
        poblar_grafo_lineas(conexiom.getArcos());

    }

    /**
     * esta funcion toma todas las lineas del grafo leidas desde el archivo de prolog
     * y crea todas las calles, poblando el grafo
     * @param arcos estos son todos los arcos en forma de string
     */
    private static void poblar_grafo_lineas(ArrayList<ArrayList<String>> arcos) {
for (int i  = 0;i<arcos.size();i++){
    String origen_name =arcos.get(i).get(0);
    origen_name = origen_name.replaceAll("\\s+","");
    Nodo oridgen = Grafo.get_Nodo(origen_name);
    String destino_n =arcos.get(i).get(1);
    destino_n = destino_n.replaceAll("\\s+","");
Nodo destino = Grafo.get_Nodo(destino_n);
int peso = Integer.parseInt(arcos.get(i).get(2));
Fabrica_elementos_interfaz.crear_linea(oridgen,destino,peso);

}
    }
}
