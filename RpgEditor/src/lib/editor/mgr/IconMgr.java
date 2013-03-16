/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.mgr;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.GrayFilter;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author gaetan
 */
public class IconMgr {
    
    public final String ICON_PATH = "/assets/icons/";
    //private Map<String, String> icons;
    private Map<String, ImageIcon> cache;
    
    public IconMgr() {
        cache = new Hashtable<String, ImageIcon>();
        //addIconExtension("png", "png.png");
    }
    
    
    public ImageIcon getIcon(String filename){
        return getIcon(filename, false);
    }
    
    public ImageIcon getIcon(String filename, boolean disabledVersion) {
        if(filename == null){
            return null;
        }
        
        //System.out.println(filename);
        addIcon(filename, disabledVersion);
        
        if(disabledVersion){
            filename = filename + "-disabled";
        }
        
        return cache.get(filename);
    }
    
    private void addIcon(String filename, boolean disabledVersion){
        ImageIcon icon = null;
        
        if(!cache.containsKey(filename)){
            if(disabledVersion){
                ImageIcon baseicon = new ImageIcon(getClass().getResource(ICON_PATH + filename));
                Image normalImage = baseicon.getImage();
                filename = filename + "-disabled";
                Image grayImage = GrayFilter.createDisabledImage(normalImage);
                icon = new ImageIcon(grayImage);
            }
            else{
                icon = new ImageIcon(getClass().getResource(ICON_PATH + filename));
            }
            
            cache.put(filename, icon);
        }
    }
    
    /*
    public void addIconExtension(String ext, String iconFilename){
        icons.put(ext, iconFilename);
    }
    
    public ImageIcon getIconByExtension(String ext){
        return getIcon(icons.get(ext));
    }*/
    
}
