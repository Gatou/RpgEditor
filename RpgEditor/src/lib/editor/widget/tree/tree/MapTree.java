/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.tree.tree;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu.Separator;
import javax.swing.KeyStroke;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import lib.editor.mgr.IconMgr;
import lib.editor.mgr.Mgr;
import lib.editor.mgr.WidgetMgr;
import lib.editor.widget.tree.item.TreeItem;

/**
 *
 * @author gaetan
 */
public class MapTree extends TreeContextMenu{
    
    public MapTree(){
        super();
        
        getInputMap().put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK), "copy");
        getActionMap().put("copy", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                WidgetMgr.MAIN_WINDOW.copy();
            }
            
        });
        getInputMap().put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK), "paste");
        getActionMap().put("paste", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                WidgetMgr.MAIN_WINDOW.paste();
            }
            
        });
         getInputMap().put(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0), "delete");
        getActionMap().put("delete", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                WidgetMgr.MAIN_WINDOW.delete();
            }
            
        });
    }
    
    public void setup(){

    }

    @Override
    public void createContextMenu() {
        JMenuItem item;
        item = new JMenuItem("New map");
        menu.add(item);
        
        menu.add(new Separator());
        

        item = new JMenuItem("Copy", Mgr.icon.getIcon("copy.png"));
        item.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WidgetMgr.MAIN_WINDOW.copy();
            }
        });
        menu.add(item);
        
        item = new JMenuItem("Paste", Mgr.icon.getIcon("paste.png"));
        item.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WidgetMgr.MAIN_WINDOW.paste();
            }
        });
        menu.add(item);
        
        item = new JMenuItem("Delete", Mgr.icon.getIcon("delete.png"));
        item.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WidgetMgr.MAIN_WINDOW.delete();
            }
        });
        menu.add(item);
    }
    
}
