package Interfaz;

/**
 * Esta clase es generalmente usada para almacenar los entrys en una ventana en la cual
 * se toman diferentes selecciones
 */
public class Entrys_crear {
    String nombre_nuevo;
    String nombre_destino;
    String peso;

    /**
     * Crea un entry y le asigna sus nombres
     * @param nombre_nuevo
     * @param nombre_destino
     * @param arco
     */
    public Entrys_crear(String nombre_nuevo,String nombre_destino,String arco){
        this.nombre_destino = nombre_destino;
        this.nombre_nuevo = nombre_nuevo;
        this.peso = arco;
    }
}
