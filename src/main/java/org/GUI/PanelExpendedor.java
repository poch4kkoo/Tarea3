package org.GUI;

import org.logica.Deposito;
import org.logica.Expendedor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelExpendedor extends JPanel {

    private final Expendedor exp;
    private final PanelPrincipal principal;
    private final Estante[] estantes;

    public PanelExpendedor(Expendedor exp, PanelPrincipal principal) {
        this.exp = exp;
        this.principal = principal;
        this.setLayout(new BorderLayout());
        setOpaque(false);

        // Fijar dimensiones del panel
        Dimension tamano = new Dimension(600, 800);
        setPreferredSize(tamano);
        setMinimumSize(tamano);
        setMaximumSize(tamano);

        // Inicializamos el array de filas calculando su posición Y
        this.estantes = new Estante[3];

        // Fila 0: CocaCola y Fanta
        estantes[0] = new Estante(new Deposito[]{exp.getCoca(), exp.getFanta(), null, null}, 130);

        // Fila 1: Sprite
        estantes[1] = new Estante(new Deposito[]{exp.getSprite(), null, null, null}, 260);

        // Fila 2: Dulces
        estantes[2] = new Estante(new Deposito[]{exp.getSuper8(), exp.getSnickers(), null, null}, 390);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                // El listener estara ubicado donde esta la pantalla CIAN
                if (x >= 460 && x <= 570 && y >= 100 && y <= 190) {
                    abrirSelectorBebidas();
                }
            }
        });
    }


    private void abrirSelectorBebidas() {
        Window ventanaPadre = SwingUtilities.getWindowAncestor(this);
        JDialog dialogo = new JDialog(ventanaPadre, "Seleccione su producto", Dialog.ModalityType.APPLICATION_MODAL);

        PanelComprador panelComprador = this.principal.getPanelComprador();

        // Pasamos tanto el expendedor como el panel del comprador
        PanelControles panelControles = new PanelControles(this.exp, panelComprador);

        dialogo.add(panelControles);
        dialogo.pack();
        dialogo.setLocationRelativeTo(ventanaPadre);
        dialogo.setVisible(true);
    }




    public Image obtenerImagen(Deposito deposito) {
        if (deposito == exp.getCoca()) return Imagenes.get("cocacola");
        if (deposito == exp.getFanta()) return Imagenes.get("fanta");
        if (deposito == exp.getSprite()) return Imagenes.get("sprite");
        if (deposito == exp.getSuper8()) return Imagenes.get("super8");
        if (deposito == exp.getSnickers()) return Imagenes.get("snickers");
        return null;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        dibujarExpendedor(g2d);

        // Cada estante se dibuja a si mismo
        for (Estante estante : estantes) {
            estante.dibujar(g2d, this);
        }
    }


    private void dibujarExpendedor(Graphics2D g2d) {
        // EXPENDEDOR CON BORDES SUAVES
        g2d.setColor(VGUI.CustomColor.NEGRO);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight() - 20, VGUI.Borde.NORMAL, VGUI.Borde.NORMAL);

        // DETALLES
        g2d.setColor(VGUI.CustomColor.GRIS_OSCURO);
        g2d.fillRect(450, 20, 130, 600);

        // Compartimento de entrega de productos (abajo)
        g2d.setColor(VGUI.CustomColor.GRIS_AZUL);
        g2d.fillRoundRect(100 + 15, 495, 210, 85, VGUI.Borde.PEQUENO, VGUI.Borde.PEQUENO);

        // FONDO INTERIOR EXPENDEDOR
        g2d.fillRect(20, 20, 400, 400);

        // Pantalla del Expendedor
        g2d.setColor(VGUI.CustomColor.CIAN);
        g2d.fillRect(460, 100, 110, 90);

        // INTERIOR EXPENDEDOR
        g2d.setColor(VGUI.CustomColor.GRIS_AZUL);
        g2d.fillRect(20, 20, 400, 400);
    }
}

