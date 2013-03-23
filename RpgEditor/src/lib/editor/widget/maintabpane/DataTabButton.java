/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.maintabpane;

/**
 *
 * @author gaetan
 */
public class DataTabButton {
    
    public String text;
    public String iconFilename;
    public int orientation; //0: top, 1: bottom
    int index;
    
    public DataTabButton(String text, String iconFilename, int orientation, int index) {
        this.text = text;
        this.iconFilename = iconFilename;
        this.orientation = orientation;
        this.index = index;
    }
    
    
}
