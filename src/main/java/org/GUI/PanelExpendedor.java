package org.GUI;

import org.logica.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PanelExpendedor extends JPanel {

    private final Expendedor exp;
    private final Estante[] estantes;

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



                //clic opcional en la ranura para ingresar monedas manualmente
                if (mouseX >= 480 && mouseX <= 550 && mouseY >= 50 && mouseY <= 70) {
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
            btn.setBounds(465, 150 + (i * 60), 100, 35);

            final EnumProducto productoSeleccionado = productos[i];

            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    PanelPrincipal principal = (PanelPrincipal) SwingUtilities.getAncestorOfClass(PanelPrincipal.class, PanelExpendedor.this);

                    if (principal != null) {
                        Comprador comprador = principal.getPanelComprador().getCom();

                        //si hay una moneda seleccionada, la insertamos automa antes de comprar
                        if (PanelComprador.monedaSeleccionada!=null) {
                            exp.ingresarMoneda(PanelComprador.monedaSeleccionada);
                            comprador.getMonedero().retirarElemento(PanelComprador.monedaSeleccionada);
                            PanelComprador.monedaSeleccionada=null;//reiniciamos la selec
                        }

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

    public Image obtenerImagen(Deposito deposito) {
        if (deposito == exp.getCoca()) return Imagenes.get("cocacola");
        if (deposito == exp.getFanta()) return Imagenes.get("fanta");
        if (deposito == exp.getSprite()) return Imagenes.get("sprite");
        if (deposito == exp.getSuper8()) return Imagenes.get("super8");
        if (deposito == exp.getSnickers()) return Imagenes.get("snickers");
        return null;
    }

    // metodo de ayuda alternativo para cuando se extrae directamente del deposito de productos
    public Image obtenerImagenDeProducto(Producto p) {
        if (p==null) return null;
        String clase=p.getClass().getSimpleName().toLowerCase(); //
        return Imagenes.get(clase);
    }

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
        g2d.fillRect(460, 100, 110, 30);

        // Saldo en la pantalla CIAN
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font(VGUI.FONT, Font.BOLD, VGUI.TamanoFuente.CUERPO));
        g2d.drawString("Saldo: $" + exp.getSaldo(), 465, 120);

        // Ranura para monedas
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRoundRect(480, 50, 70, 20, 10, 10);
        g2d.setColor(Color.BLACK);
        g2d.fillRoundRect(485, 55, 60, 10, 5, 5);   // Ranura monedas

        // INTERIOR EXPENDEDOR
        g2d.setColor(VGUI.CustomColor.GRIS_AZUL);
        g2d.fillRect(20, 20, 400, 400);
    }
}