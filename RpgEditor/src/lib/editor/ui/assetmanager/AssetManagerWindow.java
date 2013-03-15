/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.ui.assetmanager;

import java.awt.Frame;
import java.io.File;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import lib.editor.mgr.AppMgr;
import lib.editor.mgr.ProjectMgr;
import lib.editor.mgr.WidgetMgr;
import lib.editor.ui.Dialog;
import lib.editor.util.SwingUtil;
import lib.editor.widget.tree.item.FilePathTreeItem;
import lib.editor.widget.tree.item.TreeItem;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author gaetan
 */
public class AssetManagerWindow extends Dialog {

    boolean readOnly;
    
    public AssetManagerWindow(Frame parent, boolean readOnly) {
        super(parent, true);
        initComponents();
        setLocationRelativeTo(null);
        layoutDialog(0);
        
        folderList.setAssetTree(assetTree);
        assetTree.setFolderList(folderList);
        
        assetFilterTextField.getDocument().addDocumentListener(new DocumentListener(){  
            public void insertUpdate(DocumentEvent e) {
                filterTextChanged();
            }

            public void removeUpdate(DocumentEvent e) {
                filterTextChanged();
            }

            public void changedUpdate(DocumentEvent e) {}
        });
        this.readOnly = readOnly;
        if(readOnly){
            setDialogButton(new String[]{"close"});
        }
    }

    

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        leftSplitPane = new javax.swing.JSplitPane();
        middleSplitPane = new javax.swing.JSplitPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        assetFilterTextField = new lib.editor.widget.textfield.IconTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        assetTree = new lib.editor.ui.assetmanager.AssetManagerAssetsTree();
        jPanel7 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel8 = new javax.swing.JPanel();
        refreshButton = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        folderList = new lib.editor.ui.assetmanager.AssetManagerAssetFolderList();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Assets Manager");
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        mainPanel.setLayout(new javax.swing.BoxLayout(mainPanel, javax.swing.BoxLayout.LINE_AXIS));

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        middleSplitPane.setResizeWeight(1.0);

