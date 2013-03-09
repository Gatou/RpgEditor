/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.tree.tree.option;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.swing.JTextField;
import javax.swing.tree.DefaultMutableTreeNode;
import lib.editor.widget.tree.item.DatabaseTreeItem;
import lib.editor.widget.tree.item.TreeItem;
import lib.editor.widget.tree.tree.MapTree;
import lib.editor.widget.tree.tree.Tree;

/**
 *
 * @author gaetan
 */
public class MapTreeFilter extends TreeFilter{

    MapTree tree;
    
    
    public MapTreeFilter(MapTree tree, JTextField filterTextField) {
        super(filterTextField);
        this.tree = tree;
    }
    
    public void filterTextChanged(){
        String filterText = filterTextField.getText();
        isFiltering = !filterText.equals("");
        
        
        
        if(filterText.equals("")){
            tree.itemCacheEnabled = true;
            //DatabaseTreeItem item = (DatabaseTreeItem) tree.itemsByText.get("Project");
            tree.refresh(tree.root().editorData);
        }
        else{
            tree.visualClear();

            tree.itemCacheEnabled = false;
            
            for(TreeItem item : tree.getAllItems()){
                if(item.text.toLowerCase().contains(filterText.toLowerCase())){
                    item.removeAllChildren();
                    tree.addTopLevelItem(item);
                }
            }
            
            tree.itemCacheEnabled = true;
        }
        
        tree.checkEnabledMenuAction();
        
    }
    
}
