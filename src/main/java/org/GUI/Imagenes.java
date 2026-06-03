package org.GUI;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Imagenes {
    private static final Map<String, Image> imagenes = new HashMap<>();

    static {
        try {
            imagenes.put("cocacola", ImageIO.read(Imagenes.class.getResource("/Productos/cocacola.png")));
            imagenes.put("fanta", ImageIO.read(Imagenes.class.getResource("/Productos/fanta.png")));
            imagenes.put("sprite", ImageIO.read(Imagenes.class.getResource("/Productos/sprite.png")));
            imagenes.put("super8", ImageIO.read(Imagenes.class.getResource("/Productos/super8.png")));
            imagenes.put("snickers", ImageIO.read(Imagenes.class.getResource("/Productos/snickers.png")));
            imagenes.put("resorte", ImageIO.read(Imagenes.class.getResource("/Extras/resorte.png")));
            System.out.println("Imágenes cargadas exitosamente.");
        } catch (IOException e) {
            System.err.println("ERROR: No se pudieron cargar las imágenes.");
            e.printStackTrace();
        }
    }

    public static Image get(String clave) {
        return imagenes.get(clave);
    }
}