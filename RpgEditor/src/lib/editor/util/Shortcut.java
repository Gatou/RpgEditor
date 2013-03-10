/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.util;

import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 *
 * @author gaetan
 */
public class Shortcut {

    public boolean isRepeated;
    
    public Shortcut(JComponent source, KeyStroke shortcut, String name, final boolean autoRepeat, final AbstractAction action){
        isRepeated = false;
        
        source.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                isRepeated = false;
            }

            @Override
            public void focusLost(FocusEvent e) {
                isRepeated = false;
            }
        });
        
        source.getInputMap().put(shortcut, name);
        source.getActionMap().put(name,
                new AbstractAction(){
                    public void actionPerformed(ActionEvent e) {
                        if(!isRepeated){
                            if(!autoRepeat){
                                isRepeated = true;
                            }
                            action.actionPerformed(e);
                        }
                    }
                });
        
        if(!autoRepeat){
            source.getInputMap().put(KeyStroke.getKeyStroke(shortcut.getKeyCode(), shortcut.getModifiers(), true), name + "µ");
            source.getActionMap().put(name + "µ", 
                    new AbstractAction(){
                        public void actionPerformed(ActionEvent e) {
                            isRepeated = false;
                        }
                    });
        }
        

    }
            
}
