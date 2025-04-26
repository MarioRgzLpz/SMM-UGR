/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package sm.mrl.iu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import sm.mrl.events.LienzoEvent;
import sm.mrl.events.LienzoListener;
import sm.mrl.graficos.JFigura;
import sm.mrl.graficos.JRectangularShape;
import sm.mrl.graficos.MiCurva;
import sm.mrl.graficos.MiLinea;
import sm.mrl.graficos.MiRectangulo;
import sm.mrl.graficos.MiElipse;

/**
 * Clase que representa un lienzo bidimensional donde se pueden dibujar figuras
 * geométricas y realizar operaciones de dibujo como seleccionar, mover o crear
 * nuevas figuras. Soporta diferentes modos de pintura y características como
 * relleno, transparencia, y suavizado.
 *
 * Incluye funcionalidad para gestionar eventos del ratón relacionados con el
 * dibujo y la manipulación de figuras.
 *
 * @author mariorl
 */
public class Lienzo2D extends javax.swing.JPanel {

    ArrayList<LienzoListener> lienzoEventListeners = new ArrayList();
    private int xMargin, yMargin, alturaBarraEstado;
    private Point p_inicial = new Point(-100, -100);
    private Point2D deltaClick = null;
    private Boolean primerPaso = true;
    private ModoPintura modoPintura = ModoPintura.LINEA;
    private JFigura forma = new MiLinea();
    private JFigura forma2 = new MiLinea();
    private List<JFigura> vShape = new ArrayList<>();
    private Color color = Color.black;
    private Boolean relleno = false;
    private Boolean seleccionar = false;
    private Boolean transparencia = false;
    private Boolean alisar = false;
    private Boolean eliminar = false;
    private Boolean fijar = false;
    private BasicStroke trazo = new BasicStroke();
    private Point coordenadasRaton = new Point(0, 0);
    private BufferedImage imagen;
    private File sonidoFijar, sonidoEliminar;

    public enum ModoPintura {
        LINEA, RECTANGULO, ELIPSE, CURVA
    }

    public void addLienzoListener(LienzoListener listener) {
        if (listener != null) {
            lienzoEventListeners.add(listener);
        }
    }

    private void notifyShapeAddedEvent(LienzoEvent evt) {
        if (!lienzoEventListeners.isEmpty()) {
            for (LienzoListener listener : lienzoEventListeners) {
                listener.shapeAdded(evt);
            }
        }
    }

    private void notifyShapeSelectedEvent(LienzoEvent evt) {
        if (!lienzoEventListeners.isEmpty()) {
            for (LienzoListener listener : lienzoEventListeners) {
                listener.shapeSelected(evt);
            }
        }
    }

    /**
     * Establece el archivo de sonido que se reproducirá al fijar un elemento.
     *
     * @param sonidoFijar Archivo de sonido para el evento de fijar.
     */
    public void setSonidoFijar(File sonidoFijar) {
        this.sonidoFijar = sonidoFijar;
    }

    /**
     * Establece el archivo de sonido que se reproducirá al eliminar un
     * elemento.
     *
     * @param sonidoEliminar Archivo de sonido para el evento de eliminar.
     */
    public void setSonidoEliminar(File sonidoEliminar) {
        this.sonidoEliminar = sonidoEliminar;
    }

    /**
     * Configura el margen horizontal utilizado en la imagen.
     *
     * @param xMargin Valor del margen horizontal.
     */
    public void setXMargin(int xMargin) {
        this.xMargin = xMargin;
    }

    /**
     * Configura el margen vertical utilizado en la imagen.
     *
     * @param yMargin Valor del margen vertical.
     */
    public void setYMargin(int yMargin) {
        this.yMargin = yMargin;
    }

    /**
     * Configura la altura de la barra de estado.
     *
     * @param altura Altura de la barra de estado.
     */
    public void setAlturaBarraEstado(int altura) {
        this.alturaBarraEstado = altura;
    }

    /**
     * Obtiene la imagen actual utilizada.
     *
     * @return {@link BufferedImage} Imagen actual.
     */
    public BufferedImage getImagen() {
        ColorModel cm = imagen.getColorModel();
        WritableRaster raster = imagen.copyData(null);
        boolean alfaPre = imagen.isAlphaPremultiplied();
        return new BufferedImage(cm, raster, alfaPre, null);
    }

