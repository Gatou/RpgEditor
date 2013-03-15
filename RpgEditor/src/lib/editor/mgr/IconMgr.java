/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.mgr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author gaetan
 */
public class IconMgr {
    
    public final String ICON_PATH = "/assets/icons/";
    //private Map<String, String> icons;

    public IconMgr() {
        //addIconExtension("png", "png.png");
    }
    
    
    
    public ImageIcon getIcon (String filename) {
        ImageIcon icon = null;
        try{
            icon = new ImageIcon(getClass().getResource(ICON_PATH + filename));
        }
        catch(NullPointerException e){
            
        }
        return icon;
    }
    /*
    public void addIconExtension(String ext, String iconFilename){
        icons.put(ext, iconFilename);
    }
    
    public ImageIcon getIconByExtension(String ext){
        return getIcon(icons.get(ext));
    }*/
    
}
