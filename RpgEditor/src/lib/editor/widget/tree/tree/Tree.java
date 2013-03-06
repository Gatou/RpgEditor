/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.tree.tree;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import lib.editor.widget.tree.item.TreeItem;


/**
 *
 * @author gaetan
 */
public abstract class Tree extends JTree{
    
    public Tree(){
        
        TreeItem root = new TreeItem("", null);
        DefaultTreeModel model = (DefaultTreeModel)getModel();
        model.setRoot(root);
        //DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
        //root.removeAllChildren();
        //root.add(new TreeItem("another_child"));
        //expandRow(0);
        
        
        setRootVisible(false);
        setCellRenderer(new TreeItemRenderer());
    }
    
    public TreeItem getRoot(){
        return (TreeItem) getModel().getRoot();
    }
    
    public void clear(){
        DefaultTreeModel model = (DefaultTreeModel)getModel();
        getRoot().removeAllChildren();
        model.reload(getRoot());
    }
    
    public void addTopLevelItem(TreeItem item){
        DefaultTreeModel model = (DefaultTreeModel)getModel();
        model.insertNodeInto(item, getRoot(), getRoot().getChildCount());
        model.reload(getRoot());
    }
    
    public TreeItem getTopLevelItem(int index){
        return (TreeItem) getRoot().getChildAt(index);
    }
    
    public void insertTopLevelitem(int index, TreeItem item){
        DefaultTreeModel model = (DefaultTreeModel)getModel();
        model.insertNodeInto(item, getRoot(), index);
        model.reload(getRoot());
    }
    
    public TreeItem getCurrentItem(){
        if(isSelectionEmpty()){
            return null;
        }
        
        return (TreeItem) getSelectionPath().getLastPathComponent();
    }
    
    public void addItem(TreeItem item, TreeItem parentItem){
        DefaultTreeModel model = (DefaultTreeModel)getModel();
        model.insertNodeInto(item, parentItem, parentItem.getChildCount());
        model.reload(getRoot());
    }
    
}
