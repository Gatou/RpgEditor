/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.util;

/**
 *
 * @author gaetan
 */
public class ProjectError {
    
    public static final int FOLDER_MISSING = 0;
    public static final int FILE_MISSING = 1;

    public int errorType;
    public String path;

    public ProjectError(int errorType, String path){
        this.errorType = errorType;
        this.path = path;
    }
        
    
}
