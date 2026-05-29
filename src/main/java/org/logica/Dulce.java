package Tarea1;

/**
 * Clase abstracta que representa un producto del tipo dulce.
 * Sirve como base para los productos Super8 y Snickers.
 */
public abstract class Dulce extends Producto{

    /**
     *Crea un dulce con un numero de serie especifico.
     * @param s El numero de serie del producto.
     */
    public Dulce(int s) { super(s);}
}
