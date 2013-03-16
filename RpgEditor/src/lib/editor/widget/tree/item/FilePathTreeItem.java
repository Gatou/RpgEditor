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
    
    public FilePathTreeItem(Tree tree, String text, String iconFilename, String filePath) {
        super(tree, text, iconFilename);
        this.filePath = filePath;
    }
    
    public String getFilePath(){
        return filePath;
    }
    
    public boolean isLeaf(){
        File file = new File(ProjectMgr.getAssetsPath(), filePath);
        if(file.isFile()){
            return true;
        }
        if(file.list().length == 0){
            return true;
        }
        return false;
    }
}
