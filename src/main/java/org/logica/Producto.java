package Tarea1;

/**
 * Clase abstracta que representa distintos tipos productos
 * de productos, en este caso Bebidas y Dulces.
 */
public abstract class Producto {
    private  int serie;

    /**
     * Constructor para darle a cada producto un numero de serie.
     *
     * @param serie Valor entero asignado al producto.
     */
    public Producto(int serie){
        this.serie = serie;
    }

    /**
     * Metodo abstracto que devuelve el nombre o marca del producto cuando
     * el comprador lo llama.
     *
     * @return Nombre o marca del producto
     */
    public abstract String consumir();

    public int getSerie(){
        return serie;
    }

}
