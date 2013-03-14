/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.inspector;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import lib.editor.data.game.DataBase;
import lib.editor.data.game.DataMap;
import lib.editor.mgr.WidgetMgr;
import lib.editor.util.Cst;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

/**
 *
 * @author gaetan
 */
public class MapSizePanel extends InspectorPanel {

    
    
    /**
     * Creates new form PropertyPanel
     */
    public MapSizePanel(JXTaskPaneContainer container) {
        super(container, "Dimension", "grid.png");
        
        initComponents();
        
        textPanel.setMaximumSize(new Dimension(LEFT_COLUMN_WIDTH, textPanel.getComponentCount()*28));
        textPanel.setPreferredSize(new Dimension(LEFT_COLUMN_WIDTH, textPanel.getComponentCount()*28));
        
    }

    public void refresh(){
        refreshing = true;
        DataMap dataMap = (DataMap) data;
        resetSpinners();
        setSizeText();
        adjustSpinners();
        refreshing = false;
    }
    
    public void resetSpinners(){
        topSpinner.setMaximum(1);
        topSpinner.setMinimum(-1);
        topSpinner.setValue(0);
        
        bottomSpinner.setMaximum(1);
        bottomSpinner.setMinimum(-1);
        bottomSpinner.setValue(0);
        
        leftSpinner.setMaximum(1);
        leftSpinner.setMinimum(-1);
        leftSpinner.setValue(0);
        
        rightSpinner.setMaximum(1);
        rightSpinner.setMinimum(-1);
        rightSpinner.setValue(0);
        
        adjustSpinners();
    }
    
    public void setSizeText(){
        DataMap dataMap = (DataMap) data;
        int width = getResizedWidth();
        int height = getResizedHeight();
        sizeLabel.setText(width + " x " + height);
        WidgetMgr.MAP_EDITOR.resizeRenderer.setResize(dataMap.width, dataMap.height, width, height, (Integer)topSpinner.getValue(), (Integer)bottomSpinner.getValue(), (Integer)leftSpinner.getValue(), (Integer)rightSpinner.getValue());
        adjustSpinners();
    }
    
    public void setSpinnerValue(JSpinner spinner, int value){
        spinner.setValue(value);
        if(!refreshing){
            setSizeText();
        }
        adjustSpinners();
    }
    
    public void adjustSpinners(){
        int topMax = Math.min(getCurrentHeight() + (Integer)bottomSpinner.getValue() - 1, getCurrentHeight() - 1);
        topSpinner.setMaximum(topMax);
        topSpinner.setMinimum(-(Cst.MAX_MAP_SIZE - getCurrentHeight()) + (Integer)bottomSpinner.getValue());
        
        
        int bottomMax = Math.max(-getCurrentHeight() + (Integer)topSpinner.getValue() + 1, -getCurrentHeight() +1);
        bottomSpinner.setMinimum(bottomMax);
        bottomSpinner.setMaximum((Cst.MAX_MAP_SIZE - getCurrentHeight()) + (Integer)topSpinner.getValue());
        System.out.println(bottomSpinner.getMaximum());
        
        int leftMax = Math.min(getCurrentWidth() + (Integer)rightSpinner.getValue() - 1, getCurrentWidth() - 1);
        leftSpinner.setMaximum(leftMax);
        leftSpinner.setMinimum(-(Cst.MAX_MAP_SIZE - getCurrentWidth()) + (Integer)rightSpinner.getValue());
        
        int rightMax = Math.max(-getCurrentWidth() + (Integer)leftSpinner.getValue() + 1, -getCurrentWidth() +1);
        rightSpinner.setMinimum(rightMax);
        rightSpinner.setMaximum((Cst.MAX_MAP_SIZE - getCurrentWidth()) + (Integer)leftSpinner.getValue());
        
        checkButtonsEnabled();
    }
    
    public int getCurrentWidth(){
        DataMap dataMap = (DataMap) data;
        return dataMap.width;
    }
    
    public int getCurrentHeight(){
        DataMap dataMap = (DataMap) data;
        return dataMap.height;
    }
    
