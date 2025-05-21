/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sm.mrl.imagen;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.awt.image.WritableRaster;
import sm.image.BufferedImageOpAdapter;

/**
 * Filtro para convertir imágenes en estilo retro 8-bit, simulando pixel-art.
 * Resalta los bordes, reduce la resolución y posteriza.
 *
 * @author mariorl
 */
public class Retro8BitOp extends BufferedImageOpAdapter {

    private final int reduccionResolucion;
    private final int niveles;
    private final EdgeDetectorType edgeDetectorType;

    public enum EdgeDetectorType {
        SOBEL,
        PREWITT,
        ROBERTS,
        FREI_CHEN,
        LAPLACIANO,
        NONE
    }

    /**
     * Constructor de la clase Retro8BitOp.
     *
     * @param reduccionResolucion Tamaño del bloque de píxeles (por ejemplo,
     * 8x8).
     * @param niveles Número de niveles de color por canal (por ejemplo, 4, 8,
     * 16).
     * @param edgeDetectorType Tipo de detección de bordes.
     */
    public Retro8BitOp(int reduccionResolucion, int niveles, EdgeDetectorType edgeDetectorType) {
        this.reduccionResolucion = reduccionResolucion;
        this.niveles = niveles;
        this.edgeDetectorType = edgeDetectorType;
    }

