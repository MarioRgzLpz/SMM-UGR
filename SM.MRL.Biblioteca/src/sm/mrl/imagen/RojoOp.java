/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sm.mrl.imagen;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import sm.image.BufferedImageOpAdapter;

/**
 * Clase que aplica un filtro para destacar píxeles dominados por el componente
 * rojo. Si un píxel no cumple con el umbral, se convierte a escala de grises.
 *
 * @author mariorl
 */
public class RojoOp extends BufferedImageOpAdapter {

    private int umbral;

    /**
     * Constructor de la clase RojoOp.
     *
     * @param umbral Diferencia mínima requerida entre el componente rojo y la
     * suma de los componentes verde y azul.
     */
    public RojoOp(int umbral) {
        this.umbral = umbral;
    }

    /**
     * Aplica el filtro para resaltar píxeles dominados por el componente rojo
     * en la imagen de origen. Los píxeles que no cumplen el umbral se
     * transforman a escala de grises.
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
        int[] pixelComp = new int[srcRaster.getNumBands()];

        for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) {
                srcRaster.getPixel(x, y, pixelComp);
                int valorR = pixelComp[0], valorG = pixelComp[1], valorB = pixelComp[2];
                if (valorR - (valorG + valorB) < umbral) {
                    int media = (valorR + valorG + valorB) / 3;
                    for (int i = 0; i < 3; i++) {
                        pixelComp[i] = media;
                    }
                }
                destRaster.setPixel(x, y, pixelComp);
            }

        }
        return dest;
    }

}
