package Tarea1;

/**
 *
 * Esta clase simula ser un comprador, el cual puede elegir un producto e ingresar
 * dinero a una maquina expendedora.
 * Se encarga de procesar la compra, consumir el producto comprado,
 * recuperar el vuelto y manejar errores.
 *
 *
 */
public class Comprador {
    /** Guarda el nombre del producto comprado */
    private String tipo;
    /** Guarda el valor total del vuelto */
    private int vuelto = 0;

    /**
     * Constructor de Comprador. Intenta realizar una compra en el expendedor.
     * Si la compra es exitosa, consume el producto y da vuelto. Si ocurre un error,
     * se retorna un mensaje con el respectivo fallo, y devuelve la moneda.
     *
     * @param m            La Moneda que se entrega para realizar la compra.
     * @param TipoProducto El nombre del producto a comprar
     * @param exp          El Expendedor al que se le realiza la compra.
     *
     * El constructor maneja los posibles eventos o excepciones.
     *
     *    Si no hay stock, atrapa {@link NoHayProductoException} e imprime el aviso.
     *     Si el pago es insuficiente, atrapa {@link PagoInsuficienteException}.
     *     En ambos casos, asegura la recuperación de la moneda como vuelto.
     */
    public Comprador(Moneda m, EnumProducto TipoProducto, Expendedor exp){
        Producto p = null;
        try {
            p = exp.comprarProducto(m, TipoProducto);

            //Error en caso de acabarse el stock
        } catch (NoHayProductoException e) {
            System.out.println("Error: " + e.getMessage());
            p = null;

            //Error en caso de Pago insuficiente
        } catch (PagoInsuficienteException e){
            System.out.println("Error en el pago: " + e.getMessage());
            p = null;

            //error en caso de moneda nula (pago incorrecto)
        } catch (PagoIncorrectoException e){
            System.out.println("Error de moneda: " + e.getMessage());
            p =null;
        }

        if (p != null) {
            tipo = p.consumir();
        } else {
            tipo = null;
        }

        Moneda aux = exp.getVuelto();

        //Devuelve en monedas de 100
        while (aux != null){
            vuelto += aux.getValor();
            aux = exp.getVuelto();
        }
    }

    /**
     * Permite conocer el vuelto total recibido tras la compra.
     * @return El valor entero del vuelto recuperado.
     */
    public int cuantoVuelto(){
        return vuelto;
    }

    /**
     * Entrega el nombre del producto consumido.
     * @return Un String con el nombre del producto consumido, null en caso de error.
     */
    public String queProducto(){
        return tipo;
    }
}