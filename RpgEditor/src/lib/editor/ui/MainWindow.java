package lib.editor.ui;

import com.badlogic.gdx.Gdx;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;
import lib.editor.mgr.AppMgr;
import lib.editor.mgr.ProjectMgr;
import lib.editor.mgr.WidgetMgr;
import lib.editor.mgr.WindowMgr;
import lib.editor.util.SwingUtil;
import lib.editor.widget.mapeditor.MapEditorGraphicsView;
import org.jdesktop.swingx.MultiSplitLayout.Divider;
import org.jdesktop.swingx.MultiSplitLayout.Leaf;
import org.jdesktop.swingx.MultiSplitLayout.Split;
import org.jdesktop.swingx.multisplitpane.DefaultSplitPaneModel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gaetan
 */
public class MainWindow extends javax.swing.JFrame {
    
    MapEditorGraphicsView mapEditor;
    /**
     * Creates new form NewJFrame
     */
    public MainWindow() {
        
        
        initComponents();
        setLocationRelativeTo( null );
        /*
 List children = 
Arrays.asList(new Leaf("left"),
        new Divider(), 
new Leaf("center"),
new Divider(), 
new Leaf("right"));
Split modelRoot = new Split();
modelRoot.setChildren(children);

jXMultiSplitPane1.getMultiSplitLayout().setModel(modelRoot);
jXMultiSplitPane1.add(jButton1, "left");
jXMultiSplitPane1.add(jButton2, "right");
jXMultiSplitPane1.add(jPanel3, "center");

*/
        mapEditor = new MapEditorGraphicsView();
        jPanel3.add(mapEditor.getCanvas());
        



        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setProjectStateEnabled(false);
        AppMgr.init();
        setTitle(AppMgr.getNameVersion());
        WidgetMgr.MAIN_WINDOW = this;
    }
    
