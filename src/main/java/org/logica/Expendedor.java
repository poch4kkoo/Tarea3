package org.logica;

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
    private Deposito<Moneda> DepMonedas;

    private Deposito<Producto> DepProducto;

    private int saldo = 0;
    private Deposito<Moneda> pagoTemporal = new Deposito<>();

    private int series = 1000;

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
        DepMonedas = new Deposito<>();
        DepProducto = new Deposito<>();

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
     * @param TipoProducto El tipo de producto solicitado (EnumProducto).
     * @throws NoHayProductoException Si el deposito  del producto esta vacio.
     * @throws PagoInsuficienteException Si el valor de la moneda es menor que el valor del producto.
     * @throws PagoIncorrectoException Si se intenta pagar con una moneda nula.
     */

    // Se elimina el parámetro Moneda m
    public void comprarProducto(EnumProducto TipoProducto)
            throws NoHayProductoException, PagoInsuficienteException {

        int precio = TipoProducto.getPrecio();

        // Excepcion en caso de que el saldo alcance
        if (saldo < precio){
            throw new PagoInsuficienteException("Saldo insuficiente. Necesitas $" + precio);
        }

        // No permite compra si no recogiste el producto
        if (!DepProducto.estaVacio()) {
            return;
        }

        Producto p = null;

        if (TipoProducto == EnumProducto.COCA) p = coca.getElemento();
        else if (TipoProducto == EnumProducto.SPRITE) p = sprite.getElemento();
        else if (TipoProducto == EnumProducto.FANTA) p = fanta.getElemento();
        else if (TipoProducto == EnumProducto.SNICKERS) p = snickers.getElemento();
        else if (TipoProducto == EnumProducto.SUPER8) p = super8.getElemento();

        if (p != null) {
            int vuelto = saldo - precio;

            // Mueve las monedas ingresadas al depósito interno de la máquina
            Moneda mt;
            while ((mt = pagoTemporal.getElemento()) != null) {
                DepMonedas.addElemento(mt);
            }
            saldo = 0; // Reiniciamos el saldo actual

            // Generar vuelto
            while (vuelto >= 500) {
                monVu.addElemento(new Moneda500(series++));
                vuelto -= 500;
            }
            while (vuelto >= 100) {
                monVu.addElemento(new Moneda100(series++));
                vuelto -= 100;
            }

            DepProducto.addElemento(p);

        } else {
            throw new NoHayProductoException("Producto Agotado");
        }
    }

    public void ingresarMoneda(Moneda m) {
        if (m != null) {
            pagoTemporal.addElemento(m);
            saldo += m.getValor();
        }
    }

    /**
     * Metodo que entraga el vuelto acumulado moneda por moneda.
     * @return Una moneda del deposito de vuelto, o null si no queda mas.
     */
    public Moneda getVuelto(){
        return monVu.getElemento();
    }

    /**
     * Permite extraer el producto del deposito donde caen los productos comprados (uno a las vez).
     * Cuando se retira el producto, la posicion queda  y disponible para la siguiente compra.
     * @return El producto comprado que estaba en el deposito o null si es  que no habia ningun producto.
     */
    public Producto getProducto() {
        return DepProducto.getElemento();
    }

    public int getSaldo() { return saldo; }

    //GETTERS

    public Deposito<Bebida> getCoca() {
        return coca;
    }

    public Deposito<Bebida> getFanta() {
        return fanta;
    }

    public Deposito<Bebida> getSprite() {
        return sprite;
    }

    public Deposito<Dulce> getSnickers() {
        return snickers;
    }

    public Deposito<Dulce> getSuper8() {
        return super8;
    }

    public Deposito<Moneda> getDepMonedas() {
        return DepMonedas;
    }

    public Deposito<Moneda> getMonVu() {
        return monVu;
    }

    public Deposito<Producto> getDepProducto() {
        return DepProducto;
    }
}