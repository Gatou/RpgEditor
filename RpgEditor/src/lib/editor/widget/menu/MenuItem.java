/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.menu;

import javax.swing.JMenuItem;
import lib.editor.mgr.WidgetMgr;

/**
 *
 * @author gaetan
 */
public class MenuItem extends JMenuItem{
    
    
        private String statusText;
    
    public MenuItem(){
        statusText = "";
        
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                WidgetMgr.MAIN_WINDOW.getStatusLabel().setText(statusText);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                WidgetMgr.MAIN_WINDOW.getStatusLabel().setText("");
            }
        });
        
    }
    
    public String getStatusText(){
        return statusText;
    }
    
    public void setStatusText(String text){
        statusText = text;
    }
}
