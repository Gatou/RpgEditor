/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.datapane;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JSplitPane;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;

/**
 *
 * @author gaetan
 */
public class MainDataSplitpane extends JSplitPane{

    private class Divider extends BasicSplitPaneDivider{

        public Divider(BasicSplitPaneUI ui) {
            super(ui);
            
            setOpaque(false);
        }

        
        
    }
    
    public MainDataSplitpane() {
        setUI(new BasicSplitPaneUI() {
                    public BasicSplitPaneDivider createDefaultDivider() {
                        return new Divider(this);
                    }
                });
    }
    
    
    
}
