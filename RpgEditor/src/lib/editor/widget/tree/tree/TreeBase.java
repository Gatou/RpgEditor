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
public abstract class TreeBase extends JTree{
    
    public TreeBase(){
        TreeItem root = new TreeItem("root item");
        DefaultTreeModel model = (DefaultTreeModel)getModel();
        model.setRoot(root);
        //DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
        //root.removeAllChildren();
        //root.add(new DefaultMutableTreeNode("another_child"));
        //expandRow(0);
        model.reload(root);
        
        

    }
    
    public TreeItem getRoot(){
        return (TreeItem) getModel().getRoot();
    }
}
