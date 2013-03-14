/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.list.list;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import lib.editor.widget.list.item.ListItem;
import lib.editor.widget.tree.item.TreeItem;

/**
 *
 * @author gaetan
 */
public class ListItemRenderer extends DefaultListCellRenderer{

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
        ListItem item = (ListItem) value;
        label.setIcon(item.getIcon());
        label.setText(item.getText());
        
        if (isSelected) {
            label.setOpaque(true);
        } else {
            label.setOpaque(false);
        }
        
        return label;
    }
    
}
