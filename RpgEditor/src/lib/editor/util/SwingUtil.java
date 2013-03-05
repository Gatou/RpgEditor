/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.util;

import java.awt.Component;
import java.awt.Toolkit;
import java.io.File;
import java.util.Properties;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import lib.editor.mgr.WidgetMgr;

/**
 *
 * @author gaetan
 */
public class SwingUtil {
    
    public static File getDirectoryChoice(Component owner, String defaultPath,
                                          String title) {
        //
        // There is apparently a bug in the native Windows FileSystem class that
        // occurs when you use a file chooser and there is a security manager
        // active. An error dialog is displayed indicating there is no disk in
        // Drive A:. To avoid this, the security manager is temporarily set to
        // null and then reset after the file chooser is closed.
        //
        File defaultDir = new File(defaultPath);
        SecurityManager sm = null;
        JFileChooser chooser = null;
        File         choice = null;

        sm = System.getSecurityManager();
        System.setSecurityManager(null);
        chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if ((defaultDir != null) && defaultDir.exists()
                && defaultDir.isDirectory()) {
            chooser.setCurrentDirectory(defaultDir);
            //chooser.setSelectedFile(defaultDir);
        }
        chooser.setDialogTitle(title);
        chooser.setApproveButtonText("OK");
        int v = chooser.showOpenDialog(owner);

        owner.requestFocus();
        switch (v) {
        case JFileChooser.APPROVE_OPTION:
            if (chooser.getSelectedFile() != null) {
                if (chooser.getSelectedFile().exists()) {
                    choice = chooser.getSelectedFile();
                } else {
                    File parentFile =
                        new File(chooser.getSelectedFile().getParent());

                    choice = parentFile;
                }
            }
            break;
        case JFileChooser.CANCEL_OPTION:
        case JFileChooser.ERROR_OPTION:
        }
        chooser.removeAll();
        chooser = null;
        System.setSecurityManager(sm);
        return choice;
    }
    
    public static File getFileChoice(Component owner, String defaultPath,
                                     FileFilter filter, String title) {
        //
        // There is apparently a bug in the native Windows FileSystem class that
        // occurs when you use a file chooser and there is a security manager
        // active. An error dialog is displayed indicating there is no disk in
        // Drive A:. To avoid this, the security manager is temporarily set to
        // null and then reset after the file chooser is closed.
        //
        File defaultSelection = new File(defaultPath);
        SecurityManager sm = null;
        File         choice = null;
        JFileChooser chooser = null;

        sm = System.getSecurityManager();
        System.setSecurityManager(null);

        chooser = new JFileChooser();
        if (defaultSelection.isDirectory()) {
            chooser.setCurrentDirectory(defaultSelection);
        } else {
            chooser.setSelectedFile(defaultSelection);
        }
        chooser.setFileFilter(filter);
        chooser.setDialogTitle(title);
        chooser.setApproveButtonText("OK");
        int v = chooser.showOpenDialog(owner);

        owner.requestFocus();
        switch (v) {
        case JFileChooser.APPROVE_OPTION:
            if (chooser.getSelectedFile() != null) {
                choice = chooser.getSelectedFile();
            }
            break;
        case JFileChooser.CANCEL_OPTION:
        case JFileChooser.ERROR_OPTION:
        }
        chooser.removeAll();
        chooser = null;
        System.setSecurityManager(sm);
        return choice;
    }
    
    public static void adjustComponentBounds(Component comp, Component parentComp){
        if(parentComp == null){
            
            int x = Math.max(comp.getX(), 12);
            int y = Math.max(comp.getY(), 12);
            int width = Math.min(comp.getWidth(), Toolkit.getDefaultToolkit().getScreenSize().width-100);
            int height = Math.min(comp.getHeight(), Toolkit.getDefaultToolkit().getScreenSize().height-100);
            
            if(x + width > Toolkit.getDefaultToolkit().getScreenSize().width - 12){
                x = Toolkit.getDefaultToolkit().getScreenSize().width - width - 12;
            }
            if(x < 0){
                x = 12;
            }
            if(y + height > Toolkit.getDefaultToolkit().getScreenSize().height - 64){
                y = Toolkit.getDefaultToolkit().getScreenSize().height - height - 64;
            }
            if(y < 0){
                y = 12;
            }
            comp.setBounds(x, y, width, height);
        }
    }
    
}
