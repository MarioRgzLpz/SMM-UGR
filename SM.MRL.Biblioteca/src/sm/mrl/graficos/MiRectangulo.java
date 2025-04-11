/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sm.mrl.graficos;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * Clase que representa un rectángulo.
 * 
 * La clase extiende {@code JRectangularShape} y proporciona la funcionalidad para 
 * dibujar rectángulos en un contexto gráfico. Los rectángulos pueden ser dibujados 
 * con o sin relleno.
 * 
 * @author mariorl
 */
public class MiRectangulo extends JRectangularShape {

    /**
     * Constructor por defecto.
     * 
     * Crea un rectángulo con posición (0,0) y dimensiones (0,0).
     */
    public MiRectangulo() {
        super(new Rectangle2D.Float());
    }

    /**
     * Constructor que inicializa el rectángulo con las dimensiones especificadas.
     * 
     * @param x coordenada X del vértice superior izquierdo
     * @param y coordenada Y del vértice superior izquierdo
     * @param width ancho del rectángulo
     * @param height alto del rectángulo
     */
    public MiRectangulo(float x, float y, float width, float height) {
        super(new Rectangle2D.Float(x, y, width, height));
    }

    /**
     * Dibuja el rectángulo en el contexto gráfico especificado.
     * 
     * Este método dibuja el contorno del rectángulo y, si está habilitado el relleno,
     * también lo rellena con el color configurado.
     * 
     * @param g2d el contexto gráfico en el que se dibujará el rectángulo
     */
    @Override
    protected void drawShape(Graphics2D g2d) {
        Rectangle2D rectangulo = (Rectangle2D) this.getFormaRectangular();
        g2d.draw(rectangulo); 
        if (this.getRelleno()) { 
            g2d.fill(rectangulo);
        }
        this.drawSeleccionada(g2d, rectangulo.getBounds());
    }
}
