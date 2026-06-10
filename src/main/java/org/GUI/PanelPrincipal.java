package org.GUI;

import org.logica.Comprador;
import org.logica.Expendedor;

import javax.swing.*;
import java.awt.*;

/**
 * Panel contenedor principal que actua como mediador y raiz de la interfaz grafica.
 * Se encarga de instanciar el modelo logico (Expendedor y Comprador) y de posicionar
 * de manera integrada el panel del expendedor y el panel del comprador.
 */
public class PanelPrincipal extends JPanel {

    private PanelComprador com;
    private PanelExpendedor exp;
    private Expendedor llenarExp;

    /**
     * Constructor del Panel Principal. Inicializa las clases logicas de la simulacion
     * con sus valores por defecto y distribuye espacialmente los subpaneles de la GUI.
     */
    public PanelPrincipal() {
        super();
        this.setSize(600, 800);
        this.setLayout(new GridBagLayout());

        llenarExp = new Expendedor(3);
        exp = new PanelExpendedor(llenarExp);
        com = new PanelComprador(new Comprador());


        this.setBackground(Color.white);

        //agregamos el componente al panel
        this.add(exp);
        this.add(com);
    }
    /**
     * Proporciona acceso al subpanel encargado de la gestion grafica del comprador.
     * * @return La instancia activa de PanelComprador.
     */

    public PanelComprador getPanelComprador() {
        return this.com;
    }
}
