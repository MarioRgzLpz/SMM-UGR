/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package practica3;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author mariorl
 */
public class Ventana extends javax.swing.JFrame {

    /**
     * Creates new form Ventana
     */
    public Ventana() {
        initComponents();
        this.setSize(700,700);
    }
    
    @Override
    public void paint (Graphics g){
        super.paint(g);
        g.drawLine(50, 50, 200, 200);
        g.fillOval(70, 70, 20, 20);
        g.fillOval(punto_inicial.x, punto_inicial.y, 1, 1);
        g.drawLine(punto_inicial2.x, punto_inicial2.y, punto_final.x, punto_final.y);
    }
    
    private int contador=0;
    private Point punto_inicial = new Point(-100,-100), punto_inicial2 = new Point(-100,-100), punto_final = new Point(-100,-100);

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        boton1 = new javax.swing.JButton();
        boton2 = new javax.swing.JButton();

        FormListener formListener = new FormListener();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseMotionListener(formListener);
        addMouseListener(formListener);
        getContentPane().setLayout(new java.awt.FlowLayout());

        boton1.setText("Boton");
        boton1.addMouseMotionListener(formListener);
        boton1.addMouseListener(formListener);
        getContentPane().add(boton1);

        boton2.setText("Boton2");
        boton2.addMouseListener(formListener);
        boton2.addActionListener(formListener);
        getContentPane().add(boton2);

        pack();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener, java.awt.event.MouseListener, java.awt.event.MouseMotionListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == boton2) {
                Ventana.this.boton2ActionPerformed(evt);
            }
        }

        public void mouseClicked(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == boton1) {
                Ventana.this.boton1MouseClicked(evt);
            }
            else if (evt.getSource() == Ventana.this) {
                Ventana.this.formMouseClicked(evt);
            }
            else if (evt.getSource() == boton2) {
                Ventana.this.boton2MouseClicked(evt);
            }
        }

        public void mouseEntered(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == boton1) {
                Ventana.this.boton1MouseEntered(evt);
            }
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == boton1) {
                Ventana.this.boton1MouseExited(evt);
            }
        }

        public void mousePressed(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == Ventana.this) {
                Ventana.this.formMousePressed(evt);
            }
        }

        public void mouseReleased(java.awt.event.MouseEvent evt) {
        }

        public void mouseDragged(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == boton1) {
                Ventana.this.boton1MouseDragged(evt);
            }
            else if (evt.getSource() == Ventana.this) {
                Ventana.this.formMouseDragged(evt);
            }
        }

        public void mouseMoved(java.awt.event.MouseEvent evt) {
        }
    }// </editor-fold>//GEN-END:initComponents

    private void boton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boton1MouseClicked

        if(contador%2==0){
            boton1.setBackground(Color.red);
        }else{
            boton1.setBackground(Color.white);
        }
        contador++;
    }//GEN-LAST:event_boton1MouseClicked

    private void boton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boton1MouseEntered
        boton1.setBackground(Color.blue);
    }//GEN-LAST:event_boton1MouseEntered

    private void boton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boton1MouseExited
        boton1.setBackground(Color.green);
    }//GEN-LAST:event_boton1MouseExited

    private void boton1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boton1MouseDragged
        boton1.setBackground(Color.yellow);
        punto_final = evt.getPoint();
        this.repaint();
    }//GEN-LAST:event_boton1MouseDragged

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        this.setTitle("LA VENTANA");
        punto_inicial = evt.getPoint();
        this.repaint();
    }//GEN-LAST:event_formMouseClicked

    private void boton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boton2MouseClicked
        boton2.setBackground(Color.blue);
    }//GEN-LAST:event_boton2MouseClicked

    private void boton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton2ActionPerformed
        boton2.setBackground(Color.green);
    }//GEN-LAST:event_boton2ActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        punto_inicial2 = evt.getPoint();
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        punto_final = evt.getPoint();
        this.repaint();
    }//GEN-LAST:event_formMouseDragged

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton boton1;
    private javax.swing.JButton boton2;
    // End of variables declaration//GEN-END:variables
}
