/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sm.mrl.graficos;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;

/**
 * Clase que representa una curva cuadrática.
 * 
 * La clase permite manejar y dibujar curvas cuadráticas definidas por dos puntos finales y un punto de control.
 * 
 * @author mariorl
 */
public class MiCurva extends JFigura {
    private QuadCurve2D curva;

    /**
     * Constructor por defecto.
     * 
     * Crea una curva cuadrática vacía con valores iniciales en (0,0).
     */
    public MiCurva() {
        this.curva = new QuadCurve2D.Float();
    }

    /**
     * Constructor que inicializa la curva con puntos específicos.
     * 
     * @param p1 punto inicial de la curva
     * @param ctrl punto de control de la curva
     * @param p2 punto final de la curva
     */
    public MiCurva(Point2D p1, Point2D ctrl, Point2D p2) {
        this.curva = new QuadCurve2D.Float(
            (float) p1.getX(), (float) p1.getY(),
            (float) ctrl.getX(), (float) ctrl.getY(),
            (float) p2.getX(), (float) p2.getY()
        );
    }

    /**
     * Dibuja la curva en el contexto gráfico especificado.
     * 
     * @param g2d el contexto gráfico en el que se dibujará la curva
     */
    @Override
    protected void drawShape(Graphics2D g2d) {
        g2d.draw(curva);
        if(this.getRelleno())
            g2d.fill(curva);
        this.drawSeleccionada(g2d, curva.getBounds());
    }

    /**
     * Obtiene la coordenada X del punto superior izquierdo del área delimitadora de la curva.
     * 
     * @return la coordenada X de la curva
     */
    @Override
    public double getX() {
        return curva.getBounds2D().getX();
    }

    /**
     * Obtiene la coordenada Y del punto superior izquierdo del área delimitadora de la curva.
     * 
     * @return la coordenada Y de la curva
     */
    @Override
    public double getY() {
        return curva.getBounds2D().getY();
    }

    /**
     * Cambia la ubicación de la curva mediante una translación relativa.
     * 
     * @param posPoint2D vector de translación en forma de {@code Point2D}
     */
    @Override
    public void setLocation(Point2D posPoint2D) {
        double deltaX = posPoint2D.getX() - curva.getX1();
        double deltaY = posPoint2D.getY() - curva.getY1();
        curva.setCurve(
            curva.getX1() + deltaX, curva.getY1() + deltaY,
            curva.getCtrlX() + deltaX, curva.getCtrlY() + deltaY,
            curva.getX2() + deltaX, curva.getY2() + deltaY
        );
    }

    /**
     * Verifica si un punto está dentro del área delimitadora de la curva.
     * 
     * @param p el punto a verificar
     * @return {@code true} si el punto está dentro del área delimitadora; de lo contrario, {@code false}
     */
    @Override
    public boolean contains(Point2D p) {
        return curva.getBounds2D().contains(p);
    }

    /**
     * Cambia la posición del punto final de la curva.
     * 
     * @param p2 nuevo punto final de la curva
     */
    public void setEnd(Point2D p2) {
        curva.setCurve(curva.getX1(), curva.getY1(), curva.getCtrlX(), curva.getCtrlY(), p2.getX(), p2.getY());
    }

    /**
     * Cambia la posición del punto de control de la curva.
     * 
     * @param ctrl nuevo punto de control de la curva
     */
    public void setControl(Point2D ctrl) {
        curva.setCurve(curva.getX1(), curva.getY1(), ctrl.getX(), ctrl.getY(), curva.getX2(), curva.getY2());
    }
}
