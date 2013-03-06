/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.menu;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import lib.editor.mgr.WidgetMgr;

/**
 *
 * @author gaetan
 */
public class MenuItem extends JMenuItem{
    
    
    private String statusText;
    
    public MenuItem(String text, ImageIcon icon, String statusText, KeyStroke shortcut){
        super(text, icon);
        this.statusText = statusText;
        setAccelerator(shortcut);
        init();
    }
    
    public MenuItem(){
        statusText = "";
        init();
    }
    
    private void init(){
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
