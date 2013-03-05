/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.mgr;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author gaetan
 */
public class ProjectMgr {
    
    private static String projectPath;
    private static String assetsPath;
    private static String settingsPath;
    private static String dataPath;
    
    public static String getProjectPath(){
        return projectPath;
    }
    
    public static String getAssetsPath(){
        return assetsPath;
    }
    
    public static String getSettingsPath(){
        return settingsPath;
    }
    
    public static String getDataPath(){
        return dataPath;
    }
    
    public static void createNewProject(String path){
        //Create usefull path (project, assets, etc...)
        createPath(path);
        //Create project folders
        (new File(projectPath)).mkdir();
        (new File(assetsPath)).mkdir();
        (new File(settingsPath)).mkdir();
        (new File(dataPath)).mkdir();
        //Create the project file
        File file = new File(projectPath, "project." + AppMgr.getExtension("project file"));
        try {
            file.createNewFile();
              FileWriter fstream = new FileWriter(file);
              BufferedWriter out = new BufferedWriter(fstream);
              out.write(AppMgr.getNameVersion());
              out.close();
        } catch (IOException ex) {
            Logger.getLogger(ProjectMgr.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        openProject(projectPath);
        /*
        #---
        file = open(join(ProjectMgr.project_path(), "." + AppMgr.extension("project file")), "w") #create .proj file
        file.write(AppMgr.name_version())
        file.close()
        #--
        ProjectMgr.open_project(ProjectMgr.project_path())*/
                }
    
    public static void openProject(String path){
        closeProject();
        createPath(path);
        checkProjectValid();
        
        if(projectPath != null){
            WidgetMgr.MAIN_WINDOW.setTitle((new File(projectPath)).getName() + " - " + AppMgr.getNameVersion());
            DataMgr.init();
            WidgetMgr.MAIN_WINDOW.setProjectStateEnabled(true);
            loadSettings();
            WidgetMgr.MAIN_WINDOW.setVisible(true);
            
            
        }
    }
    
    public static void closeProject(){
        if(projectPath != null){
            saveSettings();
            WidgetMgr.MAIN_WINDOW.setTitle(AppMgr.getNameVersion());
        }
        WidgetMgr.MAIN_WINDOW.setProjectStateEnabled(false);
        assetsPath = null;
        settingsPath = null;
        projectPath = null;
        dataPath = null;
    }
    
    private static void createPath(String path){
        projectPath = (new File(path)).getAbsolutePath();
        assetsPath = (new File(projectPath, "Assets")).getAbsolutePath();
        settingsPath = (new File(projectPath, "ProjectSettings")).getAbsolutePath();
        dataPath = (new File(projectPath, "Data")).getAbsolutePath();
    }
    
    private static void checkProjectValid(){
        String errorMessage = "";
        
        if(!(new File(projectPath).exists())){
            errorMessage = "This project is invalid.\nCan't find project folder (" + projectPath + ")";
        }
        else if(!(new File(assetsPath).exists())){
            errorMessage = "This project is invalid.\nCan't find assets folder (" + assetsPath + ")";
        }
        else if(!(new File(dataPath).exists())){
            errorMessage = "This project is invalid.\nCan't find assets folder (" + dataPath + ")";
        }
        //else if(!(new File(settingsPath).exists())){
        //    errorMessage = "This project is invalid.\nCan't find settings folder (" + settingsPath + ")";
        //}
        
        
        if(errorMessage.equals("")){ //Valid project

        }
        else{ //Invalid project
            closeProject();
            WidgetMgr.MAIN_WINDOW.setVisible(true);
            
            JOptionPane.showMessageDialog(WidgetMgr.MAIN_WINDOW, errorMessage, "Invalid project", JOptionPane.ERROR_MESSAGE);
            
            
        }
    }
    
    public static void loadSettings(){
        //Check if the settings folder exist, if not create it
        //Check if the settings folder exist, if not create it
        File settingsFolder = new File(ProjectMgr.getSettingsPath());
        if(!settingsFolder.exists()){
            settingsFolder.mkdir();
        }
        
        File iniFile = new File(ProjectMgr.getSettingsPath(), "settings." + AppMgr.getExtension("settings file"));

        if(iniFile.exists()){
             try {
                Properties prop = new Properties();
                prop.load(new FileInputStream(iniFile));

                //save main window settings
                WidgetMgr.MAIN_WINDOW.loadSettings(prop);

             }
             catch(Exception ex) {
                System.out.println(ex.getMessage());
             }
        }
        
    }
    
    public static void saveSettings(){
        //Check if the settings folder exist, if not create it
        File settingsFolder = new File(ProjectMgr.getSettingsPath());
        if(!settingsFolder.exists()){
            settingsFolder.mkdir();
        }
        
        File iniFile = new File(ProjectMgr.getSettingsPath(), "settings." + AppMgr.getExtension("settings file"));
        try {
            iniFile.createNewFile();
            Properties prop = new Properties();
            
            //save main window settings
            WidgetMgr.MAIN_WINDOW.saveSettings(prop);

            prop.store(new FileOutputStream(iniFile), AppMgr.getNameVersion() + " project settings");
            
       }
       catch(IOException ioe) {
            System.out.println(ioe.getMessage());
       }
    }
}


