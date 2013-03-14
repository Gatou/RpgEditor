/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.tree.item;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import lib.editor.widget.tree.tree.Tree;

/**
 *
 * @author gaetan
 */
public class TreeItem extends DefaultMutableTreeNode{
    
    private String text;
    private Icon icon;
    private Tree tree;
    
    public TreeItem(Tree tree, String text, ImageIcon icon){
        super();
        this.tree = tree;
        this.text = text;
        this.icon = icon;
    }
    
    public Icon getIcon(){
        return icon;
    }
    
    public void setIcon(ImageIcon icon){
        this.icon = icon;
        itemChanged();
    }
    
    public String getText(){
        return text;
    }
    
    public void setText(String text){
        this.text = text;
        itemChanged();
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
}
