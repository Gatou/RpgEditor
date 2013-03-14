/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.spinner;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

/**
 *
 * @author gaetan
 */
public class Spinner extends JSpinner{

    
    public JButton nextButton;
    public JButton prevButton;
    int maximum;
    int minimum;
    
    public Spinner() {
        /*
        for(Component comp : getComponents()){
            if(comp instanceof JButton){
                if(comp.getName().equals("Spinner.nextButton")){
                    nextButton = (JButton) comp;
                }
                else if(comp.getName().equals("Spinner.previousButton")){
                    prevButton = (JButton) comp;
                }
            }
        }*/
        
        minimum = 0;
        maximum = 20;
        //setModel(new SpinnerNumberModel(0, null, null, 1));
        
        
        JTextField textField = ((JSpinner.DefaultEditor)getEditor()).getTextField();
        textField.setInputVerifier( new InputVerifier(){

            public boolean shouldYieldFocus(JComponent input) {
                return verify(input);
            }

            public boolean verify(JComponent input) {
                return adjustValue();
            }
        });
           
        //checkUpDownButtonsEnabled();
        setValue(0);
    }
    
    public JButton getNextButton(){
        if(nextButton == null){
            for(Component comp : getComponents()){
                if(comp instanceof JButton){
                    if("Spinner.nextButton".equals(comp.getName())){
                        nextButton = (JButton) comp;
                    }
                }
            }
        }
        return nextButton;
    }
    
    public JButton getPrevButton(){
        if(prevButton == null){
            for(Component comp : getComponents()){
                if(comp instanceof JButton){
                    if("Spinner.previousButton".equals(comp.getName())){
                        prevButton = (JButton) comp;
                    }
                }
            }
        }
        return prevButton;
    }
    public boolean adjustValue(){
        JTextField texField = ((JSpinner.DefaultEditor)getEditor()).getTextField();
        try{
            int currentValue = Integer.parseInt(texField.getText());
            setValue(currentValue);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
        
        
    }
    
    public void checkUpDownButtonsEnabled(){
        if(getNextButton() != null){
            nextButton.setEnabled((Integer)getValue() < (Integer)getMaximum());
        }
        if(getPrevButton() != null){
            prevButton.setEnabled((Integer)getValue() > (Integer)getMinimum());
        }
    }
    
    public int getMaximum(){
        return maximum;
        //SpinnerNumberModel model = (SpinnerNumberModel) getModel();
        //return (Integer) model.getMaximum();
    }
    
    public void setMaximum(int maximum){
        this.maximum = maximum;
        checkUpDownButtonsEnabled();
        //SpinnerNumberModel model = (SpinnerNumberModel) getModel();
        //model.setMaximum(maximum);
    }
    
    public int getMinimum(){
        return minimum;
        //SpinnerNumberModel model = (SpinnerNumberModel) getModel();
        //return (Integer) model.getMinimum();
    }
    
    public void setMinimum(int minimum){
        this.minimum = minimum;
        checkUpDownButtonsEnabled();
        //SpinnerNumberModel model = (SpinnerNumberModel) getModel();
        //model.setMinimum(minimum);
    }
    
    public void setValue(Object value){
        int currentValue = (Integer) getValue();
        
        int v = (Integer) value;
        int result = v;
        if(v > getMaximum()){
            result = getMaximum();
        }
        else if(v < getMinimum()){
            result = getMinimum();
        }
        
        if(result != currentValue){
            super.setValue(result);
            checkUpDownButtonsEnabled();
        }
            JTextField textField = ((JSpinner.DefaultEditor)getEditor()).getTextField();
            textField.setText(String.valueOf(getValue()));
        
        
    }
}
