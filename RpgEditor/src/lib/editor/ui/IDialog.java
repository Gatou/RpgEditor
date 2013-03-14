/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.ui;

import javax.swing.JPanel;

/**
 *
 * @author gaetan
 */
public interface IDialog {
    
    public boolean ok();
    public boolean cancel();
    public void refresh();
    public JPanel getMainPanel();
    
}
