/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.tree.item;

import java.io.File;
import javax.swing.ImageIcon;
import lib.editor.mgr.ProjectMgr;
import lib.editor.widget.tree.tree.Tree;

/**
 *
 * @author gaetan
 */
public class FilePathTreeItem extends TreeItem{

    private String filePath;
    private boolean isFile;
    private boolean hasChild;
    
    public FilePathTreeItem(Tree tree, String text, String iconFilename, String filePath, File file) {
        super(tree, text, iconFilename);
        this.filePath = filePath;
        
        //File file = new File(ProjectMgr.getAssetsPath(), filePath);
        isFile = file.isFile();
        hasChild = !isFile && file.list().length != 0;
    }
    
    public String getFilePath(){
        return filePath;
    }
    
    public boolean isLeaf(){
        return isFile || !hasChild;
        /*
        File file = new File(ProjectMgr.getAssetsPath(), filePath);
        if(file.isFile()){
            return true;
        }
        if(file.list().length == 0){
            return true;
        }
        return false;*/
    }
}
