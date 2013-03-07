/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.tree.tree;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import lib.editor.widget.tree.item.TreeItem;

/**
 *
 * @author gaetan
 */
public class TreeItemRenderer extends DefaultTreeCellRenderer{
   
    private JLabel label;

    TreeItemRenderer() {
        label = new JLabel();
        //label.setOpaque(true);
    }
    
    public Component getTreeCellRendererComponent(
                JTree tree,
                Object value,
                boolean sel,
                boolean expanded,
                boolean leaf,
                int row,
                boolean hasFocus) {

        TreeItem item = (TreeItem) value;
        label.setIcon(item.icon);
        label.setText(item.text);
        
        if (sel) {
            label.setForeground(textSelectionColor);
        } else {
            label.setForeground(textNonSelectionColor);
        }
        
        return label;
    }
            
    
}