/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package smpractica7;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import sm.mrl.iu.Lienzo2D;

/**
 *
 * @author mariorl
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();
        this.setTitle("Ñaint");
        barraEstado.setText("Modo de pintura: " + lienzo2D.getModoPintura().toString());
        lienzo2D.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        dialogoColor.addActionListener(new ManejadorColor());
        modoLinea.addActionListener(new ManejadorModoPintura(Lienzo2D.ModoPintura.LINEA));
        modoRectangulo.addActionListener(new ManejadorModoPintura(Lienzo2D.ModoPintura.RECTANGULO));
        modoElipse.addActionListener(new ManejadorModoPintura(Lienzo2D.ModoPintura.ELIPSE));
        modoCurva.addActionListener(new ManejadorModoPintura(Lienzo2D.ModoPintura.CURVA));
        botonMenuNuevo.addActionListener(new ManejadorMenu());
        botonMenuAbrir.addActionListener(new ManejadorMenu());
        botonMenuGuardar.addActionListener(new ManejadorMenu());
        relleno.addActionListener(new ManejadorPropiedadesFiguras());
        seleccionar.addActionListener(new ManejadorPropiedadesFiguras());
        transparencia.addActionListener(new ManejadorPropiedadesFiguras());
        alisar.addActionListener(new ManejadorPropiedadesFiguras());
        dialogoColor.setToolTipText("Color");
        modoLinea.setToolTipText("Linea");
        modoRectangulo.setToolTipText("Rectangulo");
        modoElipse.setToolTipText("Elipse");
        modoCurva.setToolTipText("Curva");
        botonMenuNuevo.setToolTipText("Nuevo");
        botonMenuAbrir.setToolTipText("Abrir");
        botonMenuGuardar.setToolTipText("Guardar");
        relleno.setToolTipText("Relleno");
        seleccionar.setToolTipText("Seleccionar");
        transparencia.setToolTipText("Transparencia");
        alisar.setToolTipText("Alisar");
        grosor.setToolTipText("Grosor trazo");
        
    }
    
    public class ManejadorColor implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == dialogoColor){
                Color colorSeleccionado = JColorChooser.showDialog(VentanaPrincipal.this, "Eliga el color del trazo", Color.BLACK);
                if (colorSeleccionado != null){
                    dialogoColor.setBackground(colorSeleccionado);
                    lienzo2D.setColor(colorSeleccionado);
                }
            }
        }
    }
    public class ManejadorModoPintura implements ActionListener {
        private final Lienzo2D.ModoPintura modoPintura;
        
        public ManejadorModoPintura(Lienzo2D.ModoPintura modoPintura) {
            this.modoPintura = modoPintura;
        }
        @Override
        public void actionPerformed(ActionEvent ae) {
            lienzo2D.setModoPintura(modoPintura);
            lienzo2D.setSeleccionar(seleccionar.isSelected());
            lienzo2D.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            Object source = ae.getSource();
            if (source == modoLinea){
                barraEstado.setText("Modo de pintura: LINEA");
            }
            else if (source == modoRectangulo){
                barraEstado.setText("Modo de pintura: RECTANGULO");
            }
            else if (source == modoElipse){
                barraEstado.setText("Modo de pintura: ELIPSE");
            }
            else if (source == modoCurva){
                barraEstado.setText("Modo de pintura: CURVA");
            }
        }
    }
    
    public class ManejadorMenu implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == botonMenuNuevo){
            }
            else if (ae.getSource() == botonMenuAbrir){
                JFileChooser dlg =new JFileChooser();
                int resp = dlg.showOpenDialog(null);
                if (resp == JFileChooser.APPROVE_OPTION){
                    File f = dlg.getSelectedFile();
                }
            }
            else if (ae.getSource() == botonMenuGuardar){
                JFileChooser dlg =new JFileChooser();
                int resp = dlg.showSaveDialog(null);
                if (resp == JFileChooser.APPROVE_OPTION){
                    File f = dlg.getSelectedFile();
                }
            }
        }
    }

    public class ManejadorPropiedadesFiguras implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == seleccionar){
                lienzo2D.setSeleccionar(seleccionar.isSelected());
                barraEstado.setText("Modo seleccionar");
                lienzo2D.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            if (ae.getSource() == relleno){
                lienzo2D.setRelleno(relleno.isSelected());
            }
            if (ae.getSource() == transparencia){
                lienzo2D.setTransparencia(transparencia.isSelected());
            }
            if (ae.getSource() == alisar){
                lienzo2D.setAlisar(alisar.isSelected());
            }
        }
    }    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        grupoModoDibujo = new javax.swing.ButtonGroup();
        grupoColorDibujo = new javax.swing.ButtonGroup();
        lienzo2D = new sm.mrl.iu.Lienzo2D();
        barraHerramientas = new javax.swing.JToolBar();
        modoLinea = new javax.swing.JToggleButton();
        modoRectangulo = new javax.swing.JToggleButton();
        modoElipse = new javax.swing.JToggleButton();
        modoCurva = new javax.swing.JToggleButton();
        seleccionar = new javax.swing.JToggleButton();
        panelColor = new javax.swing.JPanel();
        dialogoColor = new javax.swing.JToggleButton();
        relleno = new javax.swing.JToggleButton();
        transparencia = new javax.swing.JToggleButton();
        alisar = new javax.swing.JToggleButton();
        grosor = new javax.swing.JSlider();
        barraEstado = new javax.swing.JLabel();
        barraMenu = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        botonMenuNuevo = new javax.swing.JMenuItem();
        botonMenuAbrir = new javax.swing.JMenuItem();
        botonMenuGuardar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 153));
        setPreferredSize(new java.awt.Dimension(1000, 600));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        lienzo2D.setBackground(new java.awt.Color(255, 255, 255));
        lienzo2D.setMinimumSize(new java.awt.Dimension(100, 100));
        lienzo2D.setPreferredSize(new java.awt.Dimension(600, 600));
        lienzo2D.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lienzo2DMouseMoved(evt);
            }
        });

        javax.swing.GroupLayout lienzo2DLayout = new javax.swing.GroupLayout(lienzo2D);
        lienzo2D.setLayout(lienzo2DLayout);
        lienzo2DLayout.setHorizontalGroup(
            lienzo2DLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        lienzo2DLayout.setVerticalGroup(
            lienzo2DLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(lienzo2D, gridBagConstraints);

        barraHerramientas.setBackground(new java.awt.Color(204, 204, 204));
        barraHerramientas.setRollover(true);

        grupoModoDibujo.add(modoLinea);
        modoLinea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/linea.png"))); // NOI18N
        modoLinea.setFocusable(false);
        modoLinea.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        modoLinea.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraHerramientas.add(modoLinea);

        grupoModoDibujo.add(modoRectangulo);
        modoRectangulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/rectangulo.png"))); // NOI18N
        modoRectangulo.setFocusable(false);
        modoRectangulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        modoRectangulo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraHerramientas.add(modoRectangulo);

        modoElipse.setBackground(new java.awt.Color(255, 255, 255));
        grupoModoDibujo.add(modoElipse);
        modoElipse.setForeground(new java.awt.Color(255, 255, 255));
        modoElipse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/elipse.png"))); // NOI18N
        modoElipse.setFocusable(false);
        modoElipse.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        modoElipse.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraHerramientas.add(modoElipse);

        modoCurva.setBackground(new java.awt.Color(255, 255, 255));
        grupoModoDibujo.add(modoCurva);
        modoCurva.setForeground(new java.awt.Color(255, 255, 255));
        modoCurva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/curva.png"))); // NOI18N
        modoCurva.setFocusable(false);
        modoCurva.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        modoCurva.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraHerramientas.add(modoCurva);

        grupoModoDibujo.add(seleccionar);
        seleccionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/seleccion.png"))); // NOI18N
        seleccionar.setFocusable(false);
        seleccionar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        seleccionar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraHerramientas.add(seleccionar);

        panelColor.setMaximumSize(new java.awt.Dimension(30, 30));
        panelColor.setPreferredSize(new java.awt.Dimension(30, 30));
        panelColor.setLayout(new java.awt.BorderLayout());

        dialogoColor.setBackground(new java.awt.Color(0, 0, 0));
        dialogoColor.setForeground(new java.awt.Color(0, 0, 0));
        dialogoColor.setSelected(true);
        dialogoColor.setFocusable(false);
        dialogoColor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        dialogoColor.setPreferredSize(new java.awt.Dimension(30, 30));
        dialogoColor.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        dialogoColor.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        panelColor.add(dialogoColor, java.awt.BorderLayout.CENTER);

        barraHerramientas.add(panelColor);

        relleno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/rellenar.png"))); // NOI18N
        relleno.setFocusable(false);
        relleno.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        relleno.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraHerramientas.add(relleno);

        transparencia.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/transparencia.png"))); // NOI18N
        transparencia.setFocusable(false);
        transparencia.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        transparencia.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraHerramientas.add(transparencia);

        alisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/alisar.png"))); // NOI18N
        alisar.setFocusable(false);
        alisar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        alisar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraHerramientas.add(alisar);

        grosor.setMaximum(25);
        grosor.setMinimum(1);
        grosor.setToolTipText("");
        grosor.setValue(2);
        grosor.setMaximumSize(new java.awt.Dimension(50, 16));
        grosor.setPreferredSize(new java.awt.Dimension(50, 16));
        grosor.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                grosorStateChanged(evt);
            }
        });
        barraHerramientas.add(grosor);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(barraHerramientas, gridBagConstraints);

        barraEstado.setBackground(new java.awt.Color(204, 204, 204));
        barraEstado.setText("Barra de Estado");
        barraEstado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        barraEstado.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(barraEstado, gridBagConstraints);

        menuArchivo.setText("Archivo");

        botonMenuNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        botonMenuNuevo.setText("Nuevo");
        menuArchivo.add(botonMenuNuevo);

        botonMenuAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        botonMenuAbrir.setText("Abrir");
        menuArchivo.add(botonMenuAbrir);

        botonMenuGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        botonMenuGuardar.setText("Guardar");
        menuArchivo.add(botonMenuGuardar);

        barraMenu.add(menuArchivo);

        setJMenuBar(barraMenu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void grosorStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_grosorStateChanged
        lienzo2D.setTrazo((int)grosor.getValue());
    }//GEN-LAST:event_grosorStateChanged

    private void lienzo2DMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lienzo2DMouseMoved
        barraEstado.setText("Modo de pintura: " + lienzo2D.getModoPintura().toString() + " | Coordenadas del raton: [" + evt.getX() + ", " + evt.getY() + "]");
    }//GEN-LAST:event_lienzo2DMouseMoved


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton alisar;
    private javax.swing.JLabel barraEstado;
    private javax.swing.JToolBar barraHerramientas;
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JMenuItem botonMenuAbrir;
    private javax.swing.JMenuItem botonMenuGuardar;
    private javax.swing.JMenuItem botonMenuNuevo;
    private javax.swing.JToggleButton dialogoColor;
    private javax.swing.JSlider grosor;
    private javax.swing.ButtonGroup grupoColorDibujo;
    private javax.swing.ButtonGroup grupoModoDibujo;
    private sm.mrl.iu.Lienzo2D lienzo2D;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JToggleButton modoCurva;
    private javax.swing.JToggleButton modoElipse;
    private javax.swing.JToggleButton modoLinea;
    private javax.swing.JToggleButton modoRectangulo;
    private javax.swing.JPanel panelColor;
    private javax.swing.JToggleButton relleno;
    private javax.swing.JToggleButton seleccionar;
    private javax.swing.JToggleButton transparencia;
    // End of variables declaration//GEN-END:variables
}
