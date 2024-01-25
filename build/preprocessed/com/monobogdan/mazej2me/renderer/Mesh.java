/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monobogdan.mazej2me.renderer;

/**
 *
 * @author mono
 */
public class Mesh {
    int[] Vertices;
    int[] UV;
    int TotalTriangles;
    
    private int numVerts;
    private int ptr;
    
    public Mesh(int numVerts)
    {
        ptr = 0;
        this.numVerts = numVerts;
        
        Vertices = new int[numVerts * 3];
        UV = new int[numVerts * 2];
    }
    
    public Mesh addVertex(int x, int y, int z, int u, int v)
    {
        if(ptr >= numVerts)
        {
            Renderer.log("Vertex buffer overflow");
            return null;
        }
        
        Vertices[ptr * 3] = x;
        Vertices[ptr * 3 + 1] = y;
        Vertices[ptr * 3 + 2] = z;
        UV[ptr * 2] = u;
        UV[ptr * 2 + 1] = v;
        
        ptr++;
        TotalTriangles = ptr / 3;
        
        return this;
    }
    
    public static Mesh makeCube(int size, int texSize)
    {
        Mesh mesh = new Mesh(36);
        
        // Front
        mesh.addVertex(0, 0, 0, 0, texSize);
        mesh.addVertex(size, 0, 0, texSize, texSize);
        mesh.addVertex(size, size, 0, texSize, 0);
        mesh.addVertex(0, 0, 0, 0, texSize);
        mesh.addVertex(0, size, 0, 0, 0);
        mesh.addVertex(size, size, 0, texSize, 0);
        
        // Back
        mesh.addVertex(0, 0, size, 0, texSize);
        mesh.addVertex(size, 0, size, texSize, texSize);
        mesh.addVertex(size, size, size, texSize, 0);
        mesh.addVertex(0, 0, size, 0, texSize);
        mesh.addVertex(0, size, size, 0, 0);
        mesh.addVertex(size, size, size, texSize, 0);
        
        // Left
        mesh.addVertex(0, 0, 0, 0, texSize);
        mesh.addVertex(0, 0, size, texSize, texSize);
        mesh.addVertex(0, size, size, texSize, 0);
        mesh.addVertex(0, 0, 0, 0, texSize);
        mesh.addVertex(0, size, 0, 0, 0);
        mesh.addVertex(0, size, size, texSize, 0);
        
        // Right
        mesh.addVertex(size, 0, 0, 0, texSize);
        mesh.addVertex(size, 0, size, texSize, texSize);
        mesh.addVertex(size, size, size, texSize, 0);
        mesh.addVertex(size, 0, 0, 0, texSize);
        mesh.addVertex(size, size, 0, 0, 0);
        mesh.addVertex(size, size, size, texSize, 0);
        
        return mesh;
    }
    
    public static Mesh makePlane(int w, int h, int texSize)
    {
        Mesh mesh = new Mesh(6);
        
        mesh.addVertex(0, 0, 0, 0, texSize);
        mesh.addVertex(w, 0, 0, texSize, texSize);
        mesh.addVertex(w, 0, -h, texSize, 0);
        //mesh.addVertex(0, 0, 0, 0, texSize);
       // mesh.addVertex(0, 0, h, 0, 0);
       // mesh.addVertex(w, 0, h, texSize, 0);
        
        return mesh;
    }
    
    
}
