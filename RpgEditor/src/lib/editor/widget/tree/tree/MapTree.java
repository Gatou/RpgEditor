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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import lib.editor.widget.tree.item.TreeItem;
import javax.swing.AbstractAction;
import javax.swing.Icon;
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
import lib.editor.widget.menu.MenuItem;
import lib.editor.widget.tree.item.DatabaseTreeItem;
import lib.editor.widget.tree.tree.option.MapTreeFilter;

/**
 *
 * @author gaetan
 */
public class MapTree extends DatabaseTree {
    
    public final static String ROOT_NAME = "Project";
    DatabaseTreeItem rootItem;
            
    MenuItem newMapItem, copyItem, pasteItem, deleteItem;
    MapTreeFilter filter;
            
    
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
                newMap();
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
        pasteItem.setEnabled(TransferMgr.isEditorDataPastable(DataEditorMap.class));
        deleteItem.setEnabled(enabled);
        
        if(filter.isFiltering){
            //newMapItem.setEnabled(false);
            //pasteItem.setEnabled(false);
            deleteItem.setEnabled(false);
        }      
        else if(item == root()){
            copyItem.setEnabled(false);
            deleteItem.setEnabled(false);
        }
        

        WidgetMgr.MAIN_WINDOW.setActionEnabled("new map", newMapItem.isEnabled());
        WidgetMgr.MAIN_WINDOW.setActionEnabled("cut", false);
        WidgetMgr.MAIN_WINDOW.setActionEnabled("copy", copyItem.isEnabled());
        WidgetMgr.MAIN_WINDOW.setActionEnabled("paste", pasteItem.isEnabled());
        WidgetMgr.MAIN_WINDOW.setActionEnabled("delete", deleteItem.isEnabled());
    }
    
    public DatabaseTreeItem root(){
        return rootItem;
    }
    
    public List<TreeItem> getAllItems(){
        List<TreeItem> result = super.getAllItems();
        result.remove(0);
        return result;
    }
    
    public void setup(){
        DataEditorBase rootMapEditorData = (DataEditorBase) DataMgr.load(new File(ProjectMgr.getDataEditorPath(), "MapInfos" + "." + AppMgr.getExtension("data file")).getAbsolutePath());
        rootItem = new DatabaseTreeItem(ROOT_NAME, Mgr.icon.getIcon("project_root.png"), null, null);
        
        refresh(rootMapEditorData);
        
        checkEnabledMenuAction();
    }
    
    public void refresh(DataEditorBase dataEditor){
        clear();
        
        rootItem.editorData = dataEditor;
        addTopLevelItem(rootItem);
        
        refreshRec((DataEditorMap) dataEditor, (DatabaseTreeItem) root());
        setItemExpanded(root());
        
    }
        
    private void refreshRec(DataEditorMap dataEditorMap, DatabaseTreeItem parentItem){
        for(DataEditorBase data : dataEditorMap.children){
            DatabaseTreeItem item = new DatabaseTreeItem(data.name, null, null, data);
            //DatabaseTreeItem item = generateItem(null, data);
            addItem(item, parentItem);
            refreshRec((DataEditorMap) data, item);
            DataEditorTreeItem dataTree = (DataEditorTreeItem) item.editorData;
            if(dataTree.expanded){
                setItemExpanded(item);
            }
        }
        
    }
    
    public void newMap(){
        checkEnabledMenuAction();
        
        if(!newMapItem.isEnabled()){ return; }
        
        DatabaseTreeItem parentItem = (DatabaseTreeItem) getCurrentItem();
        
        int id = generateId();
        DataMap gameData = new DataMap(id, "" , 32, 24);
        gameData.name = "Map" + gameData.getIdName();
        
        DataEditorMap editorData = new DataEditorMap(id, gameData.name);
                
        DatabaseTreeItem item = new DatabaseTreeItem(gameData.name, null, gameData, editorData);
        
        addItem(item, parentItem, true);
        setItemExpanded(parentItem);
        //expandPath(getSelectionPath());
        
        //item.gameData = null;
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
        
        
        
        checkEnabledMenuAction();
    }
    
    public void delete(){
        checkEnabledMenuAction();
        if(!deleteItem.isEnabled()){ return; }
        
        TreeItem item = getCurrentItem();
        TreeItem parentItem = (TreeItem) item.getParent();
        int index = parentItem.getIndex(item);
                
        removeItem(item);
        
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
    
    public void save(){
        
        for(TreeItem item : getAllItems()){
            DatabaseTreeItem dataItem = (DatabaseTreeItem) item;
            
            if(dataItem.gameData != null){
                File file = new File(ProjectMgr.getDataGamePath(), "Map" + dataItem.gameData.getIdName() + "." + AppMgr.getExtension("data file"));
                DataMgr.dump(dataItem.gameData, file.getAbsolutePath());
                dataItem.gameData = null;
            }
        }
        
        DataEditorMap rootMapEditorData = (DataEditorMap) root().editorData;
        
        File file = new File(ProjectMgr.getDataEditorPath(), "MapInfos" + "." + AppMgr.getExtension("data file"));
        DataMgr.dump(rootMapEditorData, file.getAbsolutePath());
        
        for(DatabaseTreeItem item : deletedItems){
            file = new File(ProjectMgr.getDataGamePath(), "Map" + item.editorData.getIdName() + "." + AppMgr.getExtension("data file"));
            file.delete();
        }
        deletedItems.clear();
    }
        
    public boolean itemCollapsed(TreeItem item){
        if(item == root()){
            return false;
        }
        return super.itemCollapsed(item);
    }
    
}
