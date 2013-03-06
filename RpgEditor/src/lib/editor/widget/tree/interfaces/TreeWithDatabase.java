/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.tree.interfaces;

import java.util.List;
import lib.editor.data.editor.DataEditorBase;
import lib.editor.data.game.DataBase;

/**
 *
 * @author gaetan
 */
public interface TreeWithDatabase {
    
    public void setDatabase(List<DataBase> gameDatabase, List<DataEditorBase> editorDatabase);
    
}
