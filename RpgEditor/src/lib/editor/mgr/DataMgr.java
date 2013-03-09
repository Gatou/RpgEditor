/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.mgr;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import lib.editor.data.editor.DataEditorBase;
import lib.editor.data.editor.DataEditorMap;
import lib.editor.data.game.DataBase;
import lib.editor.data.game.DataMap;

/**
 *
 * @author gaetan
 */
public class DataMgr {
    
    private final static String[] dataEditorNames = {"MapInfos"};
    //public static Map<String, List> dataEditor = new Hashtable<String, List>();
    
    private final static String[] dataGameNames = {"Enemies", "Tilesets", "MapInfos"};
    //public static Map<String, List> dataGame = new Hashtable<String, List>();
    
    public static void init(){
        loadDatabase();
    }

    public static void loadDatabase(){
        File file = new File(ProjectMgr.getDataEditorPath(), "MapInfos" + "." + AppMgr.getExtension("data file"));
        

        if(!file.exists()){
            DataEditorMap rootMapEditorData = new DataEditorMap(0, "");
            //List<DataEditorMap> mapEditorDatabase = new ArrayList<DataEditorMap>();
            //mapEditorDatabase.add();
            //file2.createNewFile();
            dump(rootMapEditorData, file.getAbsolutePath());
        }
        
    }
    
    
    public static void save(){
        WidgetMgr.MAP_TREE.save();
        //File file1 = new File(ProjectMgr.getDataGamePath(), "MapInfos" + "." + AppMgr.getExtension("data file"));
        //File file = new File(ProjectMgr.getDataEditorPath(), "MapInfos" + "." + AppMgr.getExtension("data file"));
        //dump(dataEditor.get("MapInfos"), file.getAbsolutePath());

    }
    
    public static void dump(Object object, String path){
        try{
            FileOutputStream fos = new FileOutputStream(path);
            //fos.flush();
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            try {
                oos.writeObject(object); 
                oos.flush();
            } finally {
                try {
                        oos.close();
                } finally {
                        fos.close();
                }
            }
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    public static Object load(String path){
        if(! new File(path).exists()){
            return null;
        }
            
        Object object = null;
        try {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream ois = new ObjectInputStream(fis);
            try{
                object = ois.readObject();
            } finally {
                try {
                    ois.close();
                } finally {
                    fis.close();
                }
            }
        } catch(IOException ioe) {
                ioe.printStackTrace();
        } catch(ClassNotFoundException cnfe) {
                cnfe.printStackTrace();
        }
        return object;
    }
    
        
}
