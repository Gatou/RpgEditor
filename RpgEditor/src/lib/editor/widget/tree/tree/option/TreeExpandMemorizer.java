/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.tree.tree.option;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import lib.editor.data.editor.DataEditorTreeItem;
import lib.editor.mgr.DataMgr;
import lib.editor.mgr.ProjectMgr;
import lib.editor.widget.tree.item.DatabaseTreeItem;
import lib.editor.widget.tree.item.FilePathTreeItem;
import lib.editor.widget.tree.item.TreeItem;
import lib.editor.widget.tree.tree.Tree;

/**
 *
 * @author gaetan
 */
public class TreeExpandMemorizer {

    Tree tree;
    private Map<String, Boolean> expanded;
            
    public TreeExpandMemorizer(final Tree tree) {
        this.tree = tree;
        expanded = new Hashtable<String, Boolean>();
        
        tree.addTreeExpansionListener(new TreeExpansionListener () {

            @Override
            public void treeExpanded(TreeExpansionEvent evt) {
                itemExpanded((TreeItem) evt.getPath().getLastPathComponent());

            }

            @Override
            public void treeCollapsed(TreeExpansionEvent evt) {
                itemCollapsed((TreeItem) evt.getPath().getLastPathComponent());

            }
        });
                
    }
    
    public void save(String name){
        File file = new File(ProjectMgr.getSettingsPath(), name);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException ex) {
                System.err.println("TreeExpandMemorizer -> save() -> file to create file");}
        }
        DataMgr.dump(expanded, file.getAbsolutePath());
        //expanded = (Map<String, Boolean>) DataMgr.load(file.getAbsolutePath());
    }
    
    public void load(String name){
        File file = new File(ProjectMgr.getSettingsPath(), name);
        if(file.exists()){
            expanded = (Map<String, Boolean>) DataMgr.load(file.getAbsolutePath());
        }
        clean();
    }
    
    public void clean(){
        List<String> unuseds = new ArrayList<String>();
        
        for(String path : expanded.keySet()){
            if(! new File(ProjectMgr.getAssetsPath(), path).exists()){
                unuseds.add(path);
            }
        }
        
        for(String unused : unuseds){
            expanded.remove(unused);
        }
    }
    
    public void applyExpensions(){
        for(TreeItem item : tree.getItems()){
            FilePathTreeItem pathItem = (FilePathTreeItem) item;
            try{
                if(expanded.get(pathItem.getFilePath())){
                    tree.setItemExpanded(pathItem, true);
                }
            }
            catch(NullPointerException e){
                
            }
        }
    }
    
    public void itemExpanded(TreeItem item){
        if(item == tree.getRoot()){return;}
        
        expand(((FilePathTreeItem) item).getFilePath());
        
        for(int i=0; i<item.getChildCount(); i++){
            FilePathTreeItem childItem = (FilePathTreeItem) item.getChildAt(i);
            if(childItem.isLeaf()){continue;}
            
            try{
                if(expanded.get(childItem.getFilePath())){
                    childItem.setExpanded(true);
                }
            } catch(NullPointerException e){}
        }
    }
    
    public void itemCollapsed(TreeItem item){
        if(item == tree.getRoot()){return;}
                
        FilePathTreeItem item2 = (FilePathTreeItem) item;
        collapse(item2.getFilePath());
    }
    
    public void expand(String path){
        expanded.put(path, true);
    }
    
    public void collapse(String path){
        expanded.remove(path);
    }
    
    public boolean isExpanded(String path){
        return expanded.containsKey(path);
    }
    
}
