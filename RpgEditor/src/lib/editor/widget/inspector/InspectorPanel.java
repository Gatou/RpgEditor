/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.inspector;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import lib.editor.data.game.DataBase;
import lib.editor.mgr.Mgr;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

/**
 *
 * @author gaetan
 */
public abstract class InspectorPanel extends JPanel{
    
    public static final int LEFT_COLUMN_WIDTH = 70;
    
    public JXTaskPane collapsible;
    boolean refreshing;
    
    DataBase data;
    
    public InspectorPanel(JXTaskPaneContainer container, String title, String iconFilename){

        
        collapsible = new JXTaskPane();
        //collapsible.setPreferredSize(new Dimension(160, 50));
        //collapsible.setMinimumSize(getPreferredSize());
        collapsible.setIcon(Mgr.icon.getSystemIcon(iconFilename, false));//new javax.swing.ImageIcon(getClass().getResource("/assets/icons/project_root.png")));
        collapsible.setScrollOnExpand(true);
        collapsible.setSpecial(true);
        collapsible.setTitle(title);
        collapsible.add(this);
        
        container.add(collapsible);
        refreshing = false;
        
        
    }

    public void refresh(){
    }
    
    public void setVisible(boolean visible, DataBase data){
        this.data = data;
        if(visible){
            refresh();
        }
        collapsible.setVisible(visible);
        super.setVisible(visible);
    }
        
}
