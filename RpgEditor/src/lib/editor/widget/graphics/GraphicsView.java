/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.graphics;

/**
 *
 * @author gaetan
 */
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglCanvas;


public class GraphicsView extends LwjglCanvas{

    public GraphicsView(ApplicationListener listener, boolean useGL2) {
        super(listener, useGL2);
        

    }
    
}
