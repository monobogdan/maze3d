/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monobogdan.mazej2me.renderer;

import javax.microedition.lcdui.game.GameCanvas;

/**
 *
 * @author mono
 */
public class InputManager {
    private GameCanvas cnvs;
    
    public int Vertical;
    public int Horizontal;
    public boolean Fire;
    
    public InputManager(GameCanvas cnvs)
    {
        this.cnvs = cnvs;
    }
    
    void update()
    {
        int keyStates = cnvs.getKeyStates();
        
        Horizontal = 0;
        Vertical = 0;
    
        if((keyStates & GameCanvas.LEFT_PRESSED) != 0)
            Horizontal = -1;
        
        if((keyStates & GameCanvas.RIGHT_PRESSED) != 0)
            Horizontal = 1;
        
        if((keyStates & GameCanvas.UP_PRESSED) != 0)
            Vertical = 1;
        
        if((keyStates & GameCanvas.DOWN_PRESSED) != 0)
            Vertical = -1;
        
        Fire = (keyStates & GameCanvas.FIRE_PRESSED) != 0;
    }
}
