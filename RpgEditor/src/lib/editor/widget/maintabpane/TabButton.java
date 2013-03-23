/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.maintabpane;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
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
import lib.editor.mgr.Mgr;
import lib.editor.mgr.WidgetMgr;
import lib.editor.util.tween.ComponentAccessor;

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
    
    public static final BufferedImage topDropBgImage = loadImage("src/assets/tab/button_drop_bg.png", false);
    public static final BufferedImage bottomDropBgImage = loadImage("src/assets/tab/button_drop_bg.png", true);
    
    public static final int TOP_LABEL_Y_OFFSET = BUTTON_INACTIVE_HEIGHT/2 - 16/2 - 3;
    public static final int BOTTOM_LABEL_Y_OFFSET = (BUTTON_ACTIVE_HEIGHT-BUTTON_INACTIVE_HEIGHT) + BUTTON_INACTIVE_HEIGHT/2 - 16/2 + 3;
    
    public JLabel label;
    boolean isActive;
    boolean isFocused;
    boolean isMenuButton;
    boolean isDragging;
    boolean visible;
    
    boolean isMoving;
    int lastOrientation;
    //boolean hasMoveToTheLeft;
    //boolean hasMoveToTheRight;
    //boolean startDraggingOnTop;
    
    DataTabButton data;
    
    int draggingXOffset;
    
    public TabButton(DataTabButton data) {
        draggingXOffset = -1;
        
        this.data = data;
        
        setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_ACTIVE_HEIGHT));
        setMinimumSize(getPreferredSize());
        setMaximumSize(getPreferredSize());
        
        
        //setMargin(new Insets(0, -5, 0, -10));
        setLayout(null);
        
        setOpaque(false);
        //setContentAreaFilled(false);
        label = new JLabel();
        label.setForeground(new Color(200,200,100));
        //label.setBackground(Color.red);
        //label.setOpaque(true);
        label.setSize(BUTTON_WIDTH-8*2, BUTTON_ACTIVE_HEIGHT);
        label.setText(data.text);
        label.setIcon(Mgr.icon.getTabIcon(data.iconFilename, false));
            
        add(label);
        
        isActive = false;
        isFocused = false;
        isMenuButton = false;
        isDragging = false;
        visible = true;
        isMoving = false;
        lastOrientation = -1;
        //startDraggingOnTop = false;
        
        addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                //System.out.println(e.getClickCount());
                mouseClick(e);
            }
            
            public void mousePressed(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2){
                    dragStart(e);
                }
            }
            public void mouseReleased(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1 && isDragging){
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
                tabDragging(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mouseMove(e);
            }
        });
        
        setLocation(getX(), getTargetY(0));
        setSize(BUTTON_WIDTH, BUTTON_ACTIVE_HEIGHT);
        setOrientation(data.orientation);
    }
    
    public int getTargetY(int type){
        if(type == 0){ //inactive
            if(data.orientation == 0){
                return -10;
            }
            else if(data.orientation == 1){
                return 10;
            }
        }
        else if(type == 1){ //active
            if(data.orientation == 0){
                return -5;
            }
            else if(data.orientation == 1){
                return 5;
            }
        }
        else if(type == 2){ //focused
            if(data.orientation == 0){
                return 0;
            }
            else if(data.orientation == 1){
                return 0;
            }
        }
        return 50;
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
    
    public void setOrientation(int orientation){
        data.orientation = orientation;
        setLocation(getX(), getTargetY(0));
        
        if(orientation == 0){
            label.setLocation(8, 3);
        }
        else if(orientation == 1){
            label.setLocation(8, -3);
        }
    }
    
    public void paintComponent(Graphics g){
        int yOffset = 0;
        int yGap = 0;
        BufferedImage image = null;
        //boolean active = !WidgetMgr.MAIN_TAB_PANE.tabDragging && (isActive || (isMenuButton && WidgetMgr.MAIN_TAB_PANE.menu.isShowing()));
        
        if(data.orientation == 0){
            if(isFocused()){
                yOffset = 0;
                yGap = TOP_LABEL_Y_OFFSET+10;
                image = topBgImageFocused;
            }
            else{
                yOffset = -10;
                yGap = TOP_LABEL_Y_OFFSET;
                image = topBgImage;
            }
        }
        else if(data.orientation == 1){
            if(isFocused()){
                yOffset = 0;
                yGap = BOTTOM_LABEL_Y_OFFSET-10;
                image = bottomBgImageFocused;
            }
            else{
                yOffset = 10;
                yGap = BOTTOM_LABEL_Y_OFFSET;
                image = bottomBgImage;
            }
            
        }
        
        
        //g.drawImage(image, 0, 0, null);
        /*
        FlowLayout layout = (FlowLayout) getLayout();
        layout.setVgap(yGap);
        
        setLayout(getLayout());
        validate();*/
        
        g.drawImage(image, 0, 0, null);
        super.paintComponent(g);
    }
    
    public void mouseEnter(MouseEvent e){
        /*
        if(WidgetMgr.MAIN_TAB_PANE.tabDragging){return;}
        
        if(!isMoving){
            Tween.to(this, ComponentAccessor.POSITION, 50)
                    .target(getX(), getTargetY(1))
                    .start(WidgetMgr.MAIN_WINDOW.tweenManager);
        }*/
    }
    
    public void mouseExit(MouseEvent e){
        if(WidgetMgr.MAIN_TAB_PANE.tabDragging){return;}
        
        if(!isMoving){
            Tween.to(this, ComponentAccessor.POSITION, 50)
                    .target(getX(), getTargetY(0))
                    .start(WidgetMgr.MAIN_WINDOW.tweenManager);
        }
        
    }
    
    public void mouseClick(MouseEvent e){
        if(isMenuButton){
            //WidgetMgr.MAIN_TAB_PANE.menuTabClick(this);
        }
        else{
            //WidgetMgr.MAIN_TAB_PANE.setTabFocused(this, false);
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
    
    public void dragStart(MouseEvent e){
        if(this instanceof TabButtonMenu){
            return;
        }
        //setOrientation(data.orientation);
        lastOrientation = data.orientation;
        mouseExit(e);
        isDragging = true;
        WidgetMgr.MAIN_TAB_PANE.tabDragging = true;
        draggingXOffset = e.getX();
        WidgetMgr.MAIN_TAB_PANE.tabDragging(this, draggingXOffset);
        
        //WidgetMgr.MAIN_TAB_PANE.repaint();
    }
    
    public void tabDragging(MouseEvent e){
        if(isDragging){
            WidgetMgr.MAIN_TAB_PANE.tabDragging(this, draggingXOffset);
        }
    }
    
    
    public void tabDrop(){
        draggingXOffset = -1;
        isDragging = false;
        WidgetMgr.MAIN_TAB_PANE.tabDrop(this);
        WidgetMgr.MAIN_TAB_PANE.tabDragging = false;
    }
    
    public void mouseMove(MouseEvent e){
        if(WidgetMgr.MAIN_TAB_PANE.tabDragging){return;}
        
        if(getY() == getTargetY(0)){
            
            if(!isMoving){
                Tween.to(this, ComponentAccessor.POSITION, 50)
                        .target(getX(), getTargetY(1))
                        .start(WidgetMgr.MAIN_WINDOW.tweenManager);
            }
        }
    }
    
}
