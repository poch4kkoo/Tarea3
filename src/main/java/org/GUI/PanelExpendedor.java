package org.GUI;

import org.logica.Deposito;
import org.logica.Expendedor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class PanelExpendedor extends JPanel {

    private Image imgCoca;
    private Image imgFanta;
    private Image imgSprite;
    private Image imgSuper8;
    private Image imgSnickers;

    private Expendedor exp;
    private Deposito[][] estantes;

    public PanelExpendedor(Expendedor exp) {
        this.exp = exp;
        super();
        this.setLayout(new BorderLayout());

        setOpaque(false);

        this.estantes = new Deposito[][] {
                // Fila 0: CocaCola y Fanta
                {exp.getCoca(), exp.getFanta()},

                // Fila 1: Sprite
                {exp.getSprite()},

                // Fila 2: los dulces
                {exp.getSuper8(), exp.getSnickers()}
        };



        Dimension tamano = new Dimension(600, 800);

        // Le indicamos al Gestor de Diseño que no queremos que cambie de tamaño
        setPreferredSize(tamano);
        setMinimumSize(tamano);
        setMaximumSize(tamano);

        try {
            imgCoca = ImageIO.read(getClass().getResource("/Productos/cocacola.png"));
            imgFanta = ImageIO.read(getClass().getResource("/Productos/fanta.png"));
            imgSprite = ImageIO.read(getClass().getResource("/Productos/sprite.png"));
            imgSuper8 = ImageIO.read(getClass().getResource("/Productos/super8.png"));
            imgSnickers = ImageIO.read(getClass().getResource("/Productos/snickers.png"));
            System.out.println("Las imagenes cargaron exitosamente");
        } catch (IOException e) {
            System.out.println("ERROR: No se pudo cargar alguna imagen. Revisa las rutas o las carpetas del IDE.");
            e.printStackTrace();
        }


    }

    private Image obtenerImagen(Deposito deposito) {
        if (deposito == exp.getCoca()) return imgCoca;
        if (deposito == exp.getFanta()) return imgFanta;
        if (deposito == exp.getSprite()) return imgSprite;
        if (deposito == exp.getSuper8()) return imgSuper8;
        if (deposito == exp.getSnickers()) return imgSnickers;
        return null;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        //EXPENDEDOR CON BORDES SUAVES PRIMERO

        g2d.setColor(VGUI.CustomColor.NEGRO);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight() - 20, VGUI.Borde.NORMAL, VGUI.Borde.NORMAL);

        // DETALLES
        g2d.setColor(VGUI.CustomColor.GRIS_OSCURO);
        g2d.fillRect(450, 20, 130, 600);
        g2d.fillRoundRect(100 + 15, 495, 210, 85, VGUI.Borde.PEQUENO, VGUI.Borde.PEQUENO);

        // INTERIOR EXPENDEDOR
        g2d.setColor(VGUI.CustomColor.GRIS_AZUL);
        g2d.fillRect(20, 20, 400, 400);

        // Recorrer las filas
        for (int fila = 0; fila < estantes.length; fila++) {
            int yRepisa = 130 + (130 * fila);

            g2d.setColor(VGUI.CustomColor.BLANCO);
            g2d.fillRect(20, yRepisa, 400, 9);

            // Posicion horizontal en estantes
            int xProducto = 40;

            // Recorrer los depósitos que asignamos a esta fila
            for (Deposito depositoActual : estantes[fila]) {

                int cantidadEnDeposito = depositoActual.tamaño();
                Image imagenADibujar = obtenerImagen(depositoActual); // Método auxiliar

                // Dibujar los productos
                for (int i = 0; i < cantidadEnDeposito; i++) {
                    if (imagenADibujar != null) {
                        g2d.drawImage(imagenADibujar, xProducto, yRepisa - 80, 64, 80, this);

                    }
                    // Avanzamos X para el siguiente producto
                    xProducto += 60;
                }
            }
        }
    }
}

