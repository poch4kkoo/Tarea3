package Tarea1;

/**
 * Clase que simula el funcionamineto de una maquina expendedora.
 * Gestiona depositos de productos, validacion del pago y entrega de vuelto.
 */
public class Expendedor {

    private Deposito<Bebida> coca;
    private Deposito<Bebida> sprite;
    private Deposito<Bebida> fanta;

    private Deposito<Dulce> super8;
    private Deposito<Dulce> snickers;

    private Deposito<Moneda> monVu;

    /**
     * Inicializa el expendedor con una cantidad fija de cada producto,
     * asignandoles un numero de serie unico.
     * @param num Cantidad de unidades para cada tipo de producto.
     */
    public Expendedor(int num) {

        coca = new Deposito<>();
        fanta = new Deposito<>();
        sprite = new Deposito<>();
        super8 = new Deposito<>();
        snickers = new Deposito<>();
        monVu = new Deposito<>();

        for (int i = 0; i < num; i++){
            coca.addElemento(new CocaCola(100 + i));
            sprite.addElemento(new Sprite(200 + i));
            fanta.addElemento(new Fanta(300 + i));
            super8.addElemento(new Super8(400+i));
            snickers.addElemento(new Snickers(500 + i));
        }
    }

    /**
     * Metodo que procesa la compra de un producto. Valida la moneda y el stock.
     *
     * @param m La moneda utilizada para el pago.
     * @param TipoProducto El tipo de producto solicitado (EnumProducto).
     * @return El producto comprado si la transaccion es exitosa.
     * @throws NoHayProductoException Si el deposito  del producto esta vacio.
     * @throws PagoInsuficienteException Si el valor de la moneda es menor que el valor del producto.
     * @throws PagoIncorrectoException Si se intenta pagar con una moneda nula.
     */
    public Producto comprarProducto(Moneda m, EnumProducto TipoProducto)
            throws NoHayProductoException, PagoInsuficienteException, PagoIncorrectoException {

        // Validar que se ha ingresado una moneda
        if (m == null){
            throw new PagoIncorrectoException("el pago es incorrecto, ingresaste para pagar algo null");
        }

        int precio = TipoProducto.getPrecio();

        //Validar que el monto de la moneda alcance para el producto.
        if (m.getValor() < precio){
            monVu.addElemento(m);
            throw new PagoInsuficienteException("pago insuficiente, no te alcanza");
        }

        Producto p = null;

        //Seleccion manual del deposito segun el tipo de producto solicitado.
        if (TipoProducto == EnumProducto.COCA) p = coca.getElemento();
        else if (TipoProducto == EnumProducto.SPRITE) p = sprite.getElemento();
        else if (TipoProducto == EnumProducto.FANTA) p = fanta.getElemento();
        else if (TipoProducto == EnumProducto.SNICKERS) p = snickers.getElemento();
        else if (TipoProducto == EnumProducto.SUPER8) p = super8.getElemento();

        //Si se encontro el producto en el deposito.
        if (p != null) {
            int vuelto = m.getValor() - precio;

            //Generar vuelto en monedas de $100.
            while (vuelto >= 100) {
                monVu.addElemento(new Moneda100());
                vuelto -= 100;
            }
            return p;
        } else {
            monVu.addElemento(m);

            throw new NoHayProductoException("Agotado");
        }
    }

    /**
     * Metodo que entraga el vuelto acumulado moneda por moneda (de $100 cada una).
     * @return Una moneda de $100 del deposito de vuelto, o null si no queda mas.
     */
    public Moneda getVuelto(){
        return monVu.getElemento();
    }
}