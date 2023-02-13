import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.util.Random;

/**
 * Write a description of class Loops here.
 * 
 * @author Jacob Schnettler
 * @date 10/27/2022
 */

public class Loops extends Applet implements ActionListener 
{
    Image myPic;

    Random rand = new Random();

    int screenHeight = 700;
    int screenWidth = 1400;

    String name = "Schnettler";

    int starCount = 1500;

    String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public void init()
    {
        this.setLayout(null); 

        myPic = this.getImage(this.getCodeBase(), "imgs/bugsbunny.png");
    }

    public void actionPerformed(ActionEvent e) 
    {
    }

    public void paint(Graphics g) 
    {
        g.setColor(Color.black);

        g.fillRect(0, 0, screenWidth, screenHeight);

        g.setColor(Color.white);

        //g.drawString(alpha.substring(1) + "Check out this picture", 100, 30);

        /*
        // Alphabet example 
        for (int num = 1; num <= alpha.length(); num = num + 1) 
        {
        g.drawString(alpha.substring(num -1, num), 200, 50 + num * 20);
        }

        // Stars loop
        for (int num = 1; num <= starCount; num = num + 1) 
        {
        g.drawImage(myPic, rand.nextInt(screenWidth), rand.nextInt(screenHeight - 100), 0, 0, this);
        }

        for (int num = 1; num <= 40; num = num + 2) 
        {  
        g.drawString("" + num + " Drawed String", 100, 50 + (num * 10));
        }

        // numbers backwards
        for (int num = 20; num >= 2; num = num - 2) 
        g.drawString("" + num,  100, 450 - 15 * num);

        // numbers backwards
        for (int num = 2; num <= 20; num = num + 2) 
        g.drawString("" + num,  100, 100 + 15 * num); 

        // ovals    
        for (int num = 1; num <= 10; num++) 
        g.drawOval(200 + 20 * num, 100 + 20 * num,  75, 75); 

        // rectangles
        for (int num = 1; num <= 10; num++) 
        g.drawRect(400 + 20 * num, 100 + 20 * num,  75, 75); 
         */

        // #1
        /*for (int num = 1; num <= 12; num++) 
        g.drawString(name, num * 100, 100);*/  

        // #2
        /*for (int num = 1; num <= 12; num++) 
        g.drawString(name, 100, 100 + 20 * num);*/

        // #3
        /*for (int num = 1; num <= 12; num++) 
        g.drawString(name, 100 + 20 * num, 100 + 20 * num);*/

        // #4
        /*for (int num = 12; num >= 0; num = num - 1) 
        g.drawString(name, 100 + num * 25, 250 + 100 - 20 * num);*/

        // #5
        /*for (int num = 1; num <= 10; num++) 
        g.drawString("" + num, 100 + num * 25, 150);*/

        // #6
        /*for (int num = 10; num >= 1; num--) 
        g.drawString("" + num, 350 - num * 25, 150);*/

        /*for (int num = 1; num <= name.length(); num = num + 1) 
        {
        g.drawString(name.substring(num -1, num), 200, 50 + num * 20);
        }*/

        for (int num = 1; num <= 10; num++) {
            g.setFont(new Font("Ariel", 1, 15 + 5 *num));
        
            g.drawString(name, 100, 35 + num * 50);
        }
    }
}

