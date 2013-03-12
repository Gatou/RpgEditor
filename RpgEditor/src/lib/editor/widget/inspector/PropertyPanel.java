/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.inspector;

import java.awt.Dimension;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import lib.editor.data.game.DataBase;
import lib.editor.data.game.DataMap;
import lib.editor.mgr.WidgetMgr;
import static lib.editor.widget.inspector.InspectorPanel.LEFT_COLUMN_WIDTH;
import org.jdesktop.swingx.JXTaskPane;
import org.jdesktop.swingx.JXTaskPaneContainer;

/**
 *
 * @author gaetan
 */
public class PropertyPanel extends InspectorPanel {

    
    /**
     * Creates new form PropertyPanel
     */
    public PropertyPanel(JXTaskPaneContainer container) {
        super(container, "Properties", "project_root.png");
        initComponents();
        
        nameTextField.getDocument().addDocumentListener(new DocumentListener(){  
            public void insertUpdate(DocumentEvent e) {
                nameTextChanged();
            }

            public void removeUpdate(DocumentEvent e) {
                nameTextChanged();
            }

            public void changedUpdate(DocumentEvent e) {}
        }); 
        
        textPanel.setMaximumSize(new Dimension(LEFT_COLUMN_WIDTH, getComponentCount()*28));
        textPanel.setPreferredSize(new Dimension(LEFT_COLUMN_WIDTH, getComponentCount()*28));
    }

    public void refresh(){
        idLabel.setText( String.valueOf(data.id));
        nameTextField.setText(data.name);
    }
    
    public void focusNameTextField(){
        nameTextField.requestFocus();
        nameTextField.selectAll();
    }
    
    public void nameTextChanged(){
        if(!refreshing){
            switch(WidgetMgr.INSPECTOR.getMode()){
                case (Inspector.Mode.Map):{
                    WidgetMgr.MAP_TREE.mapNameChanged(nameTextField.getText());
                }
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

        textPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        idLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        textPanel.setLayout(new java.awt.GridLayout(0, 1));

        jLabel2.setText("ID");
        textPanel.add(jLabel2);

        jLabel1.setText("Name");
        textPanel.add(jLabel1);

        add(textPanel);

        jPanel2.setLayout(new java.awt.GridLayout(0, 1));

        idLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        idLabel.setText("jLabel3");
        idLabel.setMaximumSize(null);
        idLabel.setMinimumSize(null);
        idLabel.setPreferredSize(null);
        jPanel2.add(idLabel);

        nameTextField.setMaximumSize(null);
        jPanel2.add(nameTextField);

        add(jPanel2);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel idLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JPanel textPanel;
    // End of variables declaration//GEN-END:variables
}
