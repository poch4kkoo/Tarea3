package org.logica;

/**
 * Representa una moneda con valor de $1000.
 * Se utiliza principalmente para la entrega de vuelto en el Expendedor.
 */

public class Moneda1000 extends Moneda{

    /**
     * Constructor que invoca al constructor de la clase padre Moneda.
     */
    public Moneda1000(int s){ super(s);}

    /**
     * Devuelve el valor nominal de la moneda.
     * @return El valor entero 1000.
     */
    public int getValor(){

        return 1000;
    }
}
