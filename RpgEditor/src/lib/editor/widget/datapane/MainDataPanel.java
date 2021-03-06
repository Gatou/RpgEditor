/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.datapane;

import java.awt.BorderLayout;

/**
 *
 * @author gaetan
 */
public class MainDataPanel extends javax.swing.JPanel {

    /**
     * Creates new form DataPane
     */
    public MainDataPanel() {
        initComponents();
        setOpaque(false);
        
        ((BorderLayout)leftPanel.getLayout()).setVgap(mainDataSplitpane1.getDividerSize());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainDataSplitpane1 = new lib.editor.widget.datapane.MainDataSplitpane();
        mainDataSplitpane2 = new lib.editor.widget.datapane.MainDataSplitpane();
        jPanel1 = new javax.swing.JPanel();
        dataNamePane3 = new lib.editor.widget.datapane.DataNamePane();
        jPanel2 = new javax.swing.JPanel();
        dataNamePane4 = new lib.editor.widget.datapane.DataNamePane();
        leftPanel = new javax.swing.JPanel();
        dataNamePane2 = new lib.editor.widget.datapane.DataNamePane();
        dataNamePane5 = new lib.editor.widget.datapane.DataNamePane();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        mainDataSplitpane1.setBorder(null);

        mainDataSplitpane2.setResizeWeight(1.0);

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        dataNamePane3.setMinimumSize(new java.awt.Dimension(23, 42));
        dataNamePane3.setPreferredSize(new java.awt.Dimension(23, 42));
        jPanel1.add(dataNamePane3);

        mainDataSplitpane2.setLeftComponent(jPanel1);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        dataNamePane4.setMinimumSize(new java.awt.Dimension(23, 42));
        dataNamePane4.setPreferredSize(new java.awt.Dimension(23, 42));
        jPanel2.add(dataNamePane4);

        mainDataSplitpane2.setRightComponent(jPanel2);

        mainDataSplitpane1.setRightComponent(mainDataSplitpane2);

        leftPanel.setMinimumSize(new java.awt.Dimension(180, 123));
        leftPanel.setOpaque(false);
        leftPanel.setPreferredSize(new java.awt.Dimension(180, 298));
        leftPanel.setLayout(new java.awt.BorderLayout());

        dataNamePane2.setMinimumSize(new java.awt.Dimension(23, 42));
        dataNamePane2.setPreferredSize(new java.awt.Dimension(23, 42));
        leftPanel.add(dataNamePane2, java.awt.BorderLayout.PAGE_START);

        dataNamePane5.setMinimumSize(new java.awt.Dimension(23, 42));
        dataNamePane5.setPreferredSize(new java.awt.Dimension(23, 42));
        leftPanel.add(dataNamePane5, java.awt.BorderLayout.CENTER);

        mainDataSplitpane1.setLeftComponent(leftPanel);

        add(mainDataSplitpane1);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private lib.editor.widget.datapane.DataNamePane dataNamePane2;
    private lib.editor.widget.datapane.DataNamePane dataNamePane3;
    private lib.editor.widget.datapane.DataNamePane dataNamePane4;
    private lib.editor.widget.datapane.DataNamePane dataNamePane5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel leftPanel;
    private lib.editor.widget.datapane.MainDataSplitpane mainDataSplitpane1;
    private lib.editor.widget.datapane.MainDataSplitpane mainDataSplitpane2;
    // End of variables declaration//GEN-END:variables
}
