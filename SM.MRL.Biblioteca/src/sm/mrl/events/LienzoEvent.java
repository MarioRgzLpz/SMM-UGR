/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sm.mrl.events;

import java.util.EventObject;
import sm.mrl.graficos.JFigura;

/**
 *
 * @author mariorl
 */
public class LienzoEvent extends EventObject {

    private JFigura forma;

    public LienzoEvent(Object source, JFigura forma) {
        super(source);
        this.forma = forma;
    }

    public JFigura getForma() {
        return forma;
    }
}
