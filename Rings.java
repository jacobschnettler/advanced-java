import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;

public class Rings extends Applet implements MouseListener
{
    int x = 100, y = 100;

    public void init() 
    {
        addMouseListener(this);
    }
    
    public void paint(Graphics g)
    {
        int count = 0;
        while (count < 100) 
        {
            int radius = 5 * count;
            int diameter = 2* radius;
            
            g.drawOval(x-radius, y-radius, diameter, diameter);
            
            count = count + 1;
        }
    }
    
    public void mouseClicked(MouseEvent e) 
    {
        x = e.getX();
        y = e.getY();
        repaint();
    }
    
    public void mouseExited(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}