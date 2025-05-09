/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package smpractica6;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.TextAttribute;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Map;

/**
 *
 * @author mariorl
 */
public class Lienzo extends javax.swing.JFrame {

    /**
     * Creates new form Lienzo
     */
    public Lienzo() {
        initComponents();
        this.setSize(1000, 800);
        this.repaint();
    }
    
    @Override
    public void paint(Graphics g){
     super.paint(g);
     Graphics2D g2d = (Graphics2D)g;
     this.pruebaAtributos(g2d);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pruebaAtributos(Graphics2D g2d) {
    //Trazo
    Stroke trazo;
    float patronDiscontinuidad[] = {15.0f, 15.0f, 1.0f, 15.0f};
    trazo = new BasicStroke(10.0f, BasicStroke.CAP_ROUND,BasicStroke.JOIN_MITER, 1.0f, patronDiscontinuidad, 0.0f);
    g2d.setStroke(trazo);
    g2d.draw(new Line2D.Float(40,40,160,160));
    //Relleno
    Paint relleno;
    relleno = new Color(255, 100, 0);
    g2d.draw(new Rectangle(170,40,120,120));
    g2d.fill(new Rectangle(300,40,120,120));
    Point pc1 = new Point(430,40), pc2 = new Point(550,160);
    relleno = new GradientPaint(pc1, Color.RED, pc2, Color.BLUE);
    g2d.setPaint(relleno);
    g2d.fill(new Rectangle(430,40,120,120));
    //Composición
    Composite composicion;
    composicion = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
    g2d.setComposite(composicion);
    g2d.fill(new Rectangle(190, 100, 200, 120));
    //Transformación
    AffineTransform transformacion;
    Rectangle r = new Rectangle(430, 190, 120, 120);
    g2d.draw(r); //Dibujamos rectángulo sin transformación
    AffineTransform atIni, at;
    atIni = g2d.getTransform(); //Transformación actual
    at = AffineTransform.getRotateInstance(Math.toRadians(45.0), r.getCenterX(), r.getCenterY());
    g2d.transform(at);
    g2d.fill(r); //Dibujamos rectángulo con transformación
    g2d.setTransform(atIni);
    at = AffineTransform.getScaleInstance(0.5,0.5);
    g2d.transform(at);
    g2d.draw(r);
    g2d.setTransform(atIni);
    at=AffineTransform.getTranslateInstance(r.getCenterX(), r.getCenterY());
    at.scale(0.5,0.5);
    at.translate(-r.getCenterX(),-r.getCenterY());
    g2d.transform(at);
    g2d.draw(r);
    g2d.setTransform(atIni);
    //Fuente
    Font fuente;
    fuente = new Font("Arial", Font.BOLD | Font.ITALIC, 45);
    g2d.setFont(fuente);
    g2d.drawString("Hola", 30, 220);
    g2d.setFont(fuente);
    Map atributosTexto = fuente.getAttributes();
    atributosTexto.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
    g2d.setFont( new Font(atributosTexto) );
    g2d.drawString("mundo", 30, 260);
    //Renderización
    RenderingHints render;
    g2d.draw(new Ellipse2D.Float(40,350,510,50)); //Elipse sin antialiasing
    render = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
     RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setRenderingHints(render);
    g2d.draw(new Ellipse2D.Float(40,450,510,50)); //Elipse con antialiasing
    g2d.drawString("Hola", 30, 170); 
    g2d.setRenderingHints(render);
    render.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
    //Recorte
    Shape clip;
    Shape clipArea;
    clipArea = new Ellipse2D.Float(100,100,500,500);
    g2d.setClip(clipArea);
    g2d.draw(clipArea);
    }

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
