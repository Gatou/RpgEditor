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
import lib.editor.widget.tree.interfaces.TreeWithContextMenu;

/**
 *
 * @author gaetan
 */
public abstract class TreeContextMenu extends Tree implements TreeWithContextMenu{
    
    public JPopupMenu menu;
    
    public TreeContextMenu(){
        super();
        menu = new JPopupMenu();
        menu.setLightWeightPopupEnabled(false);
        
	MouseAdapter ma = new MouseAdapter() {
		private void myPopupEvent(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			JTree tree = (JTree)e.getSource();
			TreePath path = tree.getPathForLocation(x, y);
			if (path == null)
				return;	

			tree.setSelectionPath(path);

			//Object obj = path.getLastPathComponent();

			//String label = "popup: " + obj.getTreeLabel();
			
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
}
