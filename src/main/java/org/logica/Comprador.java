package org.logica;

import java.util.ArrayList;

/**
 *
 * Esta clase simula ser un comprador que interactua con la maquina expendedora.
 * El comprador posee un monedero con dinero inicial y puede ingresar este a la maquina
 * expendedora, y recibir vuelto despues de una compra. Ademas de recibir el producto
 * para su consumo.
 *
 */
public class Comprador {

    private String tipo;
    private Deposito<Moneda> monedero;
    private Producto productoListo;

    /**
     * Crea un comprador con su monedero precargado de monedas de distinto valor
     * y sin productos en la mano.
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

    /**
     * Metodo que ordena las monedas del monedero de mayor a menor valor.
     */
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

    /**
     * Agrega la moneda de vuelto al monedero y reordena las monedas.
     * @param m La moneda de vuelto a agregar, si es null no se hace nada.
     */
    public void recogerVuelto(Moneda m) {
        if (m != null) {
            monedero.addElemento(m);
            ordenarMonedero();
        }
    }

    /**
     * Asigna el producto retirado de la máquina como el producto en mano del comprador.
     * @param p El producto que se saco, o null si ya se consumio.
     */
    public void recogerProducto(Producto p) {
        productoListo = p;
        if (productoListo != null) {
            tipo = productoListo.consumir();
        } else {
            tipo = null;
        }
    }

    /**
     * Retorna el monedero del comprador con todas sus monedas actuales.
     *
     * @return Deposito de monedas que representa el monedero.
     */
    public Deposito<Moneda> getMonedero() {
        return monedero;
    }

    /**
     * Retorna el producto que el comprador tiene en la mano actualmente.
     *
     * @return El Producto retirado de la maquina o null si no hay producto en la mano.
     */
    public Producto getProductoListo() {
        return productoListo;
    }

    /**
     * Retorna el nombre del tipo de producto en mano del comprador.
     *
     * @return El string retornado por el metodo consumir de Producto del producto actual.
     */
    public String getTipo() {
        return tipo;
    }
}