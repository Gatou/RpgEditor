/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.mapeditor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import lib.editor.util.Cst;

/**
 *
 * @author gaetan
 */
public class MapResizeRenderer {

    ShapeRenderer shapeRenderer;
    public int originalWidth, originalHeight, width, height, top, bottom, left, right;
    
    
    public MapResizeRenderer() {
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setColor(new Color(255,0,0,20));
    }
    
    public void setResize(int originalWidth, int originalHeight, int width, int height, int top, int bottom, int left, int right){
        this.originalWidth = originalWidth;
        this.originalHeight = originalHeight;
        this.width = width;
        this.height = height;
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }
    
    public void update(OrthographicCamera camera){
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeType.FilledTriangle);
        updateTop(top);
        updateBottom(bottom);
        shapeRenderer.end();
    }
    
    public void updateTop(int value){
        if(value > 0){
            for(int i=0; i<width; i++){
                for(int j=0; j<value; j++){
                    drawDiamond(i, j);
                }
            }
        }
        else{
            for(int i=0; i<width; i++){
                for(int j=-1; j>value-1; j--){
                    drawDiamond(i, j);
                }
            }
        }
    }
    
    public void updateBottom(int value){
        if(value > 0){
            for(int i=0; i<width; i++){
                for(int j=originalHeight; j<originalHeight+value; j++){
                    drawDiamond(i, j);
                }
            }
        }
        else{
            for(int i=0; i<width; i++){
                for(int j=originalHeight-1; j>originalHeight+value-1; j--){
                    drawDiamond(i, j);
                }
            }
        }
    }
    
    public void drawDiamond(int i, int j){
        int x = iToX(i, j);
        int y = jToY(i, j);
        int padX = Cst.TILE_W/10;
        int padY = padX / (Cst.TILE_W/Cst.TILE_H);
        shapeRenderer.filledTriangle(x, y+padY, x+Cst.TILE_HW-padX, y+Cst.TILE_HH, x-Cst.TILE_HW+padX, y+Cst.TILE_HH);
        shapeRenderer.filledTriangle(x, y+Cst.TILE_HH*2-padY, x+Cst.TILE_HW-padX, y+Cst.TILE_HH, x-Cst.TILE_HW+padX, y+Cst.TILE_HH);
    }
    
    public int iToX(int i, int j) {
        int x = i * Cst.TILE_HW - j * Cst.TILE_HW;
        return x;
    }
    
    public int jToY(int i, int j) {
        int y = i * Cst.TILE_HH + j * Cst.TILE_HH;
        return y;
    }
    
}
