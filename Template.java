// Mr. L
// Jacob Schnettler
// 2/8/2023

import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import java.util.Random;
import java.applet.*; 

public class Template extends Applet implements MouseListener, ActionListener, KeyListener
{    
    String projectName = "";
    
    boolean showRules = false;
    
    // colors 
    Color backgroundColor = Color.red;
    Color textColor = Color.white;
    
    public void init()
    {
        this.setLayout(null);
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.resize(1400, 700);

        showRules = true;
    }

    public void mouseClicked(MouseEvent e) 
    {
    }

    public void keyPressed(KeyEvent e) 
    {
    }

    public void paint(Graphics g)   
    {   
        g.setColor(backgroundColor);

        g.fillRect(0, 0, 1400, 700);

        g.setColor(textColor);
        
        if (showRules) {
            // Draw rules
            g.setFont(new Font("Ariel", 1, 45));

            g.drawString(projectName, 565, 250);

            g.setFont(new Font("Ariel", 1, 25));

            g.drawString("By: Jacob Schnettler", 540, 300);
        } else {    
            // Draw game
        }
    }

    public void keyReleased() 
    {}

    public void actionPerformed(ActionEvent e) 
    {
    }

    public void mousePressed(MouseEvent e) 
    {}

    public void mouseReleased(MouseEvent e) 
    {}

    public void mouseEntered(MouseEvent e) 
    {}

    public void mouseExited(MouseEvent e) 
    {}

    public void keyReleased(KeyEvent e) 
    {}

    public void keyTyped(KeyEvent e) 
    {}
}