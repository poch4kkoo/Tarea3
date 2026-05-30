package org.GUI;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
    private PanelPrincipal pri;

    public Ventana() {
        super();
        this.setLayout(new BorderLayout());
        pri = new PanelPrincipal();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); //cerrar aplicación
        this.add(pri,BorderLayout.CENTER); //se agrega al centro

        this.setSize(800,600);
        this.setVisible(true);
    }
}