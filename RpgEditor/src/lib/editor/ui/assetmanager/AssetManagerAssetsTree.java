/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.ui.assetmanager;

import java.io.File;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import lib.editor.mgr.Mgr;
import lib.editor.mgr.ProjectMgr;
import lib.editor.widget.tree.item.FilePathTreeItem;
import lib.editor.widget.tree.item.TreeItem;
import lib.editor.widget.tree.tree.Tree;

/**
 *
 * @author gaetan
 */
public class AssetManagerAssetsTree extends Tree{

    AssetManagerAssetFolderList folderList;
    
    public AssetManagerAssetsTree() {
    }
    
    public void refresh(){
        clear();
        if(folderList.getCurrentItem() == null){
            return;
        }
        
        String folderName = folderList.getCurrentItem().getText();
        
        File file = new File(ProjectMgr.getAssetsPath(), folderName);
        for(File child : file.listFiles()){
            FilePathTreeItem item = generateFileItem(child);
            addTopLevelItem(item);
            if(child.isDirectory()){
                refreshRec(child, item);
            }
        }
    }
    
    public void refreshRec(File parentFile, FilePathTreeItem parentItem){
        for(File child : parentFile.listFiles()){
            FilePathTreeItem item = generateFileItem(child);
            parentItem.addChild(item);
            if(child.isDirectory()){
                refreshRec(child, item);
            };
        }
    }
    
    public FilePathTreeItem generateFileItem(File file){
        String relativePath = new File(ProjectMgr.getAssetsPath()).toURI().relativize(new File(file.getAbsolutePath()).toURI()).getPath();
        
        ImageIcon icon = null;
        if(file.isDirectory()){
            icon = Mgr.icon.getIcon("folder.png");
        }
        
        
        FilePathTreeItem item = new FilePathTreeItem(this, file.getName(), icon, relativePath);
        return item;
    }
    
    public void setFolderList(AssetManagerAssetFolderList folderList){
        this.folderList = folderList;
    }
    
    public void currentItemChanged(TreeItem item){
        if(item == null){
            return;
        }
        
        FilePathTreeItem pathItem = (FilePathTreeItem) item;
        System.out.println(pathItem.getFilePath());
    }

    
}
