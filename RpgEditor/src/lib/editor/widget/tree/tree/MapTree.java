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
import lib.editor.mgr.CopyPasteMgr;
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
    
    MenuItem newMapItem, copyItem, pasteItem, deleteItem;
       
    
    public MapTree(){
        super();
        

    }

    public void createMenu() {
        
        newMapItem = new MenuItem("New map", null, "Create a new map.", KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0));
        newMapItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                WidgetMgr.STATUS_LABEL.setText("");
                newMap();
            }
        });
        menu.add(newMapItem);
        
        menu.add(new Separator());
        

        copyItem = new MenuItem("Copy", Mgr.icon.getIcon("copy.png"), "Copy the selection and put it on the clipboard.", KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        //item.setAccelerator(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        copyItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                WidgetMgr.STATUS_LABEL.setText("");
                WidgetMgr.MAIN_WINDOW.copy();
            }
        });
        menu.add(copyItem);
        
        pasteItem = new MenuItem("Paste", Mgr.icon.getIcon("paste.png"), "Insert clipboard contents.", KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
        pasteItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                WidgetMgr.STATUS_LABEL.setText("");
                WidgetMgr.MAIN_WINDOW.paste();
            }
        });
        menu.add(pasteItem);
        
        deleteItem = new MenuItem("Delete", Mgr.icon.getIcon("delete.png"), "Delete the selection.", KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        deleteItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                WidgetMgr.STATUS_LABEL.setText("");
                WidgetMgr.MAIN_WINDOW.delete();
            }
        });
        menu.add(deleteItem);
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
        TreeItem item = getCurrentItem();
        boolean enabled = item != null;
        
        newMapItem.setEnabled(enabled);
        copyItem.setEnabled(enabled);
        pasteItem.setEnabled(CopyPasteMgr.isDataPastable(DataMap.class));
        deleteItem.setEnabled(enabled);
        
        if(item == root()){
            copyItem.setEnabled(false);
            deleteItem.setEnabled(false);
        }
        
        WidgetMgr.MAIN_WINDOW.setActionEnabled("cut", false);
        WidgetMgr.MAIN_WINDOW.setActionEnabled("copy", copyItem.isEnabled());
        WidgetMgr.MAIN_WINDOW.setActionEnabled("paste", pasteItem.isEnabled());
        WidgetMgr.MAIN_WINDOW.setActionEnabled("delete", deleteItem.isEnabled());
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
        if(!newMapItem.isEnabled()){
            return;
        }
                
        DataMap gameData = new DataMap(generateId(), "" , 20, 20);
        gameData.name = "Map" + gameData.id;
        DataEditorMap editorData = new DataEditorMap();
        
        DatabaseTreeItem item = new DatabaseTreeItem(gameData.name, null, gameData, editorData);
        
        TreeItem parentItem = getCurrentItem();
        
        addItem(item, parentItem);
        expandPath(getSelectionPath());
        

    }
    
}
