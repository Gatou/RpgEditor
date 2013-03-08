/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.button;

import java.awt.Dimension;
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
                WidgetMgr.STATUS_LABEL.setText(statusText);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                WidgetMgr.STATUS_LABEL.setText("");
            }
        });
        
        setMinimumSize(new Dimension(28, 28));
        setMaximumSize(new Dimension(28, 28));
        setPreferredSize(new Dimension(28, 28));
    }
    
    public String getStatusText(){
        return statusText;
    }
    
    public void setStatusText(String text){
        statusText = text;
    }

}
