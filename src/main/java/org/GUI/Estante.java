package org.GUI;

import org.logica.Deposito;
import java.awt.Graphics2D;
import java.awt.Image;

public class Estante {
    private final Deposito[] depositos;
    private final int yEstante; // Coordenada Y donde se dibuja este estante

    public Estante(Deposito[] depositos, int yEstante) {
        this.depositos = depositos;
        this.yEstante = yEstante;
    }

    public void dibujar(Graphics2D g2d, PanelExpendedor panel) {
        // Dibujar la base física del estante
        g2d.setColor(VGUI.CustomColor.GRIS_CLARO);
        g2d.fillRect(20, yEstante - 5, 400, 20);

        //  Iterar sobre las 4 columnas de este estante
        for (int col = 0; col < depositos.length; col++) {
            Deposito depositoActual = depositos[col];
            int xProducto = 20 + (100 * col);

            dibujarSlot(g2d, depositoActual, xProducto, panel);
        }

        //Dibujar la etiqueta de precios debajo del estante
        g2d.setColor(VGUI.CustomColor.BLANCO);
        g2d.fillRect(20, yEstante + 5, 400, 15);
    }

    private void dibujarSlot(Graphics2D g2d, Deposito deposito, int x, PanelExpendedor panel) {
        Image imgResorte = Imagenes.get("resorte");
        int xDinamico = x;
        int yDinamico = yEstante;

        if (deposito != null) {
            int cantidad = deposito.tamaño();
            // Le pedimos al panel que resuelva la imagen según el depósito
            Image imgProducto = panel.obtenerImagen(deposito);

            // Dibujar stock (máximo 5 elementos visuales para no saturar)
            int limite = Math.min(cantidad, 5);
            for (int j = 0; j < limite; j++) {
                g2d.drawImage(imgProducto, xDinamico, yDinamico - 80, 64, 80, panel);
                g2d.drawImage(imgResorte, xDinamico + 10, yDinamico - 30, 40, 40, panel);

                yDinamico += 1;
                xDinamico += 2;
            }
        } else {
            // Si el depósito es nulo, solo se dibujan los resortes vacíos
            for (int j = 0; j < 5; j++) {
                g2d.drawImage(imgResorte, xDinamico + 10, yDinamico - 30, 40, 40, panel);
                yDinamico += 1;
                xDinamico += 2;
            }
        }
    }
}