/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.inspector;

import javax.swing.JPanel;
import lib.editor.data.game.DataMap;
import org.jdesktop.swingx.JXTaskPane;

/**
 *
 * @author gaetan
 */
public class Inspector {
    
    public JXTaskPane propertyCollapsiblePane;
    public PropertyPanel propertyPanel;
    
    public Inspector(){
        propertyPanel = new PropertyPanel();
    }
    
    public void init(){
        propertyCollapsiblePane.add(propertyPanel);
    }
    
    public void hide(){
        propertyCollapsiblePane.setVisible(false);
    }
    
    public void setMapMode(DataMap data){
        hide();
        propertyCollapsiblePane.setVisible(true);
        propertyPanel.refresh(data);
    }
    
}
