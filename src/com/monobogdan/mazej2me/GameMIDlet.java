/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monobogdan.mazej2me;

import com.monobogdan.mazej2me.renderer.Renderer;
import javax.microedition.midlet.*;

/**
 * @author mono
 */
public class GameMIDlet extends MIDlet {

    public void startApp() {
        Renderer cnvs = new Renderer(this);
        cnvs.startGameLoop();
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
}
