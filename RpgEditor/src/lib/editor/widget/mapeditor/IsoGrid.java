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
        
    }


    void update(OrthographicCamera camera) {
        if(shapeRenderer == null){
            shapeRenderer = new ShapeRenderer();
        }
        
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeType.Line);
          
        shapeRenderer.setColor(1, 1, 1, 1);
        
        int lineWidth = (width*cellWidth)/2;
        int lineHeight = (height*cellHeight)/2;
        
        int toWidth = (cellWidth/2)*height;
        int toHeight = (cellHeight/2)*width;
        
        //int toWidth = (cellHeight/2)*cellWidth;
        int posX = 0;
        int posY = 0;
        
        for(int i=0; i<width+1; i++){
            shapeRenderer.line(posX, posY, posX-toWidth, posY+lineHeight);
            posX += cellWidth/2;
            posY += cellHeight/2;
        }
        
        posX = 0;
        posY = 0;
        for(int i=0; i<height+1; i++){
            shapeRenderer.line(posX, posY, posX+lineWidth, posY+toHeight);
            posX -= cellWidth/2;
            posY += cellHeight/2;
        }
        
        shapeRenderer.end();
    }
    
}
