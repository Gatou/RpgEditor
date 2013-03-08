package lib.editor.data.game;

import java.io.Serializable;



public abstract class DataBase implements Serializable{

    
    private static final long serialVersionUID = 42L;

    public int id;
    public String name;

    public DataBase(int id, String name){
            this.id = id;
            this.name = name;
    }
    
    public String getIdName(){
        return getIdNameById(id);
    }
    
    public static String getIdNameById(int id){
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
