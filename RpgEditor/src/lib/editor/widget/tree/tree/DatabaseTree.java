/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.tree.tree;

import java.util.ArrayList;
import java.util.List;
import lib.editor.data.editor.DataEditorBase;
import lib.editor.data.game.DataBase;
import lib.editor.widget.tree.interfaces.TreeWithDatabase;
import lib.editor.widget.tree.item.DatabaseTreeItem;
import lib.editor.widget.tree.item.TreeItem;

/**
 *
 * @author gaetan
 */
public abstract class DatabaseTree extends TreeMenu implements TreeWithDatabase{
    
    List<DataBase> gameDatabase;
    List<DataEditorBase> editorDatabase;
    
    public void setDatabase(List<DataBase> gameDatabase, List<DataEditorBase> editorDatabase){
        this.gameDatabase = gameDatabase;
        this.editorDatabase = editorDatabase;
        refresh();
        checkEnabledMenuAction();
    }
    
    public int generateId(){
        int id = 1;
        List<Integer> allId = new ArrayList<Integer>();
        

        for(TreeItem item : getAllItems()){
            
            DatabaseTreeItem dataItem = (DatabaseTreeItem) item;
            allId.add(dataItem.gameData.id);
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
    
}
