/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.spinner;

import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author gaetan
 */
public class Spinner extends JSpinner{

    public JButton nextButton;
    public JButton prevButton;
    
    public Spinner() {
        for(Component comp : getComponents()){
            if(comp instanceof JButton){
                if(comp.getName().equals("Spinner.nextButton")){
                    nextButton = (JButton) comp;
                }
                else if(comp.getName().equals("Spinner.previousButton")){
                    prevButton = (JButton) comp;
                }
            }
        }
        
        setModel(new SpinnerNumberModel(0, 0, 20, 1));
        addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {
                nextButton.setEnabled((Integer)getValue() < getMaximum());
                prevButton.setEnabled((Integer)getValue() > getMinimum());
            }
        });
        
        nextButton.setEnabled((Integer)getValue() < getMaximum());
        prevButton.setEnabled((Integer)getValue() > getMinimum());
    }
    
    public JButton getNextButton(){
        return nextButton;
    }
    
    public int getMaximum(){
        SpinnerNumberModel model = (SpinnerNumberModel) getModel();
        return (Integer) model.getMaximum();
    }
    
    public void setMaximum(int maximum){
        SpinnerNumberModel model = (SpinnerNumberModel) getModel();
        model.setMaximum(maximum);
    }
    
    public int getMinimum(){
        SpinnerNumberModel model = (SpinnerNumberModel) getModel();
        return (Integer) model.getMinimum();
    }
    
    public void setMinimum(int minimum){
        SpinnerNumberModel model = (SpinnerNumberModel) getModel();
        model.setMinimum(minimum);
    }
    
}
