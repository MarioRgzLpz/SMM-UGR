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
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * Clase abstracta que representa una figura gráfica genérica.
 * 
 * Proporciona propiedades y métodos comunes a todas las figuras, como
 * color, transparencia, alisado, trazo, y relleno. Además, define métodos
 * abstractos que deben implementarse en las subclases para definir
 * comportamientos específicos como la ubicación, contención, y dibujo.
 * 
 * @author mariorl
 */
public abstract class JFigura {
    private Color color = Color.black; // Color de la figura
    private Boolean relleno = false;  // Indica si la figura está rellena
    private Boolean seleccionada = false; // Indica si la figura está seleccionada
    private Boolean transparencia = false; // Indica si la figura tiene transparencia
    private Boolean alisar = false; // Indica si la figura debe alisarse
    private BasicStroke trazo = new BasicStroke(); // Define el trazo de la figura
    private RenderingHints render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    private Composite comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);

    /**
     * Constructor por defecto. Inicializa una figura con valores predeterminados.
     */
    public JFigura() {}

    /**
     * Obtiene la coordenada X de la figura.
     * 
     * @return la coordenada X
     */
    public abstract double getX();

    /**
     * Obtiene la coordenada Y de la figura.
     * 
     * @return la coordenada Y
     */
    public abstract double getY();

    /**
     * Establece la posición de la figura en función de un punto
     * 
     * @param posPoint2D el nuevo punto de posición
     */
    public abstract void setLocation(Point2D posPoint2D);

    /**
     * Verifica si un punto dado está contenido dentro de la figura.
     * 
     * @param p el punto a verificar
     * @return true si el punto está contenido, false en caso contrario
     */
    public abstract boolean contains(Point2D p);

    /**
     * Dibuja la figura en un contexto gráfico.
     * 
     * Este método debe ser implementado por las subclases para definir la
     * apariencia de la figura específica.
     * 
     * @param g2d el contexto gráfico donde se dibuja la figura
     */
    protected abstract void drawShape(Graphics2D g2d);

    /**
     * Obtiene el color actual de la figura.
     * 
     * @return el color de la figura
     */
    public Color getColor() {
        return color;
    }

    /**
     * Establece el color de la figura.
     * 
     * @param color el color deseado
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Verifica si la figura está rellena.
     * 
     * @return true si está rellena, false en caso contrario
     */
    public Boolean getRelleno() {
        return relleno;
    }

    /**
     * Activa o desactiva el relleno de la figura.
     * 
     * @param relleno true para activar el relleno, false para desactivarlo
     */
    public void setRelleno(Boolean relleno) {
        this.relleno = relleno;
    }

    /**
     * Verifica si la figura está seleccionada.
     * 
     * @return true si está seleccionada, false en caso contrario
     */
    public Boolean getSeleccionada() {
        return seleccionada;
    }

    /**
     * Establece el estado de selección de la figura.
     * 
     * @param seleccionar true para seleccionar la figura, false para deseleccionarla
     */
    public void setSeleccionada(Boolean seleccionada) {
        this.seleccionada = seleccionada;
    }

    /**
     * Verifica si la transparencia está activada para la figura.
     * 
     * @return true si la transparencia está activada, false en caso contrario
     */
    public Boolean getTransparencia() {
        return transparencia;
    }

    /**
     * Activa o desactiva la transparencia de la figura.
     * 
     * @param transparencia true para activar la transparencia, false para desactivarla
     */
    public void setTransparencia(Boolean transparencia) {
        this.transparencia = transparencia;
        comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparencia ? 0.5f : 1.0f);
        this.setComp(comp);
    }

    /**
     * Verifica si el alisado está activado para la figura.
     * 
     * @return true si el alisado está activado, false en caso contrario
     */
    public Boolean getAlisar() {
        return alisar;
    }

    /**
     * Activa o desactiva el alisado de la figura.
     * 
     * @param alisar true para activar el alisado, false para desactivarlo
     */
    public void setAlisar(Boolean alisar) {
        this.alisar = alisar;
        render = new RenderingHints(RenderingHints.KEY_ANTIALIASING, this.getAlisar() ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
        this.setRender(render);
    }

    /**
     * Obtiene el trazo actual de la figura.
     * 
     * @return el trazo como un objeto BasicStroke
     */
    public BasicStroke getTrazo() {
        return trazo;
    }

    /**
     * Establece el trazo de la figura.
     * 
     * @param trazo el nuevo trazo
     */
    public void setTrazo(BasicStroke trazo) {
        this.trazo = trazo;
    }

    /**
     * Obtiene las configuraciones de renderizado actuales de la figura.
     * 
     * @return las configuraciones de renderizado
     */
    public RenderingHints getRender() {
        return render;
    }

    /**
     * Establece las configuraciones de renderizado de la figura.
     * 
     * @param render las nuevas configuraciones de renderizado
     */
    public void setRender(RenderingHints render) {
        this.render = render;
    }

    /**
     * Obtiene el compuesto de transparencia de la figura.
     * 
     * @return el compuesto de transparencia
     */
    public Composite getComp() {
        return comp;
    }

    /**
     * Establece el compuesto de transparencia de la figura.
     * 
     * @param comp el nuevo compuesto
     */
    public void setComp(Composite comp) {
        this.comp = comp;
    }

    /**
     * Dibuja la figura aplicando sus configuraciones de color, trazo,
     * renderizado y transparencia.
     * 
     * @param g2d el contexto gráfico donde se dibuja la figura
     */
    public void draw(Graphics2D g2d) {
        g2d.setColor(this.getColor());
        g2d.setStroke(this.getTrazo());
        g2d.setRenderingHints(this.getRender());
        g2d.setComposite(this.getComp());
        drawShape(g2d);
    }
    
    protected void drawSeleccionada(Graphics2D g2d, Rectangle bounds){
        if (seleccionada){
            g2d.setColor(Color.RED);
            float[] patronLinea = {5.0f, 5.0f};
            g2d.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 1.0f, patronLinea, 0.0f));
            g2d.draw(bounds);            
            int size = 20;

            int[][] corners = {
                {bounds.x, bounds.y},
                {bounds.x + bounds.width, bounds.y},
                {bounds.x, bounds.y + bounds.height},
                {bounds.x + bounds.width, bounds.y + bounds.height}
            };

            for (int[] corner : corners) {
                g2d.drawRect(corner[0] - size / 2, corner[1] - size / 2, size, size);
            }
        }
    }
    
    
}
