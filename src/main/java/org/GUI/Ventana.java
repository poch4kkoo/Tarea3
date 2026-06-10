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

        String mensajePrecios = "LISTA DE PRECIOS:\n"
                + "--------------------------------------------------------\n"
                + "Coca-Cola:    $1000\n"
                + "Fanta:            $1000\n"
                + "Sprite:           $1000\n"
                + "Super 8:        $500\n"
                + "Snickers:      $800\n"
                + "--------------------------------------------------------\n"
                + "INSTRUCCIONES:\n"
                + "1. Haz clic en una moneda de tu monedero (Panel Derecho).\n"
                + "2. Haz clic en la ranura gris de la máquina para ingresarla.\n"
                + "3. Presiona el botón del producto que deseas comprar.\n"
                + "4. Retire el producto del deposito, haciendo clic en el.\n"
                + "5. Presione el boton consumir para comerse el producto.\n";

        JOptionPane.showMessageDialog(this, mensajePrecios, "Menú de Precios e Instrucciones", JOptionPane.INFORMATION_MESSAGE);

        pack();
        this.setSize(VGUI.LARGO,VGUI.ALTO);
        setLocationRelativeTo(null);
        this.setVisible(true);

    }
}