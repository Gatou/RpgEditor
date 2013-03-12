/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.tree.tree;

import java.awt.Rectangle;
import lib.editor.widget.tree.tree.option.TreeFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import lib.editor.widget.tree.item.TreeItem;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu.Separator;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import lib.editor.data.editor.DataEditorBase;
import lib.editor.data.editor.DataEditorMap;
import lib.editor.data.editor.DataEditorTreeItem;
import lib.editor.data.game.DataBase;
import lib.editor.data.game.DataMap;
import lib.editor.mgr.AppMgr;
import lib.editor.mgr.TransferMgr;
import lib.editor.mgr.DataMgr;
import lib.editor.mgr.IconMgr;
import lib.editor.mgr.Mgr;
import lib.editor.mgr.ProjectMgr;
import lib.editor.mgr.WidgetMgr;
import lib.editor.util.Shortcut;
import lib.editor.widget.inspector.Inspector;
import lib.editor.widget.inspector.PropertyPanel;
import lib.editor.widget.menu.MenuItem;
import lib.editor.widget.tree.item.DatabaseTreeItem;
import lib.editor.widget.tree.tree.option.MapTreeFilter;

/**
 *
 * @author gaetan
 */
public class MapTree extends DatabaseTree {
    
    public final static String ROOT_NAME = "Project";
    public DatabaseTreeItem rootItem;
            
    MenuItem newMapItem, copyItem, pasteItem, deleteItem;
    //MapTreeFilter filter;
            
    
    public MapTree(){
        super();

    }
    
    public void setFilter(JTextField filterTextField){
        filter = new MapTreeFilter(this, filterTextField);
    }

