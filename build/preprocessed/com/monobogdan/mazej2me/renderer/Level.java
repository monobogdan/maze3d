/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monobogdan.mazej2me.renderer;

import com.monobogdan.mazej2me.Player;
import java.io.IOException;
import javax.microedition.lcdui.Image;

/**
 *
 * @author mono
 */
public class Level {
    public static final int CUBE_SIZE = 128;
    private String Y;
    
    static class TileData
    {
        public int texId; // 0 - no wall at all
        public boolean isSolid;
    }
    
    private int[] tiles = {
        0, 2, 2, 1, 3, 3, 3, 3, 3, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
        2, 0, 0, 1, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
        2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
        2, 0, 0, 1, 3, 3, 3, 3, 3, 0, 2, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
        2, 0, 0, 1, 0, 0, 0, 0, 2, 0, 2, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
        2, 1, 1, 1, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
        2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
        2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
        2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
        2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
        2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
        2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
        2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
        2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
        2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
        2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
        2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1,
        0, 2, 2, 1, 2, 2, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0
    };
    
    public Player player;
    
    private Texture2D[] textureBank;
    private Texture2D floorTexture;
    
    private int worldWidth;
    private int worldHeight;
    
    private Texture2D skyTexture;
    
    private Mesh cubeMesh;
    private Mesh floorMesh;
    
    public Level()
    {
        generateGeometry();
        worldWidth = 27;
        worldHeight = 18;
        
        textureBank = new Texture2D[5];
        
        for(int i = 0; i < textureBank.length; i++) {
            textureBank[i] = new Texture2D("/wall" + (i + 1) + ".bmp");
        }
        
        skyTexture = new Texture2D("/sky.bmp");
        
        player = new Player();
    }
    
    private void generateGeometry()
    {
        cubeMesh = Mesh.makeCube(CUBE_SIZE, 64);
        floorMesh = Mesh.makePlane(2048, 2048, 512);
    }
    
    public void allocate(int width, int height)
    {
        
        worldWidth = width;
        worldHeight = height;
    }
    
    public void setBlockAt(int x, int y, TileData data)
    {
        //tiles[y * worldWidth + x] = data;
        
    }
    
    private boolean aabbTest(int x1, int y1, int x2, int y2, int w2, int h2)
    {
        return (x1 > x2) && (y1 > y2) && (x1 < x2 + w2) && (y1 < y2 + h2);
    }
    
    public boolean collideTest(int x, int y, int size)
    {
        final int collideMargin = 64;
        
        for(int i = 0; i < worldHeight; i++)
        {
            for(int j = 0; j < worldWidth; j++)
            {
                if(tiles[i * worldWidth + j] != 0 && 
                        aabbTest(x, y, (j * CUBE_SIZE) - CUBE_SIZE, i * CUBE_SIZE, CUBE_SIZE, CUBE_SIZE))
                {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    int time;
    
    public void update()
    {
        player.update();
    }
    
    public void render(Renderer renderer)
    {
        renderer.drawSky(skyTexture);
        
        for(int i = 0; i < worldHeight; i++)
        {
            for(int j = 0; j < worldWidth; j++)
            {
                int tex = tiles[i * worldWidth + j];
                
                if(tex != 0)
                    renderer.drawMesh(cubeMesh, textureBank[tex - 1], j * CUBE_SIZE, CUBE_SIZE / 2, i * CUBE_SIZE, 0);
            }
        }
    }
}
