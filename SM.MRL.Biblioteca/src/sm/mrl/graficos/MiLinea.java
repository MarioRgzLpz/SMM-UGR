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
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * Clase que representa una línea en un espacio bidimensional. Extiende la clase 
 * {@link JFigura} e implementa las funcionalidades específicas para manejar 
 * y dibujar líneas.
 * 
 * Permite crear líneas, verificar si un punto está cerca de la línea, mover 
 * la línea, y establecer su posición.
 * 
 * @author mariorl
 */
public class MiLinea extends JFigura {
    private Line2D.Float linea = new Line2D.Float();

    /**
     * Constructor por defecto. Crea una línea vacía con coordenadas iniciales (0,0).
     */
    public MiLinea() {
        this.linea = new Line2D.Float();
    }

    /**
     * Constructor que permite inicializar una línea entre dos puntos dados.
     * 
     * @param p1 el punto inicial de la línea
     * @param p2 el punto final de la línea
     */
    public MiLinea(Point2D p1, Point2D p2) {
        this.linea = new Line2D.Float(p1, p2);
    }

    /**
     * Obtiene la coordenada X del punto inicial de la línea.
     * 
     * @return la coordenada X del punto inicial
     */
    @Override
    public double getX() {
        return linea.getX1();
    }

    /**
     * Obtiene la coordenada Y del punto inicial de la línea.
     * 
     * @return la coordenada Y del punto inicial
     */
    @Override
    public double getY() {
        return linea.getY1();
    }

    /**
     * Establece la ubicación de la línea en el lienzo.
     * 
     * @param posPoint2D el nuevo punto de referencia
     */
    @Override
    public void setLocation(Point2D posPoint2D) {
        double newX2 = posPoint2D.getX() + (linea.getX2() - linea.getX1());
        double newY2 = posPoint2D.getY() + (linea.getY2() - linea.getY1());
        linea.setLine(posPoint2D.getX(), posPoint2D.getY(), newX2, newY2);
    }  

    /**
     * Verifica si un punto dado está cerca de la línea.
     * 
     * @param p el punto a verificar
     * @return true si el punto está cerca de la línea, false en caso contrario
     */
    @Override
    public boolean contains(Point2D p) {
        return isNear(p);
    }
    
    /**
     * Verifica si un punto está a una distancia aceptable de la línea o 
     * coincide con uno de sus extremos.
     * 
     * @param p el punto a comprobar
     * @return true si el punto está cerca o coincide con la línea
     */
    private boolean isNear(Point2D p) {
        if (linea.getP1().equals(linea.getP2())) {
            return linea.getP1().distance(p) <= 5.0;
        }
        return linea.ptLineDist(p) <= 5.0;
    }

    /**
     * Dibuja la línea en el contexto gráfico proporcionado.
     * 
     * @param g2d el contexto gráfico utilizado para dibujar la línea
     */
    @Override
    protected void drawShape(Graphics2D g2d) {
        g2d.draw(linea);
        this.drawSeleccionada(g2d, linea.getBounds());
    }

    /**
     * Establece los puntos inicial y final de la línea.
     * 
     * @param p_inicial el punto inicial de la línea
     * @param point el punto final de la línea
     */
    public void setLine(Point p_inicial, Point point) {
        this.linea.setLine(p_inicial, point);
    }
}
