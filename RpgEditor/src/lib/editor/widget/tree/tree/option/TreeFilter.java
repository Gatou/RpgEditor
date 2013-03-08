/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.tree.tree.option;

import java.util.Enumeration;
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
    
    public TreeFilter(JTextField filterTextField){
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
    
    
    public void filterTextChanged(){
    }
    

    
}
