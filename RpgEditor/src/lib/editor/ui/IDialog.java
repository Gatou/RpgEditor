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
    
    public void ok();
    public void cancel();
    public void refresh();
    public JPanel getMainPanel();
    
}
