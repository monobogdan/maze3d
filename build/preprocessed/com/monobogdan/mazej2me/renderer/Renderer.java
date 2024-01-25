/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monobogdan.mazej2me.renderer;

import javax.microedition.lcdui.game.GameCanvas;
import com.mascotcapsule.micro3d.v3.*;
import java.io.IOException;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.midlet.MIDlet;

/**
 *
 * @author mono
 */
public class Renderer extends GameCanvas {
    
    private Graphics3D g3d;
    private javax.microedition.lcdui.Graphics g;
    
    private int viewWidth, viewHeight;
    private Display display;
    
    private AffineTrans affineRotationY, affineTranslate, affineMatrix;
    private FigureLayout matrix;
    
    private Figure figure;
    private Texture tex;
    
    public int CameraX;
    public int CameraZ;
    public int CameraRotation;
    
    public Renderer(MIDlet midlet)
    {
        super(false);
        setFullScreenMode(true);
        
        display = Display.getDisplay(midlet);
        display.setCurrent(this);
        
        log("Initializing renderer");
        g3d = new Graphics3D();
        g = super.getGraphics();
        
        viewWidth = getWidth();
        viewHeight = getHeight();
        
        affineRotationY = new AffineTrans();
        affineTranslate = new AffineTrans();
        affineMatrix = new AffineTrans();
        matrix = new FigureLayout();
        
        Game.current = new Game(this);
    }
    
    public static void log(String str)
    {
        System.out.println(str);
    }
    
    private void beginScene()
    {
        g.setColor(128, 128, 128);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g3d.bind(g);
    }
    
    public void drawSky(Texture2D tex)
    {
        int[] verts = {
            0, 0, 0,
            viewWidth, 0, 0,
            viewWidth, viewHeight / 2, 0,
            
            0, 0, 0,
            0, viewHeight / 2, 0,
            viewWidth, viewHeight / 2, 0
        };
        int[] uv = {
            0, 0,
            255, 0,
            255, 255,
            
            0, 0,
            0, 255,
            255, 255
        };
        
        int color = ((128 << 16) | (128 << 8) | 128);
        
        int[] colors = {
            color, color, color
        };
        
        Effect3D ef = new Effect3D();
        
        affineMatrix.setIdentity();
        matrix.setAffineTrans(affineMatrix);
        matrix.setCenter(0, 0);
        matrix.setParallelSize(viewWidth, viewHeight);
        
        g3d.renderPrimitives(tex.nativeHandle, 0, 0, matrix, ef, Graphics3D.PRIMITVE_TRIANGLES | Graphics3D.PDATA_TEXURE_COORD, 2, verts, verts, uv, verts);
        g3d.flush();
        
        affineMatrix.setIdentity();
        matrix.setAffineTrans(affineMatrix);
        matrix.setCenter(0, viewHeight / 2);
        matrix.setParallelSize(viewWidth, viewHeight);
        
        g3d.renderPrimitives(tex.nativeHandle, 0, 0, matrix, ef, Graphics3D.PRIMITVE_TRIANGLES | Graphics3D.PDATA_COLOR_PER_FACE, 2, verts, verts, uv, colors);
        g3d.flush();
    }
    
    public void drawMesh(Mesh mesh, Texture2D tex,
            int x, int y, int z,
            int ry)
    {
        Effect3D ef = new Effect3D();
        
        affineMatrix.setIdentity();
        
        affineRotationY.setRotationY(CameraRotation + (2048));
        affineTranslate.lookAt(new Vector3D(-CameraX, 0, -CameraZ), new Vector3D(0, 0, -4096), new Vector3D(0, -4096, 0));
        affineMatrix.mul(affineRotationY);
        affineMatrix.mul(affineTranslate);
        
        // Model matrix calculation
        affineRotationY.setRotationY(ry);
        
        affineTranslate.lookAt(new Vector3D(x, y, -z), new Vector3D(0, 0, -1), new Vector3D(0, 4096, 0));
        
        affineMatrix.mul(affineTranslate);
        affineMatrix.mul(affineRotationY);
        // View matrix calculation
        
        
        matrix.setAffineTrans(affineMatrix);
        matrix.setCenter(getWidth() / 2, getHeight() / 2);
        matrix.setPerspective(1, 2048, 512);
        
        
        int vertFormat = Graphics3D.PRIMITVE_TRIANGLES | Graphics3D.PDATA_TEXURE_COORD;
        
        /*int[] verts = {
            0, 0, 128,
            128, 0, 128,
            128, 128, 128,
            
            0, 0, 128,
            0, 128, 128,
            128, 128, 128
        };
        int[] uv = {
            0, 255,
            255, 255,
            255, 0,
            
            0, 255,
            0, 0,
            255, 0
        };
        int[] empty = new int[verts.length];
        
        g3d.renderPrimitives(tex, 0, 0, matrix, ef, VERTEX_FORMAT, 2, verts, empty, uv, empty);
        //figure.setTexture(tex);
       // g3d.renderFigure(figure, 0, 0, matrix, ef);
        g3d.flush();*/
        
        if(mesh != null)
        {
            g3d.renderPrimitives(tex.nativeHandle, 0, 0, matrix, ef, vertFormat, mesh.TotalTriangles, mesh.Vertices, mesh.Vertices, mesh.UV, mesh.Vertices);
            
        }
    }
    
    public Graphics getGraphics()
    {
        return g;
    }
    
    private void endScene()
    {
        g3d.flush();
        g3d.release(g);
        
        Game.current.drawHUD(g);
        flushGraphics();
    }
    
    public void startGameLoop()
    {
        while(true)
        {
            Game.current.update();
            
            beginScene();
            Game.current.draw();
            endScene();
        }
    }
}
