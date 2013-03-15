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
import lib.editor.util.Cst;
import lib.editor.widget.tree.item.FilePathTreeItem;
import lib.editor.widget.tree.item.TreeItem;
import lib.editor.widget.tree.tree.Tree;
import lib.editor.widget.tree.tree.option.TreeExpandMemorizer;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author gaetan
 */
public class AssetManagerAssetsTree extends Tree{

    AssetManagerAssetFolderList folderList;
    public TreeExpandMemorizer expandMemorizer;
    String filterText;
    
    public AssetManagerAssetsTree() {
        setRowHeight(20);
        expandMemorizer = new TreeExpandMemorizer(this);
        filterText = "";
    }
    
    public void setFilterText(String filterText){
        this.filterText = filterText.toUpperCase();
    }
    
    public void refresh(){
        clear();
        if(folderList.getCurrentItem() == null){
            return;
        }
        
        String folderName = folderList.getCurrentItem().getText();
        
        File file = new File(ProjectMgr.getAssetsPath(), folderName);
        
        if(filterText.equals("")){
            refreshRec(file, getRoot());
        }
        else{
            refreshFilterRec(file);
        }
        
        
        expandMemorizer.applyExpensions();
    }
    
    private void refreshRec(File parentFile, TreeItem parentItem){
        for(File child : parentFile.listFiles()){
            if(child.isFile() && !isValidFormat(child.getName())){
                continue;
            }
            
            FilePathTreeItem item = generateFileItem(child);
            parentItem.addChild(item);
            if(child.isDirectory()){
                refreshRec(child, item);
            }
        }
    }
    
    public void refreshFilterRec(File parentFile){
        for(File child : parentFile.listFiles()){
            if(child.isFile() && !isValidFormat(child.getName())){
                continue;
            }    
            
            if(child.isFile()){
                String text = FilenameUtils.getBaseName(child.getAbsolutePath());
                if(text.toUpperCase().contains(filterText)){
                    FilePathTreeItem item = generateFileItem(child);
                    addTopLevelItem(item);
                }
                
            }
            
            //parentItem.addChild(item);
            if(child.isDirectory()){
                refreshFilterRec(child);
            };
        }
    }
    
    public FilePathTreeItem generateFileItem(File file){
        String relativePath = new File(ProjectMgr.getAssetsPath()).toURI().relativize(new File(file.getAbsolutePath()).toURI()).getPath();
        
        ImageIcon icon = null;
        if(file.isDirectory()){
            icon = Mgr.icon.getIcon("folder.png");
        }
        else{
            String ext = FilenameUtils.getExtension(file.getName());
            icon = Mgr.icon.getIcon(ext + ".png");
        }
        
        String text = FilenameUtils.getBaseName(file.getAbsolutePath());
        
        FilePathTreeItem item = new FilePathTreeItem(this, text, icon, relativePath);
        return item;
    }
    
    public boolean isValidFormat(String filename){
        String ext = FilenameUtils.getExtension(filename);
        
        for(String format : Cst.VALID_IMAGE_FORMAT){
            if(format.equals(ext)){
                return true;
            }
        }
        for(String format : Cst.VALID_SOUND_FORMAT){
            if(format.equals(ext)){
                return true;
            }
        }
        for(String format : Cst.VALID_SCRIPT_FORMAT){
            if(format.equals(ext)){
                return true;
            }
        }
        return false;
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
