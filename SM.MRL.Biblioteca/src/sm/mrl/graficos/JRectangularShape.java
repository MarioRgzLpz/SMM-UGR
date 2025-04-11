/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sm.mrl.graficos;

import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * Clase abstracta que representa una figura rectangular.
 * 
 * Esta clase sirve como base para figuras geométricas rectangulares,
 * proporcionando funcionalidades comunes como la manipulación de la posición,
 * el tamaño y la contención de puntos. Extiende la funcionalidad de la clase 
 * abstracta JFigura.
 * 
 * @author mariorl
 */
public abstract class JRectangularShape extends JFigura {

    /**
     * Forma rectangular subyacente que define la geometría de la figura.
     */
    private RectangularShape formarectangular;

    /**
     * Constructor que inicializa la forma rectangular.
     * 
     * @param forma la instancia de {@code RectangularShape} que define la geometría de la figura
     */
    public JRectangularShape(RectangularShape forma) {
        this.formarectangular = forma;
    }

    /**
     * Obtiene la coordenada X de la figura.
     * 
     * @return la coordenada X del vértice superior izquierdo de la figura
     */
    @Override
    public double getX() {
        return formarectangular.getX();
    }

    /**
     * Obtiene la coordenada Y de la figura.
     * 
     * @return la coordenada Y del vértice superior izquierdo de la figura
     */
    @Override
    public double getY() {
        return formarectangular.getY();
    }

    /**
     * Establece la posición y el tamaño de la figura.
     * 
     * @param x coordenada X del vértice superior izquierdo
     * @param y coordenada Y del vértice superior izquierdo
     * @param width ancho de la figura
     * @param height alto de la figura
     */
    public void setFrame(double x, double y, double width, double height) {
        formarectangular.setFrame(x, y, width, height);
    }

    /**
     * Establece una nueva ubicación para la figura basada en un punto.
     * 
     * @param posPoint2D punto objetivo donde se moverá la figura
     */
    @Override
    public void setLocation(Point2D posPoint2D) {
        this.setFrame(posPoint2D.getX(), posPoint2D.getY(), formarectangular.getWidth(), formarectangular.getHeight());
    }

    /**
     * Verifica si un punto está contenido dentro de la figura.
     * 
     * @param p punto a verificar
     * @return {@code true} si el punto está contenido dentro de la figura, {@code false} en caso contrario
     */
    @Override
    public boolean contains(Point2D p) {
        return formarectangular.contains(p);
    }

    /**
     * Establece la figura rectangular desde las coordenadas de dos puntos diagonales.
     * 
     * @param p1 primer punto diagonal
     * @param p2 segundo punto diagonal
     */
    public void setFrameFromDiagonal(Point2D p1, Point2D p2) {
        formarectangular.setFrameFromDiagonal(p1, p2);
    }

    /**
     * Obtiene la forma rectangular subyacente.
     * 
     * @return la instancia de {@code RectangularShape} que representa la geometría de la figura
     */
    public RectangularShape getFormaRectangular() {
        return formarectangular;
    }
}
