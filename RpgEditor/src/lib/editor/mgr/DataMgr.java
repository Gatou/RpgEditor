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
import lib.editor.data.game.DataBase;

/**
 *
 * @author gaetan
 */
public class DataMgr {
    
    private final static String[] dataEditorNames = {"MapInfos"};
    public static Map<String, List> dataEditor = new Hashtable<String, List>();
    
    private final static String[] dataGameNames = {"Enemies", "Tilesets", "MapInfos"};
    public static Map<String, List> dataGame = new Hashtable<String, List>();
    
    public static void init(){
        loadDatabase();
    }

    public static void loadDatabase(){
        for(String dataName : dataGameNames){
            File file = new File(ProjectMgr.getDataGamePath(), dataName + "." + AppMgr.getExtension("data file"));
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
            
            dataGame.put(dataName, database);
        }
        
         for(String dataName : dataEditorNames){
            File file = new File(ProjectMgr.getDataEditorPath(), dataName + "." + AppMgr.getExtension("data file"));
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
            
            dataEditor.put(dataName, database);
        }
         
    }
    
}
