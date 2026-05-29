package Tarea1;

/**
 *  Representa un dulce de marca Snickers.
 */
public class Snickers extends Dulce {

    /**
     * Crea un Snickers con un numero de serie.
     * @param s Numero de serie.
     */
    public Snickers(int s) { super(s);}


    /**
     * Indica el nombre del producto al ser consumido.
     * @return El string "snickers".
     */
    @Override
    public String consumir(){

        return "snickers";
    }
}
