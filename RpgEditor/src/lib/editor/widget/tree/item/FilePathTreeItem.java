/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.tree.item;

import javax.swing.ImageIcon;
import lib.editor.widget.tree.tree.Tree;

/**
 *
 * @author gaetan
 */
public class FilePathTreeItem extends TreeItem{

    private String filePath;
    
    public FilePathTreeItem(Tree tree, String text, ImageIcon icon, String filePath) {
        super(tree, text, icon);
        this.filePath = filePath;
    }
    
    public String getFilePath(){
        return filePath;
    }
    
}
