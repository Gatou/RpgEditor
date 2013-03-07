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
public class DataEditorMap extends DataEditorBase{
    
    public int id;
    public boolean expanded;
    public List<DataEditorBase> children;
    //int parentId;
    
    public DataEditorMap(int id){
        super(id);
        expanded = false;
        children = new ArrayList<DataEditorBase>();
    }
    
    
}
