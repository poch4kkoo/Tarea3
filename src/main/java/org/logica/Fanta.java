package org.logica;

/**
 *  Representa una bebida de marca Fanta.
 */
public class Fanta extends Bebida {

    /**
     * Crea una Fanta con un numero de serie.
     * @param s Numero de serie.
     */
    public Fanta(int s){
        super(s);
    }

    /**
     * Indica el nombre del producto al ser consumido.
     * @return El string "Fanta".
     */
    @Override
    public String consumir(){

        return "Fanta";
    }
}
