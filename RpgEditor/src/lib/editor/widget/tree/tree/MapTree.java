/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.tree.tree;

import javax.swing.Icon;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import lib.editor.mgr.IconMgr;
import lib.editor.mgr.Mgr;
import lib.editor.widget.tree.item.TreeItem;

/**
 *
 * @author gaetan
 */
public class MapTree extends TreeContextMenu{
    
    public MapTree(){
        super();
    }
    
    public void setup(){
        //getModel().
        //TreeModel treeModel = new TreeModel
        DefaultTreeModel model = (DefaultTreeModel)getModel();
        model.setRoot(new TreeItem("Je suis une root"));
        DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
        root.removeAllChildren();
        //root.add(new DefaultMutableTreeNode("another_child"));
        expandRow(0);
        model.reload(root);
        
    }

    @Override
    public void createContextMenu() {
        JMenuItem item;
        item = new JMenuItem("New map");
        menu.add(item);
        
        
        item = new JMenuItem("Copy", Mgr.icon.getIcon("copy.png"));
        item.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        menu.add(item);
    }
    
}
