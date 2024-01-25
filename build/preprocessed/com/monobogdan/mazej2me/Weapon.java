/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monobogdan.mazej2me;

import com.monobogdan.mazej2me.renderer.AudioManager;
import com.monobogdan.mazej2me.renderer.Game;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author mono
 */
public class Weapon {
    
    public String Name;
    public Image Sprite;
    public AudioManager.Stream SndFire;
    public int Ammo;
    
    public int Damage;
    public int FireRate;
    
    public static Weapon[] Inventory;
    
    private static Image precacheSprite(String fileName)
    {
        try
        {
            return Image.createImage("/textures/" + fileName);
        } catch (IOException ex) {
            ex.printStackTrace();
            
            return null;
        }
    }
    
    public static void preloadWeapons() {
        Inventory = new Weapon[1];
        
        Inventory[0] = new Weapon();
        Inventory[0].Name = "AK47";
        Inventory[0].Sprite = precacheSprite("ak47.png");
        Inventory[0].Ammo = 90;
        Inventory[0].SndFire = new AudioManager.Stream("ak47.wav");
        
        Inventory[0].FireRate = 5;
    }
    
    public void fire()
    {
        SndFire.stop();
        
        SndFire.play();
    }
    
    public void draw(Graphics g, int viewBobFactor)
    {
        Game.current.renderer.getGraphics().drawImage(Sprite, Game.current.renderer.getWidth() - Sprite.getWidth() + viewBobFactor, 
                Game.current.renderer.getHeight() - Sprite.getHeight(), Graphics.LEFT | Graphics.TOP);
    }
}
