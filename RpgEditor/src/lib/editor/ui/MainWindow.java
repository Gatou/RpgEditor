package lib.editor.ui;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Cubic;
import lib.editor.ui.assetmanager.AssetManagerWindow;
import com.badlogic.gdx.Gdx;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.filechooser.FileNameExtensionFilter;
import lib.editor.data.game.DataMap;
import lib.editor.mgr.AppMgr;
import lib.editor.mgr.TransferMgr;
import lib.editor.mgr.DataMgr;
import lib.editor.mgr.Mgr;
import lib.editor.mgr.ProjectMgr;
import lib.editor.mgr.SaveMgr;
import lib.editor.mgr.WidgetMgr;
import lib.editor.mgr.WindowMgr;
import lib.editor.util.Cst;
import lib.editor.util.SwingUtil;
import lib.editor.util.tween.ComponentAccessor;
import lib.editor.widget.inspector.Inspector;
import lib.editor.widget.mapeditor.MapEditorGraphicsView;
import lib.editor.widget.menu.MenuItem;
import lib.editor.widget.tree.tree.DatabaseTree;
import org.jdesktop.swingx.JXCollapsiblePane;
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
    Inspector inspector;
    
    public final TweenManager tweenManager;
    private final Timer tweenTimer;
    /**
     * Creates new form NewJFrame
     */
    public MainWindow() {
        //Tween.enablePooling(false);
        //Tween.registerAccessor(Component.class, new ComponentAccessor());

                
        tweenManager = new TweenManager();
        tweenTimer = new Timer(1000/60, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                tweenManager.update(1000/60);
            }
        });
        //timer.setInitialDelay(pause);
        tweenTimer.start(); 
        
        setLocationRelativeTo( null );
        


        
    }
    
    public void init(){
        initComponents();
        


        Tween.registerAccessor(Component.class, new ComponentAccessor());
        

                
        WidgetMgr.MAIN_TAB_PANE = mainTabPane;
        
        mapEditor = new MapEditorGraphicsView();
        WidgetMgr.MAP_EDITOR = mapEditor;
        jPanel3.add(mapEditor.getCanvas());
        mapEditor.init();
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        
        setTitle(AppMgr.getNameVersion());
        //WidgetMgr.MAIN_WINDOW = this;
        
        setProjectStateEnabled(false);
        AppMgr.init();
        
        WidgetMgr.MAP_TREE = mapTree;
        WidgetMgr.STATUS_LABEL = statusBarLabel;
        //WidgetMgr.MAP_TREE.setup();
        
        
        mapTree.setFilter(mapTreeFilterTextField);
        
        
        
        inspector = new Inspector(jXTaskPaneContainer2);
        WidgetMgr.INSPECTOR = inspector;
        //Inspector.init();
        //propertyCollapsiblePane.add();
        //jXTaskPane1.add(jTable1);
        AbstractButton[] saveButtons = new AbstractButton[]{fileSave, toolBarSave};
        SaveMgr.init(saveButtons);
        
    }
    
    
    public void saveSettings(Properties prop){
        String prefix = "MainWindow_";
        SwingUtil.saveWindowBasics(prop, prefix, this);
        prop.put(prefix + "maximized", String.valueOf(this.getExtendedState() & JFrame.MAXIMIZED_BOTH));
    }
    
    public void loadSettings(Properties prop){
        String prefix = "MainWindow_";
        SwingUtil.loadWindowBasics(prop, prefix, this);
        if(Integer.parseInt(prop.getProperty(prefix + "maximized")) != 0){
            setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        }
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

    private void openProject(){
        String filterText = AppMgr.NAME + " (*." + AppMgr.getExtension("project file") + ")";
        File result = SwingUtil.getFileChoice(this, "", new FileNameExtensionFilter(filterText, AppMgr.getExtension("project file")), "Open project");
        if(result != null){
            String projectPath = result.getParent();
            if(projectPath != null){
                ProjectMgr.openProject(projectPath);
            }
        }
    }
    
    private void exit(){
        tweenTimer.stop();
        //tweenTimer = null;
        AppMgr.saveSettings();
        ProjectMgr.closeProject();
        dispose();
    }
    
    public void undo(){
        System.out.println("undo");
    }
    
    public void redo(){
        System.out.println("redo");
    }
    
    public void cut(){
        if(!toolBarCut.isEnabled()){
            return;
        }
        
    }
    
    public void copy(){
        if(!toolBarCopy.isEnabled()){
            return;
        }
        
        if(TransferMgr.lastFocused == mapTree){
            mapTree.copy();
        }
    }
    
    public void paste(){
        if(!toolBarPaste.isEnabled()){
            return;
        }
        
        if(TransferMgr.lastFocused == mapTree){
            mapTree.paste();
        }
    }
    
    public void delete(){
        if(!toolBarDelete.isEnabled()){
            return;
        }
        
        if(TransferMgr.lastFocused == mapTree){
            mapTree.delete();
        }
        
    }
    
    public void setActionEnabled(String type, boolean enabled){
        if(type.equals("undo")){
            toolBarUndo.setEnabled(enabled);
            editUndo.setEnabled(enabled);
        }
        else if(type.equals("redo")){
            toolBarRedo.setEnabled(enabled);
            editRedo.setEnabled(enabled);
        }
        else if(type.equals("cut")){
            toolBarCut.setEnabled(enabled);
            editCut.setEnabled(enabled);
        }
        else if(type.equals("copy")){
            toolBarCopy.setEnabled(enabled);
            editCopy.setEnabled(enabled);
        }
        else if(type.equals("paste")){
            toolBarPaste.setEnabled(enabled);
            editPaste.setEnabled(enabled);
        }
        else if(type.equals("delete")){
            toolBarDelete.setEnabled(enabled);
            editDelete.setEnabled(enabled);
        }
        else if(type.equals("new map")){
            mapTreeCreateMapButton.setEnabled(enabled);
        }
    }
    
    public void openWindow(String window){
        statusBarLabel.setText("");
        if(window.equals("resourceManager")){
            //System.out.println(AssetManagerWindow.getAssetFilename(this, "Tiles/Graphics/Battlers/Dummy_Character.png", Cst.VALID_SOUND_FORMAT));
            (new AssetManagerWindow(this, true, "", Cst.ALL_ASSET_FORMAT)).setVisible(true);
        }
    }
    
    public void refresh(){
        mapTree.setup();//setDatabase(DataMgr.dataGame.get("MapInfos"), DataMgr.dataEditor.get("MapInfos"));
        

    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        middlePanel = new javax.swing.JPanel();
        jSplitPane3 = new javax.swing.JSplitPane();
        jSplitPane2 = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mapTree = new lib.editor.widget.tree.tree.MapTree();
        jPanel5 = new javax.swing.JPanel();
        mapTreeCreateMapButton = new lib.editor.widget.button.ToolBarButton();
        mapTreeFilterTextField = new lib.editor.widget.textfield.IconTextField();
        jXTaskPaneContainer2 = new org.jdesktop.swingx.JXTaskPaneContainer();
        mainToolBar = new javax.swing.JToolBar();
        toolBarNew = new lib.editor.widget.button.ToolBarButton();
        toolBarOpen = new lib.editor.widget.button.ToolBarButton();
        toolBarSave = new lib.editor.widget.button.ToolBarButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        toolBarCut = new lib.editor.widget.button.ToolBarButton();
        toolBarCopy = new lib.editor.widget.button.ToolBarButton();
        toolBarPaste = new lib.editor.widget.button.ToolBarButton();
        toolBarDelete = new lib.editor.widget.button.ToolBarButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        toolBarUndo = new lib.editor.widget.button.ToolBarButton();
        toolBarRedo = new lib.editor.widget.button.ToolBarButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        toolBarButton1 = new lib.editor.widget.button.ToolBarButton();
        mainTabPane = new lib.editor.widget.maintabpane.MainTabPane();
        jXStatusBar1 = new org.jdesktop.swingx.JXStatusBar();
        statusBarLabel = new javax.swing.JLabel();
        mainMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        fileNew = new lib.editor.widget.menu.MenuItem();
        fileOpen = new lib.editor.widget.menu.MenuItem();
        fileClose = new lib.editor.widget.menu.MenuItem();
        fileSave = new lib.editor.widget.menu.MenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        fileExit = new lib.editor.widget.menu.MenuItem();
        editMenu = new javax.swing.JMenu();
        editUndo = new lib.editor.widget.menu.MenuItem();
        editRedo = new lib.editor.widget.menu.MenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        editCut = new lib.editor.widget.menu.MenuItem();
        editCopy = new lib.editor.widget.menu.MenuItem();
        editPaste = new lib.editor.widget.menu.MenuItem();
        editDelete = new lib.editor.widget.menu.MenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuItem2 = new lib.editor.widget.menu.MenuItem();
        jMenu1 = new javax.swing.JMenu();
        menuItem1 = new lib.editor.widget.menu.MenuItem();

        middlePanel.setLayout(new java.awt.GridLayout(1, 0));

        jSplitPane3.setResizeWeight(1.0);

        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel3.setMinimumSize(new java.awt.Dimension(300, 300));
        jPanel3.setPreferredSize(new java.awt.Dimension(300, 300));
        jPanel3.setLayout(new java.awt.GridLayout(1, 0));
        jSplitPane2.setRightComponent(jPanel3);

        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
        jPanel2.setMinimumSize(new java.awt.Dimension(160, 50));
        jPanel2.setPreferredSize(new java.awt.Dimension(160, 362));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setAutoscrolls(true);

        mapTree.setAutoscrolls(true);
        mapTree.setDropMode(javax.swing.DropMode.ON_OR_INSERT);
        mapTree.setMaximumSize(null);
        mapTree.setMinimumSize(null);
        mapTree.setPreferredSize(null);
        mapTree.setRowHeight(18);
        jScrollPane1.setViewportView(mapTree);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));

        mapTreeCreateMapButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/new.png"))); // NOI18N
        mapTreeCreateMapButton.setToolTipText("Create a new map.");
        mapTreeCreateMapButton.setFocusable(false);
        mapTreeCreateMapButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mapTreeCreateMapButton.setStatusText("Create a new map.");
        mapTreeCreateMapButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        mapTreeCreateMapButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mapTreeCreateMapButtonActionPerformed(evt);
            }
        });
        jPanel5.add(mapTreeCreateMapButton);

        mapTreeFilterTextField.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/find.png"))); // NOI18N
        mapTreeFilterTextField.setMaximumSize(new java.awt.Dimension(2147483647, 28));
        mapTreeFilterTextField.setMinimumSize(new java.awt.Dimension(20, 28));
        mapTreeFilterTextField.setPreferredSize(new java.awt.Dimension(24, 28));
        mapTreeFilterTextField.setStatusText("Live search filtering maps name.");
        jPanel5.add(mapTreeFilterTextField);

        jPanel2.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        jSplitPane2.setLeftComponent(jPanel2);

        jSplitPane3.setLeftComponent(jSplitPane2);

        org.jdesktop.swingx.VerticalLayout verticalLayout2 = new org.jdesktop.swingx.VerticalLayout();
        verticalLayout2.setGap(14);
        jXTaskPaneContainer2.setLayout(verticalLayout2);
        jSplitPane3.setRightComponent(jXTaskPaneContainer2);

        middlePanel.add(jSplitPane3);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 200));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        mainToolBar.setFloatable(false);
        mainToolBar.setRollover(true);

        toolBarNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/new.png"))); // NOI18N
        toolBarNew.setToolTipText("Create a new project.");
        toolBarNew.setFocusable(false);
        toolBarNew.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolBarNew.setStatusText("Create a new project.");
        toolBarNew.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBarNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolBarNewActionPerformed(evt);
            }
        });
        mainToolBar.add(toolBarNew);

        toolBarOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/open.png"))); // NOI18N
        toolBarOpen.setToolTipText("Open an existing project.");
        toolBarOpen.setFocusable(false);
        toolBarOpen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolBarOpen.setStatusText("Open an existing project.");
        toolBarOpen.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBarOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolBarOpenActionPerformed(evt);
            }
        });
        mainToolBar.add(toolBarOpen);

        toolBarSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/floppy.png"))); // NOI18N
        toolBarSave.setToolTipText("Save the current project.");
        toolBarSave.setFocusable(false);
        toolBarSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolBarSave.setStatusText("Save the current project.");
        toolBarSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        mainToolBar.add(toolBarSave);
        mainToolBar.add(jSeparator1);

        toolBarCut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/cut.png"))); // NOI18N
        toolBarCut.setToolTipText("Cut the selection and put it on the clipboard.");
        toolBarCut.setFocusable(false);
        toolBarCut.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolBarCut.setStatusText("Cut the selection and put it on the clipboard.");
        toolBarCut.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBarCut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolBarCutActionPerformed(evt);
            }
        });
        mainToolBar.add(toolBarCut);

        toolBarCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/copy.png"))); // NOI18N
        toolBarCopy.setToolTipText("Copy the selection and put it on the clipboard.");
        toolBarCopy.setFocusable(false);
        toolBarCopy.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolBarCopy.setStatusText("Copy the selection and put it on the clipboard.");
        toolBarCopy.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBarCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolBarCopyActionPerformed(evt);
            }
        });
        mainToolBar.add(toolBarCopy);

        toolBarPaste.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/paste.png"))); // NOI18N
        toolBarPaste.setToolTipText("Insert clipboard contents.");
        toolBarPaste.setFocusable(false);
        toolBarPaste.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolBarPaste.setStatusText("Insert clipboard contents.");
        toolBarPaste.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBarPaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolBarPasteActionPerformed(evt);
            }
        });
        mainToolBar.add(toolBarPaste);

        toolBarDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/delete.png"))); // NOI18N
        toolBarDelete.setToolTipText("Delete the selection.");
        toolBarDelete.setFocusable(false);
        toolBarDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolBarDelete.setStatusText("Delete the selection.");
        toolBarDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBarDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolBarDeleteActionPerformed(evt);
            }
        });
        mainToolBar.add(toolBarDelete);
        mainToolBar.add(jSeparator2);

        toolBarUndo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/undo.png"))); // NOI18N
        toolBarUndo.setToolTipText("Undo the last action.");
        toolBarUndo.setFocusable(false);
        toolBarUndo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolBarUndo.setStatusText("Undo the last action.");
        toolBarUndo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBarUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolBarUndoActionPerformed(evt);
            }
        });
        mainToolBar.add(toolBarUndo);

        toolBarRedo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/redo.png"))); // NOI18N
        toolBarRedo.setToolTipText("Redo the last undo action.");
        toolBarRedo.setFocusable(false);
        toolBarRedo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolBarRedo.setStatusText("Redo the last undo action.");
        toolBarRedo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBarRedo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolBarRedoActionPerformed(evt);
            }
        });
        mainToolBar.add(toolBarRedo);
        mainToolBar.add(jSeparator3);

        toolBarButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/resource.png"))); // NOI18N
        toolBarButton1.setToolTipText("Open the assets manager.");
        toolBarButton1.setFocusable(false);
        toolBarButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        toolBarButton1.setStatusText("Open the assets manager.");
        toolBarButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBarButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                toolBarButton1ActionPerformed(evt);
            }
        });
        mainToolBar.add(toolBarButton1);

        getContentPane().add(mainToolBar, java.awt.BorderLayout.PAGE_START);
        getContentPane().add(mainTabPane, java.awt.BorderLayout.CENTER);

        jXStatusBar1.setMaximumSize(new java.awt.Dimension(2147483647, 24));
        jXStatusBar1.setMinimumSize(new java.awt.Dimension(31, 24));
        jXStatusBar1.setPreferredSize(new java.awt.Dimension(31, 24));
        jXStatusBar1.setLayout(null);

        statusBarLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 4, 0, 0));
        jXStatusBar1.add(statusBarLabel);
        statusBarLabel.setBounds(5, 4, 4, 16);

        getContentPane().add(jXStatusBar1, java.awt.BorderLayout.PAGE_END);

        fileMenu.setText("File");

        fileNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        fileNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/new.png"))); // NOI18N
        fileNew.setText("New Project...");
        fileNew.setStatusText("Create a new project.");
        fileNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileNewActionPerformed(evt);
            }
        });
        fileMenu.add(fileNew);

        fileOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        fileOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/open.png"))); // NOI18N
        fileOpen.setText("Open Project...");
        fileOpen.setPreferredSize(null);
        fileOpen.setStatusText("Open an existing project.");
        fileOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileOpenActionPerformed(evt);
            }
        });
        fileMenu.add(fileOpen);

        fileClose.setText("Close Project");
        fileClose.setStatusText("Close the current project.");
        fileClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileCloseActionPerformed(evt);
            }
        });
        fileMenu.add(fileClose);

        fileSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        fileSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/floppy.png"))); // NOI18N
        fileSave.setText("Save Project");
        fileSave.setStatusText("Save the current project.");
        fileMenu.add(fileSave);
        fileMenu.add(jSeparator4);

        fileExit.setText("Exit");
        fileExit.setStatusText("Exit the application.");
        fileExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileExitActionPerformed(evt);
            }
        });
        fileMenu.add(fileExit);

        mainMenuBar.add(fileMenu);

        editMenu.setText("Edit");

        editUndo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        editUndo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/undo.png"))); // NOI18N
        editUndo.setText("Undo");
        editUndo.setStatusText("Undo the last action.");
        editUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editUndoActionPerformed(evt);
            }
        });
        editMenu.add(editUndo);

        editRedo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        editRedo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/redo.png"))); // NOI18N
        editRedo.setText("Redo");
        editRedo.setStatusText("Redo the last undo action.");
        editRedo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editRedoActionPerformed(evt);
            }
        });
        editMenu.add(editRedo);
        editMenu.add(jSeparator5);

        editCut.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        editCut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/cut.png"))); // NOI18N
        editCut.setText("Cut");
        editCut.setStatusText("Cut the selection and put it on the clipboard.");
        editCut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editCutActionPerformed(evt);
            }
        });
        editMenu.add(editCut);

        editCopy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        editCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/copy.png"))); // NOI18N
        editCopy.setText("Copy");
        editCopy.setStatusText("Copy the selection and put it on the clipboard.");
        editCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editCopyActionPerformed(evt);
            }
        });
        editMenu.add(editCopy);

        editPaste.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        editPaste.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/paste.png"))); // NOI18N
        editPaste.setText("Paste");
        editPaste.setStatusText("Insert clipboard contents.");
        editPaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPasteActionPerformed(evt);
            }
        });
        editMenu.add(editPaste);

        editDelete.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        editDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/delete.png"))); // NOI18N
        editDelete.setText("Delete");
        editDelete.setStatusText("Delete the selection.");
        editDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editDeleteActionPerformed(evt);
            }
        });
        editMenu.add(editDelete);

        mainMenuBar.add(editMenu);

        jMenu2.setText("Tools");

        menuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, 0));
        menuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/resource.png"))); // NOI18N
        menuItem2.setText("Assets Manager");
        menuItem2.setStatusText("Open the assets manager.");
        menuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(menuItem2);

        mainMenuBar.add(jMenu2);

        jMenu1.setText("Game");

        menuItem1.setText("Open Game Folder");
        menuItem1.setStatusText("Open folder for game currently being edited.");
        menuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(menuItem1);

        mainMenuBar.add(jMenu1);

        setJMenuBar(mainMenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
       exit();
    }//GEN-LAST:event_formWindowClosing

    private void toolBarNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolBarNewActionPerformed
        statusBarLabel.setText("");
        new ProjectWindow(this, true).setVisible(true);
    }//GEN-LAST:event_toolBarNewActionPerformed

    private void toolBarOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolBarOpenActionPerformed
        statusBarLabel.setText("");
        openProject();
    }//GEN-LAST:event_toolBarOpenActionPerformed

    private void toolBarCutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolBarCutActionPerformed
        cut();
    }//GEN-LAST:event_toolBarCutActionPerformed

    private void toolBarCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolBarCopyActionPerformed
        copy();
    }//GEN-LAST:event_toolBarCopyActionPerformed

    private void toolBarPasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolBarPasteActionPerformed
        paste();
    }//GEN-LAST:event_toolBarPasteActionPerformed

    private void toolBarDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolBarDeleteActionPerformed
        delete();
    }//GEN-LAST:event_toolBarDeleteActionPerformed

    private void toolBarUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolBarUndoActionPerformed
        undo();
    }//GEN-LAST:event_toolBarUndoActionPerformed

    private void toolBarRedoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolBarRedoActionPerformed
        redo();
    }//GEN-LAST:event_toolBarRedoActionPerformed

    private void fileNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileNewActionPerformed
        statusBarLabel.setText("");
        new ProjectWindow(this, true).setVisible(true);
    }//GEN-LAST:event_fileNewActionPerformed

    private void fileOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileOpenActionPerformed
        statusBarLabel.setText("");
        openProject();
    }//GEN-LAST:event_fileOpenActionPerformed

    private void fileCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileCloseActionPerformed
        statusBarLabel.setText("");
        ProjectMgr.closeProject();
    }//GEN-LAST:event_fileCloseActionPerformed

    private void fileExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileExitActionPerformed
        statusBarLabel.setText("");
        exit();
    }//GEN-LAST:event_fileExitActionPerformed

    private void editUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editUndoActionPerformed
        statusBarLabel.setText("");
        undo();
    }//GEN-LAST:event_editUndoActionPerformed

    private void editRedoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editRedoActionPerformed
        statusBarLabel.setText("");
        redo();
    }//GEN-LAST:event_editRedoActionPerformed

    private void editCutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editCutActionPerformed
        statusBarLabel.setText("");
        cut();
    }//GEN-LAST:event_editCutActionPerformed

    private void editCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editCopyActionPerformed
        statusBarLabel.setText("");
        copy();
    }//GEN-LAST:event_editCopyActionPerformed

    private void editPasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPasteActionPerformed
        statusBarLabel.setText("");
        paste();
    }//GEN-LAST:event_editPasteActionPerformed

    private void editDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editDeleteActionPerformed
        statusBarLabel.setText("");
        delete();
    }//GEN-LAST:event_editDeleteActionPerformed

    private void mapTreeCreateMapButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mapTreeCreateMapButtonActionPerformed
        mapTree.newMap(false);
    }//GEN-LAST:event_mapTreeCreateMapButtonActionPerformed

    private void menuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem1ActionPerformed
        statusBarLabel.setText("");
        try {
            Desktop.getDesktop().open(new File(ProjectMgr.getProjectPath()));
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuItem1ActionPerformed

    private void toolBarButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_toolBarButton1ActionPerformed
        openWindow("resourceManager");
    }//GEN-LAST:event_toolBarButton1ActionPerformed

    private void menuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem2ActionPerformed
        openWindow("resourceManager");
    }//GEN-LAST:event_menuItem2ActionPerformed



    

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private lib.editor.widget.menu.MenuItem editCopy;
    private lib.editor.widget.menu.MenuItem editCut;
    private lib.editor.widget.menu.MenuItem editDelete;
    private javax.swing.JMenu editMenu;
    private lib.editor.widget.menu.MenuItem editPaste;
    private lib.editor.widget.menu.MenuItem editRedo;
    private lib.editor.widget.menu.MenuItem editUndo;
    private lib.editor.widget.menu.MenuItem fileClose;
    private lib.editor.widget.menu.MenuItem fileExit;
    private javax.swing.JMenu fileMenu;
    private lib.editor.widget.menu.MenuItem fileNew;
    private lib.editor.widget.menu.MenuItem fileOpen;
    private lib.editor.widget.menu.MenuItem fileSave;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private org.jdesktop.swingx.JXStatusBar jXStatusBar1;
    private org.jdesktop.swingx.JXTaskPaneContainer jXTaskPaneContainer2;
    private javax.swing.JMenuBar mainMenuBar;
    private lib.editor.widget.maintabpane.MainTabPane mainTabPane;
    private javax.swing.JToolBar mainToolBar;
    private lib.editor.widget.tree.tree.MapTree mapTree;
    private lib.editor.widget.button.ToolBarButton mapTreeCreateMapButton;
    private lib.editor.widget.textfield.IconTextField mapTreeFilterTextField;
    private lib.editor.widget.menu.MenuItem menuItem1;
    private lib.editor.widget.menu.MenuItem menuItem2;
    private javax.swing.JPanel middlePanel;
    private javax.swing.JLabel statusBarLabel;
    private lib.editor.widget.button.ToolBarButton toolBarButton1;
    private lib.editor.widget.button.ToolBarButton toolBarCopy;
    private lib.editor.widget.button.ToolBarButton toolBarCut;
    private lib.editor.widget.button.ToolBarButton toolBarDelete;
    private lib.editor.widget.button.ToolBarButton toolBarNew;
    private lib.editor.widget.button.ToolBarButton toolBarOpen;
    private lib.editor.widget.button.ToolBarButton toolBarPaste;
    private lib.editor.widget.button.ToolBarButton toolBarRedo;
    private lib.editor.widget.button.ToolBarButton toolBarSave;
    private lib.editor.widget.button.ToolBarButton toolBarUndo;
    // End of variables declaration//GEN-END:variables

}
