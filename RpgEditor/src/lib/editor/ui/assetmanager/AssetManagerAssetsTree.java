/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.ui.assetmanager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import lib.editor.mgr.Mgr;
import lib.editor.mgr.ProjectMgr;
import lib.editor.mgr.WidgetMgr;
import lib.editor.util.Cst;
import lib.editor.util.Util;
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
    List<String> formatFilter;
    //boolean refreshing;
    
    public AssetManagerAssetsTree() {
        setRowHeight(20);
        expandMemorizer = new TreeExpandMemorizer(this);
        filterText = "";
        //refreshing = false;
    }
    
    public void setFilterText(String filterText){
        this.filterText = filterText.toUpperCase();
    }
    
    public void refresh(){
        //refreshing = true;
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
        //refreshing = false;
        
        //expandMemorizer.applyExpensions();
    }
    
    private void refreshRec(File parentFile, TreeItem parentItem){
        for(File child : parentFile.listFiles()){
            if(child.isFile() && !isValidFormat(child.getName())){
                continue;
            }
            
            FilePathTreeItem item = generateFileItem(child);
            parentItem.addChild(item);
            if(child.isDirectory()){
                if(expandMemorizer.isExpanded(item.getFilePath())){
                    //refreshRec(child, item);
                    item.setExpanded(true);
                }
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
        String relativePath = Util.getRelativePath(file, new File(ProjectMgr.getAssetsPath()));
        //String relativePath = new File(ProjectMgr.getAssetsPath()).toURI().relativize(new File(file.getAbsolutePath()).toURI()).getPath();
        
        boolean enabled = true;
        String iconFilename = null;
        
        if(file.isDirectory()){
            iconFilename = "folder.png";
        }
        else{
            String ext = FilenameUtils.getExtension(file.getName());
            iconFilename = ext + ".png";
            enabled = formatFilter.contains(ext);
        }
        
        String text = FilenameUtils.getBaseName(file.getAbsolutePath());
        
        FilePathTreeItem item = new FilePathTreeItem(this, text, iconFilename, relativePath, file);
        
        item.setEnabled(enabled);
            
        return item;
    }
    
    public boolean isValidFormat(String filename){
        String ext = FilenameUtils.getExtension(filename);
        return Cst.ALL_ASSET_FORMAT.contains(ext);
    }
    
    public void setFolderList(AssetManagerAssetFolderList folderList){
        this.folderList = folderList;
    }
    
    public void currentItemChanged(TreeItem item){
        if(item == null){
            return;
        }
        
        FilePathTreeItem pathItem = (FilePathTreeItem) item;
        
        WidgetMgr.ASSET_MANAGER_WINDOW.setAsset(pathItem.getFilePath());
        //System.out.println(pathItem.getFilePath());
    }

    public void itemDoubleClicked(TreeItem item){
        super.itemDoubleClicked(item);
        WidgetMgr.ASSET_MANAGER_WINDOW.ok();
    }

    void setFormatFilter(List<String> formatFilter) {
        this.formatFilter = formatFilter;
    }
    
    public void itemExpanded(TreeItem item){
        //if(!refreshing){
            if(item instanceof FilePathTreeItem){
                FilePathTreeItem pathItem = (FilePathTreeItem) item;
                File file = new File(ProjectMgr.getAssetsPath(), pathItem.getFilePath());
                refreshRec(file, item);
            }
        //}
        
        super.itemExpanded(item);
    }
    
    public void setCurrentItemByPath(String relativePath){
        File file = new File(ProjectMgr.getAssetsPath(), relativePath);
        
        if(!file.exists()){return;}
        
        List<File> files = new ArrayList<File>();
        while(file.getParent() != null && !file.getParent().equals(ProjectMgr.getAssetsPath())){
            files.add(file);
            file = file.getParentFile();
        }
        
        if(file.getParent() == null){
            return;
        }
        
        for(int i=files.size()-1; i>=0; i--){
            
            file = files.get(i);
            String relPath = Util.getRelativePath(file, new File(ProjectMgr.getAssetsPath()));
            FilePathTreeItem item = getItemByPath(relPath);
            
            
            
            if(i==0){
                setCurrentItem(item);
            }
            else{
                item.setExpanded(true);
            }
        }
        
    }
    
    public FilePathTreeItem getItemByPath(String relativePath){
        for(TreeItem item : getItems()){
            if(((FilePathTreeItem) item).getFilePath().equals(relativePath)){
                return (FilePathTreeItem) item;
            }
        }
        return null;
    }
    
}
