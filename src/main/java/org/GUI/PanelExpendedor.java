package org.GUI;

import org.logica.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Panel que representa la interfaz grafica del expendedor de productos.
 * se encarga de la gestion del diseño visual de la maquina, mostrando los estantes,
 * el deposito de productos para retirar, los botones interactivos de compra, la ranura
 * de ingreso de monedas y la pantalla con el saldo retenido en tiempo real.
 */

public class PanelExpendedor extends JPanel {

    private final Expendedor exp;
    private final Estante[] estantes;

    /**
     * Constructor del panel del expendedor, configura el diseño nulo para posicionamiento
     * absoluto, inicializa los estantes de productos mapeando los depositos logicos y
     * establece los mouselisteners para interactuar con la ranura de monedas y la bandeja de retiro.
     * * @param exp instancia de la clase logica expendedor que maneja las transacciones y stock.
     */
    public PanelExpendedor(Expendedor exp) {
        this.exp = exp;

        //layout nulo para añadir botones interactivos manteniendo el diseño original
        this.setLayout(null);
        setOpaque(false);

        // Fijar dimensiones del panel
        Dimension tamano = new Dimension(600, 800);
        setPreferredSize(tamano);
        setMinimumSize(tamano);
        setMaximumSize(tamano);

        //Inicializamos el array de filas calculando su posición Y
        this.estantes = new Estante[3];

        // Fila 0: CocaCola y Fanta
        estantes[0] = new Estante(new Deposito[]{exp.getCoca(), exp.getFanta(), null, null}, 130);

        // Fila 1: Sprite
        estantes[1] = new Estante(new Deposito[]{exp.getSprite(), null, null, null}, 260);

        // Fila 2: Dulces
        estantes[2] = new Estante(new Deposito[]{exp.getSuper8(), exp.getSnickers(), null, null}, 390);

        crearBotonesCompra();

        this.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();

                // Clic para retirar producto del depósito
                if (mouseX >= 115 && mouseX <= 325 && mouseY >= 495 && mouseY <= 580) {
                    if (exp.getDepProducto() != null && !exp.getDepProducto().estaVacio()) {

                        PanelPrincipal principal = (PanelPrincipal) SwingUtilities.getAncestorOfClass(PanelPrincipal.class, PanelExpendedor.this);

                        if (principal != null) {

                            Comprador comprador = principal.getPanelComprador().getCom();

                            if (comprador.getProductoListo() != null) {
                                JOptionPane.showMessageDialog(null, "¡Ya tienes un producto en la mano! Consumelo antes de retirar otro", "Mano ocupada", JOptionPane.WARNING_MESSAGE);
                                return;
                            }

                            Producto productoSacado = exp.getProducto();
                            principal.getPanelComprador().getCom().recogerProducto(productoSacado);
                            principal.repaint();
                            JOptionPane.showMessageDialog(null, "Se ha retirado el producto de la maquina. Ahora esta en su mano. ");
                        }
                    }
                }


                if (mouseX >= 20 && mouseX <= 420 && mouseY >= 20 && mouseY <= 420) {
                    // rellena los depositos vacios del expendedor
                    exp.rellenarVacios();
                    repaint();
                }



                //clic para ingresar monedas en la ranura
                if (mouseX >= 445 && mouseX <= 585 && mouseY >= 110 && mouseY <= 230) {
                    if (PanelComprador.monedaSeleccionada != null) {
                        PanelPrincipal principal = (PanelPrincipal) SwingUtilities.getAncestorOfClass(PanelPrincipal.class, PanelExpendedor.this);

                        if (principal != null) {
                            Comprador comprador = principal.getPanelComprador().getCom();

                            // Ingresamos la moneda
                            exp.ingresarMoneda(PanelComprador.monedaSeleccionada);

                            // Eliminamos la moneda del monedero del comprador
                            comprador.getMonedero().retirarElemento(PanelComprador.monedaSeleccionada);

                            // reinciamos monedaSeleccionada para preparar la siguiente
                            PanelComprador.monedaSeleccionada = null;
                            principal.repaint(); // reiniciamos la pantalla
                        }
                    }
                }
            }
        });
    }
    /**
     * Crea e inicializa de manera dinamica los botones de compra para cada tipo de producto.
     * implementa la logica que detecta si el comprador tiene una moneda seleccionada para ingresarla
     * de forma automatica antes de procesar la orden de compra.
     */
    private void crearBotonesCompra() {
        String[] nombres = {"CocaCola", "Fanta", "Sprite", "Super8", "Snickers"};
        EnumProducto[] productos = {
                EnumProducto.COCA,
                EnumProducto.FANTA,
                EnumProducto.SPRITE,
                EnumProducto.SUPER8,
                EnumProducto.SNICKERS
        };

        for (int i = 0; i < nombres.length; i++) {
            JButton btn = new JButton(nombres[i]);
            btn.setBounds(465, 240 + (i * 60), 100, 35);

            final EnumProducto productoSeleccionado = productos[i];

            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    PanelPrincipal principal = (PanelPrincipal) SwingUtilities.getAncestorOfClass(PanelPrincipal.class, PanelExpendedor.this);

                    if (principal != null) {
                        Comprador comprador = principal.getPanelComprador().getCom();

                        try {
                            //llamamos a la compra
                            exp.comprarProducto(productoSeleccionado);

                            //reetornar el vuelto al comprador o la moneda si falla la compra
                            Moneda monedaVuelto;
                            //mientras haya vuelto, el comprador lo recoge
                            while ((monedaVuelto = exp.getVuelto()) != null) {
                                comprador.recogerVuelto(monedaVuelto);
                            }

                            principal.repaint(); //actualizamos la vista

                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null,"Aviso: "+ex.getMessage());

                            //si pasa un error como falta de stock intentamos devolver el dinero insertado
                            Moneda monedaVuelto;
                            while ((monedaVuelto=exp.getVuelto())!=null) {
                                comprador.recogerVuelto(monedaVuelto);
                            }
                            principal.repaint();
                        }
                    }
                }
            });
            this.add(btn);
        }
    }
    /**
     * Mapea un deposito especifico del expendedor con su respectiva imagen.
     * * @param deposito El deposito logico de la maquina.
     * @return Image asociada al tipo de producto que almacena el deposito, o null si no aplica.
     */

    public Image obtenerImagen(Deposito deposito) {
        if (deposito == exp.getCoca()) return Imagenes.get("cocacola");
        if (deposito == exp.getFanta()) return Imagenes.get("fanta");
        if (deposito == exp.getSprite()) return Imagenes.get("sprite");
        if (deposito == exp.getSuper8()) return Imagenes.get("super8");
        if (deposito == exp.getSnickers()) return Imagenes.get("snickers");
        return null;
    }

    //metodo de ayuda alternativo para cuando se extrae directamente del deposito de productos
    /**
     * Obtiene de forma dinamica la imagen de un objeto Producto especifico basandose en el
     * nombre de su clase para renderizarlo correctamente en la bandeja de salida.
     * * @param p El objeto Producto comprado que se encuentra en el deposito de salida.
     * @return Image correspondiente al producto grafico, o null si el producto es nulo.
     */
    public Image obtenerImagenDeProducto(Producto p) {
        if (p==null) return null;
        String clase=p.getClass().getSimpleName().toLowerCase(); //
        return Imagenes.get(clase);
    }

    /**
     * Metodo encargado de pintar y redibujar todos los componentes visuales del expendedor.
     * Dibuja la estructura del mueble, invoca el renderizado de cada estante y posiciona
     * el producto en la bandeja inferior si una compra fue exitosa.
     * * @param g El objeto Graphics utilizado para las funciones de dibujo en Swing.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        dibujarMuebleEstructura(g2d);

        // Cada estante se dibuja a si mismo
        for (Estante estante : estantes) {
            estante.dibujar(g2d, this);
        }

        //dibujar producto en la salida(abajo)
        if (exp.getDepProducto()!=null && !exp.getDepProducto().estaVacio()) {
            //obtenemos el producto que esta esperando ser retirado
            Producto comprado=exp.getDepProducto().getElementoEn(0);
            Image imgProducto=obtenerImagenDeProducto(comprado);
            if (imgProducto!=null) {
                //se dibuja centrado dentro del compartimento gris azul
                g2d.drawImage(imgProducto, 195, 510, 50, 55, this);
            }
        }
    }
    /**
     * Dibuja los componentes estructurales fijos de la maquina expendedora.
     * Incluye el exterior negro, el panel lateral gris oscuro, el fondo interior,
     * la pantalla para el saldo, la ranura de monedas y el compartimento de entrega.
     * * @param g2d El contexto grafico de dos dimensiones utilizado para renderizar el mueble.
     */
    private void dibujarMuebleEstructura(Graphics2D g2d) {
        // EXPENDEDOR CON BORDES SUAVES
        g2d.setColor(VGUI.CustomColor.NEGRO);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight() - 20, VGUI.Borde.NORMAL, VGUI.Borde.NORMAL);

        // DETALLES
        g2d.setColor(VGUI.CustomColor.GRIS_OSCURO);
        g2d.fillRect(450, 20, 130, 600);

        // Compartimento de entrega de productos (abajo)
        g2d.setColor(VGUI.CustomColor.GRIS_AZUL);
        g2d.fillRoundRect(100 + 15, 495, 210, 85, VGUI.Borde.PEQUENO, VGUI.Borde.PEQUENO);

        // FONDO INTERIOR EXPENDEDOR
        g2d.fillRect(20, 20, 400, 400);

        // Pantalla del Expendedor
        g2d.setColor(VGUI.CustomColor.CIAN);
        g2d.fillRect(460, 70, 110, 30);

        // Saldo en la pantalla CIAN
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font(VGUI.FONT, Font.BOLD, VGUI.TamanoFuente.CUERPO));
        g2d.drawString("Saldo: $" + exp.getSaldo(), 465, 90);

        // Ranura para monedas
        Image imgRanura = Imagenes.get("ranura");
        g2d.drawImage(imgRanura, 445, 110, 140, 120, this);

        // INTERIOR EXPENDEDOR
        g2d.setColor(VGUI.CustomColor.GRIS_AZUL);
        g2d.fillRect(20, 20, 400, 400);
    }
}