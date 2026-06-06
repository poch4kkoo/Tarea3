package org.GUI;

import org.logica.Comprador;
import org.logica.Expendedor;

import javax.swing.*;
import java.awt.*;

public class PanelPrincipal extends JPanel {

    private PanelComprador com;
    private PanelExpendedor exp;
    private Expendedor llenarExp;

    public PanelPrincipal() {
        super();
        this.setSize(600, 800);
        this.setLayout(new GridBagLayout());

        llenarExp = new Expendedor(9);
        exp = new PanelExpendedor(llenarExp, this);
        com = new PanelComprador(new Comprador(), llenarExp);


        this.setBackground(Color.white);

        // Agregamos el componente al panel de forma real
        this.add(exp);
        this.add(com);
    }

    public PanelComprador getPanelComprador() {
        return this.com;
    }
}
