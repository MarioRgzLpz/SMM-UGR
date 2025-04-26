/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sm.mrl.events;

import java.util.EventListener;

/**
 *
 * @author mariorl
 */
public interface LienzoListener extends EventListener {

    public void shapeAdded(LienzoEvent evt);

    public void shapeSelected(LienzoEvent evt);
}
