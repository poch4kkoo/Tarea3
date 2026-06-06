package org.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.logica.EnumProducto;
import org.logica.Expendedor;
import org.logica.Moneda;
import org.logica.PagoIncorrectoException;

public class PanelControles extends JPanel {

    private Expendedor exp;
    private PanelComprador panelComprador;

    public PanelControles(Expendedor expendedor,PanelComprador panelComprador) {
        this.exp = expendedor;

        // Configuramos el diseño: 3 filas, 4 columna, con separación de 10px
        setLayout(new GridLayout(3, 4, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(VGUI.CustomColor.GRIS_OSCURO);


        String[] nombres = {"CocaCola", "Fanta", "Sprite", "Super 8", "Snickers"};
        EnumProducto[] tipos = {EnumProducto.COCA, EnumProducto.FANTA, EnumProducto.SPRITE, EnumProducto.SUPER8, EnumProducto.SNICKERS};

        for (int i = 0; i < nombres.length; i++) {
            String nombreProducto = nombres[i];
            EnumProducto tipoProducto = tipos[i];

            JButton boton = crearBoton(nombreProducto);

            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    hacerCompra(tipoProducto);
                }
            });

            add(boton);
        }
    }

    private void hacerCompra(EnumProducto tipo) {
        // Obtenemos la moneda seleccionada en el PanelComprador
        Moneda monedaUsa = panelComprador.getMonedaSeleccionada();

        try {
            // Intentar la compra
            exp.comprarProducto(monedaUsa, tipo);

            // Quitamos la moneda de la mano del comprador (ya se la tragó la máquina)
            panelComprador.setMonedaSeleccionada(null);

        } catch (PagoIncorrectoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de Pago", JOptionPane.WARNING_MESSAGE);
        } finally {
            // Redibujamos la ventana una vez hecha la compra, Deberian haber menos productos en el estante
            Window ventanaPrincipal = SwingUtilities.getWindowAncestor(panelComprador);
            if (ventanaPrincipal != null) {
                ventanaPrincipal.repaint();
            }
            cerrarVentana();
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