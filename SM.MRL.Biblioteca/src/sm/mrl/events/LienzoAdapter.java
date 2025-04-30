/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sm.mrl.events;

/**
 * Clase adaptadora que implementa la interfaz LienzoListener.
 * Proporciona implementaciones vacías para los métodos de la interfaz,
 * permitiendo a las clases derivadas sobrescribir solo los métodos que necesitan.
 * 
 * @author mariorl
 */
public class LienzoAdapter implements LienzoListener {

    /**
     * Método llamado cuando se añade una figura al lienzo.
     * La implementación por defecto no realiza ninguna acción.
     *
     * @param evt Evento de tipo LienzoEvent que contiene información de la figura añadida.
     */
    public void shapeAdded(LienzoEvent evt) {
    }

    /**
     * Método llamado cuando se selecciona una figura en el lienzo.
     * La implementación por defecto no realiza ninguna acción.
     *
     * @param evt Evento de tipo LienzoEvent que contiene información de la figura seleccionada.
     */
    public void shapeSelected(LienzoEvent evt) {
    }
}
