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
    private JButton botonConsumir;
    public static Moneda monedaSeleccionada = null;

    public PanelComprador(Comprador comprador) {
        super();

        com = comprador;

        Dimension tamano = new Dimension(450, 780);
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

                    String msgConsumo = com.getProductoListo().consumir();
                    com.recogerProducto(null);
                    JOptionPane.showMessageDialog(null, "¡Consumiste tu " + msgConsumo + "!");
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

                    for (int i = 0; i < com.getMonedero().tamano(); i++) {
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

        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {

            @Override
            public void mouseMoved(java.awt.event.MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();

                boolean sobreMoneda = false;
                int totalMonedas = com.getMonedero().tamano();

                int diametro = 55;
                int margenX = 40;
                int margenY = 50;
                int espacio = 20;
                int columnas = 5;

                for (int i = 0; i < totalMonedas; i++) {
                    int col = i % columnas;
                    int fila = i / columnas;

                    int xFinal = margenX + col * (diametro + espacio);
                    int yFinal = margenY + fila * (diametro + espacio);

                    int centroX = xFinal + diametro /2;
                    int centroY = yFinal + diametro /2;
                    double distancia = Math.sqrt(Math.pow(mouseX - centroX, 2) + Math.pow(mouseY - centroY, 2));

                    if (distancia <= 40) {
                        Moneda m = com.getMonedero().getElementoEn(i);
                        if (m != null) {
                            // Asignamos el texto que flotará al lado del cursor
                            setToolTipText("N° serie: " + m.getSerie());
                            sobreMoneda = true;
                        }
                        break;
                    }
                }

                if (!sobreMoneda) {
                    setToolTipText(null);
                }
            }
        });
    }

    private Image obtenerImagenProducto(Producto p) {
        if (p == null) {
            return null;
        }

        String nombreTipo = p.getClass().getSimpleName().toLowerCase();

        if (nombreTipo.contains("cocacola"))  return Imagenes.get("cocacola");
        if (nombreTipo.contains("fanta"))  return Imagenes.get("fanta");
        if (nombreTipo.contains("sprite"))  return Imagenes.get("sprite");
        if (nombreTipo.contains("snickers"))  return Imagenes.get("snickers");
        if (nombreTipo.contains("super8"))  return Imagenes.get("super8");

        return null;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.setColor(VGUI.CustomColor.GRIS_OSCURO);
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

            int totalMonedas = com.getMonedero().tamano();

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

            Image imgProducto = obtenerImagenProducto(enMano);

            if(imgProducto != null) {
                graphics2D.drawImage(imgProducto, 85, 495,70,90,this);
            }

        } else {
            graphics2D.setColor(Color.LIGHT_GRAY);
            graphics2D.setFont(new Font(VGUI.FONT, Font.ITALIC, VGUI.TamanoFuente.CUERPO));
            graphics2D.drawString("Mano vacia", 88, 550);
        }
    }

    public Comprador getCom() {
        return com;
    }

}
