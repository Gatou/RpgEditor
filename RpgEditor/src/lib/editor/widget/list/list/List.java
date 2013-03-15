/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.list.list;

import java.awt.Component;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import lib.editor.widget.list.item.ListItem;
import org.jdesktop.swingx.JXList;

/**
 *
 * @author gaetan
 */
public class List extends JXList{

    public List() {
        setCellRenderer(new ListItemRenderer());
        setModel(new DefaultListModel());
        clear();
        
        addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                currentItemChanged(getItem(e.getLastIndex()), e.getLastIndex());
            }
        });
    }
    
    public void clear(){
        DefaultListModel model = (DefaultListModel) getModel();
        clearSelection();
        model.clear();
    }
    
    public int getCurrentRow(){
        return getSelectedIndex();
    }
    
    public ListItem getCurrentItem(){
        return (ListItem) getSelectedValue();
    }
    
    public ListItem getItem(int row){
        DefaultListModel model = (DefaultListModel) getModel();
        try{
            return (ListItem) model.getElementAt(row);
        }
        catch(ArrayIndexOutOfBoundsException e){
            return null;
        }
    }
    
    public void addItem(ListItem item){
        DefaultListModel model = (DefaultListModel) getModel();
        model.addElement(item);
    }
    
    public void currentItemChanged(ListItem item, int row){
        
    }
    
    public void setCurrentItem(String text){
        DefaultListModel model = (DefaultListModel) getModel();
        for(int i=0; i<model.getSize(); i++){
            ListItem item = (ListItem) model.getElementAt(i);
            if(item.getText().equals(text)){
                setSelectedValue(item, true);
            }
        }

    }
    
}
