package Tarea1;

/**
 *  Representa una bebida de marca CocaCola.
 */
public class CocaCola extends Bebida{

    /**
     * Crea una CocaCola con un numero de serie.
     * @param s Numero de serie.
     */
    public CocaCola(int s){
        super(s);
    }

    /**
     * Indica el nombre del producto al ser consumido.
     * @return El string "cocacola".
     */
    @Override
    public String consumir() {

        return "cocacola";
    }
}
