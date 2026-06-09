package org.logica;

import java.util.ArrayList;

/**
 * La clase Deposito es un contenedor genérico que permite almacenar y extraer
 * elementos
 *
 * @param <T> El tipo de objetos que se almacenarán en este depósito (Bebida, Moneda, Dulce).
 */
public class Deposito<T> {

    /** Lista donde se guardan los elementos. */
    private ArrayList<T> al;

    /** Crea un deposito*/
    public Deposito(){
        al = new ArrayList<T>();
    }

    /**
     * Agrega un nuevo elemento al depósito.
     * @param obj El objeto que se desea almacenar.
     */
    public void addElemento(T obj){
        al.add(obj);
    }

    /**
     * @return El primer elemento de tipo T disponible,
     *         o null si el depósito no tiene elementos.
     */
    public T getElemento(){
        if (al.isEmpty()){
            return null;
        }
        return al.remove(0);
    }

    public void retirarElemento(T elemento){
        al.remove(elemento);
    }

    public boolean estaVacio() {
        return al.isEmpty();
    }

    public int tamano() {
        return al.size();
    }

    public T getElementoEn(int index) {
        if (index >= 0 && index < al.size()) {
            return al.get(index);
        }

        return null;
    }
}