    public int getResizedWidth(){
        DataMap dataMap = (DataMap) data;
        return dataMap.width + (((Integer)rightSpinner.getValue() - (Integer)leftSpinner.getValue()));
    }
    
    public int getResizedHeight(){
        DataMap dataMap = (DataMap) data;
        return dataMap.height + (((Integer)bottomSpinner.getValue() - (Integer)topSpinner.getValue()));
    }
    
    public void checkButtonsEnabled(){
        topDecreaseButton.setEnabled(topSpinner.nextButton.isEnabled());
        topIncreaseButton.setEnabled(topSpinner.prevButton.isEnabled());
        
        bottomDecreaseButton.setEnabled(bottomSpinner.prevButton.isEnabled());
        bottomIncreaseButton.setEnabled(bottomSpinner.nextButton.isEnabled());
        
        leftDecreaseButton.setEnabled(leftSpinner.nextButton.isEnabled());
        leftIncreaseButton.setEnabled(leftSpinner.prevButton.isEnabled());
        
        rightDecreaseButton.setEnabled(rightSpinner.prevButton.isEnabled());
        rightIncreaseButton.setEnabled(rightSpinner.nextButton.isEnabled());
        
        boolean applyEnbaled = (Integer)topSpinner.getValue() != 0 || (Integer)bottomSpinner.getValue() != 0 || (Integer)leftSpinner.getValue() != 0 || (Integer)rightSpinner.getValue() != 0;
        applyButton.setEnabled(applyEnbaled);
    }
    
