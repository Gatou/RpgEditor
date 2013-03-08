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
    public String name;
    
    public DataEditorBase(int id, String name){
        this.id = id;
        this.name = name;
    }
    
    public String getIdName(){
        if(id < 10){
            return "000" + id;
        }
        else if(id >= 10 && id < 100){
            return "00" + id;
        }
        else if(id >= 100 && id < 1000){
            return "0" + id;
        }
        else{
            return "" + id;
        }
    }
    
}