        jPanel6.setMinimumSize(new java.awt.Dimension(160, 226));
        jPanel6.setPreferredSize(new java.awt.Dimension(160, 226));
        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));

        assetFilterTextField.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/find.png"))); // NOI18N
        assetFilterTextField.setMaximumSize(new java.awt.Dimension(2147483647, 28));
        assetFilterTextField.setMinimumSize(new java.awt.Dimension(20, 28));
        assetFilterTextField.setPreferredSize(new java.awt.Dimension(24, 28));
        assetFilterTextField.setStatusText("Live search filtering maps name.");
        jPanel5.add(assetFilterTextField);

        jPanel6.add(jPanel5, java.awt.BorderLayout.PAGE_END);

        jScrollPane1.setViewportView(assetTree);

        jPanel6.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        middleSplitPane.setLeftComponent(jPanel6);

        jPanel7.setLayout(new javax.swing.BoxLayout(jPanel7, javax.swing.BoxLayout.LINE_AXIS));

        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        jPanel3.add(jScrollPane3);

        jPanel7.add(jPanel3);

        jPanel8.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4));
        jPanel8.setLayout(new javax.swing.BoxLayout(jPanel8, javax.swing.BoxLayout.PAGE_AXIS));

        refreshButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/refresh.png"))); // NOI18N
        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });
        jPanel8.add(refreshButton);
        jPanel8.add(filler1);

        jPanel7.add(jPanel8);

        middleSplitPane.setRightComponent(jPanel7);

        leftSplitPane.setRightComponent(middleSplitPane);

        jPanel4.setMinimumSize(new java.awt.Dimension(160, 226));
        jPanel4.setPreferredSize(new java.awt.Dimension(160, 226));
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.LINE_AXIS));

        folderList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(folderList);

        jPanel4.add(jScrollPane4);

        leftSplitPane.setLeftComponent(jPanel4);

        jPanel2.add(leftSplitPane);

        mainPanel.add(jPanel2);

        getContentPane().add(mainPanel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        String folderSelectedText = null;
        if(folderList.getCurrentItem() != null){
            folderSelectedText = folderList.getCurrentItem().getText();
        }
        
        String assetSelectedPath = null;
        if(assetTree.getCurrentItem() != null){
            assetSelectedPath = ((FilePathTreeItem) assetTree.getCurrentItem()).getFilePath();
        }
        
        folderList.refresh();
        if(folderSelectedText != null){
            folderList.setCurrentItem(folderSelectedText);
        }
        
        if(assetSelectedPath != null){
            setCurrentTreeItemByPath(assetSelectedPath);
        }
    }//GEN-LAST:event_refreshButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private lib.editor.widget.textfield.IconTextField assetFilterTextField;
    private lib.editor.ui.assetmanager.AssetManagerAssetsTree assetTree;
    private javax.swing.Box.Filler filler1;
    private lib.editor.ui.assetmanager.AssetManagerAssetFolderList folderList;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JSplitPane leftSplitPane;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JSplitPane middleSplitPane;
    private javax.swing.JButton refreshButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public boolean ok() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean cancel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void refresh() {
        folderList.refresh();
        assetTree.refresh();
        setCurrentPath("Tiles/Graphics/Battlers/Dummy_Character.png");
    }

    @Override
    public JPanel getMainPanel() {
        return mainPanel;
    }
    
    public void saveSettings(){
        if(ProjectMgr.isProjectOpen()){
            Properties prop = ProjectMgr.getProperties();
            String prefix = "AssetManagerWindow_";
            SwingUtil.saveWindowBasics(prop, prefix, this);
            prop.setProperty(prefix + "LeftSplitPane", String.valueOf(leftSplitPane.getDividerLocation()));
            prop.setProperty(prefix + "MiddleSplitPane", String.valueOf(middleSplitPane.getDividerLocation()));
            assetTree.expandMemorizer.save("AssetManagerWindow_AssetTree." + AppMgr.getExtension("settings file"));
        }
    }
    
    public void loadSettings(){
        Properties prop = ProjectMgr.getProperties();
        String prefix = "AssetManagerWindow_";
        SwingUtil.loadWindowBasics(prop, prefix, this);
        try{
            leftSplitPane.setDividerLocation( Integer.parseInt(prop.getProperty(prefix + "LeftSplitPane")));
            middleSplitPane.setDividerLocation( Integer.parseInt(prop.getProperty(prefix + "MiddleSplitPane")));
        }catch(NumberFormatException e){}
        assetTree.expandMemorizer.load("AssetManagerWindow_AssetTree." + AppMgr.getExtension("settings file"));
    }
    
    public void dispose(){
        saveSettings();
        super.dispose();
    }
    
    public void setVisible(boolean visible){
        if(visible){
            WidgetMgr.ASSET_MANAGER_WINDOW = this;
            loadSettings();
        }
        else{
            WidgetMgr.ASSET_MANAGER_WINDOW = null;
        }
        super.setVisible(visible);
    }
    
    public void setCurrentPath(String relativePath){
        File file = new File(ProjectMgr.getAssetsPath() ,relativePath);
        
        if(!file.exists()){return;}
        
        while(!file.getParent().equals(ProjectMgr.getAssetsPath())){
            file = file.getParentFile();
        }
        String folder = file.getName();
        folderList.setCurrentItem(folder);
        
        setCurrentTreeItemByPath(relativePath);
    }
    
    public void setCurrentTreeItemByPath(String relativePath){
        for(TreeItem item : assetTree.getItems()){
            if(((FilePathTreeItem) item).getFilePath().equals(relativePath)){
                assetTree.setCurrentItem(item);
                return;
            }
        }
    }
    
    public void clearFilter(){
        assetFilterTextField.setText("");
    }
    
    public void filterTextChanged(){
        String filterText = assetFilterTextField.getText();
        assetTree.setFilterText(assetFilterTextField.getText());
        assetTree.refresh();
    }
    
}
