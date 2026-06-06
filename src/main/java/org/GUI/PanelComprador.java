package org.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.logica.*;

public class PanelComprador extends JPanel {

    private Comprador com;
    private Expendedor exp;

    private JButton botonConsumir;

    //guarda la moneda seleccionada
    public static Moneda monedaSeleccionada = null;

    public PanelComprador(Comprador comprador, Expendedor expendedor) {
        super();

        com = comprador;
        exp = expendedor;

        Dimension tamano = new Dimension(450, 800);
        setPreferredSize(tamano);
        setMinimumSize(tamano);
        setMaximumSize(tamano);

        setLayout(new BorderLayout());
        setOpaque(false);

        botonConsumir = new JButton("consumir producto");
        botonConsumir.setFont(new Font(VGUI.FONT, Font.BOLD, VGUI.TamanoFuente.ENCABEZADO_2));
        botonConsumir.setBackground(VGUI.CustomColor.VERDE);
        botonConsumir.setForeground(VGUI.CustomColor.BLANCO);
        botonConsumir.setFocusable(false);
        botonConsumir.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        botonConsumir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (com.getProductoListo() != null) {
                    com.recogerProducto(com.getProductoListo());
                    JOptionPane.showMessageDialog(null, "Consumiste tu producto");
                    SwingUtilities.getWindowAncestor(PanelComprador.this).repaint();
                }
                else {
                    JOptionPane.showMessageDialog(null, "No tienes ningun producto para consumir");
                }
            }
        });

        add(botonConsumir, BorderLayout.SOUTH);

        //detector de clics en las monedas
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (com.getMonedero() != null) {
                    int xInicial = 45;
                    int yInicial = 70;
                    int columnas = 5;

                    for (int i = 0; i < com.getMonedero().tamaño(); i++) {
                        Moneda m = com.getMonedero().getElementoEn(i);
                        if (m == null) continue;

                        int fila = i / columnas;
                        int col = i % columnas;
                        int x = xInicial + (col * 75);
                        int y = yInicial + (fila * 75);

                        //si el clic cae dentro del area de la moneda
                        if (e.getX() >= x && e.getX() <= (x + 50) && e.getY() >= y && e.getY() <= (y + 50)) {
                            monedaSeleccionada = m;
                            repaint();
                            break;
                        }
                    }
                }
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.setColor(new Color(34, 38, 41));
        graphics2D.fillRect(10, 0, getWidth(), getHeight());

        graphics2D.setColor(new Color(45, 49, 53));
        graphics2D.fillRoundRect(20, 20, getWidth() - 40, 340, VGUI.Borde.PEQUENO, VGUI.Borde.PEQUENO);
        graphics2D.setColor(new Color(65, 70, 75));
        graphics2D.drawRoundRect(20, 20, getWidth() - 40, 340, VGUI.Borde.PEQUENO, VGUI.Borde.PEQUENO);

        graphics2D.setColor(new Color(150, 155, 160));
        graphics2D.setFont(new Font(VGUI.FONT, Font.BOLD, VGUI.TamanoFuente.CUERPO));
        graphics2D.drawString("Monedas:", 40, 45);

        int xInicial = 45;
        int yInicial = 70;
        int columnas = 5;

        if (com.getMonedero() != null) {

            int totalMonedas = com.getMonedero().tamaño();

            for (int i = 0; i < totalMonedas; i++) {

                Moneda m = com.getMonedero().getElementoEn(i);
                if (m == null) continue;

                int fila = i / columnas;
                int col = i % columnas;
                int x = xInicial + (col * 75);
                int y = yInicial + (fila * 75);

                int diametro = 50;
                Color colorMoneda = Color.GRAY;

                if (m.getValor() == 100) {
                    colorMoneda = new Color(191, 137, 82);
                    diametro = 44;
                } else if (m.getValor() == 500) {
                    colorMoneda = new Color(212, 175, 55);
                    diametro = 50;
                } else if (m.getValor() == 1000) {
                    colorMoneda = new Color(77, 170, 139);
                    diametro = 56;
                }

                int offset = (56 - diametro) / 2;
                int xFinal = x + offset;
                int yFinal = y + offset;

                // Borde rojo alrededor de la moneda seleccionada
                if (m == monedaSeleccionada) {
                    graphics2D.setColor(Color.RED);
                    graphics2D.setStroke(new BasicStroke(3f));
                    graphics2D.drawOval(xFinal - 3, yFinal - 3, diametro + 6, diametro + 6);
                }

                graphics2D.setColor(new Color(0, 0, 0, 50));
                graphics2D.fillOval(xFinal + 2, yFinal + 2, diametro, diametro);

                graphics2D.setColor(colorMoneda);
                graphics2D.fillOval(xFinal, yFinal, diametro, diametro);

                graphics2D.setColor(colorMoneda.brighter());
                graphics2D.setStroke(new BasicStroke(1.5f));
                graphics2D.drawOval(xFinal, yFinal, diametro, diametro);

                graphics2D.setColor(Color.WHITE);
                graphics2D.setFont(new Font(VGUI.FONT, Font.BOLD, 13));
                String textoValor = "$" + m.getValor();

                FontMetrics fm = graphics2D.getFontMetrics();
                int tx = xFinal + (diametro - fm.stringWidth(textoValor)) / 2;
                int ty = yFinal + (diametro - fm.getHeight()) / 2 + fm.getAscent();
                graphics2D.drawString(textoValor, tx, ty);
            }
        }

        graphics2D.setStroke(new BasicStroke(1f));

        graphics2D.setColor(VGUI.CustomColor.NEGRO);
        graphics2D.setFont(new Font(VGUI.FONT, Font.BOLD, VGUI.TamanoFuente.ENCABEZADO_2));
        graphics2D.drawString("En la mano:", 40, 440);

        graphics2D.setColor(VGUI.CustomColor.BLANCO);
        graphics2D.fillRoundRect(40, 460, 160, 180, VGUI.Borde.PEQUENO, VGUI.Borde.PEQUENO);
        graphics2D.setColor(VGUI.CustomColor.GRIS_OSCURO);
        graphics2D.drawRoundRect(40, 460, 160, 180, VGUI.Borde.PEQUENO, VGUI.Borde.PEQUENO);

        Producto enMano = com.getProductoListo();

        if (enMano != null) {
            graphics2D.setColor(VGUI.CustomColor.ROSA);
            graphics2D.fillRoundRect(80, 500, 80, 110, 10, 10);
            graphics2D.setColor(VGUI.CustomColor.NEGRO);
            graphics2D.drawRoundRect(80, 500, 80, 110, 10, 10);

            graphics2D.setColor(VGUI.CustomColor.BLANCO);
            graphics2D.setFont(new Font(VGUI.FONT, Font.BOLD, VGUI.TamanoFuente.OCULTO));
            graphics2D.drawString("N° :" + enMano.getSerie(), 100, 540);
        } else {
            graphics2D.setColor(Color.LIGHT_GRAY);
            graphics2D.setFont(new Font(VGUI.FONT, Font.ITALIC, VGUI.TamanoFuente.CUERPO));
            graphics2D.drawString("Mano vacia", 88, 550);
        }
    }
}