    public void createMenu() {
        
        newMapItem = new MenuItem("New map", null, "Create a new map.", KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0));
        newMapItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                WidgetMgr.STATUS_LABEL.setText("");
                newMap(false);
            }
        });
        menu.add(newMapItem);
        
        menu.add(new Separator());
        
        
        copyItem = new MenuItem("Copy", Mgr.icon.getIcon("copy.png"), "Copy the selection and put it on the clipboard.", KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
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
        new Shortcut(this, KeyStroke.getKeyStroke(KeyEvent.VK_INSERT, 0), "NEW_MAP", false, 
                new AbstractAction(){
                    public void actionPerformed(ActionEvent e) {
                        newMap(false);
                    }
                });
        //copy
        new Shortcut(this, KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK), "COPY", false, 
                new AbstractAction(){
                    public void actionPerformed(ActionEvent e) {
                        WidgetMgr.MAIN_WINDOW.copy();
                    }
                });

        //paste
        new Shortcut(this, KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK), "PASTE", false, 
                new AbstractAction(){
                    public void actionPerformed(ActionEvent e) {
                        WidgetMgr.MAIN_WINDOW.paste();
                    }
                });
        //delete
        new Shortcut(this, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "DELETE", false, 
                new AbstractAction(){
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
        pasteItem.setEnabled(TransferMgr.isEditorDataPastable(DataEditorMap.class));
        deleteItem.setEnabled(enabled);
        
        if(filter.isFiltering){
            //newMapItem.setEnabled(false);
            //pasteItem.setEnabled(false);
            deleteItem.setEnabled(false);
        }      
        else if(item == rootItem){
            copyItem.setEnabled(false);
            deleteItem.setEnabled(false);
        }
        

        WidgetMgr.MAIN_WINDOW.setActionEnabled("new map", newMapItem.isEnabled());
        WidgetMgr.MAIN_WINDOW.setActionEnabled("cut", false);
        WidgetMgr.MAIN_WINDOW.setActionEnabled("copy", copyItem.isEnabled());
        WidgetMgr.MAIN_WINDOW.setActionEnabled("paste", pasteItem.isEnabled());
        WidgetMgr.MAIN_WINDOW.setActionEnabled("delete", deleteItem.isEnabled());
    }
    
    //public DatabaseTreeItem getMapRoot(){
    //    return rootItem;
    //}
    
    /*
    public List<TreeItem> getAllItems(){
        List<TreeItem> result = super.getAllItems();
        result.remove(0);
        return result;
    }*/
    
    public void setup(){
        DataEditorBase rootMapEditorData = (DataEditorBase) DataMgr.load(new File(ProjectMgr.getDataEditorPath(), "MapInfos" + "." + AppMgr.getExtension("data file")).getAbsolutePath());
        rootItem = new DatabaseTreeItem(this, ROOT_NAME, Mgr.icon.getIcon("project_root.png"), null, null);
        
        refresh(rootMapEditorData);
        
        checkEnabledMenuAction();
        /*
        setCurrentItem(rootItem);
        for(int i=0; i<2000; i++){
            if(i%5==0){
                setCurrentItem(root());
            }
            newMap(false);
        }*/
        
    }
    
    public void refresh(DataEditorBase dataEditor){
        clear();
        
        rootItem.removeAllChildren();
        rootItem.editorData = (DataEditorTreeItem) dataEditor;
        addTopLevelItem(rootItem);
        refreshRec((DataEditorMap) dataEditor, (DatabaseTreeItem) rootItem);
        setItemExpanded(rootItem, true);
        
    }
        
    private void refreshRec(DataEditorMap dataEditorMap, DatabaseTreeItem parentItem){
        for(DataEditorBase data : dataEditorMap.children){
            //System.out.println(data.name);
            DatabaseTreeItem item = new DatabaseTreeItem(this, data.name, null, null, (DataEditorTreeItem) data);
            //DatabaseTreeItem item = generateItem(null, data);
            //addItem(item, parentItem, false);
            parentItem.addChild(item, false);
            refreshRec((DataEditorMap) data, item);
            DataEditorTreeItem dataTree = (DataEditorTreeItem) item.editorData;
            
            if(dataTree.expanded){
                setItemExpanded(item, true);
            }
            
        }
        
    }
    
    public void newMap(boolean pastedData){
        checkEnabledMenuAction();
        if(!newMapItem.isEnabled()){ return; }
        
        DatabaseTreeItem parentItem = (DatabaseTreeItem) getCurrentItem();
        
        int id = generateId();
        
        DataMap gameData = null;
        DataEditorMap editorData = null;
        
        if(pastedData){
            gameData = (DataMap) TransferMgr.pasteGameData();
            if(gameData == null){
                gameData = new DataMap(0, "" , 4, 8);
            }
            //editorData = (DataEditorMap) TransferMgr.pasteEditorData();
        }
        else{
            gameData = new DataMap(0, "" , 4, 8);
            
        }
        
        gameData.id = id;
        gameData.name = "Map" + gameData.getIdName();
        editorData = new DataEditorMap(gameData.id, gameData.name);
        //editorData.id = gameData.id;
        //editorData.name = gameData.name;
        
        DatabaseTreeItem item = new DatabaseTreeItem(this, gameData.name, null, gameData, editorData);
        
        parentItem.addChild(item, true);
        setItemExpanded(parentItem, true);
        setCurrentItem(item);
        
        ((PropertyPanel) WidgetMgr.INSPECTOR.panels.get("property")).focusNameTextField();
        
        checkEnabledMenuAction();
    }
    
    
    public void copy(){
        checkEnabledMenuAction();
        if(!copyItem.isEnabled()){ return; }
        
        TransferMgr.copyEditorData(getCurrentEditorData());
        TransferMgr.copyGameData(getCurrentGameData());
        
        checkEnabledMenuAction();
    }
    
    public void paste(){
        checkEnabledMenuAction();
        if(!pasteItem.isEnabled()){ return; }
        
        newMap(true);
                
        checkEnabledMenuAction();
    }
    
    public void delete(){
        checkEnabledMenuAction();
        if(!deleteItem.isEnabled()){ return; }
        
        TreeItem item = getCurrentItem();
        TreeItem parentItem = (TreeItem) item.getParent();
        int index = parentItem.getIndex(item);
            
        parentItem.removeChild(item);
        //removeItem(item);
        
        int count = parentItem.getChildCount();
        if(count == 0){
            setCurrentItem(parentItem);
        }
        else{
            if(index == count){
                setCurrentItem((TreeItem) parentItem.getChildAt(index-1));
            }
            else{
                setCurrentItem((TreeItem) parentItem.getChildAt(index));
            }
        }
        //if(parentItem.ch)
        //setCurrentItem(rootItem);
        
        checkEnabledMenuAction();
    }
    
    //public List<TreeItem> getItems(){
        //List<TreeItem> result = super.getItems();
        //result.remove(0);
        //return result;
    //}
    
    public void save(){
        
        List<TreeItem> items = getItems();
        items.remove(0); //Remove the tree root ("Project")
        
        for(TreeItem item : items){
            DatabaseTreeItem dataItem = (DatabaseTreeItem) item;
            
            if(dataItem.gameData != null){
                File file = new File(ProjectMgr.getDataGamePath(), "Map" + dataItem.gameData.getIdName() + "." + AppMgr.getExtension("data file"));
                DataMgr.dump(dataItem.gameData, file.getAbsolutePath());
                dataItem.gameData = null;
            }
        }
        
        
        DataEditorMap rootMapEditorData = (DataEditorMap) rootItem.editorData;
        
        File file = new File(ProjectMgr.getDataEditorPath(), "MapInfos" + "." + AppMgr.getExtension("data file"));
        DataMgr.dump(rootMapEditorData, file.getAbsolutePath());
        
        for(DatabaseTreeItem item : deletedItems){
            file = new File(ProjectMgr.getDataGamePath(), "Map" + item.editorData.getIdName() + "." + AppMgr.getExtension("data file"));
            file.delete();
        }
        deletedItems.clear();
    }
        
    public boolean itemWillCollapsed(TreeItem item){
        if(item == rootItem){
            return false;
        }
        return super.itemWillCollapsed(item);
    }
    
    public DataBase getGameData(TreeItem item){
        if(item == null){
            return null;
        }
        
        DatabaseTreeItem dataItem = (DatabaseTreeItem) item;
        DataBase data = dataItem.gameData;
        
        if(data == null){
            File file = new File(ProjectMgr.getDataGamePath(), "Map" + dataItem.editorData.getIdName() + "." + AppMgr.getExtension("data file"));
            return (DataBase) DataMgr.load(file.getAbsolutePath());
        }
        
        return data;
    }
    
    public DataBase getCurrentGameData(){
        return getGameData(getCurrentItem()); 
    }
    
    public void currentItemChanged(TreeItem newItem){
        super.currentItemChanged(newItem);
        WidgetMgr.INSPECTOR.setMapMode((DataMap) getGameData(newItem));
        WidgetMgr.MAP_EDITOR.refresh((DataMap) getGameData(newItem));
    }
    
    public void currentMapEdited(){
        DataMap data = (DataMap) getCurrentGameData();
        DatabaseTreeItem item = (DatabaseTreeItem) getCurrentItem();
        item.gameData = data;
    }
    
    public void mapNameChanged(String name){
        currentMapEdited();
        DataMap gameData = (DataMap) getCurrentGameData();
        DataEditorBase editorData = getCurrentEditorData();
        gameData.name = name;
        editorData.name = name;
        getCurrentItem().setText(name);
    }
    
    
}
