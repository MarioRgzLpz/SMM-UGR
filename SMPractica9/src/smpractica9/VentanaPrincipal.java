/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package smpractica9;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.RescaleOp;
import java.io.File;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.filechooser.FileNameExtensionFilter;
import sm.mrl.iu.Lienzo2D;
import sm.image.KernelProducer;
import sm.mrl.events.LienzoAdapter;
import sm.mrl.events.LienzoEvent;
import sm.mrl.graficos.JFigura;

/**
 *
 * @author mariorl
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    private Lienzo2D lienzo;
    private BufferedImage imgFuente = null;
    private final int XMARGIN = 75;
    private final int YMARGIN = 50;
    private final ManejadorMenu manejadorMenu = new ManejadorMenu();
    private final ManejadorPropiedadesFiguras manejadorPropiedadesFiguras = new ManejadorPropiedadesFiguras();
    private final ManejadorVentanaInterna manejadorVentana = new ManejadorVentanaInterna();
    private final ManejadorModoPintura manejadorModoPintura = new ManejadorModoPintura();
    private final ManejadorRatonLienzo manejadorRatonLienzo = new ManejadorRatonLienzo();
    private final ManejadorMenuImagen manejadorMenuImagen = new ManejadorMenuImagen();
    private final ManejadorPropiedadesImagenes manejadorPropiedadesImagen = new ManejadorPropiedadesImagenes();
    private final ManejadorLienzo manejadorLienzo = new ManejadorLienzo();

    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();
        this.setTitle("Ñaint");
        modoLinea.addActionListener(manejadorModoPintura);
        modoRectangulo.addActionListener(manejadorModoPintura);
        modoElipse.addActionListener(manejadorModoPintura);
        modoCurva.addActionListener(manejadorModoPintura);
        seleccionar.addActionListener(manejadorModoPintura);
        eliminar.addActionListener(manejadorModoPintura);
        fijar.addActionListener(manejadorModoPintura);
        botonMenuNuevo.addActionListener(manejadorMenu);
        botonMenuAbrir.addActionListener(manejadorMenu);
        botonMenuGuardar.addActionListener(manejadorMenu);
        botonNuevo.addActionListener(manejadorMenu);
        botonAbrir.addActionListener(manejadorMenu);
        botonGuardar.addActionListener(manejadorMenu);
        relleno.addActionListener(manejadorPropiedadesFiguras);
        dialogoColor.addActionListener(manejadorPropiedadesFiguras);
        transparencia.addActionListener(manejadorPropiedadesFiguras);
        alisar.addActionListener(manejadorPropiedadesFiguras);
        grosor.addChangeListener(manejadorPropiedadesFiguras);
        botonReescalar.addActionListener(manejadorMenuImagen);
        botonConvolucion.addActionListener(manejadorMenuImagen);
        listaFiltros.addActionListener(manejadorPropiedadesImagen);
        brillo.addChangeListener(manejadorPropiedadesImagen);
        contraste.addChangeListener(manejadorPropiedadesImagen);
        desenfoque.addChangeListener(manejadorPropiedadesImagen);
        perfilado.addChangeListener(manejadorPropiedadesImagen);
        brillo.addFocusListener(manejadorPropiedadesImagen);
        contraste.addFocusListener(manejadorPropiedadesImagen);
        desenfoque.addFocusListener(manejadorPropiedadesImagen);
        perfilado.addFocusListener(manejadorPropiedadesImagen);
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
        botonReescalar.setToolTipText("Reescalar");
        botonConvolucion.setToolTipText("Convolucion");
        barraEstado.setToolTipText("Modo-Grosor-Coordenadas");
        iconoBrillo.setToolTipText("Brillo");
        iconoContraste.setToolTipText("Contraste");
        iconoDesenfoque.setToolTipText("Desenfoque");
        iconoPerfilado.setToolTipText("Perfilado");
    }

    /**
     * Obtiene el lienzo seleccionado en la ventana interna activa.
     *
     * @return El lienzo seleccionado, o null si no hay una ventana interna
     * activa.
     */
    private Lienzo2D getSelectedLienzo() {
        VentanaInterna vi = (VentanaInterna) escritorio.getSelectedFrame();
        return vi != null ? vi.getLienzo2D() : null;
    }

    /**
     * Añade sonidos de fijar y eliminar a la ventana interna proporcionada.
     *
     * @param vi La ventana interna a la que se añaden los sonidos.
     */
    private void añadirSonidosVentana(VentanaInterna vi) {
        File f = new File(getClass().getResource("/sonidos/raaaah.wav").getFile());
        vi.getLienzo2D().setSonidoFijar(f);
        f = new File(getClass().getResource("/sonidos/metal-pipe.wav").getFile());
        vi.getLienzo2D().setSonidoEliminar(f);
    }

    public void setTextoBarraEstado(Lienzo2D lienzo) {
        String textoBarraEstado;
        String modo;
        modo = lienzo.getModoPintura().toString();
        if (seleccionar.isSelected()) {
            modo = "SELECCIONAR";
        } else if (eliminar.isSelected()) {
            modo = "ELIMINAR";
        } else if (fijar.isSelected()) {
            modo = "FIJAR";
        }
        textoBarraEstado = String.format("MODO: %s    GROSOR: %.2f    COORDENADAS ([X: %.2f ], [Y: %.2f ])",
                modo,
                lienzo.getTrazo().getLineWidth(),
                lienzo.getCoordenadasRaton().getX() - XMARGIN,
                lienzo.getCoordenadasRaton().getY() - YMARGIN);
        barraEstado.setText(textoBarraEstado);
    }

    /**
     * Clase que maneja los eventos del ratón sobre el lienzo.
     */
    public class ManejadorRatonLienzo extends MouseMotionAdapter {

        /**
         * Actualiza la barra de estado con las coordenadas del ratón al moverse
         * sobre el lienzo.
         *
         * @param e Evento de movimiento del ratón.
         */
        @Override
        public void mouseMoved(MouseEvent e) {
            lienzo = getSelectedLienzo();
            if (lienzo != null) {
                setTextoBarraEstado(lienzo);
            } else {
                barraEstado.setText("Barra de estado");
            }
        }
    }

    /**
     * Clase que maneja los cambios en el modo de pintura del lienzo.
     */
    public class ManejadorModoPintura implements ActionListener {

        /**
         * Gestiona los cambios en el modo de pintura y actualiza las
         * propiedades del lienzo.
         *
         * @param ae Evento de acción que contiene la fuente de la acción
         * realizada.
         */
        @Override
        public void actionPerformed(ActionEvent ae) {
            lienzo = getSelectedLienzo();
            if (lienzo != null) {
                lienzo.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                lienzo.setEliminar(eliminar.isSelected());
                lienzo.setFijar(fijar.isSelected());
                lienzo.setSeleccionar(seleccionar.isSelected());
                setTextoBarraEstado(lienzo);
                Object source = ae.getSource();
                if (source != seleccionar && lienzo.getForma() != null) {
                    lienzo.actualizarAtributosFormaSeleccionada();
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
    
    /**
     * Clase que maneja las acciones relacionadas con las propiedades de las
     * figuras, como relleno, transparencia y suavizado.
     */
    public class ManejadorPropiedadesFiguras implements ActionListener, ChangeListener {

        /**
         * Gestiona las acciones realizadas sobre las propiedades de las
         * figuras.
         *
         * @param ae Evento de acción que contiene la fuente de la acción
         * realizada.
         */
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (lienzo != null) {
                if (ae.getSource() == relleno) {
                    lienzo.setRelleno(relleno.isSelected());
                }
                if (ae.getSource() == transparencia) {
                    lienzo.setTransparencia(transparencia.isSelected());
                }
                if (ae.getSource() == alisar) {
                    lienzo.setAlisar(alisar.isSelected());
                }
                if (ae.getSource() == dialogoColor) {
                    Color colorSeleccionado = JColorChooser.showDialog(VentanaPrincipal.this, "Elija el color del trazo", Color.BLACK);
                    if (colorSeleccionado != null) {
                        dialogoColor.setBackground(colorSeleccionado);
                        lienzo.setColor(colorSeleccionado);
                    }
                }
                if(seleccionar.isSelected()){
                    lienzo.actualizarAtributosFormaSeleccionada();
                    System.out.print("Actualizo");
                }
            }
        }

        @Override
        public void stateChanged(ChangeEvent ae) {
            if (lienzo != null) {
                lienzo.setTrazo((int) grosor.getValue());
                lienzo.actualizarAtributosFormaSeleccionada();
            }
        }
    }

    /**
     * Clase que maneja las acciones del menú de la aplicación.
     */
    public class ManejadorMenu implements ActionListener {

        /**
         * Gestiona las acciones realizadas por el usuario en los elementos del
         * menú.
         *
         * @param ae Evento de acción que contiene la fuente de la acción
         * realizada.
         */
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == botonMenuNuevo || ae.getSource() == botonNuevo) {
                Dimension desktopSize = escritorio.getSize();
                String anchoStr = (String) JOptionPane.showInputDialog(VentanaPrincipal.this, "Ingrese el ancho de la ventana (valor maximo por defecto):",
                        "Dimensiones de la ventana", JOptionPane.QUESTION_MESSAGE, null, null, desktopSize.width - 2 * XMARGIN);
                if (anchoStr == null) {
                    return;
                }

                int anchoDibujo = Integer.parseInt(anchoStr);

                String altoStr = (String) JOptionPane.showInputDialog(null, "Ingrese el alto de la ventana (valor maximo por defecto):",
                        "Dimensiones de la ventana", JOptionPane.QUESTION_MESSAGE, null, null, desktopSize.height - 2 * YMARGIN);
                if (altoStr == null) {
                    return;
                }

                int altoDibujo = Integer.parseInt(altoStr);
                VentanaInterna vi = new VentanaInterna();
                VentanaPrincipal.this.añadirSonidosVentana(vi);
                vi.getLienzo2D().addMouseMotionListener(manejadorRatonLienzo);
                vi.getLienzo2D().addLienzoListener(manejadorLienzo);
                vi.getLienzo2D().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                escritorio.add(vi);
                vi.addInternalFrameListener(VentanaPrincipal.this.manejadorVentana);
                vi.setSize(anchoDibujo + 2 * XMARGIN, altoDibujo + 2 * YMARGIN);
                BufferedImage img = new BufferedImage(
                        vi.getWidth(),
                        vi.getHeight(),
                        BufferedImage.TYPE_INT_RGB
                );
                Graphics2D g2d = img.createGraphics();
                g2d.setColor(new Color(220, 220, 220));
                g2d.fillRect(0, 0, img.getWidth(), img.getHeight());
                g2d.setColor(Color.WHITE);
                g2d.fillRect(
                        XMARGIN,
                        YMARGIN,
                        anchoDibujo,
                        altoDibujo - barraEstado.getHeight()
                );
                g2d.dispose();
                vi.getLienzo2D().setImagen(img);
                vi.getLienzo2D().setXMargin(XMARGIN);
                vi.getLienzo2D().setYMargin(YMARGIN);
                vi.getLienzo2D().setAlturaBarraEstado(barraEstado.getHeight());
                vi.setLocation(0, 0);
                vi.setVisible(true);

            } else if (ae.getSource() == botonMenuAbrir || ae.getSource() == botonAbrir) {
                JFileChooser dlg = new JFileChooser();
                dlg.setFileFilter(new FileNameExtensionFilter(Arrays.toString(ImageIO.getReaderFormatNames()), ImageIO.getReaderFormatNames()));
                int resp = dlg.showOpenDialog(null);
                if (resp == JFileChooser.APPROVE_OPTION) {
                    try {
                        File f = dlg.getSelectedFile();
                        BufferedImage img = ImageIO.read(f);
                        if (img == null) {
                            throw new IllegalArgumentException("Formato de archivo no soportado");
                        }
                        VentanaInterna vi = new VentanaInterna();
                        vi.getLienzo2D().setImagen(img);
                        vi.getLienzo2D().addMouseMotionListener(manejadorRatonLienzo);
                        vi.getLienzo2D().addLienzoListener(manejadorLienzo);
                        vi.getLienzo2D().setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                        vi.setSize(new Dimension(img.getWidth() + 35, img.getHeight() + 45));
                        escritorio.add(vi);
                        vi.addInternalFrameListener(VentanaPrincipal.this.manejadorVentana);
                        vi.setTitle(f.getName());
                        vi.setVisible(true);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error al abrir la imagen: " + ex.getMessage(),
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else if (ae.getSource() == botonMenuGuardar || ae.getSource() == botonGuardar) {
                VentanaInterna vi = (VentanaInterna) escritorio.getSelectedFrame();
                if (vi != null) {
                    BufferedImage img = vi.getLienzo2D().getPaintedImage();
                    if (img != null) {
                        JFileChooser dlg = new JFileChooser();
                        dlg.setFileFilter(new FileNameExtensionFilter(Arrays.toString(ImageIO.getWriterFormatNames()), ImageIO.getWriterFormatNames()));
                        int resp = dlg.showSaveDialog(null);
                        if (resp == JFileChooser.APPROVE_OPTION) {
                            try {
                                File f = dlg.getSelectedFile();
                                String fileName = f.getName();
                                String extension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();

                                if (!Arrays.asList(ImageIO.getWriterFormatNames()).contains(extension)) {
                                    throw new IllegalArgumentException("Extensión de archivo no soportada");
                                }

                                ImageIO.write(img, extension, f);
                                vi.setTitle(f.getName());
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Error al guardar la imagen: " + ex.getMessage(),
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Clase que maneja los eventos relacionados con las ventanas internas.
     */
    public class ManejadorVentanaInterna extends InternalFrameAdapter {

        /**
         * Maneja el evento cuando una ventana interna se activa.
         *
         * @param evt Evento de activación de una ventana interna.
         */
        @Override
        public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            VentanaInterna vi = (VentanaInterna) evt.getInternalFrame();
            lienzo = vi.getLienzo2D();
            actualizarInterfaz(lienzo);
        }

        /**
         * Actualiza la interfaz de usuario para reflejar las propiedades del
         * lienzo actual.
         *
         * @param lienzo Lienzo2D cuya configuración se usa para actualizar la
         * interfaz.
         */
        private void actualizarInterfaz(Lienzo2D lienzo) {
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
            } else if (lienzo.getSeleccionar()) {
                seleccionar.setSelected(true);
            } else if (lienzo.getEliminar()) {
                eliminar.setSelected(true);
            } else if (lienzo.getFijar()) {
                fijar.setSelected(true);
            }
        }
    }

    class ManejadorMenuImagen implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == botonReescalar) {
                VentanaInterna vi = (VentanaInterna) (escritorio.getSelectedFrame());
                if (vi != null) {
                    BufferedImage img = vi.getLienzo2D().getImagen();
                    if (img != null) {
                        try {
                            RescaleOp rop = new RescaleOp(1.0F, 100.0F, null);
                            BufferedImage imgdest = rop.filter(img, null);
                            vi.getLienzo2D().setImagen(imgdest);
                            vi.getLienzo2D().repaint();
                        } catch (IllegalArgumentException e) {
                            System.err.println(e.getLocalizedMessage());
                        }
                    }
                }
            } else if (ae.getSource() == botonConvolucion) {
                VentanaInterna vi = (VentanaInterna) (escritorio.getSelectedFrame());
                if (vi != null) {
                    BufferedImage img = vi.getLienzo2D().getImagen();
                    if (img != null) {
                        try {
                            float filtro[] = {0.1f, 0.1f, 0.1f, 0.1f, 0.2f, 0.1f, 0.1f, 0.1f, 0.1f};
                            Kernel k = new Kernel(3, 3, filtro);
                            ConvolveOp cop = new ConvolveOp(k);
                            BufferedImage imgdest = cop.filter(img, null);
                            vi.getLienzo2D().setImagen(imgdest);
                            vi.getLienzo2D().repaint();
                        } catch (IllegalArgumentException e) {
                            System.err.println(e.getLocalizedMessage());
                        }
                    }
                }
            }
        }
    }

    class ManejadorPropiedadesImagenes implements ActionListener, ChangeListener, FocusListener {

        @Override
        public void focusGained(FocusEvent e) {
            if (lienzo != null && lienzo.getImagen() != null) {
                imgFuente = lienzo.getImagen();
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (lienzo != null && imgFuente != null) {
                imgFuente = null;
                lienzo.repaint();
            }
        }

        @Override
        public void stateChanged(ChangeEvent evt) {
            if (lienzo != null && imgFuente != null) {
                BufferedImage imagenFiltrada = new BufferedImage(
                        imgFuente.getColorModel(),
                        imgFuente.copyData(null),
                        imgFuente.isAlphaPremultiplied(),
                        null
                );

                if (evt.getSource() == brillo) {
                    aplicarBrillo(imagenFiltrada);
                } else if (evt.getSource() == contraste) {
                    aplicarContraste(imagenFiltrada);
                } else if (evt.getSource() == desenfoque) {
                    aplicarDesenfoque(imgFuente, imagenFiltrada);
                } else if (evt.getSource() == perfilado) {
                    aplicarPerfilado(imgFuente, imagenFiltrada);
                }

                lienzo.setImagen(imagenFiltrada);
                lienzo.repaint();
            }
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (lienzo != null && ae.getSource() == listaFiltros && lienzo.getImagen() != null) {
                Kernel kernel = null;
                ConvolveOp op = null;

                switch (listaFiltros.getSelectedIndex()) {
                    case 0:
                        kernel = KernelProducer.createKernel(KernelProducer.TYPE_MEDIA_3x3);
                        op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
                        break;

                    case 1:
                        kernel = KernelProducer.createKernel(KernelProducer.TYPE_BINOMIAL_3x3);
                        op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
                        break;

                    case 2:
                        kernel = KernelProducer.createKernel(KernelProducer.TYPE_ENFOQUE_3x3);
                        op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
                        break;

                    case 3:
                        kernel = KernelProducer.createKernel(KernelProducer.TYPE_RELIEVE_3x3);
                        op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
                        break;

                    case 4:
                        kernel = KernelProducer.createKernel(KernelProducer.TYPE_LAPLACIANA_3x3);
                        op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
                        break;

                    case 5:
                        float[] kernel5x5 = generarMascaraMedia(5);
                        op = new ConvolveOp(new Kernel(5, 5, kernel5x5));
                        break;

                    case 6:
                        float[] kernel7x7 = generarMascaraMedia(7);
                        op = new ConvolveOp(new Kernel(7, 7, kernel7x7));
                        break;
                }

                if (op != null) {
                    lienzo.setImagen(op.filter(lienzo.getImagen(), null));
                    lienzo.repaint();
                }
            }
        }

        private float[] generarMascaraMedia(int tamaño) {
            float[] kernel = new float[tamaño * tamaño];
            float valor = 1.0f / (tamaño * tamaño);
            Arrays.fill(kernel, valor);
            return kernel;
        }

        private float[] generarMascaraPerfilado(float a) {
            return new float[]{0, -a, 0, -a, 4 * a + 1, -a, 0, -a, 0};
        }

        private void aplicarBrillo(BufferedImage imagen) {
            float factorSuma = brillo.getValue();
            RescaleOp op = new RescaleOp(1.0f, factorSuma, null);
            op.filter(imagen, imagen);
        }

        private void aplicarContraste(BufferedImage imagen) {
            float factorEscala = contraste.getValue() / 10f;
            RescaleOp op = new RescaleOp(factorEscala, 0.0f, null);
            op.filter(imagen, imagen);
        }

        private void aplicarDesenfoque(BufferedImage imagen, BufferedImage imagendst) {
            int tamaño = desenfoque.getValue();
            float[] kernel = generarMascaraMedia(tamaño);
            ConvolveOp op = new ConvolveOp(new Kernel(tamaño, tamaño, kernel));
            op.filter(imagen, imagendst);
        }

        private void aplicarPerfilado(BufferedImage imagen, BufferedImage imagendst) {
            float a = perfilado.getValue();
            float[] kernel = generarMascaraPerfilado(a);
            ConvolveOp op = new ConvolveOp(new Kernel(3, 3, kernel));
            op.filter(imagen, imagendst);
        }
    }

    public class ManejadorLienzo extends LienzoAdapter {

        @Override
        public void shapeAdded(LienzoEvent evt) {
            //System.out.println("Figura " + evt.getForma() + " añadida");
        }

        @Override
        public void shapeSelected(LienzoEvent evt) {
            actualizarLienzoForma();
            actualizarInterfazForma(evt.getForma());
        }

        private void actualizarInterfazForma(JFigura forma) {
            dialogoColor.setBackground(forma.getColor());
            relleno.setSelected(forma.getRelleno());
            transparencia.setSelected(forma.getTransparencia());
            alisar.setSelected(forma.getAlisar());
            grosor.setValue((int) forma.getTrazo().getLineWidth());
        }

        private void actualizarLienzoForma() {
            if (lienzo != null) {
                lienzo.setRelleno(relleno.isSelected());
                lienzo.setTransparencia(transparencia.isSelected());
                lienzo.setAlisar(alisar.isSelected());
                lienzo.setColor(dialogoColor.getBackground());
                lienzo.setTrazo(grosor.getValue());
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
        separador1 = new javax.swing.JToolBar.Separator();
        panelColor = new javax.swing.JPanel();
        dialogoColor = new javax.swing.JToggleButton();
        relleno = new javax.swing.JToggleButton();
        transparencia = new javax.swing.JToggleButton();
        alisar = new javax.swing.JToggleButton();
        separador2 = new javax.swing.JToolBar.Separator();
        grosor = new javax.swing.JSlider();
        escritorio = new javax.swing.JDesktopPane();
        contenedor = new javax.swing.JPanel();
        barraEstado = new javax.swing.JLabel();
        toolBarImagenes = new javax.swing.JToolBar();
        iconoBrillo = new javax.swing.JLabel();
        brillo = new javax.swing.JSlider();
        separadorBlanco1 = new javax.swing.JLabel();
        iconoContraste = new javax.swing.JLabel();
        contraste = new javax.swing.JSlider();
        separador3 = new javax.swing.JToolBar.Separator();
        listaFiltros = new javax.swing.JComboBox<>();
        separadorBlanco2 = new javax.swing.JLabel();
        iconoDesenfoque = new javax.swing.JLabel();
        desenfoque = new javax.swing.JSlider();
        separadorBlanco3 = new javax.swing.JLabel();
        iconoPerfilado = new javax.swing.JLabel();
        perfilado = new javax.swing.JSlider();
        separador4 = new javax.swing.JToolBar.Separator();
        barraMenu = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        botonMenuNuevo = new javax.swing.JMenuItem();
        botonMenuAbrir = new javax.swing.JMenuItem();
        botonMenuGuardar = new javax.swing.JMenuItem();
        menuImagen = new javax.swing.JMenu();
        botonReescalar = new javax.swing.JMenuItem();
        botonConvolucion = new javax.swing.JMenuItem();

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

        separador1.setBackground(new java.awt.Color(0, 0, 0));
        barraHerramientas.add(separador1);

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

        separador2.setBackground(new java.awt.Color(0, 0, 0));
        barraHerramientas.add(separador2);

        grosor.setMaximum(25);
        grosor.setMinimum(1);
        grosor.setValue(1);
        grosor.setMaximumSize(new java.awt.Dimension(50, 16));
        grosor.setPreferredSize(new java.awt.Dimension(50, 16));
        barraHerramientas.add(grosor);

        getContentPane().add(barraHerramientas, java.awt.BorderLayout.NORTH);

        escritorio.setPreferredSize(new java.awt.Dimension(600, 300));

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 613, Short.MAX_VALUE)
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );

        getContentPane().add(escritorio, java.awt.BorderLayout.CENTER);

        contenedor.setLayout(new java.awt.BorderLayout());

        barraEstado.setBackground(new java.awt.Color(204, 204, 204));
        barraEstado.setText("Barra de Estado");
        barraEstado.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        barraEstado.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        contenedor.add(barraEstado, java.awt.BorderLayout.SOUTH);

        toolBarImagenes.setBackground(new java.awt.Color(204, 204, 204));
        toolBarImagenes.setRollover(true);
        toolBarImagenes.setMaximumSize(new java.awt.Dimension(332, 30));
        toolBarImagenes.setPreferredSize(new java.awt.Dimension(332, 30));

        iconoBrillo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/brillo.png"))); // NOI18N
        toolBarImagenes.add(iconoBrillo);

        brillo.setMaximum(255);
        brillo.setMinimum(-255);
        brillo.setToolTipText("");
        brillo.setValue(0);
        brillo.setMaximumSize(new java.awt.Dimension(50, 16));
        brillo.setPreferredSize(new java.awt.Dimension(50, 16));
        toolBarImagenes.add(brillo);

        separadorBlanco1.setMaximumSize(new java.awt.Dimension(10, 10));
        separadorBlanco1.setMinimumSize(new java.awt.Dimension(10, 10));
        separadorBlanco1.setPreferredSize(new java.awt.Dimension(10, 10));
        toolBarImagenes.add(separadorBlanco1);

        iconoContraste.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/contraste.png"))); // NOI18N
        toolBarImagenes.add(iconoContraste);

        contraste.setMaximum(20);
        contraste.setPaintLabels(true);
        contraste.setValue(10);
        contraste.setMaximumSize(new java.awt.Dimension(50, 16));
        contraste.setPreferredSize(new java.awt.Dimension(50, 16));
        toolBarImagenes.add(contraste);

        separador3.setToolTipText("");
        toolBarImagenes.add(separador3);

        listaFiltros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Media", "Binomial", "Enfoque", "Relieve", "Laplaciano", "Media5x5", "Media7x7" }));
        listaFiltros.setMaximumSize(new java.awt.Dimension(100, 25));
        listaFiltros.setMinimumSize(new java.awt.Dimension(100, 25));
        listaFiltros.setPreferredSize(new java.awt.Dimension(100, 25));
        toolBarImagenes.add(listaFiltros);

        separadorBlanco2.setMaximumSize(new java.awt.Dimension(10, 10));
        separadorBlanco2.setMinimumSize(new java.awt.Dimension(10, 10));
        separadorBlanco2.setPreferredSize(new java.awt.Dimension(10, 10));
        toolBarImagenes.add(separadorBlanco2);

        iconoDesenfoque.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/emborronar.png"))); // NOI18N
        toolBarImagenes.add(iconoDesenfoque);

        desenfoque.setMaximum(25);
        desenfoque.setMinimum(1);
        desenfoque.setMinorTickSpacing(2);
        desenfoque.setValue(1);
        desenfoque.setMaximumSize(new java.awt.Dimension(50, 16));
        desenfoque.setPreferredSize(new java.awt.Dimension(50, 16));
        toolBarImagenes.add(desenfoque);

        separadorBlanco3.setMaximumSize(new java.awt.Dimension(10, 10));
        separadorBlanco3.setMinimumSize(new java.awt.Dimension(10, 10));
        separadorBlanco3.setPreferredSize(new java.awt.Dimension(10, 10));
        toolBarImagenes.add(separadorBlanco3);

        iconoPerfilado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/perfilar.png"))); // NOI18N
        toolBarImagenes.add(iconoPerfilado);

        perfilado.setMaximum(15);
        perfilado.setValue(0);
        perfilado.setMaximumSize(new java.awt.Dimension(50, 16));
        perfilado.setPreferredSize(new java.awt.Dimension(50, 16));
        toolBarImagenes.add(perfilado);

        separador4.setBackground(new java.awt.Color(0, 0, 0));
        toolBarImagenes.add(separador4);

        contenedor.add(toolBarImagenes, java.awt.BorderLayout.NORTH);

        getContentPane().add(contenedor, java.awt.BorderLayout.SOUTH);

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

        menuImagen.setText("Imagen");

        botonReescalar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        botonReescalar.setText("Reescalar");
        botonReescalar.setToolTipText("");
        menuImagen.add(botonReescalar);

        botonConvolucion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        botonConvolucion.setText("Convolucion");
        menuImagen.add(botonConvolucion);

        barraMenu.add(menuImagen);

        setJMenuBar(barraMenu);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton alisar;
    private javax.swing.JLabel barraEstado;
    private javax.swing.JToolBar barraHerramientas;
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JButton botonAbrir;
    private javax.swing.JMenuItem botonConvolucion;
    private javax.swing.JButton botonGuardar;
    private javax.swing.JMenuItem botonMenuAbrir;
    private javax.swing.JMenuItem botonMenuGuardar;
    private javax.swing.JMenuItem botonMenuNuevo;
    private javax.swing.JButton botonNuevo;
    private javax.swing.JMenuItem botonReescalar;
    private javax.swing.JSlider brillo;
    private javax.swing.JPanel contenedor;
    private javax.swing.JSlider contraste;
    private javax.swing.JSlider desenfoque;
    private javax.swing.JToggleButton dialogoColor;
    private javax.swing.JToggleButton eliminar;
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JToggleButton fijar;
    private javax.swing.JSlider grosor;
    private javax.swing.ButtonGroup grupoModoDibujo;
    private javax.swing.JLabel iconoBrillo;
    private javax.swing.JLabel iconoContraste;
    private javax.swing.JLabel iconoDesenfoque;
    private javax.swing.JLabel iconoPerfilado;
    private javax.swing.JComboBox<String> listaFiltros;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenu menuImagen;
    private javax.swing.JToggleButton modoCurva;
    private javax.swing.JToggleButton modoElipse;
    private javax.swing.JToggleButton modoLinea;
    private javax.swing.JToggleButton modoRectangulo;
    private javax.swing.JPanel panelColor;
    private javax.swing.JSlider perfilado;
    private javax.swing.JToggleButton relleno;
    private javax.swing.JToggleButton seleccionar;
    private javax.swing.JToolBar.Separator separador1;
    private javax.swing.JToolBar.Separator separador2;
    private javax.swing.JToolBar.Separator separador3;
    private javax.swing.JToolBar.Separator separador4;
    private javax.swing.JLabel separadorBlanco1;
    private javax.swing.JLabel separadorBlanco2;
    private javax.swing.JLabel separadorBlanco3;
    private javax.swing.JToolBar toolBarImagenes;
    private javax.swing.JToggleButton transparencia;
    // End of variables declaration//GEN-END:variables
}
