/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.util;

import java.io.File;
import lib.editor.mgr.ProjectMgr;

/**
 *
 * @author gaetan
 */
public class Util {
    
    public static String getRelativePath(File absoluteFile, File baseFile){
        return baseFile.toURI().relativize(absoluteFile.toURI()).getPath();
    }
}
