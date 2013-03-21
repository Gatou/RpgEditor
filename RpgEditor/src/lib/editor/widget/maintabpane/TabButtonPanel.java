/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.maintabpane;

import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.Box.Filler;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 *
 * @author gaetan
 */
public class TabButtonPanel extends JPanel{

    public static final int BUTTON_SPACING = 2;
    
    TabButton button;
    boolean isMoving;
    boolean hasMoveToTheLeft;
    boolean hasMoveToTheRight;
    
    public TabButtonPanel(TabButton button) {
        this.button = button;
        button.panel = this;
        
        
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        Dimension dim = new Dimension(BUTTON_SPACING, 0);
        add(new Filler(dim, dim, dim));
        add(button);
        add(new Filler(dim, dim, dim));
        
        setOpaque(false);
        isMoving = false;
        hasMoveToTheLeft = false;
        hasMoveToTheRight = false;
        
        setSize(MainTabPane.TOTAL_BUTTON_WIDTH, TabButton.BUTTON_ACTIVE_HEIGHT);
    }
    
}
