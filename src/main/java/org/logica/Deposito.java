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
     * Extrae y retorna el primer elemento del depósito y el elemento queda eliminado del depósito tras la llamada.
     * @return El primer elemento de tipo T disponible o null si el depósito no tiene elementos.
     */
    public T getElemento(){
        if (al.isEmpty()){
            return null;
        }
        return al.remove(0);
    }

    /**
     * Elimina del pedosito la primera ocurrencia del elemento indicado,
     * independientemente de su posicion.
     *
     * @param elemento El objeto a eliminar del deposito.
     */
    public void retirarElemento(T elemento){
        al.remove(elemento);
    }

    /**
     * Metodo que indica si un deposito no tiene ningun elemento.
     *
     * @return True si el deposito esat vacio, False en el caso contrario.
     */
    public boolean estaVacio() {
        return al.isEmpty();
    }

    /**
     * Retorna la cantidad de elemntos actualmente guardados en un deposito.
     *
     * @return Un entero que representa el numero de elemnetos en el deposito.
     */
    public int tamano() {
        return al.size();
    }

    /**
     * Retorna el elemento en la posición indicada sin extraerlo del depósito.
     * @param index Índice del elemento a consultar.
     *
     * @return El elemneto en la posicion indicada o null si es que index esta fuera de rango.
     */
    public T getElementoEn(int index) {
        if (index >= 0 && index < al.size()) {
            return al.get(index);
        }

        return null;
    }
}