    public void applyResizeMap(){
        DataMap dataMap = (DataMap) data;
        //resetSpinners();
        
        dataMap.width = getResizedWidth();
        dataMap.height = getResizedHeight();
        
        refresh();
        WidgetMgr.MAP_EDITOR.refresh(dataMap);
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(16, 0), new java.awt.Dimension(16, 0), new java.awt.Dimension(20, 32767));
        jPanel2 = new javax.swing.JPanel();
        sizeLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        topIncreaseButton = new javax.swing.JButton();
        topDecreaseButton = new javax.swing.JButton();
        topSpinner = new lib.editor.widget.spinner.Spinner();
        jPanel3 = new javax.swing.JPanel();
        bottomDecreaseButton = new javax.swing.JButton();
        bottomIncreaseButton = new javax.swing.JButton();
        bottomSpinner = new lib.editor.widget.spinner.Spinner();
        jPanel4 = new javax.swing.JPanel();
        leftIncreaseButton = new javax.swing.JButton();
        leftDecreaseButton = new javax.swing.JButton();
        leftSpinner = new lib.editor.widget.spinner.Spinner();
        jPanel5 = new javax.swing.JPanel();
        rightDecreaseButton = new javax.swing.JButton();
        rightIncreaseButton = new javax.swing.JButton();
        rightSpinner = new lib.editor.widget.spinner.Spinner();
        jPanel6 = new javax.swing.JPanel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        applyButton = new javax.swing.JButton();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        textPanel.setMaximumSize(null);
        textPanel.setLayout(new java.awt.GridLayout(0, 1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/grid.png"))); // NOI18N
        jLabel6.setText("Size");
        jLabel6.setMaximumSize(new java.awt.Dimension(28, 28));
        jLabel6.setMinimumSize(new java.awt.Dimension(28, 28));
        jLabel6.setPreferredSize(new java.awt.Dimension(28, 28));
        textPanel.add(jLabel6);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/grid_top.png"))); // NOI18N
        jLabel2.setText("Top");
        jLabel2.setMaximumSize(new java.awt.Dimension(28, 28));
        jLabel2.setMinimumSize(new java.awt.Dimension(28, 28));
        jLabel2.setPreferredSize(new java.awt.Dimension(28, 28));
        textPanel.add(jLabel2);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/grid_down.png"))); // NOI18N
        jLabel1.setText("Bottom");
        jLabel1.setMaximumSize(new java.awt.Dimension(28, 28));
        jLabel1.setMinimumSize(new java.awt.Dimension(28, 28));
        jLabel1.setPreferredSize(new java.awt.Dimension(28, 28));
        textPanel.add(jLabel1);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/grid_left.png"))); // NOI18N
        jLabel4.setText("Left");
        jLabel4.setMaximumSize(new java.awt.Dimension(28, 28));
        jLabel4.setMinimumSize(new java.awt.Dimension(28, 28));
        jLabel4.setPreferredSize(new java.awt.Dimension(28, 28));
        textPanel.add(jLabel4);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/grid_right.png"))); // NOI18N
        jLabel5.setText("Right");
        jLabel5.setMaximumSize(new java.awt.Dimension(28, 28));
        jLabel5.setMinimumSize(new java.awt.Dimension(28, 28));
        jLabel5.setPreferredSize(new java.awt.Dimension(28, 28));
        textPanel.add(jLabel5);

        jLabel3.setMaximumSize(new java.awt.Dimension(28, 28));
        jLabel3.setMinimumSize(new java.awt.Dimension(28, 28));
        jLabel3.setPreferredSize(new java.awt.Dimension(28, 28));
        textPanel.add(jLabel3);

        add(textPanel);
        add(filler1);

        jPanel2.setMaximumSize(null);
        jPanel2.setLayout(new java.awt.GridLayout(0, 1));

        sizeLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        sizeLabel.setText("10x 10");
        sizeLabel.setMaximumSize(new java.awt.Dimension(28, 28));
        sizeLabel.setMinimumSize(new java.awt.Dimension(28, 28));
        sizeLabel.setPreferredSize(new java.awt.Dimension(28, 28));
        jPanel2.add(sizeLabel);

        jPanel1.setMaximumSize(new java.awt.Dimension(32831, 28));
        jPanel1.setMinimumSize(new java.awt.Dimension(93, 28));
        jPanel1.setPreferredSize(new java.awt.Dimension(100, 28));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        topIncreaseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/arrow_up.png"))); // NOI18N
        topIncreaseButton.setMargin(null);
        topIncreaseButton.setMaximumSize(new java.awt.Dimension(28, 28));
        topIncreaseButton.setMinimumSize(new java.awt.Dimension(28, 28));
        topIncreaseButton.setPreferredSize(new java.awt.Dimension(28, 28));
        topIncreaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                topIncreaseButtonActionPerformed(evt);
            }
        });
        jPanel1.add(topIncreaseButton);

        topDecreaseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/arrow_down.png"))); // NOI18N
        topDecreaseButton.setMargin(null);
        topDecreaseButton.setMaximumSize(new java.awt.Dimension(28, 28));
        topDecreaseButton.setMinimumSize(new java.awt.Dimension(28, 28));
        topDecreaseButton.setPreferredSize(new java.awt.Dimension(28, 28));
        topDecreaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                topDecreaseButtonActionPerformed(evt);
            }
        });
        jPanel1.add(topDecreaseButton);

        topSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                topSpinnerStateChanged(evt);
            }
        });
        jPanel1.add(topSpinner);

        jPanel2.add(jPanel1);

        jPanel3.setMaximumSize(new java.awt.Dimension(32831, 28));
        jPanel3.setMinimumSize(new java.awt.Dimension(93, 28));
        jPanel3.setPreferredSize(new java.awt.Dimension(100, 28));
        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

        bottomDecreaseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/arrow_up.png"))); // NOI18N
        bottomDecreaseButton.setMargin(null);
        bottomDecreaseButton.setMaximumSize(new java.awt.Dimension(28, 28));
        bottomDecreaseButton.setMinimumSize(new java.awt.Dimension(28, 28));
        bottomDecreaseButton.setPreferredSize(new java.awt.Dimension(28, 28));
        bottomDecreaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottomDecreaseButtonActionPerformed(evt);
            }
        });
        jPanel3.add(bottomDecreaseButton);

        bottomIncreaseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/arrow_down.png"))); // NOI18N
        bottomIncreaseButton.setMargin(null);
        bottomIncreaseButton.setMaximumSize(new java.awt.Dimension(28, 28));
        bottomIncreaseButton.setMinimumSize(new java.awt.Dimension(28, 28));
        bottomIncreaseButton.setPreferredSize(new java.awt.Dimension(28, 28));
        bottomIncreaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bottomIncreaseButtonActionPerformed(evt);
            }
        });
        jPanel3.add(bottomIncreaseButton);

        bottomSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                bottomSpinnerStateChanged(evt);
            }
        });
        jPanel3.add(bottomSpinner);

        jPanel2.add(jPanel3);

        jPanel4.setMaximumSize(new java.awt.Dimension(32831, 28));
        jPanel4.setMinimumSize(new java.awt.Dimension(93, 28));
        jPanel4.setPreferredSize(new java.awt.Dimension(100, 28));
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.LINE_AXIS));

        leftIncreaseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/arrow_left.png"))); // NOI18N
        leftIncreaseButton.setMargin(null);
        leftIncreaseButton.setMaximumSize(new java.awt.Dimension(28, 28));
        leftIncreaseButton.setMinimumSize(new java.awt.Dimension(28, 28));
        leftIncreaseButton.setPreferredSize(new java.awt.Dimension(28, 28));
        leftIncreaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leftIncreaseButtonActionPerformed(evt);
            }
        });
        jPanel4.add(leftIncreaseButton);

        leftDecreaseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/arrow_right.png"))); // NOI18N
        leftDecreaseButton.setMargin(null);
        leftDecreaseButton.setMaximumSize(new java.awt.Dimension(28, 28));
        leftDecreaseButton.setMinimumSize(new java.awt.Dimension(28, 28));
        leftDecreaseButton.setPreferredSize(new java.awt.Dimension(28, 28));
        leftDecreaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leftDecreaseButtonActionPerformed(evt);
            }
        });
        jPanel4.add(leftDecreaseButton);

        leftSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                leftSpinnerStateChanged(evt);
            }
        });
        jPanel4.add(leftSpinner);

        jPanel2.add(jPanel4);

        jPanel5.setMaximumSize(new java.awt.Dimension(32831, 28));
        jPanel5.setMinimumSize(new java.awt.Dimension(93, 28));
        jPanel5.setPreferredSize(new java.awt.Dimension(100, 28));
        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));

        rightDecreaseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/arrow_left.png"))); // NOI18N
        rightDecreaseButton.setMargin(null);
        rightDecreaseButton.setMaximumSize(new java.awt.Dimension(28, 28));
        rightDecreaseButton.setMinimumSize(new java.awt.Dimension(28, 28));
        rightDecreaseButton.setPreferredSize(new java.awt.Dimension(28, 28));
        rightDecreaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rightDecreaseButtonActionPerformed(evt);
            }
        });
        jPanel5.add(rightDecreaseButton);

        rightIncreaseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/arrow_right.png"))); // NOI18N
        rightIncreaseButton.setMargin(null);
        rightIncreaseButton.setMaximumSize(new java.awt.Dimension(28, 28));
        rightIncreaseButton.setMinimumSize(new java.awt.Dimension(28, 28));
        rightIncreaseButton.setPreferredSize(new java.awt.Dimension(28, 28));
        rightIncreaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rightIncreaseButtonActionPerformed(evt);
            }
        });
        jPanel5.add(rightIncreaseButton);

        rightSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rightSpinnerStateChanged(evt);
            }
        });
        jPanel5.add(rightSpinner);

        jPanel2.add(jPanel5);

        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.LINE_AXIS));
        jPanel6.add(filler2);

        applyButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icons/refresh.png"))); // NOI18N
        applyButton.setText("Apply");
        applyButton.setMargin(null);
        applyButton.setMaximumSize(null);
        applyButton.setMinimumSize(new java.awt.Dimension(60, 25));
        applyButton.setPreferredSize(new java.awt.Dimension(80, 25));
        applyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                applyButtonActionPerformed(evt);
            }
        });
        jPanel6.add(applyButton);

        jPanel2.add(jPanel6);

        add(jPanel2);
    }// </editor-fold>//GEN-END:initComponents

    private void applyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_applyButtonActionPerformed
        applyResizeMap();
    }//GEN-LAST:event_applyButtonActionPerformed

    private void topIncreaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_topIncreaseButtonActionPerformed
        setSpinnerValue(topSpinner, ((Integer)topSpinner.getValue())-1);
    }//GEN-LAST:event_topIncreaseButtonActionPerformed

    private void topDecreaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_topDecreaseButtonActionPerformed
        setSpinnerValue(topSpinner, ((Integer)topSpinner.getValue())+1);
    }//GEN-LAST:event_topDecreaseButtonActionPerformed

    private void bottomDecreaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottomDecreaseButtonActionPerformed
        setSpinnerValue(bottomSpinner, ((Integer)bottomSpinner.getValue())-1);
    }//GEN-LAST:event_bottomDecreaseButtonActionPerformed

    private void bottomIncreaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bottomIncreaseButtonActionPerformed
        setSpinnerValue(bottomSpinner, ((Integer)bottomSpinner.getValue())+1);
    }//GEN-LAST:event_bottomIncreaseButtonActionPerformed

    private void leftIncreaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leftIncreaseButtonActionPerformed
        setSpinnerValue(leftSpinner, ((Integer)leftSpinner.getValue())-1);
    }//GEN-LAST:event_leftIncreaseButtonActionPerformed

    private void leftDecreaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leftDecreaseButtonActionPerformed
        setSpinnerValue(leftSpinner, ((Integer)leftSpinner.getValue())+1);
    }//GEN-LAST:event_leftDecreaseButtonActionPerformed

    private void rightDecreaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rightDecreaseButtonActionPerformed
        setSpinnerValue(rightSpinner, ((Integer)rightSpinner.getValue())-1);
    }//GEN-LAST:event_rightDecreaseButtonActionPerformed

    private void rightIncreaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rightIncreaseButtonActionPerformed
        setSpinnerValue(rightSpinner, ((Integer)rightSpinner.getValue())+1);
    }//GEN-LAST:event_rightIncreaseButtonActionPerformed

    private void topSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_topSpinnerStateChanged
        if(!refreshing){
            setSpinnerValue(topSpinner, (Integer)topSpinner.getValue());
        }
    }//GEN-LAST:event_topSpinnerStateChanged

    private void bottomSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_bottomSpinnerStateChanged
        if(!refreshing){
            setSpinnerValue(bottomSpinner, (Integer)bottomSpinner.getValue());
        }
    }//GEN-LAST:event_bottomSpinnerStateChanged

    private void leftSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_leftSpinnerStateChanged
        if(!refreshing){
            setSpinnerValue(leftSpinner, (Integer)leftSpinner.getValue());
        }
    }//GEN-LAST:event_leftSpinnerStateChanged

    private void rightSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rightSpinnerStateChanged
        if(!refreshing){
            setSpinnerValue(rightSpinner, (Integer)rightSpinner.getValue());
        }
    }//GEN-LAST:event_rightSpinnerStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton applyButton;
    private javax.swing.JButton bottomDecreaseButton;
    private javax.swing.JButton bottomIncreaseButton;
    private lib.editor.widget.spinner.Spinner bottomSpinner;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JButton leftDecreaseButton;
    private javax.swing.JButton leftIncreaseButton;
    private lib.editor.widget.spinner.Spinner leftSpinner;
    private javax.swing.JButton rightDecreaseButton;
    private javax.swing.JButton rightIncreaseButton;
    private lib.editor.widget.spinner.Spinner rightSpinner;
    private javax.swing.JLabel sizeLabel;
    private javax.swing.JPanel textPanel;
    private javax.swing.JButton topDecreaseButton;
    private javax.swing.JButton topIncreaseButton;
    private lib.editor.widget.spinner.Spinner topSpinner;
    // End of variables declaration//GEN-END:variables
}
