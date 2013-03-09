/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.tree.tree;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.TreePath;
import lib.editor.mgr.TransferMgr;
import lib.editor.widget.tree.interfaces.TreeWithMenu;
import lib.editor.widget.tree.item.TreeItem;

/**
 *
 * @author gaetan
 */
public abstract class TreeMenu extends Tree implements TreeWithMenu{
    
    public JPopupMenu menu;
    
    public TreeMenu(){
        super();
        menu = new JPopupMenu();
        menu.setLightWeightPopupEnabled(false);
        
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                grabFocus();
                
            }
        });
        
	MouseAdapter ma = new MouseAdapter() {
		private void myPopupEvent(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			JTree tree = (JTree)e.getSource();
			TreePath path = tree.getPathForLocation(x, y);
			if (path != null){
                            tree.setSelectionPath(path);
                        }
			//	return;	

			

			//Object obj = path.getLastPathComponent();

			//String label = "popup: " + obj.getTreeLabel();
			checkEnabledMenuAction();
			menu.show(tree, x, y);
		}
		public void mousePressed(MouseEvent e) {
			if (e.isPopupTrigger()) myPopupEvent(e);
		}
		public void mouseReleased(MouseEvent e) {
			if (e.isPopupTrigger()) myPopupEvent(e);
		}
	};

	addMouseListener(ma);
        
        createMenu();
        createMenuShortcut();
    }
    
    public void grabFocus(){
        TransferMgr.lastFocused = this;
    }
    
    public void currentItemChanged(TreeItem newItem){
        super.currentItemChanged(newItem);
        checkEnabledMenuAction();
    }
}
