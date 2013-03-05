/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.mgr;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
    
    private final static String ICON_PATH = "/assets/icons/";
    
    public Icon getIcon (String filename) {
        Icon icon = new ImageIcon(getClass().getResource(ICON_PATH + filename));
        return icon;
        /*
        System.out.println(new File(ICON_PATH, filename).getAbsolutePath());
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(ICON_PATH, filename));
        } catch (IOException ex) {
            Logger.getLogger(IconMgr.class.getName()).log(Level.SEVERE, null, ex);
        }
        //java.awt.Graphics g = image.getGraphics();
        //g.setColor(color);
        //g.fillRect(1, 1, 30, 14);
        //g.setColor(java.awt.Color.black);
        //g.drawRect(0, 0, 31, 15);
        return new ImageIcon(image);*/
    }
}
