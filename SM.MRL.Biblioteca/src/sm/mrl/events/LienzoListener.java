/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sm.mrl.events;

import java.util.EventListener;

/**
 * Interfaz para escuchar eventos relacionados con el lienzo.
 * Define métodos para manejar eventos como la adición o selección de figuras en el lienzo.
 * 
 * @author mariorl
 */
public interface LienzoListener extends EventListener {

    /**
     * Método llamado cuando se añade una figura al lienzo.
     *
     * @param evt Evento de tipo LienzoEvent que contiene información de la figura añadida.
     */
    public void shapeAdded(LienzoEvent evt);

    /**
     * Método llamado cuando se selecciona una figura en el lienzo.
     *
     * @param evt Evento de tipo LienzoEvent que contiene información de la figura seleccionada.
     */
    public void shapeSelected(LienzoEvent evt);
}

