package Tarea1;

/**
 * Representa una moneda con valor de $500.
 * Se utiliza principalmente para la entrega de vuelto en el Expendedor.
 */

public class Moneda500 extends Moneda {

    /**
     * Constructor que invoca al constructor de la clase padre Moneda.
     */
    public Moneda500(){ super();}

    /**
     * Devuelve el valor nominal de la moneda.
     * @return El valor entero 500.
     */
    public int getValor(){

        return 500;
    }
}
