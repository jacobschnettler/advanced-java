import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;

public class FirstGame extends Applet implements ActionListener 
{
    Number Health = 100;
    
    public void init() 
    {
        
        
    }
    
    public void actionPerformed( ActionEvent e ) 
    {
    }
    
    public void paint(Graphics g) 
    {   
        // UI 
        g.drawString(
            "NFL Team Picker", 
            100, 
            50
        );
        
        repaint();
    }
}
