package org.GUI;

import org.logica.Deposito;
import org.logica.Expendedor;
import org.logica.EnumProducto;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelExpendedor extends JPanel {

    private final Expendedor exp;
    private final Estante[] estantes;

    public PanelExpendedor(Expendedor exp) {
        this.exp = exp;
        //agregue layout nulo para posicionar botones, sin modificar los tamaños del panel
        this.setLayout(null);
        setOpaque(false);

        // Fijar dimensiones del panel
        Dimension tamano = new Dimension(600, 800);
        setPreferredSize(tamano);
        setMinimumSize(tamano);
        setMaximumSize(tamano);

        // Inicializamos el array de filas calculando su posición Y
        this.estantes = new Estante[3];

        // Fila 0: CocaCola y Fanta
        estantes[0] = new Estante(new Deposito[]{exp.getCoca(), exp.getFanta(), null, null}, 130);

        // Fila 1: Sprite
        estantes[1] = new Estante(new Deposito[]{exp.getSprite(), null, null, null}, 260);

        // Fila 2: Dulces
        estantes[2] = new Estante(new Deposito[]{exp.getSuper8(), exp.getSnickers(), null, null}, 390);

        //agregue
        crearBotonesCompra();
    }

    //agregue metodo que genera la capacidad de hacer clics (botones)
    private void crearBotonesCompra() {
        String[] nombres={"CocaCola","Fanta","Sprite","Super8","Snickers"};
        EnumProducto[] productos={
                EnumProducto.COCA,
                EnumProducto.FANTA,
                EnumProducto.SPRITE,
                EnumProducto.SUPER8,
                EnumProducto.SNICKERS
        };

        for (int i=0;i<nombres.length;i++) {
            JButton btn=new JButton(nombres[i]);
            btn.setBounds(465,150 +(i * 60),100,35); //se acomodan en el rectángulo gris

            final EnumProducto productoSeleccionado=productos[i];

            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (PanelComprador.monedaSeleccionada == null) {
                        JOptionPane.showMessageDialog(null, "Selecciona una moneda primero.");
                        return;
                    }
                    try {
                        exp.comprarProducto(PanelComprador.monedaSeleccionada, productoSeleccionado);
                        PanelComprador.monedaSeleccionada = null;
                        SwingUtilities.getWindowAncestor(PanelExpendedor.this).repaint();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error: "+ex.getMessage());
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

        // INTERIOR EXPENDEDOR
        g2d.setColor(VGUI.CustomColor.GRIS_AZUL);
        g2d.fillRect(20, 20, 400, 400);
    }
}