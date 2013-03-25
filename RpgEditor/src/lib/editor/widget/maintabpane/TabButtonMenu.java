/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.maintabpane;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
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
    
    JPopupMenu menu;
    
    private class Item extends JMenuItem{

        public TabButton tab;
        
        public Item(String text, Icon icon, TabButton tab) {
            super(text, icon);
            this.tab = tab;
        }
    }
    
    public TabButtonMenu(DataTabButton data) {
        super(data);
        
        menu = new JPopupMenu();
        
        menu.addPopupMenuListener(new PopupMenuListener() {
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {}

            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                
            }

            public void popupMenuCanceled(PopupMenuEvent e) {
                menuClose();
            }
        });
        
    }
    
    
    public void refresh(List<TabButton> tabs){
        menu.removeAll();
        if(tabs == null){return;}
        
        for(TabButton tab : tabs){
            Item item = new Item(tab.data.text, Mgr.icon.getTabIcon(tab.data.iconFilename, false), tab);
            item.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    itemClick(((Item)e.getSource()).tab);
                }
            });
            menu.add(item);
        }
        menu.setVisible(true);
        menu.setVisible(false);
    }
    
    public void mouseClick(MouseEvent e){
        if(e.getButton() == MouseEvent.BUTTON1){
            if(data.orientation == 0){
                menu.show(this, 0, TabButton.BUTTON_ACTIVE_HEIGHT);
            }
            else if(data.orientation == 1){
                menu.show(this, 0, -menu.getHeight());
            }
        }
        
    }
    
    public void menuClose(){
        menu.setVisible(false);
        mouseExit(null);
    }
    
    public void itemClick(TabButton button){
        mouseExit(null);
        WidgetMgr.MAIN_TAB_PANE.setTabFocused(null);
    }

}
