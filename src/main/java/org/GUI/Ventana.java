package org.GUI;

import javax.swing.*;
import java.awt.*;
/**
 * Clase principal de la interfaz grafica que extiende de JFrame.
 * Actua como la ventana contenedora principal de la aplicacion, configurando
 * el tamaño de la pantalla, el comportamiento de cierre y montando el PanelPrincipal.
 */

public class Ventana extends JFrame {
    private PanelPrincipal pri;

    /**
     * Constructor de la Ventana. Configura las propiedades esenciales del marco
     * (titulo, operacion de salida por defecto, centrado en pantalla y la dimesion)
     * e inicializa el PanelPrincipal que contiene la simulacion.
     */
    public Ventana() {
        super();
        this.setLayout(new BorderLayout());
        pri = new PanelPrincipal();

        setTitle("Expendedor de Bebidas");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE); //cerrar aplicación
        this.add(pri,BorderLayout.CENTER); //se agrega al centro

        pack();
        this.setSize(VGUI.LARGO,VGUI.ALTO);
        setLocationRelativeTo(null);
        this.setVisible(true);

    }
}