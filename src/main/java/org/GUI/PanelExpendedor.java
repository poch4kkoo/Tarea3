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

        g2d.setColor(VGUI.CustomColor.NEGRO);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight()-20, VGUI.Borde.NORMAL, VGUI.Borde.NORMAL);

        // DETALLES
        g2d.setColor(VGUI.CustomColor.GRIS_OSCURO);
        g2d.fillRect(450, 20, 130, 600);
        g2d.fillRoundRect(100+15, 495, 210, 85, VGUI.Borde.PEQUENO, VGUI.Borde.PEQUENO);

        // INTERIOR EXP

        g2d.setColor(VGUI.CustomColor.GRIS_AZUL);
        g2d.fillRect(20, 20, 400, 400);

        for(int i=0; i<4; i++) {
            g2d.setColor(VGUI.CustomColor.BLANCO);
            g2d.fillRect(20, 100+(100*i), 400, 9);
        }

        // DONDE CAE EL PRODUCTO

        g2d.setColor(VGUI.CustomColor.GRIS_AZUL);
        g2d.fillRect(100+20, 500, 200, 75);
    }

}

