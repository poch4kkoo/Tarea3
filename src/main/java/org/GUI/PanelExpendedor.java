package org.GUI;

import org.logica.Deposito;
import org.logica.Expendedor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;


public class PanelExpendedor extends JPanel {

    private Image imgCoca;
    private Image imgFanta;
    private Image imgSprite;
    private Image imgSuper8;
    private Image imgSnickers;
    private Image imgResorte;

    private Expendedor exp;
    private Deposito[][] estantes;

    public PanelExpendedor(Expendedor exp) {
        this.exp = exp;
        super();
        this.setLayout(new BorderLayout());

        setOpaque(false);

        this.estantes = new Deposito[3][4];
                // Fila 0: CocaCola y Fanta
                estantes[0][0] = exp.getCoca();
                estantes[0][1] = exp.getFanta();

                // Fila 1: Sprite
                estantes[1][0] = exp.getSprite();

                // Fila 2: Dulces
                estantes[2][0] = exp.getSuper8();
                estantes[2][1] = exp.getSnickers();



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
            imgResorte = ImageIO.read(getClass().getResource("/Extras/resorte.png"));
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

            // Base del estante
            g2d.setColor(VGUI.CustomColor.GRIS_CLARO);
            g2d.fillRect(20, yRepisa - 5, 400, 20);

            // Iteramos sobre las 4 columnas
            for (int col = 0; col < estantes[fila].length; col++) {
                Deposito depositoActual = estantes[fila][col];
                int xProducto = 20 + (100 * col);

                int yDinamico = yRepisa;
                int xDinamico = xProducto;

                // Solo si el espacio actual de la matriz TIENE un depósito asignado
                if (depositoActual != null) {
                    int cantidadEnDeposito = depositoActual.tamaño();
                    Image imagenADibujar = obtenerImagen(depositoActual);


                    // Dibujar el stock de productos
                    for (int j = 0; j < cantidadEnDeposito && j < 10; j++) {
                        if (imagenADibujar != null) {
                            g2d.drawImage(imagenADibujar, xDinamico, yDinamico - 80, 64, 80, this);
                            //resortes extra por cada producto:
                            g2d.drawImage(imgResorte, xDinamico + 10, yDinamico - 30, 40, 40, this);

                            yDinamico += 1;
                            xDinamico += 2;
                        }
                    }

                // El resorte siempre se dibuja, haya o no producto
                }  else {
                    int r = 0;
                    while (r<5) {
                        g2d.drawImage(imgResorte, xDinamico + 10, yDinamico - 30, 40, 40, this);

                        yDinamico += 1;
                        xDinamico += 2;

                        r++;
                    }
                }
            }

            // Etiquetas de precios
            g2d.setColor(VGUI.CustomColor.BLANCO);
            g2d.fillRect(20, yRepisa + 5, 400, 15);
        }
    }
}


