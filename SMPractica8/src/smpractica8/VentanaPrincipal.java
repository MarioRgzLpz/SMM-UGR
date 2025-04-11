/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package smpractica8;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.event.InternalFrameAdapter;
import sm.mrl.iu.Lienzo2D;

/**
 *
 * @author mariorl
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    private Lienzo2D lienzo;
    private final ManejadorColor manejadorColor = new ManejadorColor();
    private final ManejadorMenu manejadorMenu = new ManejadorMenu();
    private final ManejadorPropiedadesFiguras manejadorPropiedades = new ManejadorPropiedadesFiguras();
    private final ManejadorVentanaInterna manejadorVentana = new ManejadorVentanaInterna();
    private final ManejadorModoPintura manejadorModoPintura = new ManejadorModoPintura();
    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();
        this.setTitle("Ñaint");
        lienzo = getSelectedLienzo();
        if (lienzo != null) {
            lienzo.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        }
        dialogoColor.addActionListener(manejadorColor);
        modoLinea.addActionListener(manejadorModoPintura);
        modoRectangulo.addActionListener(manejadorModoPintura);
        modoElipse.addActionListener(manejadorModoPintura);
        modoCurva.addActionListener(manejadorModoPintura);
        botonMenuNuevo.addActionListener(manejadorMenu);
        botonMenuAbrir.addActionListener(manejadorMenu);
        botonMenuGuardar.addActionListener(manejadorMenu);
        botonNuevo.addActionListener(manejadorMenu);
        botonAbrir.addActionListener(manejadorMenu);
        botonGuardar.addActionListener(manejadorMenu);
        relleno.addActionListener(manejadorPropiedades);
        seleccionar.addActionListener(manejadorModoPintura);
        transparencia.addActionListener(manejadorPropiedades);
        alisar.addActionListener(manejadorPropiedades);
        eliminar.addActionListener(manejadorModoPintura);
        fijar.addActionListener(manejadorModoPintura);
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
        eliminar.setToolTipText("Eliminar");
        botonNuevo.setToolTipText("Nuevo (ctrl + n)");
        botonAbrir.setToolTipText("Abrir (ctrl + a)");
        botonGuardar.setToolTipText("Guardar (ctrl + s)");
        fijar.setToolTipText("Fijar");
        transparencia.setToolTipText("Transparencia");
        alisar.setToolTipText("Alisar");
        grosor.setToolTipText("Grosor trazo");

    }

    private Lienzo2D getSelectedLienzo() {
        VentanaInterna vi = (VentanaInterna) escritorio.getSelectedFrame();
        return vi != null ? vi.getLienzo2D() : null;
    }
    
    private void añadirSonidosVentana(VentanaInterna vi){
        File f = new File(getClass().getResource("/sonidos/raaaah.wav").getFile());
        vi.getLienzo2D().setSonidoFijar(f);
        f = new File(getClass().getResource("/sonidos/metal-pipe.wav").getFile());
        vi.getLienzo2D().setSonidoEliminar(f);
    }

    public class ManejadorColor implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            lienzo = getSelectedLienzo();
            if (lienzo != null) {
                if (ae.getSource() == dialogoColor) {
                    Color colorSeleccionado = JColorChooser.showDialog(VentanaPrincipal.this, "Elija el color del trazo", Color.BLACK);
                    if (colorSeleccionado != null) {
                        dialogoColor.setBackground(colorSeleccionado);
                        lienzo.setColor(colorSeleccionado);
                        lienzo.actualizarAtributos();
                    }
                }
            }
        }
    }

    public class ManejadorModoPintura implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            lienzo = getSelectedLienzo();
            if (lienzo != null) {
                lienzo.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                lienzo.setEliminar(eliminar.isSelected());
                lienzo.setFijar(fijar.isSelected());
                lienzo.setSeleccionar(seleccionar.isSelected());
                Object source = ae.getSource();
                if(source != seleccionar){
                    System.out.print("hola");
                    lienzo.actualizarAtributos();
                }
                if (source == modoLinea) {
                    lienzo.setModoPintura(Lienzo2D.ModoPintura.LINEA);
                } else if (source == modoRectangulo) {
                    lienzo.setModoPintura(Lienzo2D.ModoPintura.RECTANGULO);
                } else if (source == modoElipse) {
                    lienzo.setModoPintura(Lienzo2D.ModoPintura.ELIPSE);
                } else if (source == modoCurva) {
                    lienzo.setModoPintura(Lienzo2D.ModoPintura.CURVA);
                } else if (source == seleccionar) {
                    lienzo.setSeleccionar(seleccionar.isSelected());
                    lienzo.setCursor(new Cursor(Cursor.HAND_CURSOR));
                } else if (source == eliminar) {
                    lienzo.setEliminar(eliminar.isSelected());
                } else if (source == fijar) {
                    lienzo.setFijar(fijar.isSelected());
                }
            }
        }
    }

    public class ManejadorMenu implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == botonMenuNuevo || ae.getSource() == botonNuevo) {
                VentanaInterna vi = new VentanaInterna();
                VentanaPrincipal.this.añadirSonidosVentana(vi);
                escritorio.add(vi);
                Dimension desktopSize = escritorio.getSize();
                vi.addInternalFrameListener(VentanaPrincipal.this.manejadorVentana);
                vi.setSize(desktopSize);
                vi.setLocation(0, 0);
                BufferedImage img = new BufferedImage(
                        desktopSize.width,
                        desktopSize.height,
                        BufferedImage.TYPE_INT_RGB
                );
                Graphics2D g2d = img.createGraphics();
                g2d.setColor(new Color(220, 220, 220));
                g2d.fillRect(0, 0, desktopSize.width, desktopSize.height);
                g2d.setColor(Color.WHITE);
                int xMargin = 200, yMargin = 100;
                g2d.fillRect(xMargin, yMargin, desktopSize.width - 2 * xMargin - 15, desktopSize.height - 2 * yMargin - 35);
                g2d.dispose();
                vi.getLienzo2D().setImagen(img);
                vi.setVisible(true);
                
            } else if (ae.getSource() == botonMenuAbrir || ae.getSource() == botonAbrir) {
                JFileChooser dlg = new JFileChooser();
                int resp = dlg.showOpenDialog(null);
                if (resp == JFileChooser.APPROVE_OPTION) {
                    try {
                        File f = dlg.getSelectedFile();
                        BufferedImage img = ImageIO.read(f);
                        VentanaInterna vi = new VentanaInterna();
                        vi.getLienzo2D().setImagen(img);
                        escritorio.add(vi);
                        vi.setTitle(f.getName());
                        vi.setVisible(true);
                    } catch (Exception ex) {
                        System.err.println("Error al leer la imagen");
                    }
                }
            } else if (ae.getSource() == botonMenuGuardar || ae.getSource() == botonGuardar) {
                VentanaInterna vi = (VentanaInterna) escritorio.getSelectedFrame();
                if (vi != null) {
                    BufferedImage img = vi.getLienzo2D().getPaintedImage();
                    if (img != null) {
                        JFileChooser dlg = new JFileChooser();
                        int resp = dlg.showSaveDialog(null);
                        if (resp == JFileChooser.APPROVE_OPTION) {
                            try {
                                File f = dlg.getSelectedFile();
                                ImageIO.write(img, "jpg", f);
                                vi.setTitle(f.getName());
                            } catch (Exception ex) {
                                System.err.println("Error al guardar la imagen");
                            }
                        }
                    }
                }
            }
        }
    }

    public class ManejadorPropiedadesFiguras implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == relleno) {
                if (lienzo != null) {
                    lienzo.setRelleno(relleno.isSelected());
                }
            }
            if (ae.getSource() == transparencia) {
                if (lienzo != null) {
                    lienzo.setTransparencia(transparencia.isSelected());
                }
            }
            if (ae.getSource() == alisar) {
                if (lienzo != null) {
                    lienzo.setAlisar(alisar.isSelected());
                }
            }
            
            lienzo.actualizarAtributos();
        }
    }
    
    public class ManejadorVentanaInterna extends InternalFrameAdapter {
        @Override
        public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt){
            VentanaInterna vi = (VentanaInterna) evt.getInternalFrame();
            lienzo = vi.getLienzo2D();
            actualizarInterfaz(lienzo);
        }
        
        private void actualizarInterfaz(Lienzo2D lienzo){
            dialogoColor.setBackground(lienzo.getColor());
            relleno.setSelected(lienzo.getRelleno());
            transparencia.setSelected(lienzo.getTransparencia());
            alisar.setSelected(lienzo.getAlisar());
            grosor.setValue((int) lienzo.getTrazo().getLineWidth());
            
            Lienzo2D.ModoPintura modoPintura = lienzo.getModoPintura();
            if (modoPintura == Lienzo2D.ModoPintura.LINEA) {
                modoLinea.setSelected(true);
            } else if (modoPintura == Lienzo2D.ModoPintura.RECTANGULO) {
                modoRectangulo.setSelected(true);
            } else if (modoPintura == Lienzo2D.ModoPintura.ELIPSE) {
                modoElipse.setSelected(true);
            } else if (modoPintura == Lienzo2D.ModoPintura.CURVA) {
                modoCurva.setSelected(true);
            } else if (lienzo.getSeleccionar()){
                seleccionar.setSelected(true);
            } else if (lienzo.getEliminar()){
                eliminar.setSelected(true);
            } else if (lienzo.getFijar()){
                fijar.setSelected(true);
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

        grupoModoDibujo = new javax.swing.ButtonGroup();
        barraHerramientas = new javax.swing.JToolBar();
        botonNuevo = new javax.swing.JButton();
        botonAbrir = new javax.swing.JButton();
        botonGuardar = new javax.swing.JButton();
        modoLinea = new javax.swing.JToggleButton();
        modoRectangulo = new javax.swing.JToggleButton();
        modoElipse = new javax.swing.JToggleButton();
        modoCurva = new javax.swing.JToggleButton();
        seleccionar = new javax.swing.JToggleButton();
        fijar = new javax.swing.JToggleButton();
        eliminar = new javax.swing.JToggleButton();
        panelColor = new javax.swing.JPanel();
        dialogoColor = new javax.swing.JToggleButton();
        relleno = new javax.swing.JToggleButton();
        transparencia = new javax.swing.JToggleButton();
        alisar = new javax.swing.JToggleButton();
        grosor = new javax.swing.JSlider();
        escritorio = new javax.swing.JDesktopPane();
        barraEstado = new javax.swing.JLabel();
        barraMenu = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        botonMenuNuevo = new javax.swing.JMenuItem();
        botonMenuAbrir = new javax.swing.JMenuItem();
        botonMenuGuardar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 153));

        barraHerramientas.setBackground(new java.awt.Color(204, 204, 204));
        barraHerramientas.setRollover(true);

        botonNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/nuevo.png"))); // NOI18N
        botonNuevo.setFocusable(false);
        botonNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonNuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraHerramientas.add(botonNuevo);

        botonAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/abrir.png"))); // NOI18N
        botonAbrir.setFocusable(false);
        botonAbrir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonAbrir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraHerramientas.add(botonAbrir);

        botonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/guardar.png"))); // NOI18N
        botonGuardar.setFocusable(false);
        botonGuardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonGuardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraHerramientas.add(botonGuardar);

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

        grupoModoDibujo.add(fijar);
        fijar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/fijar.png"))); // NOI18N
        fijar.setFocusable(false);
        fijar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        fijar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraHerramientas.add(fijar);

        grupoModoDibujo.add(eliminar);
        eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/eliminar.png"))); // NOI18N
        eliminar.setFocusable(false);
        eliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        eliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        barraHerramientas.add(eliminar);

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

        getContentPane().add(barraHerramientas, java.awt.BorderLayout.PAGE_START);

        escritorio.setPreferredSize(new java.awt.Dimension(600, 300));

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 613, Short.MAX_VALUE)
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 370, Short.MAX_VALUE)
        );

        getContentPane().add(escritorio, java.awt.BorderLayout.CENTER);

        barraEstado.setBackground(new java.awt.Color(204, 204, 204));
        barraEstado.setText("Barra de Estado");
        barraEstado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        barraEstado.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        getContentPane().add(barraEstado, java.awt.BorderLayout.PAGE_END);

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
        if(lienzo != null){
            lienzo.setTrazo((int) grosor.getValue());
            lienzo.actualizarAtributos();
        }
    }//GEN-LAST:event_grosorStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton alisar;
    private javax.swing.JLabel barraEstado;
    private javax.swing.JToolBar barraHerramientas;
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JButton botonAbrir;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JMenuItem botonMenuAbrir;
    private javax.swing.JMenuItem botonMenuGuardar;
    private javax.swing.JMenuItem botonMenuNuevo;
    private javax.swing.JButton botonNuevo;
    private javax.swing.JToggleButton dialogoColor;
    private javax.swing.JToggleButton eliminar;
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JToggleButton fijar;
    private javax.swing.JSlider grosor;
    private javax.swing.ButtonGroup grupoModoDibujo;
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
