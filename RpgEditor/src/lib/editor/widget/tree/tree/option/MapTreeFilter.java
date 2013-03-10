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
        super(tree, filterTextField);
        this.tree = tree;
    }
    
    public void filterTextChanged(){
        super.filterTextChanged();
        
        String filterText = filterTextField.getText();
        
        if(filterText.equals("")){
            tree.refresh(tree.rootItem.editorData);
        }
        else{
            tree.clear();
            
            for(TreeItem item : items){
                if(item.getText().toLowerCase().contains(filterText.toLowerCase())){
                    item.removeAllChildren();
                    tree.addTopLevelItem(item);
                }
            }
            
        }
        
        tree.checkEnabledMenuAction();
        
    }
    
}
