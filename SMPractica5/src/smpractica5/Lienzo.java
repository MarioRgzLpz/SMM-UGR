/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package smpractica5;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

/**
 *
 * @author mariorl
 */
public class Lienzo extends javax.swing.JPanel {

    private Point p_inicial = new Point(-100,-100);
    private ModoPintura modoPintura = ModoPintura.LINEA;
    private Shape forma = new MiLinea();
    private List<Shape> vShape = new ArrayList<>();
    private Color color = Color.black;
    private Boolean relleno = false;
    private Boolean mover = false;
    private Boolean transparencia = false;
    private Boolean alisar = false;
    private BasicStroke trazo = new BasicStroke();
    private RenderingHints render = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    private Composite comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
    
    class MiLinea extends Line2D.Float {
        
        private Point2D deltaClick = null;
        
        public MiLinea() {
            super();
        }
        public MiLinea(Point2D p1, Point2D p2) {
            super(p1,p2);
        }
        public boolean isNear(Point2D p){
            if (this.getP1().equals(this.getP2())) {
                return this.getP1().distance(p) <= 5.0;
            }
            return this.ptLineDist(p) <= 5.0;
        }
        @Override
        public boolean contains(Point2D p) {
            return isNear(p);
        }
        public void setDeltaClick(Point2D clickPoint) {
            this.deltaClick = new Point2D.Double(clickPoint.getX() - this.getX1(), clickPoint.getY() - this.getY1());
        }
        public void setLocation(Point2D posPoint2D) {
            if (deltaClick != null) {
                double newX1 = posPoint2D.getX() - deltaClick.getX();
                double newY1 = posPoint2D.getY() - deltaClick.getY();
                double newX2 = newX1 + (this.getX2() - this.getX1());
                double newY2 = newY1 + (this.getY2() - this.getY1());
                this.setLine(newX1, newY1, newX2, newY2);
            }
        }
    }
    
    class MiRectangulo extends Rectangle2D.Float {

        private Point2D deltaClick = null;

        public MiRectangulo() {
            super();
        }

        @Override
        public void setFrame(double x, double y, double width, double height) {
            super.setFrame(x, y, width, height);
        }

        public void setDeltaClick(Point2D clickPoint) {
            deltaClick = new Point2D.Double(clickPoint.getX() - this.getX(), clickPoint.getY() - this.getY());
        }

        public void setLocation(Point2D posPoint2D) {
            if (deltaClick != null) {
                double newX = posPoint2D.getX() - deltaClick.getX();
                double newY = posPoint2D.getY() - deltaClick.getY();
                this.setFrame(newX, newY, this.getWidth(), this.getHeight());
            }
        }
    }
    
    class MiElipse extends Ellipse2D.Float {

        private Point2D deltaClick = null;

        public MiElipse() {
            super();
        }

        @Override
        public void setFrame(double x, double y, double width, double height) {
            super.setFrame(x, y, width, height);
        }

        public void setDeltaClick(Point2D clickPoint) {
            deltaClick = new Point2D.Double(clickPoint.getX() - this.getX(), clickPoint.getY() - this.getY());
        }

        public void setLocation(Point2D posPoint2D) {
            if (deltaClick != null) {
                double newX = posPoint2D.getX() - deltaClick.getX();
                double newY = posPoint2D.getY() - deltaClick.getY();
                this.setFrame(newX, newY, this.getWidth(), this.getHeight());
            }
        }
    }

    /**
     *
     */
    public enum ModoPintura {LINEA, RECTANGULO, ELIPSE}
    /**
     * Creates new form Lienzo
     */
    public Lienzo(){
        initComponents();
    }
    @Override
    public void paint (Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        if (alisar) {
            g2d.setRenderingHints(render);
            System.out.println("hola");
        }
        g2d.setPaint(color); // Color
        g2d.setStroke(trazo); // Trazo

        if (transparencia) {
            g2d.setComposite(comp);
            System.out.println("hola");
        } else {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f)); // Sin transparencia
        }
        for(Shape shape:vShape){
            if(relleno){
                g2d.fill(shape);
                System.out.println("hola");
            }
            g2d.draw(shape);
        }
    }

    /**
     *
     * @param modoPintura
     */
    public void setModoPintura(ModoPintura modoPintura){
        this.modoPintura = modoPintura;
    }
    
    /**
     *
     * @return
     */
    public ModoPintura getModoPintura(){
        return modoPintura;
    }
    
    /**
     *
     * @param color
     */
    public void setColor(Color color){
        this.color = color;
        this.repaint();
    }
    
    /**
     *
     * @return
     */
    public Color getColor(){
        return color;
    }
    
    /**
     *
     * @param relleno
     */
    public void setRelleno(Boolean relleno){
        this.relleno = relleno;
        this.repaint();
    }
 
    /**
     *
     * @return
     */
    public Boolean isRelleno(){
        return relleno;
    }
    
    public Boolean getMover() {
        return mover;
    }

    public void setMover(Boolean mover) {
        this.mover = mover;
    }
    
    public Boolean getTransparencia() {
        return transparencia;
    }

    public void setTransparencia(Boolean transparencia) {
        this.transparencia = transparencia;
        this.repaint();
    }

    public Boolean getAlisar() {
        return alisar;
    }

    public void setAlisar(Boolean alisar) {
        this.alisar = alisar;
        this.repaint();
    }

    public BasicStroke getTrazo() {
        return trazo;
    }

    public void setTrazo(int grosor) {
        this.trazo = new BasicStroke(grosor);
        this.repaint();
    }
    
    public void limpiar() {
        vShape.clear();
        this.repaint();
    }
    
    private Shape figuraSeleccionada(Point2D p){
        for (int i = vShape.size() - 1; i >= 0; i--) { 
            Shape s = vShape.get(i);
            if (s.contains(p)) {
                return s; 
            }
        }
        return null;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        if(mover){
            forma = figuraSeleccionada(evt.getPoint());
            if(forma instanceof MiLinea)
                ((MiLinea)forma).setDeltaClick(evt.getPoint());
            else if(forma instanceof MiRectangulo)
                ((MiRectangulo)forma).setDeltaClick(evt.getPoint());
            else if(forma instanceof MiElipse)
                ((MiElipse)forma).setDeltaClick(evt.getPoint());
        }
        else{
            p_inicial = new Point(evt.getPoint());
            switch(modoPintura){
                case LINEA:
                    forma = new MiLinea(evt.getPoint(),evt.getPoint());
                    break;
                case RECTANGULO:
                    forma = new MiRectangulo();
                    break;
                case ELIPSE:
                    forma = new MiElipse();
                    break;
            }
            vShape.add(forma);
        }
        
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        if(mover){
            if(forma != null && forma instanceof MiLinea){
                ((MiLinea)forma).setLocation(evt.getPoint());
            }
            else if (forma != null && forma instanceof MiRectangulo) {
                ((MiRectangulo)forma).setLocation(evt.getPoint());
            }
            else if (forma != null && forma instanceof MiElipse) {
                ((MiElipse)forma).setLocation(evt.getPoint());
            }
            this.repaint();
        }
        else{
            if(forma instanceof Line2D)
                ((Line2D)forma).setLine(p_inicial, evt.getPoint());
            else if(forma instanceof RectangularShape)
                ((RectangularShape)forma).setFrameFromDiagonal(p_inicial, evt.getPoint());
            this.repaint();
        }
    }//GEN-LAST:event_formMouseDragged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
