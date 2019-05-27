package Interfaz;

/**
 * Esta clase es generalmente usada para almacenar los entrys en una ventana en la cual
 * se toman diferentes selecciones.
 */
@SuppressWarnings("ALL")
public class CrearEntradas {
    String nombreNuevo;
    String nombreDestino;
    String peso;

    /**
     * Crea una entrada y le asigna sus nombres.
     *
     * @param nombreNuevo   Nombre nuevo de la entrada.
     * @param nombreDestino Nombre de destino de la entrada.
     * @param arco          Arco entre la nueva entrada y su destino.
     */
    public CrearEntradas(String nombreNuevo, String nombreDestino, String arco) {
        this.nombreDestino = nombreDestino;
        this.nombreNuevo = nombreNuevo;
        this.peso = arco;
    }
}
