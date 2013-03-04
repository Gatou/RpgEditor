
import lib.editor.mgr.WindowMgr;
import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import lib.editor.ui.MainWindow;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gaetan
 */
public class Main {

    /**
     * @param args the command line arguments
     */
  public static void main (String[] args) {
      
      
    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
      if ("Nimbus".equals(info.getName())) {
        try {
          UIManager.setLookAndFeel(info.getClassName());
        } catch (Throwable ignored) {
        }
        break;
      }
    }
    EventQueue.invokeLater(new Runnable() {
      public void run () {
        new MainWindow().setVisible(true);
      }
    });
  }
}
