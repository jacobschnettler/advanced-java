// Mr. L
// Jacob Schnettler
// 2/8/2023

import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import java.util.Random;
import java.applet.*; 

public class SurvivorGame extends Applet implements MouseListener, ActionListener, KeyListener
{    
    String projectName = "Survivor";

    int board[][] = new int[10][10];

    boolean showRules = false;
    
    // Styling
    int paddingLeft = 425;
    int paddingTop = 100;

    int boxSize = 45;

    int logCount = 20;

    // colors 
    Color backgroundColor = Color.red;
    Color borderColor = Color.black;
    Color textColor = Color.white;

    public void init()
    {
        this.setLayout(null);
        this.addMouseListener(this);
        this.addKeyListener(this);
        this.resize(1400, 700);

        showRules = false;
        
        for (int r = 0; r <= 9; r++) {
            for (int c = 0; c <= 9; c++) { 
                if (r == 0 || c == 0 || r == 9 || c == 9)
                {
                    board[r][c] = 9;
                }
            }
        }
    }

    public void mouseClicked(MouseEvent e) 
    {
        int mouseX = e.getX();
        int mouseY = e.getY();

        int x = (mouseX - paddingLeft) / (boxSize);
        int y = (mouseY - paddingTop) / (boxSize);
        
        repaint();
    }

    public void keyPressed(KeyEvent e) 
    {
    }

    public void paint(Graphics g)   
    {   
        g.setColor(backgroundColor);

        g.fillRect(0, 0, 1400, 700);

        g.setColor(textColor);

        //g.drawString("X: " + x + " Y: " + y, 200, 200);
        
        if (showRules) {
            // Draw rules
            g.setFont(new Font("Ariel", 1, 45));

            g.drawString(projectName, 565, 250);

            g.setFont(new Font("Ariel", 1, 25));

            g.drawString("By: Jacob Schnettler", 540, 300);
        } else {    
            // Draw game
            for (int r = 0; r <= 9; r++) 
            {
                for (int c = 0; c <= 9; c++) 
                {
                    g.setColor(borderColor);
                    
                    int x = paddingLeft + boxSize *c;
                    int y = paddingTop + boxSize * r;
                    
                    g.drawRect(x, y, boxSize, boxSize);
                    
                    if (board[r][c] == 9) {
                        g.setFont(new Font("Ariel", 1, 25));
                        
                        g.drawString("X", x + 15, y + 30);
                    }
                }
            }
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