    /**
     * Establece una nueva imagen para ser utilizada.
     *
     * @param imagen Nueva imagen a configurar.
     */
    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
    }

    /**
     * Obtiene el estado actual de la funcionalidad de eliminación.
     *
     * @return {@link Boolean} {@code true} si la funcionalidad de eliminar está
     * activa, {@code false} en caso contrario.
     */
    public Boolean getEliminar() {
        return eliminar;
    }

    /**
     * Activa o desactiva la funcionalidad de eliminación.
     *
     * @param eliminar {@code true} para activar la funcionalidad de eliminar,
     * {@code false} para desactivarla.
     */
    public void setEliminar(Boolean eliminar) {
        this.eliminar = eliminar;
    }

    /**
     * Obtiene el estado actual de la funcionalidad de fijar.
     *
     * @return {@link Boolean} {@code true} si la funcionalidad de fijar está
     * activa, {@code false} en caso contrario.
     */
    public Boolean getFijar() {
        return fijar;
    }

    /**
     * Activa o desactiva la funcionalidad de fijar.
     *
     * @param fijar {@code true} para activar la funcionalidad de fijar,
     * {@code false} para desactivarla.
     */
    public void setFijar(Boolean fijar) {
        this.fijar = fijar;
    }

    /**
     * Obtiene el estado del relleno de las figuras.
     *
     * @return true si las figuras deben dibujarse rellenas, false en caso
     * contrario
     */
    public Boolean getRelleno() {
        return relleno;
    }

    /**
     * Establece el estado del relleno de las figuras.
     *
     * @param relleno true para dibujar figuras rellenas, false para figuras sin
     * relleno
     */
    public void setRelleno(Boolean relleno) {
        this.relleno = relleno;
    }

    /**
     * Obtiene el estado de selección de figuras.
     *
     * @return true si está activada la selección de figuras, false en caso
     * contrario
     */
    public Boolean getSeleccionar() {
        return seleccionar;
    }

    /**
     * Establece el estado de selección de figuras.
     *
     * @param seleccionar true para activar la selección, false para
     * desactivarla
     */
    public void setSeleccionar(Boolean seleccionar) {
        this.seleccionar = seleccionar;
    }

    /**
     * Obtiene el estado de transparencia en las figuras.
     *
     * @return true si la transparencia está activada, false en caso contrario
     */
    public Boolean getTransparencia() {
        return transparencia;
    }

    /**
     * Establece el estado de transparencia en las figuras.
     *
     * @param transparencia true para activar la transparencia, false para
     * desactivarla
     */
    public void setTransparencia(Boolean transparencia) {
        this.transparencia = transparencia;
    }

    /**
     * Obtiene el estado de alisado (suavizado de bordes) en las figuras.
     *
     * @return true si el alisado está activado, false en caso contrario
     */
    public Boolean getAlisar() {
        return alisar;
    }

    /**
     * Establece el estado de alisado (suavizado de bordes) en las figuras.
     *
     * @param alisar true para activar el alisado, false para desactivarlo
     */
    public void setAlisar(Boolean alisar) {
        this.alisar = alisar;
    }

    /**
     * Obtiene el modo de pintura actual.
     *
     * @return el modo de pintura actual (LINEA, RECTANGULO, ELIPSE, CURVA)
     */
    public ModoPintura getModoPintura() {
        return modoPintura;
    }

    /**
     * Establece el modo de pintura.
     *
     * @param modoPintura el modo de pintura deseado (LINEA, RECTANGULO, ELIPSE,
     * CURVA)
     */
    public void setModoPintura(ModoPintura modoPintura) {
        this.modoPintura = modoPintura;
    }

    /**
     * Obtiene el color actual.
     *
     * @return el color actual
     */
    public Color getColor() {
        return color;
    }

    /**
     * Establece el color de las figuras.
     *
     * @param color el color a usar para las figuras
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Obtiene el trazo actual utilizado para dibujar figuras.
     *
     * @return el trazo (grosor y estilo) de las figuras
     */
    public BasicStroke getTrazo() {
        return trazo;
    }

    /**
     * Establece el grosor del trazo para dibujar figuras.
     *
     * @param grosor el grosor del trazo en píxeles
     */
    public void setTrazo(int grosor) {
        this.trazo = new BasicStroke(grosor);
    }

    /**
     * Obtiene las coordenadas actuales del ratón.
     *
     * @return un objeto Point que representa las coordenadas actuales del ratón
     */
    public Point getCoordenadasRaton() {
        return coordenadasRaton;
    }
    
    public void setForma(JFigura forma){
        this.forma = forma;
    }

    public JFigura getForma(){
        return forma;
    }
    /**
     * Constructor de la clase Lienzo2D. Inicializa los componentes gráficos del
     * lienzo, configurando el entorno para el dibujo de figuras.
     */
    public Lienzo2D() {
        initComponents();
    }

    /**
     * Sobreescribe el método paint para dibujar las figuras almacenadas en el
     * lienzo. Utiliza un objeto Graphics2D para renderizar cada figura en
     * pantalla.
     *
     * @param g el objeto Graphics proporcionado por el sistema para el dibujo
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        if (imagen != null) {
            g2d.drawImage(imagen, 0, 0, this);
        }
        recortar(g2d);
        for (JFigura figura : vShape) {
            figura.draw(g2d);
        }
    }

    /**
     * Busca y devuelve la figura que contiene el punto especificado. La
     * búsqueda se realiza desde la última figura dibujada hacia la primera para
     * priorizar las figuras en el frente.
     *
     * @param p el punto a verificar, representado como un objeto Point2D
     * @return la figura que contiene el punto, o null si ninguna figura lo
     * contiene
     */
    private JFigura figuraSeleccionada(Point2D p) {
        for (int i = vShape.size() - 1; i >= 0; i--) {
            JFigura s = vShape.get(i);
            s.setSeleccionada(false);
        }
        for (int i = vShape.size() - 1; i >= 0; i--) {
            JFigura s = vShape.get(i);
            if (s.contains(p)) {
                s.setSeleccionada(getSeleccionar());
                return s;
            }
        }
        return null;
    }

    /**
     * Obtiene una imagen pintada basada en las configuraciones actuales,
     * incluyendo las figuras y recortes aplicados al gráfico.
     *
     * @return {@link BufferedImage} La imagen resultante pintada.
     */
    public BufferedImage getPaintedImage() {
        BufferedImage imgout = new BufferedImage(imagen.getWidth(), imagen.getHeight(), imagen.getType());
        Graphics2D g2dImagen = imgout.createGraphics();

        recortar(g2dImagen);
        if (imagen != null) {
            g2dImagen.drawImage(imagen, 0, 0, this);
        }
        for (JFigura figura : vShape) {
            figura.draw(g2dImagen);
        }
        return imgout;
    }

    /**
     * Actualiza los atributos de la figura seleccionada basándose en las
     * propiedades actuales (como color, relleno, transparencia, etc.).
     */
    public void actualizarAtributosFormaSeleccionada() {
        if (forma != null && seleccionar) {
            forma.setSeleccionada(seleccionar);
            forma.setColor(color);
            forma.setRelleno(relleno);
            forma.setTransparencia(transparencia);
            forma.setAlisar(alisar);
            forma.setTrazo(trazo);
        }
        this.repaint();
    }

    /**
     * Reproduce un archivo de sonido especificado.
     *
     * @param f Archivo de audio que se desea reproducir.
     */
    private void play(File f) {
        try {
            Clip sound = AudioSystem.getClip();
            sound.open(AudioSystem.getAudioInputStream(f));
            sound.start();
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    /**
     * Aplica un recorte al área de dibujo del objeto {@link Graphics2D}.
     *
     * @param g2d El objeto {@link Graphics2D} en el que se aplica el recorte.
     */
    private void recortar(Graphics2D g2d) {
        int rectWidth = imagen.getWidth() - 2 * xMargin;
        int rectHeight = imagen.getHeight() - 2 * yMargin - alturaBarraEstado;
        Rectangle recorte = new Rectangle(xMargin, yMargin, rectWidth, rectHeight);
        g2d.clip(recorte);
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
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
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
    /**
     * Maneja el evento de pulsación del ratón. Determina si se selecciona una
     * figura existente o inicia la creación de una nueva figura según el modo
     * actual.
     *
     * @param evt el evento MouseEvent que se genera cuando se presiona el botón
     * del ratón
     */
    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        if (seleccionar) {
            forma = figuraSeleccionada(evt.getPoint());
            if (forma != null) {
                notifyShapeSelectedEvent(new LienzoEvent(this, forma));
                deltaClick = new Point2D.Double(evt.getX() - forma.getX(), evt.getY() - forma.getY());
            }
        } else {
            if (!eliminar && !fijar) {
                p_inicial = new Point(evt.getPoint());
                switch (modoPintura) {
                    case LINEA:
                        forma = new MiLinea(p_inicial, p_inicial);
                        break;
                    case RECTANGULO:
                        forma = new MiRectangulo();
                        break;
                    case ELIPSE:
                        forma = new MiElipse();
                        break;
                    case CURVA:
                        if (primerPaso) {
                            forma = new MiCurva(evt.getPoint(), evt.getPoint(), evt.getPoint());
                        } else {
                            ((MiCurva) forma).setControl(evt.getPoint());
                        }
                        break;
                }
                forma.setColor(color);
                forma.setRelleno(relleno);
                forma.setTransparencia(transparencia);
                forma.setAlisar(alisar);
                forma.setTrazo(trazo);
                if (primerPaso) {
                    vShape.add(forma);
                    notifyShapeAddedEvent(new LienzoEvent(this, forma));
                }
            }

        }

    }//GEN-LAST:event_formMousePressed
    /**
     * Maneja el evento de arrastre del ratón. Actualiza la posición o las
     * dimensiones de la figura que se está dibujando o moviendo.
     *
     * @param evt el evento MouseEvent que se genera cuando se arrastra el ratón
     */
    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        if (seleccionar) {
            if (forma != null && deltaClick != null) {
                Point2D newPoint = new Point2D.Double(evt.getX() - deltaClick.getX(), evt.getY() - deltaClick.getY());
                forma.setLocation(newPoint);
            }
        } else {
            if (!eliminar && !fijar) {
                if (forma instanceof MiLinea) {
                    ((MiLinea) forma).setLine(p_inicial, evt.getPoint());
                } else if (forma instanceof JRectangularShape) {
                    ((JRectangularShape) forma).setFrameFromDiagonal(p_inicial, evt.getPoint());
                } else if (forma instanceof MiCurva) {
                    if (primerPaso) {
                        ((MiCurva) forma).setEnd(evt.getPoint());
                    } else {
                        ((MiCurva) forma).setControl(evt.getPoint());
                    }
                }
            }
        }
        this.repaint();
    }//GEN-LAST:event_formMouseDragged
    /**
     * Maneja el evento de liberación del ratón. Finaliza el dibujo de la figura
     * o actualiza el estado para la creación de una curva (si aplica).
     *
     * @param evt el evento MouseEvent que se genera cuando se libera el botón
     * del ratón
     */
    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        if (forma instanceof MiCurva && !seleccionar) {
            if (primerPaso) {
                primerPaso = false;
            } else {
                primerPaso = true;
            }
        }
        this.repaint();
    }//GEN-LAST:event_formMouseReleased
    /**
     * Maneja el evento de movimiento del ratón. Actualiza las coordenadas
     * almacenadas del puntero del ratón como referencia.
     *
     * @param evt el evento MouseEvent que se genera cuando el ratón se mueve
     */
    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        coordenadasRaton.setLocation(evt.getX(), evt.getY());
        forma2 = figuraSeleccionada(evt.getPoint());
        if (forma2 != null) {
            if (eliminar) {
                this.play(sonidoEliminar);
                vShape.remove(forma2);
                this.repaint();
            } else if (fijar) {
                Graphics2D g2dImagen = imagen.createGraphics();
                this.play(sonidoFijar);
                recortar(g2dImagen);
                forma2.draw(g2dImagen);
                g2dImagen.dispose();
                vShape.remove(forma2);
                this.repaint();
            }
        }
    }//GEN-LAST:event_formMouseMoved


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
