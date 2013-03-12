/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.mapeditor;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector3;
import lib.editor.data.game.DataMap;
import lib.editor.widget.graphics.GraphicsView;

/**
 *
 * @author gaetan
 */
public class MapEditorGraphicsView extends GraphicsView{

    IsoGrid grid;
    final Vector3 camPos = new Vector3();
    public OrthographicCamera camera; 
    
    public MapEditorGraphicsView() {
        super(new MapEditorApp(), false);
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        grid = new IsoGrid(100, 100, 32, 16);
    }
    
    public void resize(int width, int height){
      camera.setToOrtho(true, width, height);
      camera.position.set( camPos);
    }
    
    public void refresh(DataMap data){
        if(data == null){
            return;
        }
        resizeGrid(data.width, data.height);
    }
    
    
    public void resizeGrid(int width, int height){
        grid.width = width;
        grid.height = height;
    }
    
    public void update(){
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        camera.update();
        grid.update(camera);
    }
    

}
