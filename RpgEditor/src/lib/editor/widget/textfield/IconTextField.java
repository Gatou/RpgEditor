package lib.editor.widget.textfield;


import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import lib.editor.mgr.Mgr;
import lib.editor.mgr.WidgetMgr;

public class IconTextField extends JTextField{
 
    protected Icon icon;//=new ImageIcon("images\\omag\\user.png");
    private String statusText;
    
    
    public IconTextField(){
        icon = new ImageIcon("images\\omag\\user.png");
        
        statusText = "";
        
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                WidgetMgr.STATUS_LABEL.setText(statusText);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                WidgetMgr.STATUS_LABEL.setText("");
            }
        });
        
    }
    
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        int y = getHeight()/2 - icon.getIconHeight()/2;
        
        icon.paintIcon(null, g, 5, y);
    }
 
    public Insets getInsets() {
        Insets i = super.getInsets();
        i.left += icon.getIconWidth() + 2;
        return i;
    }
    
    public void setIcon(Icon icon){
        this.icon = icon;
    }
    
    public Icon getIcon(){
        return icon;
    }
    
    public String getStatusText(){
        return statusText;
    }
    
    public void setStatusText(String text){
        statusText = text;
    }
}