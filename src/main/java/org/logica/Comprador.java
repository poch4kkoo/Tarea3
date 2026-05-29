package org.logica;

/**
 *
 * Esta clase simula ser un comprador, el cual puede elegir un producto e ingresar
 * dinero a una maquina expendedora.
 * Se encarga de procesar la compra, consumir el producto comprado,
 * recuperar el vuelto y manejar errores.
 *
 *
 */
public class Comprador {
    /** Guarda el nombre del producto comprado */
    private String tipo;

    private Deposito<Moneda> monedero;
    private Producto productoListo;

    /**
     * Constructor de Comprador. Intenta realizar una compra en el expendedor.
     * Si la compra es exitosa, consume el producto y da vuelto. Si ocurre un error,
     * se retorna un mensaje con el respectivo fallo, y devuelve la moneda.
     *
     * El constructor maneja los posibles eventos o excepciones.
     *
     *    Si no hay stock, atrapa {@link NoHayProductoException} e imprime el aviso.
     *     Si el pago es insuficiente, atrapa {@link PagoInsuficienteException}.
     *     En ambos casos, asegura la recuperación de la moneda como vuelto.
     */
    public Comprador(){

        monedero = new Deposito<>();
        productoListo = null;

        monedero.addElemento(new Moneda100(1));
        monedero.addElemento(new Moneda100(2));
        monedero.addElemento(new Moneda100(3));
        monedero.addElemento(new Moneda100(4));
        monedero.addElemento(new Moneda100(5));
        monedero.addElemento(new Moneda500(6));
        monedero.addElemento(new Moneda500(7));
        monedero.addElemento(new Moneda500(8));
        monedero.addElemento(new Moneda1000(9));
        monedero.addElemento(new Moneda1000(10));
        monedero.addElemento(new Moneda1000(11));

    }

    public Moneda escogerMoneda() {
        return monedero.getElemento();
    }

    public void recogerVuelto(Moneda m) {
        if (m != null) {
            monedero.addElemento(m);
        }
    }

    public void recogerProducto( Producto p) {
        if (productoListo != null) {

            tipo = productoListo.consumir();
            productoListo = null;
        }
    }

    //GETTERS
    public Deposito<Moneda> getMonedero() {
        return monedero;
    }

    public Producto getProductoListo() {
        return productoListo;
    }

    public String getTipo() {
        return tipo;
    }
}