package org.logica;

import java.util.ArrayList;

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

        monedero.addElemento(new Moneda1000(1));
        monedero.addElemento(new Moneda1000(2));
        monedero.addElemento(new Moneda1000(3));
        monedero.addElemento(new Moneda500(4));
        monedero.addElemento(new Moneda500(5));
        monedero.addElemento(new Moneda500(6));
        monedero.addElemento(new Moneda100(7));
        monedero.addElemento(new Moneda100(8));
        monedero.addElemento(new Moneda100(9));
        monedero.addElemento(new Moneda100(10));
        monedero.addElemento(new Moneda100(11));

    }

    private void ordenarMonedero() {
        if (this.monedero == null || this.monedero.estaVacio()) {
            return;
        }

        java.util.ArrayList<Moneda> listaTemporal = new java.util.ArrayList<>();
        while (!this.monedero.estaVacio()) {
            listaTemporal.add(this.monedero.getElemento());
        }

        java.util.Collections.sort(listaTemporal, new java.util.Comparator<Moneda>() {
            @Override
            public int compare(Moneda m1, Moneda m2) {
                return Integer.compare(m2.getValor(), m1.getValor());
            }
        });

        for (Moneda m : listaTemporal) {
            this.monedero.addElemento(m);
        }
    }

    public void recogerVuelto(Moneda m) {
        if (m != null) {
            monedero.addElemento(m);
            ordenarMonedero();
        }
    }

    public void recogerProducto(Producto p) {
        productoListo = p;
        if (productoListo != null) {
            tipo = productoListo.consumir();
        } else {
            tipo = null;
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