    public void setProjectStateEnabled(boolean enabled){
        //Enabled tool bar, except new and open project
        for(int i=2; i<mainToolBar.getComponentCount(); i++){
            if(!(mainToolBar.getComponent(i) instanceof JToolBar.Separator)){
                mainToolBar.getComponent(i).setEnabled(enabled);
            }
        }
        
        //Enabled file menu, except new, open project and exit
        for(int i=2; i<fileMenu.getMenuComponentCount()-1; i++){
            if(!(fileMenu.getMenuComponent(i) instanceof JPopupMenu.Separator)){
                fileMenu.getMenuComponent(i).setEnabled(enabled);
            }
        }
        
        //Enabled all the other menu in the mainMenuBar
        for(int i=1; i<mainMenuBar.getComponentCount(); i++){
            JMenu menu = (JMenu) mainMenuBar.getComponent(i);
            for(Component comp : menu.getMenuComponents()){
                if(!(comp instanceof JPopupMenu.Separator)){
                    comp.setEnabled(enabled);
                }
            }
        }
        
        //Show or not the middle panel (map editor, map tree, etc...)
        middlePanel.setVisible(enabled);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainToolBar = new javax.swing.JToolBar();
        toolBarNewProject = new javax.swing.JButton();
        toolBarOpenProject = new javax.swing.JButton();
        toolBarSaveProject = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jPanel1 = new javax.swing.JPanel();
        statusBarLabel = new javax.swing.JLabel();
        middlePanel = new javax.swing.JPanel();
        jSplitPane3 = new javax.swing.JSplitPane();
        jSplitPane2 = new javax.swing.JSplitPane();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        mainMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        fileMenuNewProject = new javax.swing.JMenuItem();
        fileMenuOpenProject = new javax.swing.JMenuItem();
        fileMenuSaveProject = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        fileMenuExit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 200));

        mainToolBar.setFloatable(false);
        mainToolBar.setRollover(true);

        toolBarNewProject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/new.png"))); // NOI18N
        toolBarNewProject.setToolTipText("Create a new project.");
        toolBarNewProject.setFocusable(false);
        toolBarNewProject.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolBarNewProject.setPreferredSize(new java.awt.Dimension(28, 28));
        toolBarNewProject.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBarNewProject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                toolBarNewProjectMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                toolBarNewProjectMouseExited(evt);
            }
        });
        toolBarNewProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolBarNewProjectActionPerformed(evt);
            }
        });
        mainToolBar.add(toolBarNewProject);

        toolBarOpenProject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/open.png"))); // NOI18N
        toolBarOpenProject.setToolTipText("Open an existing project.");
        toolBarOpenProject.setFocusable(false);
        toolBarOpenProject.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolBarOpenProject.setPreferredSize(new java.awt.Dimension(28, 28));
        toolBarOpenProject.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBarOpenProject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                toolBarOpenProjectMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                toolBarOpenProjectMouseExited(evt);
            }
        });
        toolBarOpenProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolBarOpenProjectActionPerformed(evt);
            }
        });
        mainToolBar.add(toolBarOpenProject);

        toolBarSaveProject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/floppy.png"))); // NOI18N
        toolBarSaveProject.setToolTipText("Save the current project.");
        toolBarSaveProject.setFocusable(false);
        toolBarSaveProject.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolBarSaveProject.setPreferredSize(new java.awt.Dimension(28, 28));
        toolBarSaveProject.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBarSaveProject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                toolBarSaveProjectMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                toolBarSaveProjectMouseExited(evt);
            }
        });
        mainToolBar.add(toolBarSaveProject);
        mainToolBar.add(jSeparator1);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/cut.png"))); // NOI18N
        jButton4.setToolTipText("Cut the selection and put it on the clipboard.");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setPreferredSize(new java.awt.Dimension(28, 28));
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        mainToolBar.add(jButton4);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/copy.png"))); // NOI18N
        jButton5.setToolTipText("Copy the selection and put it on the clipboard.");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setPreferredSize(new java.awt.Dimension(28, 28));
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        mainToolBar.add(jButton5);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/paste.png"))); // NOI18N
        jButton6.setToolTipText("Insert clipboard contents.");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setPreferredSize(new java.awt.Dimension(28, 28));
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        mainToolBar.add(jButton6);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/delete.png"))); // NOI18N
        jButton7.setToolTipText("Delete the selection.");
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setPreferredSize(new java.awt.Dimension(28, 28));
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        mainToolBar.add(jButton7);
        mainToolBar.add(jSeparator2);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/undo.png"))); // NOI18N
        jButton8.setToolTipText("Undo the last action.");
        jButton8.setFocusable(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setPreferredSize(new java.awt.Dimension(28, 28));
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        mainToolBar.add(jButton8);

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/redo.png"))); // NOI18N
        jButton9.setToolTipText("Redo the last undo action.");
        jButton9.setFocusable(false);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setPreferredSize(new java.awt.Dimension(28, 28));
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        mainToolBar.add(jButton9);
        mainToolBar.add(jSeparator3);

        getContentPane().add(mainToolBar, java.awt.BorderLayout.PAGE_START);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel1.setPreferredSize(new java.awt.Dimension(35, 24));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));
        jPanel1.add(statusBarLabel);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        middlePanel.setLayout(new java.awt.GridLayout(1, 0));

        jSplitPane3.setResizeWeight(1.0);

        jButton1.setText("jButton1");
        jSplitPane2.setLeftComponent(jButton1);

        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel3.setMinimumSize(new java.awt.Dimension(300, 300));
        jPanel3.setPreferredSize(new java.awt.Dimension(300, 300));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));
        jSplitPane2.setRightComponent(jPanel3);

        jSplitPane3.setLeftComponent(jSplitPane2);

        jButton2.setText("jButton1");
        jButton2.setMaximumSize(new java.awt.Dimension(20, 20));
        jSplitPane3.setRightComponent(jButton2);

        middlePanel.add(jSplitPane3);

        getContentPane().add(middlePanel, java.awt.BorderLayout.CENTER);

        fileMenu.setText("File");

        fileMenuNewProject.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        fileMenuNewProject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/new.png"))); // NOI18N
        fileMenuNewProject.setText("New Project...");
        fileMenuNewProject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fileMenuNewProjectMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                fileMenuNewProjectMouseExited(evt);
            }
        });
        fileMenuNewProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileMenuNewProjectActionPerformed(evt);
            }
        });
        fileMenu.add(fileMenuNewProject);

        fileMenuOpenProject.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        fileMenuOpenProject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/open.png"))); // NOI18N
        fileMenuOpenProject.setText("Open project...");
        fileMenuOpenProject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fileMenuOpenProjectMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                fileMenuOpenProjectMouseExited(evt);
            }
        });
        fileMenuOpenProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileMenuOpenProjectActionPerformed(evt);
            }
        });
        fileMenu.add(fileMenuOpenProject);

        fileMenuSaveProject.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        fileMenuSaveProject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/floppy.png"))); // NOI18N
        fileMenuSaveProject.setText("Save project");
        fileMenuSaveProject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fileMenuSaveProjectMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                fileMenuSaveProjectMouseExited(evt);
            }
        });
        fileMenu.add(fileMenuSaveProject);
        fileMenu.add(jSeparator4);

        fileMenuExit.setText("Exit");
        fileMenuExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                fileMenuExitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                fileMenuExitMouseExited(evt);
            }
        });
        fileMenuExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileMenuExitActionPerformed(evt);
            }
        });
        fileMenu.add(fileMenuExit);

        mainMenuBar.add(fileMenu);

        jMenu2.setText("Edit");

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/undo.png"))); // NOI18N
        jMenuItem5.setText("Undo");
        jMenu2.add(jMenuItem5);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/redo.png"))); // NOI18N
        jMenuItem6.setText("Redo");
        jMenu2.add(jMenuItem6);
        jMenu2.add(jSeparator5);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/cut.png"))); // NOI18N
        jMenuItem7.setText("Cut");
        jMenu2.add(jMenuItem7);

        jMenuItem8.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/copy.png"))); // NOI18N
        jMenuItem8.setText("Copy");
        jMenu2.add(jMenuItem8);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/paste.png"))); // NOI18N
        jMenuItem9.setText("Paste");
        jMenu2.add(jMenuItem9);

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/delete.png"))); // NOI18N
        jMenuItem10.setText("Delete");
        jMenu2.add(jMenuItem10);

        mainMenuBar.add(jMenu2);

        setJMenuBar(mainMenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fileMenuNewProjectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fileMenuNewProjectMouseEntered
        statusBarLabel.setText("Create a new project.");
    }//GEN-LAST:event_fileMenuNewProjectMouseEntered

    private void fileMenuNewProjectMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fileMenuNewProjectMouseExited
        statusBarLabel.setText("");
    }//GEN-LAST:event_fileMenuNewProjectMouseExited

    private void fileMenuOpenProjectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fileMenuOpenProjectMouseEntered
        statusBarLabel.setText("Open an existing project.");
    }//GEN-LAST:event_fileMenuOpenProjectMouseEntered

    private void fileMenuOpenProjectMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fileMenuOpenProjectMouseExited
        statusBarLabel.setText("");
    }//GEN-LAST:event_fileMenuOpenProjectMouseExited

    private void fileMenuSaveProjectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fileMenuSaveProjectMouseEntered
        statusBarLabel.setText("Save the current project.");
    }//GEN-LAST:event_fileMenuSaveProjectMouseEntered

    private void fileMenuSaveProjectMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fileMenuSaveProjectMouseExited
        statusBarLabel.setText("");
    }//GEN-LAST:event_fileMenuSaveProjectMouseExited

    private void fileMenuExitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fileMenuExitMouseEntered
        statusBarLabel.setText("Exit the application.");
    }//GEN-LAST:event_fileMenuExitMouseEntered

    private void fileMenuExitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fileMenuExitMouseExited
        
    }//GEN-LAST:event_fileMenuExitMouseExited

    private void toolBarNewProjectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toolBarNewProjectMouseEntered
        statusBarLabel.setText("Create a new project.");
    }//GEN-LAST:event_toolBarNewProjectMouseEntered

    private void toolBarNewProjectMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toolBarNewProjectMouseExited
        statusBarLabel.setText("");
    }//GEN-LAST:event_toolBarNewProjectMouseExited

    private void toolBarOpenProjectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toolBarOpenProjectMouseEntered
        statusBarLabel.setText("Open an existing project.");
    }//GEN-LAST:event_toolBarOpenProjectMouseEntered

    private void toolBarOpenProjectMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toolBarOpenProjectMouseExited
        statusBarLabel.setText("");
    }//GEN-LAST:event_toolBarOpenProjectMouseExited

    private void toolBarSaveProjectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toolBarSaveProjectMouseEntered
        statusBarLabel.setText("Save the current project.");
    }//GEN-LAST:event_toolBarSaveProjectMouseEntered

    private void toolBarSaveProjectMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_toolBarSaveProjectMouseExited
        statusBarLabel.setText("");
    }//GEN-LAST:event_toolBarSaveProjectMouseExited

    private void fileMenuExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileMenuExitActionPerformed
        dispose();
    }//GEN-LAST:event_fileMenuExitActionPerformed

    private void fileMenuNewProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileMenuNewProjectActionPerformed
        ProjectWindow win = new ProjectWindow(this, true);
        win.setVisible(true);
    }//GEN-LAST:event_fileMenuNewProjectActionPerformed

    private void toolBarNewProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolBarNewProjectActionPerformed
        ProjectWindow win = new ProjectWindow(this, true);
        win.setVisible(true);
    }//GEN-LAST:event_toolBarNewProjectActionPerformed

    private void fileMenuOpenProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileMenuOpenProjectActionPerformed
        performOpenProject();
    }//GEN-LAST:event_fileMenuOpenProjectActionPerformed

    private void toolBarOpenProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolBarOpenProjectActionPerformed
        performOpenProject();
    }//GEN-LAST:event_toolBarOpenProjectActionPerformed

    private void performOpenProject(){
        String filterText = AppMgr.NAME + " (*." + AppMgr.getExtension("project file") + ")";
        File result = SwingUtil.getFileChoice(this, "", new FileNameExtensionFilter(filterText, AppMgr.getExtension("project file")), "Open project");
        if(result != null){
            String projectPath = result.getParent();
            if(projectPath != null){
                ProjectMgr.openProject(projectPath);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem fileMenuExit;
    private javax.swing.JMenuItem fileMenuNewProject;
    private javax.swing.JMenuItem fileMenuOpenProject;
    private javax.swing.JMenuItem fileMenuSaveProject;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JToolBar mainToolBar;
    private javax.swing.JPanel middlePanel;
    private javax.swing.JLabel statusBarLabel;
    private javax.swing.JButton toolBarNewProject;
    private javax.swing.JButton toolBarOpenProject;
    private javax.swing.JButton toolBarSaveProject;
    // End of variables declaration//GEN-END:variables
}
