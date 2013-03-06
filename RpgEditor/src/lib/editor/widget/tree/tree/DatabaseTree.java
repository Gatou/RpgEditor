/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.tree.tree;

import java.util.List;
import lib.editor.data.editor.DataEditorBase;
import lib.editor.data.game.DataBase;
import lib.editor.widget.tree.interfaces.TreeWithDatabase;

/**
 *
 * @author gaetan
 */
public abstract class DatabaseTree extends TreeContextMenu implements TreeWithDatabase{
    
    List<DataBase> gameDatabase;
    List<DataEditorBase> editorDatabase;
    
    public void setDatabase(List<DataBase> gameDatabase, List<DataEditorBase> editorDatabase){
        this.gameDatabase = gameDatabase;
        this.editorDatabase = editorDatabase;
    }
}
