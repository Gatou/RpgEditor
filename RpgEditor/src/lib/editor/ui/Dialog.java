/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.ui;

import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author gaetan
 */
public abstract class Dialog extends javax.swing.JDialog implements IDialog{

    protected boolean refreshing;
    
    /**
     * Creates new form Dialog
     */
    public Dialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        refreshing = false;
        setDialogButton(new String[]{"ok", "cancel"});
    }

    public void close(){
        setVisible(false);
        dispose();
    }
    
    public void setVisible(boolean visible){
        if(visible){
            refresh();
        }
        super.setVisible(visible);
    }
    
    public JPanel getBodyPanel(){
        return bodyPanel;
    }
    
    public JButton getDialogButton(String buttonName){
        if(buttonName.equals("ok")){
            return okButton;
        }
        else if(buttonName.equals("cancel")){
            return cancelButton;
        }
        else if(buttonName.equals("close")){
            return closeButton;
        }
        return null;
    }
    
    public void layoutDialog(int borderSize){
        if(borderSize > 0){
            getMainPanel().setBorder(javax.swing.BorderFactory.createEmptyBorder(borderSize, borderSize, borderSize, borderSize));
        }
        getBodyPanel().add(getMainPanel());
        pack();
        setMinimumSize(getSize());
    }
    
    public void setDialogButton(String[] buttons){
        dialogButtonsPanel.removeAll();
        dialogButtonsPanel.add(dialogFiller);
        
        for(String buttonName : buttons){
            if(buttonName.equals("ok")){
                dialogButtonsPanel.add(okButton);
            }
            else if(buttonName.equals("cancel")){
                dialogButtonsPanel.add(cancelButton);
            }
            else if(buttonName.equals("close")){
                dialogButtonsPanel.add(closeButton);
            }
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

        jPanel1 = new javax.swing.JPanel();
        bodyPanel = new javax.swing.JPanel();
        dialogButtonsPanel = new javax.swing.JPanel();
        dialogFiller = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 8, 8, 8));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.PAGE_AXIS));

        bodyPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        bodyPanel.setLayout(new javax.swing.BoxLayout(bodyPanel, javax.swing.BoxLayout.LINE_AXIS));
        jPanel1.add(bodyPanel);

        dialogButtonsPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(4, 0, 0, 0));
        dialogButtonsPanel.setLayout(new javax.swing.BoxLayout(dialogButtonsPanel, javax.swing.BoxLayout.LINE_AXIS));
        dialogButtonsPanel.add(dialogFiller);

        okButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/ok.png"))); // NOI18N
        okButton.setText("OK");
        okButton.setMaximumSize(new java.awt.Dimension(90, 28));
        okButton.setMinimumSize(new java.awt.Dimension(90, 28));
        okButton.setPreferredSize(new java.awt.Dimension(90, 28));
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });
        dialogButtonsPanel.add(okButton);

        cancelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/cancel.png"))); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.setMaximumSize(new java.awt.Dimension(90, 28));
        cancelButton.setMinimumSize(new java.awt.Dimension(90, 28));
        cancelButton.setPreferredSize(new java.awt.Dimension(90, 28));
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        dialogButtonsPanel.add(cancelButton);

        closeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/cancel.png"))); // NOI18N
        closeButton.setText("Close");
        closeButton.setMaximumSize(new java.awt.Dimension(90, 28));
        closeButton.setMinimumSize(new java.awt.Dimension(90, 28));
        closeButton.setPreferredSize(new java.awt.Dimension(90, 28));
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });
        dialogButtonsPanel.add(closeButton);

        jPanel1.add(dialogButtonsPanel);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        boolean close = ok();
        if(close){
            close();
        }
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        boolean close = cancel();
        if(close){
            close();
        }
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        close();
    }//GEN-LAST:event_closeButtonActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JPanel dialogButtonsPanel;
    private javax.swing.Box.Filler dialogFiller;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables
}
