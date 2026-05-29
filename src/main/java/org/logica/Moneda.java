package Tarea1;

/**
 * Clase abstarcta que establece el comportamiento de una moneda dentro del programa.
 * Implementa la interfaz Comparable para posibilitar el ordenamiento por valor.
 */
public abstract class Moneda implements Comparable<Moneda> {

    //Constructor
    public Moneda(){}

    /**
     * Metodo abstracto utilizado para obtener el valor de la moneda.
     * @return El valor entero de la denominacion (100, 500, 1000).
     */
    public abstract int getValor();

    /**
     * Metodo que devuelve una cadena de texto que identifica a la moneda por su valor y serie.
     * El valor se obtiene mediante get.valor() y la serie medianet el identificador unico del objeto (hashcode).
     * @return Una cadena con el formato "valor: [valor], serie: [hashcode]".
     */
    @Override
    public String toString() {
        return "valor: " + getValor() + ", serie: " + this.hashCode();
    }

    /**
     * Metodo que compara la moneda con otra segun su valor.
     * @param m El objeto (moneda) a comparar.
     * @return Un entero negativo, cero o positivo si la moneda es menor, igual o mayor, respectivamente.
     */
    @Override
    public int compareTo(Moneda m) {
        return Integer.compare(this.getValor(), m.getValor());
    }
}
