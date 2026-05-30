package org.GUI;

import javax.swing.*;
import java.awt.*;

public class PanelPrincipal extends JPanel {

    //private PanelComprador com;
    private PanelExpendedor exp;

    public PanelPrincipal() {
        super();
        this.setSize(600,400);
        exp = new PanelExpendedor();
   //     com = new PanelComprador();
        this.setBackground(Color.white);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    //    com.paintComponent(g);
        exp.paintComponent(g);
    }

}
