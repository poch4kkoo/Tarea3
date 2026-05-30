package org.GUI;

import javax.swing.*;
import java.awt.*;
public class PanelExpendedor extends JPanel {

    public PanelExpendedor() {
        super();
        this.setLayout(new BorderLayout());
    }

    @Override
    public void paintComponent(Graphics g){
        //g define el contexto en el que se dibuja
        super.paintComponent(g);
        g.setColor(Color.green);
        g.fillRect(100,100,200,100);


    }

}

