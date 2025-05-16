/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sm.mrl.imagen;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import sm.image.BufferedImageOpAdapter;

/**
 * Clase para aplicar una transformación basada en el modelo HSB (Tono,
 * Saturación, Brillo) a una imagen. Modifica el tono de los píxeles dentro de
 * un rango definido y aplica un desplazamiento.
 *
 * @author mariorl
 */
public class HSBOp extends BufferedImageOpAdapter {

    private final int hReferencia;
    private final int umbral;
    private final int desplazamiento;

    /**
     * Constructor de la clase HSBOp.
     *
     * @param hReferencia Tono de referencia (0-360).
     * @param umbral Rango de tolerancia para seleccionar píxeles (0-360).
     * @param desplazamiento Desplazamiento a aplicar al tono seleccionado
     * (0-360).
     */
    public HSBOp(int hReferencia, int umbral, int desplazamiento) {
        this.hReferencia = hReferencia;
        this.umbral = umbral;
        this.desplazamiento = desplazamiento;
    }

    /**
     * Aplica la operación HSB a la imagen de origen y almacena el resultado en
     * la imagen de destino.
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

                float[] hsb = Color.RGBtoHSB(pixelComp[0], pixelComp[1], pixelComp[2], null);
                int hPixel = (int) (hsb[0] * 360);
                int distancia = Math.min(Math.abs(hPixel - hReferencia), 360 - Math.abs(hPixel - hReferencia));

                if (distancia <= umbral) {
                    int nuevoH = (hPixel + desplazamiento) % 360;
                    hsb[0] = nuevoH / 360.0f;
                }
                int rgb = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
                pixelComp[0] = (rgb >> 16) & 0xFF;
                pixelComp[1] = (rgb >> 8) & 0xFF;
                pixelComp[2] = rgb & 0xFF;
                destRaster.setPixel(x, y, pixelComp);
            }
        }

        return dest;
    }
}
