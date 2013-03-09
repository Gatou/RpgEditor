/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.mgr;

import java.awt.Component;
import lib.editor.util.deepcopy.DeepCopy;

/**
 *
 * @author gaetan
 */
public class TransferMgr {
    
    private static Object copiedGameData;
    private static Object copiedEditorData;
    public static Object lastFocused;
    
    public static void copyGameData(Object data){
        //System.out.println(data);
        copiedGameData = DeepCopy.copy(data);
    }
    
    public static void copyEditorData(Object data){
        copiedEditorData = DeepCopy.copy(data);
    }
    
    public static boolean isEditorDataPastable(Object className){
        return copiedEditorData != null && copiedEditorData.getClass() == className;
    }
    
    public static Object pasteGameData(){
        return DeepCopy.copy(copiedGameData);
    }
    
    public static Object pasteEditorData(){
        return DeepCopy.copy(copiedEditorData);
    }
}
