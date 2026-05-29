package org.logica;

/**
 * Excepción lanzada cuando el dinero ingresado no alcanza para comprar el
 * producto seleccionado.
 */
public class PagoInsuficienteException extends RuntimeException {
    public PagoInsuficienteException(String mensaje) {
        super(mensaje);
    }
}
