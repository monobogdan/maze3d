/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monobogdan.mazej2me.renderer;

import com.monobogdan.mazej2me.HUD;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author mono
 */
public class Game {
    public static Game current;
    
    public InputManager inputManager;
    public Renderer renderer;
    
    public Level level;
    public HUD hud;
    
    public Game(Renderer renderer)
    {
        this.renderer = renderer;
     
        inputManager = new InputManager(renderer);
        
        level = new Level();
        hud = new HUD();
    }
    
    public void update()
    {
        inputManager.update();
        
        level.update();
    }
    
    public void draw()
    {
        level.render(renderer);
    }
    
    public void drawHUD(Graphics g)
    {
        level.player.drawView(g);
        
        hud.draw(g);
    }
}
