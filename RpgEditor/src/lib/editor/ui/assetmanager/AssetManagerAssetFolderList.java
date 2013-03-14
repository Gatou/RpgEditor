/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.ui.assetmanager;

import java.io.File;
import lib.editor.mgr.Mgr;
import lib.editor.mgr.ProjectMgr;
import lib.editor.widget.list.item.ListItem;
import lib.editor.widget.list.list.List;

/**
 *
 * @author gaetan
 */
public class AssetManagerAssetFolderList extends List{

    AssetManagerAssetsTree assetTree;
    
    public AssetManagerAssetFolderList() {
        //addItem(new ListItem("onjours", null));
    }
    
    public void refresh(){
        clear();
        
        File assetsFolder = new File(ProjectMgr.getAssetsPath());
        for (String name : assetsFolder.list()) {
            addItem(new ListItem(name, Mgr.icon.getIcon("folder.png")));
        }
    }
    
    public void currentItemChanged(ListItem item, int row){
        assetTree.refresh();
    }
    
    public void setAssetTree(AssetManagerAssetsTree assetTree){
        this.assetTree = assetTree;
    }
    
}
