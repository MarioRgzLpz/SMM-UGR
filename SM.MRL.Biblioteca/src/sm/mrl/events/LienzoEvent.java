/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sm.mrl.events;

import java.util.EventObject;
import sm.mrl.graficos.JFigura;

import java.util.EventObject;

/**
 * Clase que representa un evento relacionado con el lienzo.
 * Contiene información sobre la figura asociada al evento.
 * 
 * @author mariorl
 */
public class LienzoEvent extends EventObject {

    private JFigura forma;

    /**
     * Constructor para crear un evento de lienzo.
     *
     * @param source Objeto que genera el evento.
     * @param forma Figura de tipo JFigura asociada al evento.
     */
    public LienzoEvent(Object source, JFigura forma) {
        super(source);
        this.forma = forma;
    }

    /**
     * Obtiene la figura asociada al evento.
     *
     * @return Figura de tipo JFigura asociada al evento.
     */
    public JFigura getForma() {
        return forma;
    }
}

