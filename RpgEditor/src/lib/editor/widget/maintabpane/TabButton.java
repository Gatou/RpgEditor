/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.maintabpane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import lib.editor.mgr.WidgetMgr;

/**
 *
 * @author gaetan
 */
public class TabButton extends JPanel{

    public static final int BUTTON_WIDTH = 116;
    public static final int BUTTON_ACTIVE_HEIGHT = 42;
    public static final int BUTTON_INACTIVE_HEIGHT = 32;
    
    public static final BufferedImage topBgImage = loadImage("src/assets/tab/button_bg.png", false);
    public static final BufferedImage bottomBgImage = loadImage("src/assets/tab/button_bg.png", true);
    public static final BufferedImage topBgImageFocused = loadImage("src/assets/tab/button_bg_focused.png", false);
    public static final BufferedImage bottomBgImageFocused = loadImage("src/assets/tab/button_bg_focused.png", true);
    
    public static final int TOP_LABEL_Y_OFFSET = BUTTON_INACTIVE_HEIGHT/2 - 16/2 - 3;
    public static final int BOTTOM_LABEL_Y_OFFSET = (BUTTON_ACTIVE_HEIGHT-BUTTON_INACTIVE_HEIGHT) + BUTTON_INACTIVE_HEIGHT/2 - 16/2 + 3;
    
    Orientation orientation;
    public JLabel label;
    boolean isActive;
    boolean isFocused;
    boolean isMenuButton;
    boolean isDragging;
    
    DataTabButton data;
    TabButtonPanel panel;
    
    int draggingXOffset;
    
    public enum Orientation{
        TOP,
        BOTTOM,
    }
    
    public TabButton(DataTabButton data) {
        draggingXOffset = -1;
        
        this.panel = panel;
        this.data = data;
        
        setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_ACTIVE_HEIGHT));
        setMinimumSize(getPreferredSize());
        setMaximumSize(getPreferredSize());
        
        
        //setMargin(new Insets(0, -5, 0, -10));
        setLayout(new FlowLayout(FlowLayout.LEFT, 8, 0));
        
        setOpaque(false);
        //setContentAreaFilled(false);
        label = new JLabel();
        label.setForeground(new Color(200,200,100));
        add(label);
        
        isActive = false;
        isFocused = false;
        isMenuButton = false;
        isDragging = false;
        
        addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                mouseClick(e);
            }
            
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {
                draggingXOffset = -1;
                if(isDragging){
                    isDragging = false;
                    tabDrop();
                }
            }

            public void mouseEntered(MouseEvent e) {
                mouseEnter(e);
            }

            public void mouseExited(MouseEvent e) {
                mouseExit(e);
            }
        });
        
        addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                isDragging = true;
                if(draggingXOffset == -1){
                    draggingXOffset = e.getX();
                }
                
                tabDragging();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });
    }
    
    private static BufferedImage loadImage(String filename, boolean flip){
        try {
            BufferedImage image = ImageIO.read(new File(System.getProperty("user.dir"), filename));
            if(flip){
                AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
                tx.translate(0, -image.getHeight(null));
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
                image = op.filter(image, null);
               
            }
            return image;
        } catch (IOException ex) {
            Logger.getLogger(TabButton.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void setOrientation(Orientation orientation){
        this.orientation = orientation;
        /*
        if(orientation == Orientation.TOP){
            FlowLayout layout = (FlowLayout) getLayout();
            layout.setVgap(TOP_LABEL_Y_OFFSET+yOffset);
            
            //setLayout(new FlowLayout(FlowLayout.LEFT, 8, TOP_LABEL_Y_OFFSET+yOffset));
        }
        else if(orientation == Orientation.BOTTOM){
            //setLayout(new FlowLayout(FlowLayout.LEFT, 8, BOTTOM_LABEL_Y_OFFSET-yOffset));
        }
        */
        //setLayout(getLayout());
        //validate();
    }
    
    public void paintComponent(Graphics g){
        int yOffset = 0;
        int yGap = 0;
        BufferedImage image = null;
        boolean active = isActive || (isMenuButton && WidgetMgr.MAIN_TAB_PANE.menu.isShowing());
        
        if(orientation == Orientation.TOP){
            if(isFocused()){
                yOffset = 0;
                yGap = TOP_LABEL_Y_OFFSET+10;
                image = topBgImageFocused;
            }
            else if(active){
                yOffset = -5;
                yGap = TOP_LABEL_Y_OFFSET+5;
                image = topBgImage;
            }
            else{
                yOffset = -10;
                yGap = TOP_LABEL_Y_OFFSET;
                image = topBgImage;
            }
        }
        else if(orientation == Orientation.BOTTOM){
            if(isFocused()){
                yOffset = 0;
                yGap = BOTTOM_LABEL_Y_OFFSET-10;
                image = bottomBgImageFocused;
            }
            else if(active){
                yOffset = 5;
                yGap = BOTTOM_LABEL_Y_OFFSET-5;
                image = bottomBgImage;
            }
            else{
                yOffset = 10;
                yGap = BOTTOM_LABEL_Y_OFFSET;
                image = bottomBgImage;
            }
            
        }
        
        g.drawImage(image, 0, yOffset, null);
        FlowLayout layout = (FlowLayout) getLayout();
        layout.setVgap(yGap);
        
        setLayout(getLayout());
        validate();
        
        super.paintComponent(g);
    }
    
    public void mouseEnter(MouseEvent e){
        isActive = true;
        repaint();
    }
    
    public void mouseExit(MouseEvent e){
        isActive = false;
        repaint();
    }
    
    public void mouseClick(MouseEvent e){
        if(isMenuButton){
            WidgetMgr.MAIN_TAB_PANE.menuTabClick(this);
        }
        else{
            WidgetMgr.MAIN_TAB_PANE.setTabFocused(this, false);
        }
    }
    
    public void setFocused(boolean focused){
        isFocused = focused;
        //data.focused = focused;
    }
    
    public boolean isFocused(){
        return isFocused;
    }
    
    public void setMenuButton(boolean menu){
        isMenuButton = menu;
    }
    
    public void tabDragging(){
        WidgetMgr.MAIN_TAB_PANE.tabDragging(this.panel, draggingXOffset);
    }
    
    public void tabDrop(){
        WidgetMgr.MAIN_TAB_PANE.tabDrop(this.panel);
    }
    
    
}
