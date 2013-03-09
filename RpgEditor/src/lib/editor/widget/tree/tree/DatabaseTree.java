/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.tree.tree;

import java.util.ArrayList;
import java.util.List;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import lib.editor.data.editor.DataEditorBase;
import lib.editor.data.editor.DataEditorTreeItem;
import lib.editor.data.editor.DataEditorTreeItem;
import lib.editor.data.game.DataBase;
import lib.editor.widget.tree.interfaces.TreeWithDatabase;
import lib.editor.widget.tree.item.DatabaseTreeItem;
import lib.editor.widget.tree.item.TreeItem;

/**
 *
 * @author gaetan
 */
public abstract class DatabaseTree extends TreeMenu implements TreeWithDatabase{
    
    public List<DatabaseTreeItem> deletedItems;

    public DatabaseTree() {
        deletedItems = new ArrayList<DatabaseTreeItem>();
    }
    
    
    public int generateId(){
        int id = 1;
        List<Integer> allId = new ArrayList<Integer>();
        

        for(TreeItem item : getAllItems()){
            
            DatabaseTreeItem dataItem = (DatabaseTreeItem) item;
            allId.add(dataItem.editorData.id);
        }
        while(allId.contains(id)){
            id += 1;
        }
        
        return id;
    }
    
    public DataBase getCurrentGameData(){
        DatabaseTreeItem item = (DatabaseTreeItem) getCurrentItem();
        
        if(item == null){
            return null;
        }
        return item.gameData;
    }
    
    public DataEditorBase getCurrentEditorData(){
        DatabaseTreeItem item = (DatabaseTreeItem) getCurrentItem();
        if(item == null){
            return null;
        }
        return item.editorData;
    }
    
    public void addItem(TreeItem item, TreeItem parentItem, boolean addToDatabase){
        super.addItem(item, parentItem);
        DataEditorTreeItem parentData = (DataEditorTreeItem) ((DatabaseTreeItem) parentItem).editorData;
        if(addToDatabase){
            parentData.children.add( ((DatabaseTreeItem) item).editorData );
        }
    }
    
    public void removeItem(TreeItem item){
        
        
        DataEditorTreeItem data = (DataEditorTreeItem) ((DatabaseTreeItem) item).editorData;
        DataEditorTreeItem parentData = (DataEditorTreeItem) ((DatabaseTreeItem) item.getParent()).editorData;
        
        parentData.children.remove( data );
        
        deletedItems.add((DatabaseTreeItem) item);
        
        super.removeItem(item);
    }
    
    public void itemExpanded(TreeItem item){
        
        for(int i=0; i<item.getChildCount(); i++){
            DatabaseTreeItem childItem = (DatabaseTreeItem) item.getChildAt(i);
            if(((DataEditorTreeItem)childItem.editorData).expanded){
                setItemExpanded(childItem, true);
            }
        }
    }
    
    public boolean itemWillExpanded(TreeItem item){
        if(item instanceof DatabaseTreeItem){
            DatabaseTreeItem dataItem = (DatabaseTreeItem) item;
            DataEditorTreeItem data = (DataEditorTreeItem) dataItem.editorData;
            data.expanded = true;
        }

        return true;
    }
        
    public boolean itemWillCollapsed(TreeItem item){
        if(item instanceof DatabaseTreeItem){
            DatabaseTreeItem dataItem = (DatabaseTreeItem) item;
            DataEditorTreeItem data = (DataEditorTreeItem) dataItem.editorData;
            data.expanded = false;
        }
        return true;
    }
    
    public void setItemExpanded(TreeItem item, boolean expanded){
        
        if(!(item.getParent() == getRoot()) && expanded){
            DatabaseTreeItem dataItem = (DatabaseTreeItem) item.getParent();
            
            while(true){
                if( !((DataEditorTreeItem)dataItem.editorData).expanded){
                    return;
                }
                
                if(dataItem.getParent() == getRoot()){
                    break;
                }
                dataItem = (DatabaseTreeItem) dataItem.getParent();
            }
        }
        super.setItemExpanded(item, expanded);
        

    }
    
}
