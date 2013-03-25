/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MEGA ATTENTIOn:
 *          Verifier qui y'a pas des pb quand un drag est en train de bouger
 *          et qu'on fait certaine action..
 * 
 * 
 * 
 * 
 */
package lib.editor.widget.maintabpane;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Cubic;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import lib.editor.mgr.Mgr;
import lib.editor.mgr.WidgetMgr;
import lib.editor.util.tween.ComponentAccessor;
import lib.editor.widget.datapane.MainDataPanel;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 *
 * @author gaetan
 */
public class MainTabPane extends JPanel{

    private class DropHighlightInfo{
        public int x;
        public int orientation;
        public boolean visible;

        public DropHighlightInfo() {
            x = -1;
            orientation = 0;
            visible = false;
        }
        
        
    }
    
    private DropHighlightInfo dropHighlightInfo;
    
    public static final int TOTAL_BUTTON_WIDTH = TabButton.BUTTON_WIDTH + 4;
         
    List<DataTabButton> tabsDatabase;
    //List<TabButton> tabButtons;
    List<TabButton> topTabs;
    List<TabButton> bottomTabs;
    
    
    JPanel topPanel;
    JPanel middlePanel;
    JPanel bottomPanel;
    
    public TabButtonMenu topMenu;
    public TabButtonMenu bottomMenu;
    public MainDataPanel mainDataPanel;
    
    boolean tabDragging;
    
