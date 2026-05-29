package Tarea1;

/**
 * Excepción lanzada cuando el Expendedor no tiene stock del producto solicitado.
 */
public class NoHayProductoException extends RuntimeException {
    public NoHayProductoException(String mensaje) {

        super(mensaje);
    }
}
