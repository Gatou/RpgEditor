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
        
        String trueFilename = filename;
        if(disabledVersion){
            trueFilename = filename + "-disabled___";
        }
        
        //System.out.println(filename);
        addIcon(filename, trueFilename, disabledVersion);
        
        return cache.get(trueFilename);
    }
    
    private void addIcon(String filename, String trueFilename, boolean disabledVersion){
        try{
        ImageIcon icon = null;
        
            if(!cache.containsKey(trueFilename)){

                if(disabledVersion){
                    ImageIcon baseicon = new ImageIcon(getClass().getResource(ICON_PATH + filename));
                    Image normalImage = baseicon.getImage();

                    Image grayImage = GrayFilter.createDisabledImage(normalImage);
                    icon = new ImageIcon(grayImage);
                }
                else{
                    icon = new ImageIcon(getClass().getResource(ICON_PATH + filename));
                }
                cache.put(trueFilename, icon);
            }
        }
        catch(NullPointerException e){
            //Icon filename doesn't exist
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