    public MainTabPane() {
        
        
        tabsDatabase = new ArrayList<DataTabButton>();
        tabsDatabase.add(new DataTabButton("Maps", "map.png", 0, 0));
        tabsDatabase.add(new DataTabButton("Actors", "actor.png", 0, 1));
        tabsDatabase.add(new DataTabButton("Classes", "class.png", 0, 2));
        tabsDatabase.add(new DataTabButton("Skills", "skill.png", 0, 3));
        tabsDatabase.add(new DataTabButton("Items", "item.png", 0, 4));
        tabsDatabase.add(new DataTabButton("Weapons", "weapon.png", 0, 5));
        tabsDatabase.add(new DataTabButton("Armors", "armor.png", 1, 0));
        tabsDatabase.add(new DataTabButton("Enemies", "enemy.png", 1, 1));
        tabsDatabase.add(new DataTabButton("Tiles", "tile.png", 1, 2));
        tabsDatabase.add(new DataTabButton("System", "system.png", 1, 3));
        
        setLayout(new java.awt.BorderLayout());
        
        
        topPanel = createTabContainer();
        bottomPanel = createTabContainer();
        
        mainDataPanel = new MainDataPanel();
        middlePanel = new JPanel();
        middlePanel.setOpaque(false);
        middlePanel.setBorder(new EmptyBorder(8, 24, 8, 24));
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.LINE_AXIS));
        middlePanel.add(mainDataPanel);
        
        add(topPanel, java.awt.BorderLayout.PAGE_START);
        add(middlePanel, java.awt.BorderLayout.CENTER);
        add(bottomPanel, java.awt.BorderLayout.PAGE_END);
        
        topMenu = new TabButtonMenu(new DataTabButton("Other", "arrow_up.png", 0, 0));
        bottomMenu = new TabButtonMenu(new DataTabButton("Other", "arrow_up.png", 1, 0));

        tabDragging = false;
        dropHighlightInfo = new DropHighlightInfo();
        
        
        

        addComponentListener(new ComponentListener() {

            @Override
            public void componentResized(ComponentEvent e) {
                resized(e);
            }

            @Override
            public void componentMoved(ComponentEvent e) {
            }

            @Override
            public void componentShown(ComponentEvent e) {
            }

            @Override
            public void componentHidden(ComponentEvent e) {
            }

        });
        
        
        rebuild();
    }
    
    private JPanel createTabContainer(){
        JPanel panel = new JPanel();
        
        panel.setPreferredSize(new Dimension(200, TabButton.BUTTON_ACTIVE_HEIGHT));
        panel.setMaximumSize(new java.awt.Dimension(32767, TabButton.BUTTON_ACTIVE_HEIGHT));
        panel.setMinimumSize(new java.awt.Dimension(200, TabButton.BUTTON_ACTIVE_HEIGHT));
        panel.setOpaque(false);
        
        //panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panel.setLayout(null);
        
        return panel;
    }
    
    public void rebuild(){
        topPanel.removeAll();
        bottomPanel.removeAll();
        topTabs = new ArrayList<TabButton>();
        bottomTabs = new ArrayList<TabButton>();
        
        for(DataTabButton tabData : tabsDatabase){
            TabButton tab = new TabButton(tabData);
            if(tabData.orientation == 0){
                topTabs.add(tab);
            }
            else if(tabData.orientation == 1){
                bottomTabs.add(tab);
            }
        }
    }
    
    public void resized(ComponentEvent e) {
        layoutTabs();
    }
    
    public void layoutTabs(){
        topPanel.removeAll();
        bottomPanel.removeAll();
        
        for(TabButton tab : topTabs){
            topPanel.add(tab);
            tab.setLocation(tab.data.index*TOTAL_BUTTON_WIDTH+4, tab.getLocation().y);
        }
        
        
        for(TabButton tab : bottomTabs){
            bottomPanel.add(tab);
            tab.setLocation(tab.data.index*TOTAL_BUTTON_WIDTH+4, tab.getLocation().y);
        }
        

        
   
        checkNeedMenu(topPanel, topTabs, topMenu);
        checkNeedMenu(bottomPanel, bottomTabs, bottomMenu);
        
        topPanel.validate();
        bottomPanel.validate();
        
    }

    public void checkNeedMenu(JPanel panel, List<TabButton> tabs, TabButtonMenu menuTab){
        boolean tabOutScreen = false;
        int lastVisibleIndex = (getWidth()+TOTAL_BUTTON_WIDTH/2)/TOTAL_BUTTON_WIDTH - 1;
        
        for(TabButton tab : tabs){
            if(tab.data.index > lastVisibleIndex){
                tabOutScreen = true;
                break;
            }
        }
        
        if(tabOutScreen){
            List<TabButton> outScreenTabs = new ArrayList<TabButton>();
            
            for(TabButton tab : tabs){
                if(tab.data.index >= lastVisibleIndex){
                    outScreenTabs.add(tab);
                    panel.remove(tab);
                }
            }
            menuTab.data.index = lastVisibleIndex;
            menuTab.setLocation(menuTab.data.index*TOTAL_BUTTON_WIDTH+4, menuTab.getLocation().y);
            menuTab.visible = true;
            panel.add(menuTab);
            menuTab.refresh(outScreenTabs);
        }
        else{
            menuTab.visible = false;
            panel.remove(menuTab);
            menuTab.refresh(null);
        }
    }
    
    public void setTabFocused(TabButton focusedTab){
        for(TabButton tab : getAllTabs()){
            tab.setFocused(tab == focusedTab);
        }
        repaint();
    }
    
            
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint gp = new GradientPaint(0, 0,
                new Color(70,70,70), 0, getHeight(),
                new Color(50,50,50));

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        
        if(dropHighlightInfo.visible){
            if(dropHighlightInfo.orientation == 0){
                g.drawImage(TabButton.topDropBgImage, dropHighlightInfo.x, 0, null);
            }
            else if(dropHighlightInfo.orientation == 1){
                g.drawImage(TabButton.bottomDropBgImage, dropHighlightInfo.x, getHeight()-TabButton.BUTTON_ACTIVE_HEIGHT, null);
            }
        }
    }
    
    /*
    public void menuTabClick(TabButton button){
        //button.setFocused(true);
        menu.show(button, 0, -menu.getHeight()+5);
    }

    public void setMenuButtonVisible(boolean visible) {
        if(visible){
            int buttonNumber = bottomPanel.getComponents().length-1;
            bottomPanel.remove(buttonNumber);
            int w = buttonNumber * TOTAL_BUTTON_WIDTH;
            //AbsoluteConstraints constraint = new AbsoluteConstraints(w, 0, -1, -1);
            bottomPanel.add(menu.menuButtonPanel);//, constraint);
            menu.menuButtonPanel.setLocation(w, 0);
        }
        else{
            
        }
    }
    */
    
    public void checkDropLocationValid(){
        if(dropHighlightInfo.x + TOTAL_BUTTON_WIDTH/2 > getWidth()){
            dropHighlightInfo.visible = false;
            return;
        }
        
        int dropIndex = dropHighlightInfo.x/TOTAL_BUTTON_WIDTH;
        if(topMenu.visible && dropHighlightInfo.orientation == 0 && dropIndex >= topMenu.data.index){
            dropHighlightInfo.visible = false;
            return;
        }
        if(bottomMenu.visible && dropHighlightInfo.orientation == 1 && dropIndex >= bottomMenu.data.index){
            dropHighlightInfo.visible = false;
            return;
        }
    }
    
    public void tabDragging(TabButton draggingTab){
        if(getMousePosition() == null){
            return;
        }
        
        int w = getMousePosition().x;
        draggingTab.setLocation(w-draggingTab.dragXOffset, draggingTab.getLocation().y);
        
        dropHighlightInfo.visible = true;
        dropHighlightInfo.x = (getMousePosition().x/TOTAL_BUTTON_WIDTH) * TOTAL_BUTTON_WIDTH + 4;
        checkDropLocationValid();
        
        boolean mouseOnTop = getMousePosition().y < getHeight()/2;
        if(mouseOnTop){
            dropHighlightInfo.orientation = 0;
            if(draggingTab.data.orientation != 0){
                draggingTab.setOrientation(0);
                bottomPanel.remove(draggingTab);
                topPanel.add(draggingTab);
            }
        }
        else{
            dropHighlightInfo.orientation = 1;
            if(draggingTab.data.orientation != 1){
                draggingTab.setOrientation(1);
                topPanel.remove(draggingTab);
                bottomPanel.add(draggingTab);
            }
        }
        
        if(draggingTab.data.orientation == 0){
            topPanel.setComponentZOrder(draggingTab, 0);
        }
        else if(draggingTab.data.orientation == 1){
            bottomPanel.setComponentZOrder(draggingTab, 0);
        }
        
        topPanel.repaint();
        bottomPanel.repaint();
    }
    
    public void startTabMoveTo(final TabButton tab, int index){
        if(!tab.moving){
            tab.moving = true;

            int targetX = index*TOTAL_BUTTON_WIDTH+4;

            Tween.to(tab, ComponentAccessor.POSITION, 200)
                .target(targetX, tab.getY())
                .setCallback(new TweenCallback() {public void onEvent(int type, BaseTween<?> source){endButtonMove(tab);}})
                .start(WidgetMgr.MAIN_WINDOW.tweenManager);
        }
    }
    
    public void tabDrop(TabButton draggingTab){
        for(Component comp : topPanel.getComponents()){
            topPanel.setComponentZOrder(comp, Math.min(2, topPanel.getComponents().length-1));
        }
        for(Component comp : bottomPanel.getComponents()){
            bottomPanel.setComponentZOrder(comp, Math.min(2, bottomPanel.getComponents().length-1));
        }
        //tabDragging(buttonPanel, buttonPanel.button.draggingXOffset);
        //System.out.println("---------------------");
        /*
        boolean ok = false;
        
        while(!ok){
            ok = true;
            
            for(TabButton tab : getAllTabs()){
                if(tab.isMoving){
                    ok = false;
                    //break;
                }
            }
            
            WidgetMgr.MAIN_WINDOW.tweenManager.update(1000/60);
        }*/
        int oldIndex = draggingTab.data.index;
        int oldOrientation = draggingTab.lastOrientation;
        bottomTabs.remove(draggingTab);
        topTabs.remove(draggingTab);
        
        
        int dropIndex = dropHighlightInfo.x/TOTAL_BUTTON_WIDTH;
        
        if(dropHighlightInfo.visible){ //valid drop location
            TabButton tab = null;
            
            if(draggingTab.data.orientation == 0){
                topTabs.add(draggingTab);
                topPanel.setComponentZOrder(draggingTab, 0);
                tab = getTabAtIndex(topTabs, dropIndex);       
            }
            else if(draggingTab.data.orientation == 1){
                bottomTabs.add(draggingTab);
                bottomPanel.setComponentZOrder(draggingTab, 0);
                tab = getTabAtIndex(bottomTabs, dropIndex);
            }

            
            draggingTab.data.index = dropIndex;
            
            if(tab != draggingTab && tab != null){
                bottomTabs.remove(tab);
                topTabs.remove(tab);
                tab.data.index = oldIndex;
                tab.setOrientation(oldOrientation);
                startTabMoveTo(tab, tab.data.index);
                if(tab.data.orientation == 0){
                    topTabs.add(tab);
                    topPanel.setComponentZOrder(tab, 1);
                }
                else if(tab.data.orientation == 1){
                    bottomTabs.add(tab);
                    bottomPanel.setComponentZOrder(tab, 1);
                }
            }
            
        }
        else{ //invalid drop location
            draggingTab.setOrientation(oldOrientation);
            
            if(draggingTab.data.orientation == 0){
                topTabs.add(draggingTab);
                topPanel.setComponentZOrder(draggingTab, 0);           
            }
            else if(draggingTab.data.orientation == 1){
                bottomTabs.add(draggingTab);
                bottomPanel.setComponentZOrder(draggingTab, 0);
            }
        }
        
        


        
        

            
        dropHighlightInfo.visible = false;
        startTabMoveTo(draggingTab, draggingTab.data.index);
           
        /*
        for(int i=0; i<getAllTabs().size(); i++){
            TabButton tab = getAllTabs().get(i);
            System.out.println(tab.label.getText());
        }*/
        
        repaint();
    }
    
    
    public void endButtonMove(TabButton tab){
        tab.moving = false;
    }

    

    public List<TabButton> getAllTabs(){
        List<TabButton> result = new ArrayList<TabButton>();
        result.addAll(topTabs);
        result.addAll(bottomTabs);
        return result;
    }
    
    public TabButton getTabAtIndex(List<TabButton> tabs, int index){
        for(TabButton tab : tabs){
            if(tab.data.index == index){
                return tab;
            }
        }
        return null;
    }
    
    public boolean isAnyMenuShowing(){
        return topMenu.menu.isVisible() || bottomMenu.menu.isVisible();
    }
}
