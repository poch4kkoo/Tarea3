package org.GUI;

import javax.swing.*;
import java.awt.*;


public class PanelExpendedor extends JPanel {

    public PanelExpendedor() {
        super();
        this.setLayout(new BorderLayout());


        setOpaque(false);

        Dimension tamano = new Dimension(600, 800);

        // Le indicamos al Gestor de Diseño que no queremos que cambie de tamaño
        setPreferredSize(tamano);
        setMinimumSize(tamano);
        setMaximumSize(tamano);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //EXPENDEDOR CON BORDES SUAVES PRIMERO

        g2d.setColor(VGUI.CustomColor.VERDE_EXP);

        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), VGUI.Borde.NORMAL, VGUI.Borde.NORMAL);

        // FONDO INTERNO

        g2d.setColor(VGUI.CustomColor.NEGRO);

        g2d.fillRect(15, 15, 400, 400);

        // DONDE CAE EL PRODUCTO
        g2d.fillRect(100+15, 500, 200, 75);
    }

}

