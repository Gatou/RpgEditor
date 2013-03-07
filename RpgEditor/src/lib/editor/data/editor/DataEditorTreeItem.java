/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.data.editor;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gaetan
 */
public class DataEditorTreeItem extends DataEditorBase{
    
    public boolean expanded;
    public List<DataEditorBase> children;
    
    public DataEditorTreeItem(int id, String name){
        super(id, name);
        expanded = false;
        children = new ArrayList<DataEditorBase>();
    }
    
}
