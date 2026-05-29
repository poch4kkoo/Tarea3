package Tarea1;

/**
 * Representa una moneda con valor de $100.
 * Se utiliza principalmente para la entrega de vuelto en el Expendedor.
 */

public class Moneda100 extends Moneda {

    /**
     * Constructor que invoca al constructor de la clase padre Moneda.
     */
    public Moneda100(){ super();}

    /**
     * Devuelve el valor nominal de la moneda.
     * @return El valor entero 100.
     */
    public int getValor(){
        return 100;
    }
}
