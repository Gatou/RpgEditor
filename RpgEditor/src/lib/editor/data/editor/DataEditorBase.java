/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.data.editor;

import java.io.Serializable;

/**
 *
 * @author gaetan
 */
public class DataEditorBase implements Serializable{
    
    
    private static final long serialVersionUID = 42L;

    public int id;
    
    public DataEditorBase(int id){
        this.id = id;
    }
    
}
