/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.tree.item;

import java.awt.Image;
import javax.swing.GrayFilter;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import lib.editor.mgr.Mgr;
import lib.editor.widget.tree.tree.Tree;

/**
 *
 * @author gaetan
 */
public class TreeItem extends DefaultMutableTreeNode{
    
    private String text;
    private String iconFilename;
    //private ImageIcon disableIcon;
    private Tree tree;
    private boolean enabled;
    //private boolean canBeDisable;
    
    public TreeItem(Tree tree, String text, String iconFilename){
        super();
        this.tree = tree;
        
        setText(text);
        setIcon(iconFilename);
        setEnabled(true);
    }
    
    public String getIconFilename(){
        return iconFilename;
    }
    
    public void setIcon(String filename){
        iconFilename = filename;//Mgr.icon.getIcon(filename);
        //this.icon = icon;
        itemChanged();
    }
    
    public String getText(){
        return text;
    }
    
    public void setText(String text){
        this.text = text;
        itemChanged();
    }
    
    public void setEnabled(boolean enabled){
        this.enabled = enabled;
        itemChanged();
        /*
        if(enabled){
            setIcon(icon);
        }
        else{
            if(disableIcon == null){

            }
            setIcon(disableIcon);
        }*/
    }
    
    public boolean isEnabled(){
        return enabled;
    }
    
    private void itemChanged(){
        DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
        model.nodeChanged(this);
    }
    
    public void addChild(TreeItem item){
        DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
        model.insertNodeInto(item, this, getChildCount());
        //add(item);
        if(tree.filter != null && tree.filter.isFiltering){
            tree.filter.needRefresh(item, true);
        }
    }
    
    public void removeChild(TreeItem item){
        DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
        model.removeNodeFromParent(item);
        if(tree.filter != null && tree.filter.isFiltering){
            tree.filter.needRefresh(item, false);
        }
    }
    
    public void setExpanded(boolean expanded){
        tree.setItemExpanded(this, expanded);
    }
    
}
