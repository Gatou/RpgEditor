/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.tree.item;

import javax.swing.ImageIcon;
import lib.editor.data.editor.DataEditorBase;
import lib.editor.data.game.DataBase;

/**
 *
 * @author gaetan
 */
public class DatabaseTreeItem extends TreeItem{

    public DataBase gameData;
    public DataEditorBase editorData;
    
    public DatabaseTreeItem(String text, ImageIcon icon, DataBase gameData, DataEditorBase editorData) {
        super(text, icon);
        this.gameData = gameData;
        this.editorData = editorData;
        
    }
    
}
