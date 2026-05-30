package org.GUI;

import java.awt.*;

public interface CustomGUI {
    int ALTO = 800;
    int LARGO = 600;
    String FONT = "Calibri";

    public class CustomColor
    {
        public static final Color AZUL_MARCA = new Color(35, 116, 171);
        public static final Color NEGRO = new Color(34, 38, 41);
        public static final Color VERDE = new Color(134, 194, 50);
        public static final Color VERDE_EXP = new Color(104, 154, 40);
        public static final Color ROSA = new Color(255, 132, 132);
        public static final Color GRIS= new Color(153, 153, 153);
        public static final Color BLANCO = Color.white;
        public static final Color BLANCO_OSCURO = new Color(196, 196, 196);
    }

    public class TamanoFuente
    {
        public static final int ENCABEZADO_1 = 34;
        public static final int ENCABEZADO_2 = 20;
        public static final int CUERPO = 15;
        public static final int OCULTO = 12;
    }
}
