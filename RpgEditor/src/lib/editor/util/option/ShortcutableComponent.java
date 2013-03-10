/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.util.option;

import java.awt.Component;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import lib.editor.mgr.WidgetMgr;

/**
 *
 * @author gaetan
 */
public class ShortcutableComponent {
    
    boolean shortcutRepeated = false;
    
    public ShortcutableComponent(Component comp){
        comp.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(shortcutRepeated){
                    return;
                }
                
                if(! (e.getKeyCode() == KeyEvent.VK_CONTROL || e.getKeyCode() == KeyEvent.VK_ALT || e.getKeyCode() == KeyEvent.VK_SHIFT)  ){
                    shortcutRepeated = true;
                }

                /*
                if(e.getModifiers() == InputEvent.CTRL_MASK){
                    if(e.getKeyCode() == KeyEvent.VK_C){
                        WidgetMgr.MAIN_WINDOW.copy();
                    }
                    else if(e.getKeyCode() == KeyEvent.VK_V){
                        WidgetMgr.MAIN_WINDOW.paste();
                    }
                    
                }
                else{
                    if(e.getKeyCode() == KeyEvent.VK_INSERT){
                        newMap(false);
                    }
                    else if(e.getKeyCode() == KeyEvent.VK_DELETE){
                        WidgetMgr.MAIN_WINDOW.delete();
                    }
                }*/
            }

            @Override
            public void keyReleased(KeyEvent e) {
                shortcutRepeated = false;
            }
        });
    }
}
