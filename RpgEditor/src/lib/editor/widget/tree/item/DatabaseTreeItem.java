/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.tree.item;

import javax.swing.ImageIcon;
import lib.editor.data.editor.DataEditorBase;
import lib.editor.data.editor.DataEditorTreeItem;
import lib.editor.data.game.DataBase;
import lib.editor.widget.tree.tree.Tree;

/**
 *
 * @author gaetan
 */
public class DatabaseTreeItem extends TreeItem{

    public DataBase gameData;
    public DataEditorTreeItem editorData;
    
    public DatabaseTreeItem(Tree tree, String text, ImageIcon icon, DataBase gameData, DataEditorTreeItem editorData) {
        super(tree, text, icon);
        this.gameData = gameData;
        this.editorData = editorData;
        
    }
    
    public void addChild(DatabaseTreeItem item, boolean addToDatabase){
        if(addToDatabase){
            DataEditorBase data = item.editorData;
            editorData.children.add(data);
        }
        super.addChild(item);
    }
    
    public void removeChild(TreeItem item){
        DataEditorBase data = ((DatabaseTreeItem) item).editorData;
        
        editorData.children.remove(data);
        super.removeChild(item);
    }
    
}
