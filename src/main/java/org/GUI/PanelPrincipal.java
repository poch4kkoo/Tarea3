package org.GUI;

import javax.swing.*;
import java.awt.*;

public class PanelPrincipal extends JPanel {

    //private PanelComprador com;
    private PanelExpendedor exp;

    public PanelPrincipal() {
        super();
        this.setSize(600, 800);
        this.setLayout(new GridBagLayout());
        exp = new PanelExpendedor();
        //     com = new PanelComprador();
        this.setBackground(Color.white);

        // Agregamos el componente al panel de forma real
        this.add(exp);
    }
}
