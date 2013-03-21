/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.maintabpane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import lib.editor.mgr.Mgr;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 *
 * @author gaetan
 */
public class MainTabPane extends JPanel{

    
    
    public static final int TOTAL_BUTTON_WIDTH = TabButton.BUTTON_WIDTH + TabButtonPanel.BUTTON_SPACING*2;
         
    List<DataTabButton> tabsDatabase;
    List<TabButtonPanel> tabButtons;
    
    JPanel topPanel;
    JPanel middlePanel;
    JPanel bottomPanel;
    
    public MainTabMenu menu;
    
    public MainTabPane() {
        tabsDatabase = new ArrayList<DataTabButton>();
        tabsDatabase.add(new DataTabButton("Maps", "map.png"));
        tabsDatabase.add(new DataTabButton("Actors", "actor.png"));
        tabsDatabase.add(new DataTabButton("Classes", "class.png"));
        tabsDatabase.add(new DataTabButton("Skills", "skill.png"));
        tabsDatabase.add(new DataTabButton("Items", "item.png"));
        tabsDatabase.add(new DataTabButton("Weapons", "weapon.png"));
        tabsDatabase.add(new DataTabButton("Armors", "armor.png"));
        tabsDatabase.add(new DataTabButton("Enemies", "enemy.png"));
        tabsDatabase.add(new DataTabButton("Tiles", "tile.png"));
        tabsDatabase.add(new DataTabButton("System", "system.png"));
        
        setLayout(new java.awt.BorderLayout());
        
        
        topPanel = createTabContainer();
        middlePanel = new JPanel();
        bottomPanel = createTabContainer();
        
        add(topPanel, java.awt.BorderLayout.PAGE_START);
        add(middlePanel, java.awt.BorderLayout.CENTER);
        add(bottomPanel, java.awt.BorderLayout.PAGE_END);
        
        menu = new MainTabMenu();
        

        

        
        middlePanel.setOpaque(false);
        

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
        panel.setLayout(new AbsoluteLayout());
        
        return panel;
    }
    
    public void rebuild(){
        topPanel.removeAll();
        bottomPanel.removeAll();
        tabButtons = new ArrayList<TabButtonPanel>();
        
        for(DataTabButton tabData : tabsDatabase){
            TabButtonPanel buttonPanel = new TabButtonPanel(new TabButton(tabData));
            buttonPanel.button.label.setText(tabData.text);
            buttonPanel.button.label.setIcon(Mgr.icon.getTabIcon(tabData.iconFilename, false));
            tabButtons.add(buttonPanel);
            //topPanel.add(button1);
        }

        layoutTabs();
    }
    
    public void resized(ComponentEvent e) {
        layoutTabs();
    }
    
    public void layoutTabs(){
        topPanel.removeAll();
        bottomPanel.removeAll();
        
        

        
        int tabIndex = 0;
        
        tabIndex = fillTabs(topPanel, tabIndex);
        tabIndex = fillTabs(bottomPanel, tabIndex);

        menu.refresh(tabIndex, tabButtons, tabsDatabase);
        
        topPanel.validate();
        bottomPanel.validate();
    }
    
    public int fillTabs(JPanel panel, int tabIndex){
        int currentWidth = 0;
        
        while(tabIndex < tabButtons.size() && currentWidth + TOTAL_BUTTON_WIDTH < getWidth()){
            TabButton button = tabButtons.get(tabIndex).button;
            
            if(panel == topPanel){
                button.setOrientation(TabButton.Orientation.TOP);
            }
            else if(panel == bottomPanel){
                button.setOrientation(TabButton.Orientation.BOTTOM);
            }
            
            DataTabButton tabData = tabsDatabase.get(tabIndex);
            TabButtonPanel buttonPanel = tabButtons.get(tabIndex);
            buttonPanel.button.label.setText(tabData.text);
            buttonPanel.button.label.setIcon(Mgr.icon.getTabIcon(tabData.iconFilename, false));
            //buttonPanel.button.setMenuButton(false);
                    
            AbsoluteConstraints constraint = new AbsoluteConstraints(currentWidth, 0, -1, -1);
            panel.add(buttonPanel, constraint);
            currentWidth += TOTAL_BUTTON_WIDTH;
            tabIndex += 1;
        }
        
        return tabIndex;
    }

    public void setTabFocused(TabButton button, boolean menuButtonFocused){
        for(TabButtonPanel tabPanel : tabButtons){
            tabPanel.button.setFocused(tabPanel.button == button);
            tabPanel.button.repaint();
        }
        menu.menuButtonPanel.button.setFocused(menuButtonFocused);
        menu.menuButtonPanel.button.repaint();
    }
    
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint gp = new GradientPaint(0, 0,
                new Color(70,70,70), 0, getHeight(),
                new Color(50,50,50));

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
    
    public void menuTabClick(TabButton button){
        //button.setFocused(true);
        menu.show(button, 0, -menu.getHeight()+5);
    }

    public void setMenuButtonVisible(boolean visible) {
        if(visible){
            int buttonNumber = bottomPanel.getComponents().length-1;
            bottomPanel.remove(buttonNumber);
            int w = buttonNumber * TOTAL_BUTTON_WIDTH;
            AbsoluteConstraints constraint = new AbsoluteConstraints(w, 0, -1, -1);
            bottomPanel.add(menu.menuButtonPanel, constraint);
        }
        else{
            
        }
    }
    
    public void tabDragging(TabButtonPanel buttonPanel, int xOffset){
        if(getMousePosition() == null){
            return;
        }
        
        topPanel.remove(buttonPanel);
        int w = getMousePosition().x;
        AbsoluteConstraints constraint = new AbsoluteConstraints(w-xOffset, 0, -1, -1);
        
        topPanel.add(buttonPanel, constraint);
        topPanel.validate();
    }
    
}
