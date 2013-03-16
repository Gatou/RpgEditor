/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.mgr;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.UIManager;

/**
 *
 * @author gaetan
 */
public class AppMgr {
    
    final public static String NAME = "Agm2D";
    final public static String VERSION = "0.1";
    
    public static void init(){
        UIManager.put("Item.disableText", new Color(150, 150, 150));
    }
    
    public static String getNameVersion(){
        return NAME + " v" + VERSION;
    }
    
    public static String getExtension(String ext){
        if(ext.equals("project file")){
            return "agmproj";
        }
        else if(ext.equals("data file")){
            return "agmdata";
        }
        else if(ext.equals("settings file")){
            return "agmset";
        }
        return "";
        /*
        else if(ext.equals("project file")){
            return "agmproj";
        }
        else if(ext.equals("project file")){
            return "agmproj";
        }
        elif ext == "":
            return "agmsc"
        elif ext == "settings":
            return "agmset"*/
    }

    
    public static void loadSettings(){
        File settingsFolder = new File("settings");
        if(!settingsFolder.exists()){
            settingsFolder.mkdir();
        }

        File iniFile = new File("settings", "settings." + getExtension("settings file"));

        if(iniFile.exists()){
             try {
                Properties prop = new Properties();
                prop.load(new FileInputStream(iniFile));

                if(!prop.getProperty("last project path").equals("")){
                    ProjectMgr.openProject(prop.getProperty("last project path"));
                }

             }

             catch(Exception ex) {
                System.out.println(ex.getMessage());
             }
        }
    }
    

    
    public static void saveSettings(){
        //Check if the settings folder exist, if not create it
        File settingsFolder = new File("settings");
        if(!settingsFolder.exists()){
            settingsFolder.mkdir();
        }
        
        //create and fill the ini file
        File iniFile = new File("settings", "settings." + getExtension("settings file"));
        try {
            iniFile.createNewFile();
            Properties prop = new Properties();
            
            //Memorize last opened project
            if(ProjectMgr.getProjectPath() == null){
                prop.setProperty("last project path", "");
            }
            else{
                prop.setProperty("last project path", ProjectMgr.getProjectPath());
            }
           
            prop.store(new FileOutputStream(iniFile), AppMgr.getNameVersion() + " main settings");
       }
       catch(IOException ioe) {
            System.out.println(ioe.getMessage());
       }
    }
    
    


}
