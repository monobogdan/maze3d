/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monobogdan.mazej2me.renderer;

import com.mascotcapsule.micro3d.v3.Texture;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author mono
 */
public class Texture2D {
    Texture nativeHandle;
    
    public Texture2D(String fileName)
    {
        try {
            Renderer.log("Loading texture " + fileName);
            
            nativeHandle = new Texture("/textures" + fileName, true);
        } catch (IOException ex) {
            Renderer.log("Failed to load texture");
            
            ex.printStackTrace();
        }
    }
}
