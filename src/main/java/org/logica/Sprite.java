package Tarea1;

/**
 *  Representa una bebida de marca Sprite.
 */
public class Sprite extends Bebida{

    /**
     * Crea una Sprite con un numero de serie.
     * @param s Numero de serie.
     */
    public Sprite(int s){
        super(s);
    }

    /**
     * Indica el nombre del producto al ser consumido.
     * @return El string "sprite".
     */
    @Override
    public String consumir(){

        return "sprite";
    }
}
