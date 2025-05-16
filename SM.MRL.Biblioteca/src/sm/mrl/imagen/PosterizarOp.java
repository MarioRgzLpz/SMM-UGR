/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sm.mrl.imagen;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import sm.image.BufferedImageOpAdapter;

/**
 * Clase que aplica un efecto de posterización a una imagen. Reduce los niveles
 * de color para cada banda de la imagen.
 *
 * @author mariorl
 */
public class PosterizarOp extends BufferedImageOpAdapter {

    private int niveles;
    private int k;

    /**
     * Constructor de la clase PosterizarOp.
     *
     * @param niveles Número de niveles de color a mantener (mayor a 0).
     */
    public PosterizarOp(int niveles) {
        this.niveles = niveles;
        this.k = 256 / niveles;
    }

    /**
     * Aplica el efecto de posterización a una imagen de origen y almacena el
     * resultado en una imagen de destino.
     *
     * @param src Imagen de origen. No puede ser {@code null}.
     * @param dest Imagen de destino. Si es {@code null}, se crea una nueva.
     * @return Imagen procesada.
     * @throws NullPointerException Si la imagen de origen es {@code null}.
     */
    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dest) {
        if (src == null) {
            throw new NullPointerException("src image is null");
        }
        if (dest == null) {
            dest = createCompatibleDestImage(src, null);
        }
        WritableRaster srcRaster = src.getRaster();
        WritableRaster destRaster = dest.getRaster();
        int sample;

        for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) {
                for (int band = 0; band < srcRaster.getNumBands(); band++) {
                    sample = srcRaster.getSample(x, y, band);
                    int suelo = sample / k;
                    sample = k * suelo;
                    destRaster.setSample(x, y, band, sample);
                }
            }
        }
        return dest;
    }
}
