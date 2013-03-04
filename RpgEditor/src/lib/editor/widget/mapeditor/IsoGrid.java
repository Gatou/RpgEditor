/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.mapeditor;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

/**
 *
 * @author gaetan
 */
public class IsoGrid {
    
    int width, height, cellWidth, cellHeight;
    ShapeRenderer shapeRenderer;
    
    public IsoGrid(int width, int height, int cellWidth, int cellHeight){
        this.width = width;
        this.height = height;
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        shapeRenderer = new ShapeRenderer();
    }
    
    public void update(){
 
    }

    void update(OrthographicCamera camera) {
           shapeRenderer.setProjectionMatrix(camera.combined);
            shapeRenderer.begin(ShapeType.Line);
          
        shapeRenderer.setColor(1, 1, 1, 1);
        
        int lineSizeX = (width*cellWidth)/2;
        int lineSizeY = (height*cellHeight)/2;
        int posX = 0;
        int posY = 0;
        for(int i=0; i<width+1; i++){
            
            
               shapeRenderer.line(posX, posY, posX+lineSizeX, posY+lineSizeY);
           posX -= cellWidth/2;
           posY += cellHeight/2;
        }
        
        posX = 0;
        posY = 0;
        for(int i=0; i<height+1; i++){
            
            
               shapeRenderer.line(posX, posY, posX-lineSizeX, posY+lineSizeY);
           posX += cellWidth/2;
           posY += cellHeight/2;
        }
        
        shapeRenderer.end();
    }
    
}
