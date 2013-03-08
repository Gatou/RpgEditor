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
            DatabaseTreeItem item = (DatabaseTreeItem) tree.itemsByExt.get("Project");
            tree.refresh(item.editorData);
        }
        else{
        
            tree.visualClear();

            for(String text : tree.itemsByExt.keySet()){
                if(text.contains(filterText)){
                    tree.addTopLevelItem(tree.itemsByExt.get(text));
                }
            }
        }
        
        tree.checkEnabledMenuAction();
        
    }
    
}
