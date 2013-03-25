/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lib.editor.widget.datapane;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import lib.editor.widget.maintabpane.TabButton;

/**
 *
 * @author gaetan
 */
public class DataNamePane extends JPanel{

    JLabel label;
    
    public DataNamePane() {
        setOpaque(false);
        label = new JLabel("MY NAME");
        label.setFont(new Font("Tahoma", 1, 14));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.LIGHT_GRAY);
        setLayout(new GridLayout());
        add(label);
    }
    
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint gp = new GradientPaint(0, 0,
                new Color(40,40,40), 0, getHeight(),
                new Color(30,30,30));

        g2d.setPaint(gp);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        
        gp = new GradientPaint(0, 0,
                new Color(20,20,20), 0, getHeight(),
                new Color(10,10,10));
        g2d.setPaint(gp);
        g2d.fillRoundRect(4, 4, getWidth()-8, getHeight()-8, 15, 15);
        //super.paintComponent(g);
    }
    
}
