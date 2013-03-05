/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.mgr;

/**
 *
 * @author gaetan
 */
public class AppMgr {
    
    final public static String NAME = "Agm2D";
    final public static String VERSION = "0.1";
    
    public static void init(){
        
    }
    
    public static String getNameVersion(){
        return NAME + " v" + VERSION;
    }
    
    public static String getExtension(String ext){
        if(ext.equals("project file")){
            return "agmproj";
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
    
    
}
