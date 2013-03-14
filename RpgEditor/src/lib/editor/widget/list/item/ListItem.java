/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.list.item;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import lib.editor.widget.tree.tree.Tree;

/**
 *
 * @author gaetan
 */
public class ListItem {
    
    private String text;
    private Icon icon;

    public ListItem(String text, ImageIcon icon) {
        this.text = text;
        this.icon = icon;
    }
    
    public Icon getIcon(){
        return icon;
    }
    
    public void setIcon(ImageIcon icon){
        this.icon = icon;
    }
    
    public String getText(){
        return text;
    }
    
    public void setText(String text){
        this.text = text;
    }
    
}
