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
        container.setPreferredSize(new Dimension(200, 0));
        container.setMinimumSize(container.getPreferredSize());
         
        panels = new Hashtable<String, InspectorPanel>();
        panels.put("property", new PropertyPanel(container));
        panels.put("map", new MapPanel(container));
        
        hide();
    }
    
    
    public void hide(){
        mode = Inspector.Mode.Null;
        //container.removeAll();
        panels.get("property").collapsible.setVisible(false);
        panels.get("map").collapsible.setVisible(false);
    }
    
    public void setMapMode(DataMap data){
        hide();
        if(data != null){
            mode = Inspector.Mode.Map;
            panels.get("property").collapsible.setVisible(true);
            panels.get("property").refresh(data);
            panels.get("map").collapsible.setVisible(true);
            panels.get("map").refresh(data);
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
