/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.inspector;

import java.awt.Dimension;
import javax.swing.JPanel;
import lib.editor.mgr.Mgr;
import org.jdesktop.swingx.JXTaskPane;

/**
 *
 * @author gaetan
 */
public abstract class InspectorPanel extends JPanel{
    
    public JXTaskPane collapsible;
    boolean refreshing;
    
    public InspectorPanel(String title, String iconFilename){

        
        collapsible = new JXTaskPane();
        //collapsible.setPreferredSize(new Dimension(160, 50));
        //collapsible.setMinimumSize(getPreferredSize());
        collapsible.setIcon(Mgr.icon.getIcon(iconFilename));//new javax.swing.ImageIcon(getClass().getResource("/assets/icons/project_root.png")));
        collapsible.setScrollOnExpand(true);
        collapsible.setSpecial(true);
        collapsible.setTitle(title);
        collapsible.add(this);
        
        refreshing = false;
    }

        
}
