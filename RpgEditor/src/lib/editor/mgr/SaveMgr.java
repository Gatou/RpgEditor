/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.mgr;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;

/**
 *
 * @author gaetan
 */
public class SaveMgr {
    
    private static AbstractButton[] saveButtons;
    
    public static void init(AbstractButton[] buttons){
        saveButtons = buttons;
        for(AbstractButton button : saveButtons){
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    save();
                }
            });
        }
        clear();
    }
    
    public static void clear(){
        for(Component comp : saveButtons){
            comp.setEnabled(false);
        }
    }
    
    public static void requestSaveEnabled(){
        for(Component comp : saveButtons){
            comp.setEnabled(true);
        }
    }
    
    public static void save(){
        if(!saveButtons[0].isEnabled()){
            return;
        }
        
        clear();
        
        WidgetMgr.MAP_TREE.save();
        
        for(int i=0; i<2; i++){
            System.gc();
        }
    }
    
}
