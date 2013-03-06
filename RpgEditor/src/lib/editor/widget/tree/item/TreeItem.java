/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.tree.item;

import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author gaetan
 */
public class TreeItem extends DefaultMutableTreeNode{
    
    public String text;
    public ImageIcon icon;
    
    public TreeItem(String text, ImageIcon icon){
        super();
        this.text = text;
        this.icon = icon;
    }
    
    public void setIcon(ImageIcon icon){
        this.icon = icon;
    }
    
    public void setText(String text){
        this.text = text;
    }
    
}
