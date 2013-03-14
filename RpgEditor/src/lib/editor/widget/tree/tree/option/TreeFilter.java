/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.tree.tree.option;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.DefaultMutableTreeNode;
import lib.editor.mgr.WidgetMgr;
import lib.editor.widget.tree.item.TreeItem;
import lib.editor.widget.tree.tree.Tree;

/**
 *
 * @author gaetan
 */
public abstract class TreeFilter {
    
    JTextField filterTextField;
    public boolean isFiltering;
    public List<TreeItem> items;
    Tree source;
    
    Map<Integer, TreeItem> itemsById;
    
    public TreeFilter(Tree source, JTextField filterTextField){
        this.source = source;
        
        this.filterTextField = filterTextField;
        isFiltering = false;
                
        filterTextField.getDocument().addDocumentListener(new DocumentListener(){  
            public void insertUpdate(DocumentEvent e) {
                filterTextChanged();
            }

            public void removeUpdate(DocumentEvent e) {
                filterTextChanged();
            }

            public void changedUpdate(DocumentEvent e) {}
      }); 
        

        
    }
    
    public void needRefresh(TreeItem item, boolean add){
        
        if(add){
            items.add(item);
            System.out.println(item.getText());
        }
        else{
            items.remove(item);
        }
        //items = null;
    }
    
    public void refreshItems(){
        if(items == null){
            items = source.getItems();
        }
    }
    
    public void filterTextChanged(){
        String filterText = filterTextField.getText();
        
        if(filterText.equals("")){
            isFiltering = false;
            
        }
        else{
            isFiltering = true;
            refreshItems();
        }
    }
    

    
}
