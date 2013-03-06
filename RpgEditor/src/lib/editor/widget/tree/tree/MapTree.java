/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.tree.tree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import lib.editor.widget.tree.item.TreeItem;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu.Separator;
import javax.swing.KeyStroke;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import lib.editor.data.editor.DataEditorBase;
import lib.editor.data.editor.DataEditorMap;
import lib.editor.data.game.DataBase;
import lib.editor.data.game.DataMap;
import lib.editor.mgr.IconMgr;
import lib.editor.mgr.Mgr;
import lib.editor.mgr.WidgetMgr;
import lib.editor.widget.menu.MenuItem;
import lib.editor.widget.tree.item.DatabaseTreeItem;

/**
 *
 * @author gaetan
 */
public class MapTree extends DatabaseTree{
    
    public MapTree(){
        super();
        

    }

    public void createMenu() {
        JMenuItem item;
        item = new MenuItem("New map", null, "Add kaka", KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                newMap();
            }
        });
        menu.add(item);
        
        menu.add(new Separator());
        

        item = new JMenuItem("Copy", Mgr.icon.getIcon("copy.png"));
        item.setAccelerator(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                WidgetMgr.MAIN_WINDOW.copy();
            }
        });
        menu.add(item);
        
        item = new JMenuItem("Paste", Mgr.icon.getIcon("paste.png"));
        item.setAccelerator(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                WidgetMgr.MAIN_WINDOW.paste();
            }
        });
        menu.add(item);
        
        item = new JMenuItem("Delete", Mgr.icon.getIcon("delete.png"));
        item.setAccelerator(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        item.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                WidgetMgr.MAIN_WINDOW.delete();
            }
        });
        menu.add(item);
    }
    
    public void createMenuShortcut() {
        //new map
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0), "newMap");
        getActionMap().put("newMap", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                newMap();
            }
        });
        //copy
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK), "copy");
        getActionMap().put("copy", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                WidgetMgr.MAIN_WINDOW.copy();
            }
        });
        //paste
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK), "paste");
        getActionMap().put("paste", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                WidgetMgr.MAIN_WINDOW.paste();
            }
        });
        //delete
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "delete");
        getActionMap().put("delete", new AbstractAction(){
            public void actionPerformed(ActionEvent e) {
                WidgetMgr.MAIN_WINDOW.delete();
            }
        });
    }

    public void checkEnabledMenuAction() {
        
    }
    
    public TreeItem root(){
        return getTopLevelItem(0);
    }
    
    public void refresh(){
        clear();
        
        TreeItem root = new DatabaseTreeItem("Project", Mgr.icon.getIcon("project_root.png"), new DataMap(0, "", 0,0), new DataEditorBase());
        addTopLevelItem(root);
        
        for(int i=0; i<gameDatabase.size(); i++){
            DataMap data = (DataMap) gameDatabase.get(i);
            
            //DatabaseTreeItem item = new DatabaseTreeItem(data.name, null);
            //item.gameData = data; FAIRE DEEPCOPY ICI
                    
            //root().add(item);
        }
    }
    
    private void newMap(){
        
        DataMap gameData = new DataMap(generateId(), "" , 20, 20);
        gameData.name = "Map" + gameData.id;
        DataEditorMap editorData = new DataEditorMap();
        
        DatabaseTreeItem item = new DatabaseTreeItem(gameData.name, null, gameData, editorData);
        
        TreeItem parentItem = getCurrentItem();
        
        addItem(item, parentItem);
        expandPath(getSelectionPath());
        

    }
    
}
