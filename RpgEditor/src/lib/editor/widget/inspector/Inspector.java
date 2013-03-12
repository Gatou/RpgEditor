/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.inspector;

import java.awt.Dimension;
import java.util.Hashtable;
import java.util.Map;
import javax.swing.JPanel;
import lib.editor.data.game.DataMap;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

/**
 *
 * @author gaetan
 */
public class Inspector {
    
    public JXTaskPaneContainer container;
    public Map<String, InspectorPanel> panels;
    private int mode;
    
    public Inspector(JXTaskPaneContainer container){
        this.container = container;
        container.setPreferredSize(new Dimension(240, 0));
        container.setMinimumSize(container.getPreferredSize());
         
        panels = new Hashtable<String, InspectorPanel>();
        panels.put("property", new PropertyPanel(container));
        panels.put("map", new MapPanel(container));
        
        hide();
    }
    
    
    public void hide(){
        mode = Inspector.Mode.Null;
        //container.removeAll();
        panels.get("property").setVisible(false, null);
        panels.get("map").setVisible(false, null);
    }
    
    public void setMapMode(DataMap data){
        hide();
        if(data != null){
            mode = Inspector.Mode.Map;
            panels.get("property").setVisible(true, data);
            panels.get("map").setVisible(true, data);
        }
    }
    
    public int getMode(){
        return mode;
    }
    
    public class Mode{
        public final static int Null = 0;
        public final static int Map = 1;
    }
}
