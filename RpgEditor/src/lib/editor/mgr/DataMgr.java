/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.mgr;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import lib.editor.data.DataBase;

/**
 *
 * @author gaetan
 */
public class DataMgr {
    
    private final static String[] dataNames = {"Enemies", "Tilesets", "MapInfos"};
    public static Map<String, List> data = new Hashtable<String, List>();
    
    public static void init(){
        loadDatabase();
    }

    public static void loadDatabase(){
        for(String dataName : dataNames){
            File file = new File(ProjectMgr.getDataPath(), dataName + "." + AppMgr.getExtension("data file"));
            List<DataBase> database;
            
            if(file.exists()){
                database = new ArrayList<DataBase>();
            }
            else{
                database = new ArrayList<DataBase>();
                try {
                    file.createNewFile();
                    
                } catch (IOException ex) {
                    Logger.getLogger(DataMgr.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            data.put(dataName, database);
                    
        }
    }
    
}
