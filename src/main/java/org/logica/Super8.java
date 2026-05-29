package Tarea1;

/**
 *  Representa un dulce de marca Super8.
 */
public class Super8 extends Dulce{

    /**
     * Crea un Super8 con un numero de serie.
     * @param s Numero de serie.
     */
    public Super8(int s){
        super(s);
    }

    /**
     * Indica el nombre del producto al ser consumido.
     * @return El string "super8".
     */
    @Override
    public String consumir(){

        return "super8";
    }
}
