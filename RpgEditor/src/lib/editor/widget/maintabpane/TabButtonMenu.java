/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.maintabpane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import lib.editor.mgr.Mgr;
import lib.editor.mgr.WidgetMgr;

/**
 *
 * @author gaetan
 */
public class TabButtonMenu extends TabButton{

    //public TabButton menuButton;
    public TabButton menuButtonPanel;
    
    private class Item extends JMenuItem{

        public TabButton button;
        
        public Item(String text, Icon icon, TabButton button) {
            super(text, icon);
            this.button = button;
        }
    }
    
    public TabButtonMenu(DataTabButton data) {
        super(data);
        /*
        menuButtonPanel = new TabButton(null);
        menuButtonPanel.label.setText("Other...");
        menuButtonPanel.label.setIcon(Mgr.icon.getTabIcon("arrow_up.png", false));
        menuButtonPanel.setMenuButton(true);
        //menuButtonPanel.button.setOrientation(TabButton.Orientation.BOTTOM);
                
        addPopupMenuListener(new PopupMenuListener() {
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {}

            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                menuClose();
            }

            public void popupMenuCanceled(PopupMenuEvent e) {}
        });*/
        
    }
    
    
    public void refresh(int tabIndex, List<TabButton> tabButtons, List<DataTabButton> tabsDatabase){
        removeAll();
        
        //if(menuButton != null){
            
        //    menuButton = null;
        //}
        
        
        //for(TabButtonPanel buttonPanel : tabButtons){
        //    buttonPanel.button.setMenuButton(false);
        //}
        /*
        if(tabIndex > 0 && tabIndex < tabButtons.size()){
            
            boolean focusedTabInMenu = false;
            
            for(int i=tabIndex; i<tabButtons.size(); i++){
                DataTabButton tabData = tabsDatabase.get(i);
                TabButton button = tabButtons.get(i).button;
                Item item = new Item(tabData.text, Mgr.icon.getTabIcon(tabData.iconFilename, false), button);
                item.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        itemClick(((Item)e.getSource()).button);
                    }
                });
                add(item);
                if(button.isFocused()){
                    focusedTabInMenu = true;
                }
            }
            
            WidgetMgr.MAIN_TAB_PANE.setMenuButtonVisible(true);

            setVisible(true);
            setVisible(false);
        
        }
        
        */
    }
    
    public void menuClose(){
        menuButtonPanel.repaint();
        //if(WidgetMgr.MAIN_TAB_PANE == null){return;}
        /*
        for(TabButtonPanel buttonPanel : WidgetMgr.MAIN_TAB_PANE.tabButtons){
            if(buttonPanel.button.isMenuButton){
                buttonPanel.button.repaint();
            }
        }*/
    }
    
    public void itemClick(TabButton button){
        //WidgetMgr.MAIN_TAB_PANE.setTabFocused(button, true);
    }

}
