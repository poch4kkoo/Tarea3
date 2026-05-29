package org.logica;

/**
 * Enumeracion que define el catalogo de productos disponibles en el expendedor.
 * Contiene el nombre del producto y su precio asociado.
 */
public enum EnumProducto {

    COCA(1000),
    SPRITE(1000),
    FANTA(1000),
    SNICKERS(800),
    SUPER8(500);

    private int precio;

    /**
     * Constructor del enum para asignar el precio a cada producto.
     * @param p El valor monetario del producto.
     */
    EnumProducto(int p){
        precio = p;
    }

    /**
     * Metodo que permite obtener el precio del producto seleccionado.
     * @return El precio entero del producto.
     */
    public int getPrecio(){
        return precio;
    }

}