    /**
     * Aplica el filtro retro 8-bit a una imagen.
     *
     * @param src Imagen de origen. No puede ser {@code null}.
     * @param dest Imagen de destino. Si es {@code null}, se crea una nueva.
     * @return Imagen procesada en estilo retro 8-bit con bordes resaltados.
     */
    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dest) {
        if (src == null) {
            throw new NullPointerException("src image is null");
        }
        if (dest == null) {
            dest = createCompatibleDestImage(src, null);
        }

        BufferedImage pixelated = pixelar(src);
        PosterizarOp posterizarOp = new PosterizarOp(niveles);
        BufferedImage posterized = posterizarOp.filter(pixelated, null);
        BufferedImage grayscalePixelated = toGrayscale(posterized);
        BufferedImage edges = detectarBordes(grayscalePixelated);

        WritableRaster pixelRaster = posterized.getRaster();
        WritableRaster edgeRaster = edges.getRaster();
        if (edgeDetectorType == EdgeDetectorType.NONE) {
            dest.getRaster().setPixels(0, 0, posterized.getWidth(), posterized.getHeight(),
                    posterized.getRaster().getPixels(0, 0, posterized.getWidth(), posterized.getHeight(), (int[]) null));
            return dest;
        }
        WritableRaster destRaster = dest.getRaster();

        int bands = pixelRaster.getNumBands();
        int edgeVal;

        for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) {
                edgeVal = edgeRaster.getSample(x, y, 0);

                if (edgeVal > 128) {
                    for (int b = 0; b < bands; b++) {
                        destRaster.setSample(x, y, b, 0);
                    }
                } else {
                    for (int b = 0; b < bands; b++) {
                        int sample = pixelRaster.getSample(x, y, b);
                        destRaster.setSample(x, y, b, sample);
                    }
                }
            }
        }

        return dest;
    }

    /**
     * Modifica la imagen a escala de grises.
     */
    private BufferedImage toGrayscale(BufferedImage src) {
        BufferedImage gray = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster srcRaster = src.getRaster();
        WritableRaster grayRaster = gray.getRaster();
        int[] pixel = new int[srcRaster.getNumBands()];

        for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) {
                srcRaster.getPixel(x, y, pixel);
                int r = pixel[0];
                int g = pixel[1];
                int b = pixel[2];
                int lum = (int) (0.299 * r + 0.587 * g + 0.114 * b);
                grayRaster.setSample(x, y, 0, lum);
            }
        }
        return gray;
    }

    /**
     * Reduce la resolución de la imagen.
     */
    private BufferedImage pixelar(BufferedImage src) {
        BufferedImage result = createCompatibleDestImage(src, null);
        WritableRaster srcRaster = src.getRaster();
        WritableRaster destRaster = result.getRaster();

        int width = src.getWidth();
        int height = src.getHeight();
        int[] pixel = new int[srcRaster.getNumBands()];

        for (int x = 0; x < width; x += reduccionResolucion) {
            for (int y = 0; y < height; y += reduccionResolucion) {
                int[] avgColor = new int[srcRaster.getNumBands()];
                int count = 0;
                for (int dx = 0; dx < reduccionResolucion && x + dx < width; dx++) {
                    for (int dy = 0; dy < reduccionResolucion && y + dy < height; dy++) {
                        srcRaster.getPixel(x + dx, y + dy, pixel);
                        for (int band = 0; band < avgColor.length; band++) {
                            avgColor[band] += pixel[band];
                        }
                        count++;
                    }
                }
                for (int band = 0; band < avgColor.length; band++) {
                    avgColor[band] /= count;
                }
                for (int dx = 0; dx < reduccionResolucion && x + dx < width; dx++) {
                    for (int dy = 0; dy < reduccionResolucion && y + dy < height; dy++) {
                        destRaster.setPixel(x + dx, y + dy, avgColor);
                    }
                }
            }
        }

        return result;
    }

    /**
     * Detecta los bordes de una imagen usando distintos algoritmos.
     */
    private BufferedImage detectarBordes(BufferedImage src) {
        float[] kernelX;
        float[] kernelY;
        int kernelWidth;
        int kernelHeight;

        switch (edgeDetectorType) {
            case NONE:
                return src;
            case PREWITT:
                kernelX = new float[]{
                    -1, 0, 1,
                    -1, 0, 1,
                    -1, 0, 1
                };
                kernelY = new float[]{
                    -1, -1, -1,
                    0, 0, 0,
                    1, 1, 1
                };
                kernelWidth = 3;
                kernelHeight = 3;
                break;
            case ROBERTS:
                kernelX = new float[]{
                    1, 0,
                    0, -1
                };
                kernelY = new float[]{
                    0, 1,
                    -1, 0
                };
                kernelWidth = 2;
                kernelHeight = 2;
                break;
            case FREI_CHEN:
                kernelX = new float[]{
                    -1, 0, 1,
                    -(float) Math.sqrt(2), 0, (float) Math.sqrt(2),
                    -1, 0, 1
                };
                kernelY = new float[]{
                    -1, -(float) Math.sqrt(2), -1,
                    0, 0, 0,
                    1, (float) Math.sqrt(2), 1
                };
                kernelWidth = 3;
                kernelHeight = 3;
                break;

            case LAPLACIANO:
                kernelX = new float[]{
                    0, 1, 0,
                    1, -4, 1,
                    0, 1, 0
                };
                kernelY = kernelX;
                kernelWidth = 3;
                kernelHeight = 3;
                break;

            case SOBEL:
            default:
                kernelX = new float[]{
                    -1, 0, 1,
                    -2, 0, 2,
                    -1, 0, 1
                };
                kernelY = new float[]{
                    -1, -2, -1,
                    0, 0, 0,
                    1, 2, 1
                };
                kernelWidth = 3;
                kernelHeight = 3;
                break;
        }

        Kernel kx = new Kernel(kernelWidth, kernelHeight, kernelX);
        Kernel ky = new Kernel(kernelWidth, kernelHeight, kernelY);

        ConvolveOp opX = new ConvolveOp(kx, ConvolveOp.EDGE_NO_OP, null);
        ConvolveOp opY = new ConvolveOp(ky, ConvolveOp.EDGE_NO_OP, null);

        BufferedImage gradX = opX.filter(src, null);
        BufferedImage gradY = opY.filter(src, null);

        WritableRaster rasterX = gradX.getRaster();
        WritableRaster rasterY = gradY.getRaster();

        BufferedImage edges = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster edgesRaster = edges.getRaster();

        for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) {
                int gx = rasterX.getSample(x, y, 0);
                int gy = rasterY.getSample(x, y, 0);
                int g = (int) Math.min(255, Math.hypot(gx, gy));
                edgesRaster.setSample(x, y, 0, g);
            }
        }

        return edges;
    }
}
