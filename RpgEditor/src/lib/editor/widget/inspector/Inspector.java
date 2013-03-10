/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.inspector;

import java.awt.Dimension;
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
    public PropertyPanel propertyPanel;
    private int mode;
    
    public Inspector(JXTaskPaneContainer container){
        this.container = container;
        container.setPreferredSize(new Dimension(200, 0));
        container.setMinimumSize(container.getPreferredSize());
         
        propertyPanel = new PropertyPanel();
        container.add(propertyPanel.collapsible);
        
        hide();
    }
    
    
    public void hide(){
        mode = Inspector.Mode.Null;
        //container.removeAll();
        propertyPanel.collapsible.setVisible(false);
    }
    
    public void setMapMode(DataMap data){
        hide();
        if(data != null){
            mode = Inspector.Mode.Map;
            propertyPanel.collapsible.setVisible(true);
            propertyPanel.refresh(data);
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
