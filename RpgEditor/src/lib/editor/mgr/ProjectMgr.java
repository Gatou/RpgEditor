/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.mgr;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author gaetan
 */
public class ProjectMgr {
    
    private static String projectPath;
    private static String assetsPath;
    private static String settingsPath;
    
    public static String getAssetsPath(){
        return assetsPath;
    }
    
    public static String getSettingsPath(){
        return settingsPath;
    }
    
    public static void createNewProject(String path) throws IOException{
        createPath(path);
        (new File(projectPath)).mkdir();
        (new File(assetsPath)).mkdir();
        (new File(settingsPath)).mkdir();
        
        File file = new File(projectPath, ".project");
        file.createNewFile();
        
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
        createPath(path);
    }
    
    private static void createPath(String path){
        projectPath = (new File(path)).getAbsolutePath();
        assetsPath = (new File(projectPath, "Assets")).getAbsolutePath();
        settingsPath = (new File(projectPath, "ProjectSettings")).getAbsolutePath();
    }
}



/*

import os
from os.path import join, normpath
from agm3d.editor import *

#------------------------------------------------------------------
# ** ProjectMgr
#------------------------------------------------------------------
class ProjectMgr():
    """
    This static class provides methods for creating or opening a project.
    It also holds paths relative to where the project has been opened.
    """
    
    __PROJECT_PATH = None
    __ASSETS_PATH = None
    __SETTINGS_PATH = None
        
    #------------------------------------------------------------------
    # * project_path
    #------------------------------------------------------------------
    @staticmethod
    def project_path():
        """
        Return the absolute path to the current project.
        @rtype: str
        """
        return ProjectMgr.__PROJECT_PATH
    
    #------------------------------------------------------------------
    # * assets_path
    #------------------------------------------------------------------
    @staticmethod
    def assets_path():
        """
        Return the absolute path to the project assets folder.
        @rtype: str
        """
        return ProjectMgr.__ASSETS_PATH
    
    #------------------------------------------------------------------
    # * settings_path
    #------------------------------------------------------------------
    @staticmethod
    def settings_path():
        """
        Return the absolute path to the project settings folder.
        @rtype: str
        """
        return ProjectMgr.__SETTINGS_PATH
    
    #------------------------------------------------------------------
    # * create_new_project
    #------------------------------------------------------------------
    @staticmethod
    def create_new_project(path):
        """
        Create a new project at the indicated I{path} and open it.
        @param path: The path where to create the new project.
        @type path: str
        """
        ProjectMgr.__create_path(path)
        os.makedirs(ProjectMgr.project_path()) #create project folder path
        os.makedirs(ProjectMgr.assets_path()) #create assets folder
        os.makedirs(ProjectMgr.settings_path()) #create settings folder
        os.makedirs(join(ProjectMgr.settings_path(), "Layouts")) #create user custom window layouts folder
        #---
        file = open(join(ProjectMgr.project_path(), "." + AppMgr.extension("project file")), "w") #create .proj file
        file.write(AppMgr.name_version())
        file.close()
        #--
        ProjectMgr.open_project(ProjectMgr.project_path())
      
    #------------------------------------------------------------------
    # * open_project
    #------------------------------------------------------------------
    @staticmethod
    def open_project(path):
        """
        Open an existing project at the indicated I{path}.
        @param path: The path to the project.
        @type path: str
        """
        ProjectMgr.__create_path(path)
        
    #------------------------------------------------------------------
    # * create_path
    #------------------------------------------------------------------
    @staticmethod  
    def __create_path(project_path):
        ProjectMgr.__PROJECT_PATH = normpath(project_path)
        ProjectMgr.__ASSETS_PATH = normpath(join(ProjectMgr.project_path(), "Assets"))
        ProjectMgr.__SETTINGS_PATH = normpath(join(ProjectMgr.project_path(), "ProjectSettings"))
    

*/