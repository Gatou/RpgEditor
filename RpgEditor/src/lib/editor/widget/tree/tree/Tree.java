/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.tree.tree;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import lib.editor.widget.tree.item.TreeItem;


/**
 *
 * @author gaetan
 */
public abstract class Tree extends JTree{
    
    public Tree(){
        TreeItem root = new TreeItem("");
        DefaultTreeModel model = (DefaultTreeModel)getModel();
        model.setRoot(root);
        //DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
        //root.removeAllChildren();
        //root.add(new TreeItem("another_child"));
        //expandRow(0);
        
        
        setRootVisible(false);
        addTopLevelItem(new TreeItem("another_child1"));
        addTopLevelItem(new TreeItem("another_child2"));
        addTopLevelItem(new TreeItem("another_child3"));
        //model.reload(root);
        //model.in
        getTopLevelItem(1).add(new TreeItem("roblochon"));
        insertTopLevelitem(0, new TreeItem("rEUblochon"));
        insertTopLevelitem(0, new TreeItem("rEEEEEEEEEUblochon"));
    }
    
    public TreeItem getRoot(){
        return (TreeItem) getModel().getRoot();
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
    
}
