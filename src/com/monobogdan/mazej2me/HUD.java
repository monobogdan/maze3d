/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monobogdan.mazej2me;

import com.monobogdan.mazej2me.renderer.Game;
import com.monobogdan.mazej2me.renderer.Renderer;
import java.io.IOException;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author mono
 */
public class HUD {
    private Image crosshair;
    private Image bottomBar;
    
    private Font font;
    
    public HUD()
    {
        font = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_SMALL);
        
        try
        {
            crosshair = Image.createImage("/textures/ch.png");
            
        }
        catch (IOException e)
        {
            e.printStackTrace();
            
            Renderer.log("Failed to load HUD resources");
        }
    }
    
    public void draw(Graphics g)
    {
        final int statusMargin = 24;
        
        g.drawImage(crosshair, Game.current.renderer.getWidth() / 2 - (crosshair.getWidth() / 2), 
                Game.current.renderer.getHeight() / 2 - (crosshair.getHeight() / 2), Graphics.LEFT | Graphics.TOP);
        
        int yBase = Game.current.renderer.getHeight() - statusMargin;
        
        g.setColor(64, 64, 64);
        g.fillRect(0, yBase, Game.current.renderer.getWidth(), statusMargin);
        
        g.setColor(128, 0, 0);
        g.setFont(font);
        g.drawString(Integer.toString(Game.current.level.player.Health), statusMargin / 2, yBase + (font.getHeight() / 2), Graphics.LEFT | Graphics.TOP);
    }
}
