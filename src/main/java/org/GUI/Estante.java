package org.GUI;

import org.logica.Deposito;
import java.awt.Graphics2D;
import java.awt.Image;


/**
 * Representa un estante físico dentro de la máquina expendedora.
 * Se encarga de organizar y dibujar los productos dentro del expendedor
 * Maneja en tiempo real la cantidad de productos y los resortes en
 * segun cada compra.
 */
public class Estante {
    private final Deposito[] depositos;
    private final int yEstante; // Coordenada Y donde se dibuja este estante

    /**
     * asigna la posición de los estantes.
     * @param depositos Arreglo de deposito que contiene los productos de cada columna.
     * @param yEstante Coordenada entera en el eje Y para dibujar el estante.
     */
    public Estante(Deposito[] depositos, int yEstante) {
        this.depositos = depositos;
        this.yEstante = yEstante;
    }


    /**
     * Dibuja la estructura del estante en la pantalla.
     * en cada columna se maneja el deposito de un producto diferente
     * @param panel actúa como contenedor y observador de las imágenes.
     */
    public void dibujar(Graphics2D g2d, PanelExpendedor panel) {
        // Dibujar la base física del estante
        g2d.setColor(VGUI.CustomColor.GRIS_CLARO);
        g2d.fillRect(20, yEstante - 5, 400, 20);

        //  Iterar sobre las 4 columnas de este estante
        for (int col = 0; col < depositos.length; col++) {
            Deposito depositoActual = depositos[col];
            int xProducto = 20 + (100 * col);

            dibujarStock(g2d, depositoActual, xProducto, panel);
        }

        //Dibujar la etiqueta de precios debajo del estante
        g2d.setColor(VGUI.CustomColor.BLANCO);
        g2d.fillRect(20, yEstante + 5, 400, 15);
    }

    /**
     * Metodo que dibuja los stock de productos.
     * Renderiza los resortes y superpone de forma escalonada
     * para representar los productos que estan en el fondo.
     * @param deposito El depósito asociado a la columna actual.
     * @param x Coordenada en el eje X donde inicia la columna.
     * @param panel El panel contenedor.
     */
    private void dibujarStock(Graphics2D g2d, Deposito deposito, int x, PanelExpendedor panel) {
        Image imgResorte = Imagenes.get("resorte");
        int xDinamico = x;
        int yDinamico = yEstante;

        if (deposito != null) {
            int cantidad = deposito.tamano();
            // Le pedimos al panel que resuelva la imagen según el depósito
            Image imgProducto = panel.obtenerImagen(deposito);

            // Dibujar stock (máximo 5 elementos visuales para no saturar)
            for (int j = 0; j < 5; j++) {
                if (cantidad > j) {
                    g2d.drawImage(imgProducto, xDinamico, yDinamico - 80, 64, 80, panel);
                }
                g2d.drawImage(imgResorte, xDinamico + 10, yDinamico - 30, 40, 40, panel);

                yDinamico += 2;
                xDinamico += 3;
            }
        } else {
            // Si el depósito es nulo, solo se dibujan los resortes vacíos
            for (int j = 0; j < 5; j++) {
                g2d.drawImage(imgResorte, xDinamico + 10, yDinamico - 30, 40, 40, panel);
                yDinamico += 2;
                xDinamico += 3;
            }
        }
    }
}