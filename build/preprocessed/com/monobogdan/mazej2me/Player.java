/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monobogdan.mazej2me;

import com.mascotcapsule.micro3d.v3.Util3D;
import com.monobogdan.mazej2me.renderer.AudioManager;
import com.monobogdan.mazej2me.renderer.Game;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author mono
 */
public class Player {
    final float MOVEMENT_SPEED = 15;
    final int SENSITIVITY = 35;
    
    public float X;
    public float Z;
    public int Rotation;
    
    public int Health;
    public int Score;
    public int CurrentWeapon;
    
    private int nextAttack;
    
    private Image weapon;
    private int viewBob;
    
    private AudioManager.Stream sndFoot;
    
    public Player()
    {
        X = 256;
        Z = 256;
        
        Weapon.preloadWeapons();
        
        Health = 100;
        
        try {
            weapon = Image.createImage("/textures/ak47.png");
            
            sndFoot = new AudioManager.Stream("walk.wav");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private boolean collideTest()
    {
        return Game.current.level.collideTest((int)X, (int)Z, 8);
    }
    
    int num;
    
    public void update()
    {
        Rotation += -Game.current.inputManager.Horizontal * SENSITIVITY;
        
        int vInput = Game.current.inputManager.Vertical;
        float rad = ((float)Rotation / 4096) * (3.14f * 2); // Mascot Capsule rotation represents not 180 degrees rotation, but 360. That's why we need to convert it from normalized space to Pi * 2 space.
        
        float prevX = X;
        float prevZ = Z;
        boolean isMoved = vInput != 0;
        
        X -= -Math.sin(rad) * (vInput * MOVEMENT_SPEED);
        Z -= Math.cos(rad) * (vInput * MOVEMENT_SPEED);
        
        if(collideTest())
        {
            X = prevX;
            Z = prevZ;
            
            isMoved = false;
        }
        
        if(isMoved) {
            viewBob++;
            
            sndFoot.play();
        }
        else
            viewBob = 0;
        
        nextAttack--;
        
        if(nextAttack < 0 && Game.current.inputManager.Fire)
        {
            Weapon.Inventory[CurrentWeapon].fire();
            nextAttack = Weapon.Inventory[CurrentWeapon].FireRate;
        }
            
        Game.current.renderer.CameraRotation = Rotation;
        Game.current.renderer.CameraX = (int)X;
        Game.current.renderer.CameraZ = (int)Z;
    }
    
    public void drawView(Graphics g)
    {
        float viewBobFactor = (float)Math.sin((float)viewBob * 0.5f) * 15;
        
        Weapon.Inventory[CurrentWeapon].draw(g, (int)viewBobFactor);
    }
}
