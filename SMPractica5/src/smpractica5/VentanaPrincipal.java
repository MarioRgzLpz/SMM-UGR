/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package smpractica5;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;

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
        barraEstado.setText(lienzo.getModoPintura().toString());
        negro.addActionListener(new ManejadorColor(Color.BLACK));
        rojo.addActionListener(new ManejadorColor(Color.RED));
        azul.addActionListener(new ManejadorColor(Color.BLUE));
        dialogoColor.addActionListener(new ManejadorColor(Color.WHITE));
        amarillo.addActionListener(new ManejadorColor(Color.YELLOW));
        verde.addActionListener(new ManejadorColor(Color.GREEN));
        modoLinea.addActionListener(new ManejadorModoPintura(Lienzo.ModoPintura.LINEA));
        modoRectangulo.addActionListener(new ManejadorModoPintura(Lienzo.ModoPintura.RECTANGULO));
        modoElipse.addActionListener(new ManejadorModoPintura(Lienzo.ModoPintura.ELIPSE));
        botonMenuNuevo.addActionListener(new ManejadorMenu());
        botonMenuAbrir.addActionListener(new ManejadorMenu());
        botonMenuGuardar.addActionListener(new ManejadorMenu());
        relleno.addActionListener(new ManejadorPropiedadesFiguras());
        mover.addActionListener(new ManejadorPropiedadesFiguras());
        transparencia.addActionListener(new ManejadorPropiedadesFiguras());
        alisar.addActionListener(new ManejadorPropiedadesFiguras());
    }
    
    public class ManejadorColor implements ActionListener {
        private final Color color;
        
        public ManejadorColor(Color color) {
            this.color = color;
        }
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == dialogoColor){
                Color colorSeleccionado = JColorChooser.showDialog(VentanaPrincipal.this, "Gama colores", Color.BLACK);
                if (colorSeleccionado != null){
                    dialogoColor.setBackground(colorSeleccionado);
                    lienzo.setColor(colorSeleccionado);
                }
            }
            else {
                lienzo.setColor(color);
            }
        }
    }
    public class ManejadorModoPintura implements ActionListener {
        private final Lienzo.ModoPintura modoPintura;
        
        public ManejadorModoPintura(Lienzo.ModoPintura modoPintura) {
            this.modoPintura = modoPintura;
        }
        @Override
        public void actionPerformed(ActionEvent ae) {
            lienzo.setModoPintura(modoPintura);
            Object source = ae.getSource();
            if (source == modoLinea){
                barraEstado.setText("LINEA");
            }
            else if (source == modoRectangulo){
                barraEstado.setText("RECTANGULO");
            }
            else if (source == modoElipse){
                barraEstado.setText("ELIPSE");
            }
        }
    }
    
    public class ManejadorMenu implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == botonMenuNuevo){
                lienzo.limpiar();
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
            if (ae.getSource() == mover){
                lienzo.setMover(mover.isSelected());
            }
            if (ae.getSource() == relleno){
                lienzo.setRelleno(relleno.isSelected());
            }
            if (ae.getSource() == transparencia){
                lienzo.setTransparencia(transparencia.isSelected());
            }
            if (ae.getSource() == alisar){
                lienzo.setAlisar(alisar.isSelected());
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
        barraHerramientas = new javax.swing.JToolBar();
        modoLinea = new javax.swing.JToggleButton();
        modoRectangulo = new javax.swing.JToggleButton();
        modoElipse = new javax.swing.JToggleButton();
        lienzo = new smpractica5.Lienzo();
        propiedadesPintar = new javax.swing.JPanel();
        propiedadesFiguras = new javax.swing.JPanel();
        grosor = new javax.swing.JSpinner();
        alisar = new javax.swing.JCheckBox();
        transparencia = new javax.swing.JCheckBox();
        mover = new javax.swing.JCheckBox();
        relleno = new javax.swing.JCheckBox();
        seleccionColor = new javax.swing.JPanel();
        negro = new javax.swing.JToggleButton();
        rojo = new javax.swing.JToggleButton();
        azul = new javax.swing.JToggleButton();
        dialogoColor = new javax.swing.JToggleButton();
        amarillo = new javax.swing.JToggleButton();
        verde = new javax.swing.JToggleButton();
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

        barraHerramientas.setBackground(new java.awt.Color(204, 204, 204));
        barraHerramientas.setRollover(true);

        grupoModoDibujo.add(modoLinea);
        modoLinea.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/linea.png"))); // NOI18N
        modoLinea.setSelected(true);
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

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(barraHerramientas, gridBagConstraints);

        lienzo.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout lienzoLayout = new javax.swing.GroupLayout(lienzo);
        lienzo.setLayout(lienzoLayout);
        lienzoLayout.setHorizontalGroup(
            lienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        lienzoLayout.setVerticalGroup(
            lienzoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        getContentPane().add(lienzo, gridBagConstraints);

        propiedadesPintar.setBackground(new java.awt.Color(204, 204, 204));
        propiedadesPintar.setPreferredSize(new java.awt.Dimension(104, 60));
        propiedadesPintar.setLayout(new java.awt.BorderLayout());

        propiedadesFiguras.setBackground(new java.awt.Color(204, 204, 204));
        propiedadesFiguras.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 20));

        grosor.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        grosor.setPreferredSize(new java.awt.Dimension(41, 25));
        grosor.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                grosorStateChanged(evt);
            }
        });
        propiedadesFiguras.add(grosor);

        alisar.setBackground(new java.awt.Color(153, 153, 153));
        alisar.setText("Alisar");
        alisar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        alisar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        propiedadesFiguras.add(alisar);

        transparencia.setBackground(new java.awt.Color(153, 153, 153));
        transparencia.setText("Transparencia");
        transparencia.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        transparencia.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        propiedadesFiguras.add(transparencia);

        mover.setBackground(new java.awt.Color(153, 153, 153));
        mover.setText("Mover");
        mover.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        mover.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        propiedadesFiguras.add(mover);

        relleno.setBackground(new java.awt.Color(153, 153, 153));
        relleno.setText("Relleno");
        relleno.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        relleno.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        relleno.setPreferredSize(null);
        propiedadesFiguras.add(relleno);

        propiedadesPintar.add(propiedadesFiguras, java.awt.BorderLayout.EAST);

        seleccionColor.setBackground(new java.awt.Color(204, 204, 204));
        seleccionColor.setPreferredSize(new java.awt.Dimension(100, 80));
        seleccionColor.setLayout(new java.awt.GridLayout(2, 3));

        negro.setBackground(new java.awt.Color(0, 0, 0));
        grupoColorDibujo.add(negro);
        negro.setForeground(new java.awt.Color(0, 0, 0));
        negro.setSelected(true);
        negro.setMargin(new java.awt.Insets(2, 5, 2, 5));
        negro.setMinimumSize(new java.awt.Dimension(20, 20));
        negro.setName(""); // NOI18N
        negro.setPreferredSize(null);
        seleccionColor.add(negro);

        rojo.setBackground(new java.awt.Color(204, 0, 0));
        grupoColorDibujo.add(rojo);
        rojo.setMargin(new java.awt.Insets(2, 5, 2, 5));
        rojo.setMinimumSize(new java.awt.Dimension(20, 20));
        rojo.setName(""); // NOI18N
        rojo.setPreferredSize(null);
        seleccionColor.add(rojo);

        azul.setBackground(new java.awt.Color(0, 0, 204));
        grupoColorDibujo.add(azul);
        azul.setMargin(new java.awt.Insets(2, 5, 2, 5));
        azul.setMinimumSize(new java.awt.Dimension(20, 20));
        azul.setName(""); // NOI18N
        azul.setPreferredSize(null);
        seleccionColor.add(azul);

        dialogoColor.setBackground(new java.awt.Color(255, 255, 255));
        grupoColorDibujo.add(dialogoColor);
        dialogoColor.setMargin(new java.awt.Insets(2, 5, 2, 5));
        dialogoColor.setMinimumSize(new java.awt.Dimension(20, 20));
        dialogoColor.setName(""); // NOI18N
        dialogoColor.setPreferredSize(null);
        seleccionColor.add(dialogoColor);

        amarillo.setBackground(new java.awt.Color(204, 204, 0));
        grupoColorDibujo.add(amarillo);
        amarillo.setMargin(new java.awt.Insets(2, 5, 2, 5));
        amarillo.setMinimumSize(new java.awt.Dimension(20, 20));
        amarillo.setName(""); // NOI18N
        amarillo.setPreferredSize(null);
        seleccionColor.add(amarillo);

        verde.setBackground(new java.awt.Color(0, 204, 0));
        grupoColorDibujo.add(verde);
        verde.setMargin(new java.awt.Insets(2, 5, 2, 5));
        verde.setMinimumSize(new java.awt.Dimension(20, 20));
        verde.setName(""); // NOI18N
        verde.setPreferredSize(null);
        seleccionColor.add(verde);

        propiedadesPintar.add(seleccionColor, java.awt.BorderLayout.WEST);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(propiedadesPintar, gridBagConstraints);

        barraEstado.setBackground(new java.awt.Color(204, 204, 204));
        barraEstado.setText("Barra de Estado");
        barraEstado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        barraEstado.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
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
        lienzo.setTrazo((int)grosor.getValue());
    }//GEN-LAST:event_grosorStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox alisar;
    private javax.swing.JToggleButton amarillo;
    private javax.swing.JToggleButton azul;
    private javax.swing.JLabel barraEstado;
    private javax.swing.JToolBar barraHerramientas;
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JMenuItem botonMenuAbrir;
    private javax.swing.JMenuItem botonMenuGuardar;
    private javax.swing.JMenuItem botonMenuNuevo;
    private javax.swing.JToggleButton dialogoColor;
    private javax.swing.JSpinner grosor;
    private javax.swing.ButtonGroup grupoColorDibujo;
    private javax.swing.ButtonGroup grupoModoDibujo;
    private smpractica5.Lienzo lienzo;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JToggleButton modoElipse;
    private javax.swing.JToggleButton modoLinea;
    private javax.swing.JToggleButton modoRectangulo;
    private javax.swing.JCheckBox mover;
    private javax.swing.JToggleButton negro;
    private javax.swing.JPanel propiedadesFiguras;
    private javax.swing.JPanel propiedadesPintar;
    private javax.swing.JCheckBox relleno;
    private javax.swing.JToggleButton rojo;
    private javax.swing.JPanel seleccionColor;
    private javax.swing.JCheckBox transparencia;
    private javax.swing.JToggleButton verde;
    // End of variables declaration//GEN-END:variables
}
