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
	
}
