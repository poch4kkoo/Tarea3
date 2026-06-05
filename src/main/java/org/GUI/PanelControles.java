package org.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.logica.Expendedor;

public class PanelControles extends JPanel {

    private Expendedor exp;

    public PanelControles(Expendedor expendedor) {
        this.exp = expendedor;

        // Configuramos el diseño: 3 filas, 4 columna, con separación de 10px
        setLayout(new GridLayout(3, 4, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(VGUI.CustomColor.GRIS_OSCURO);

        String[] productos = {"CocaCola", "Fanta", "Sprite", "Super 8", "Snickers"};

        // Generamos y agregamos los botones
        for (String nombreProducto : productos) {
            JButton boton = crearBoton(nombreProducto);

            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Aquí registramos el clic

                    cerrarVentana();
                }
            });

            add(boton);
        }
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font(VGUI.FONT, Font.BOLD, VGUI.TamanoFuente.ENCABEZADO_2));
        boton.setBackground(VGUI.CustomColor.AZUL_MARCA);
        boton.setForeground(VGUI.CustomColor.BLANCO);
        boton.setFocusable(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia el cursor al pasar por encima
        return boton;
    }

    private void cerrarVentana() {
        Window ventana = SwingUtilities.getWindowAncestor(this);
        if (ventana != null) {
            ventana.dispose();
        }
    }
}