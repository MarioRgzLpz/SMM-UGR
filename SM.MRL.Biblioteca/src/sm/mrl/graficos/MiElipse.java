/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sm.mrl.graficos;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;

/**
 * Clase que representa una elipse como una figura rectangular. Extiende la clase
 * {@link JRectangularShape}, proporcionando funcionalidad específica para el dibujo
 * de elipses con soporte para relleno y otras propiedades gráficas.
 * 
 * @author mariorl
 */
public class MiElipse extends JRectangularShape {

    /**
     * Constructor por defecto. Crea una elipse utilizando un objeto 
     * {@link Ellipse2D.Float} como forma rectangular inicial.
     */
    public MiElipse() {
        super(new Ellipse2D.Float());
    }

    /**
     * Constructor que permite especificar las coordenadas y dimensiones de la elipse.
     * 
     * @param x la coordenada X de la esquina superior izquierda del marco de la elipse
     * @param y la coordenada Y de la esquina superior izquierda del marco de la elipse
     * @param width el ancho del marco que delimita la elipse
     * @param height la altura del marco que delimita la elipse
     */
    public MiElipse(double x, double y, double width, double height) {
        super(new Ellipse2D.Double(x, y, width, height));
    }

    /**
     * Sobrescribe el método abstracto {@code drawShape} de {@link JRectangularShape}.
     * Dibuja la elipse utilizando el contexto gráfico proporcionado. Si el estado 
     * de relleno está habilitado, rellena la elipse además de dibujar su contorno.
     * 
     * @param g2d el contexto gráfico {@link Graphics2D} utilizado para dibujar la elipse
     */
    @Override
    protected void drawShape(Graphics2D g2d) {
        Ellipse2D elipse = (Ellipse2D) this.getFormaRectangular();
        g2d.draw(elipse);
        if (this.getRelleno()) {
            g2d.fill(elipse);
        }
        this.drawSeleccionada(g2d, elipse.getBounds());
    }
}
