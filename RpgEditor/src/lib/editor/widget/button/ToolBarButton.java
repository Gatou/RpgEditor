/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.button;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import lib.editor.mgr.WidgetMgr;

/**
 *
 * @author gaetan
 */
public class ToolBarButton extends JButton{
    
    
    private String statusText;
    
    public ToolBarButton(){
        statusText = "";
        
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                WidgetMgr.MAIN_WINDOW.getStatusLabel().setText(statusText);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                WidgetMgr.MAIN_WINDOW.getStatusLabel().setText("");
            }
        });
        
        setPreferredSize(new java.awt.Dimension(28, 28));
    }
    
    public String getStatusText(){
        return statusText;
    }
    
    public void setStatusText(String text){
        statusText = text;
    }

}
