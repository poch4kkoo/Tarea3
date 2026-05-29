package org.logica;

/**
 * Excepción lanzada cuando no se inserta una moneda valida.
 */
public class PagoIncorrectoException extends RuntimeException {
    public PagoIncorrectoException(String mensaje) {
        super(mensaje);
    }
}