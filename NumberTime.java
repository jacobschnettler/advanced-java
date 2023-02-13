// By Jacob Schnettler
// Mr. L Advanced Java
// Number Time 12/6/22

import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class NumberTime extends Applet implements ActionListener
{
    Button numberBtns[] = new Button[25];

    Button AgainBtn = new Button();

    Font btnFont = new Font("Ariel", 1, 22);

    int []buttonInts = { 7, 3, 6, 8, 19, 12, 14, 11, 16, 20, 4, 23, 15, 13, 5, 2, 10, 17, 9, 24, 21, 1, 18, 22, 25 };

    int nextNum = 1;

    int arraySize = numberBtns.length - 1;

    long beginTime, endTime;

    double elapsedTime;

    Boolean showButtons = false;

    Boolean wrongTriggered = false;

    Boolean started = false;

    public void shuffleArray(int someArray[]) 
    {
        Random rand = new Random();

        for (int n = 0; n < someArray.length; n++) 
        {
            int randSpot = rand.nextInt(someArray.length);
            int temp = someArray[n];
            someArray[n] = someArray[randSpot];
            someArray[randSpot] = temp;
        }
    }

    public void clickedRight(int num) 
    {
        numberBtns[num].setVisible(false);

        nextNum++;
    }

    public void clickedWrong(Graphics g) 
    {
        try
        {
            for (int i= 0; i <= 20; i++) {
                Boolean even = (i & 1) == 0;

                Thread.sleep(50);

                if (even) {
                    g.setColor(Color.red);
                } else {
                    g.setColor(Color.white);
                }

                g.fillRect(0, 0, 1400, 700);
            }

            wrongTriggered = false;

            repaint();
        }
        catch(InterruptedException ex){
        }
    }

    public void drawBtns()
    {
        if (showButtons) {
            int arraySize = numberBtns.length - 1;

            for (int num = 0; num <= arraySize; num++){
                int displayInt = buttonInts[num];

                Button btn = new Button("" + displayInt);

                numberBtns[num] = btn;

                int btnMargin = 100;

                int xPosition = 425 + btnMargin * num;

                int buttonXScale = 75;
                int buttonYScale = 50;

                if (num + 1 <= 5) {
                    btn.setBounds(xPosition, 100, buttonXScale, buttonYScale);
                } else if (num + 1<= 10) {
                    btn.setBounds(xPosition - 5 * btnMargin, 200, buttonXScale, buttonYScale);
                } else if (num + 1 <= 15) { 
                    btn.setBounds(xPosition - 10 * btnMargin, 300, buttonXScale, buttonYScale);
                } else if (num + 1 <= 20) {
                    btn.setBounds(xPosition - 15 * btnMargin, 400, buttonXScale, buttonYScale);
                } else if (num + 1 <= 25) {
                    btn.setBounds(xPosition - 20 * btnMargin, 500, buttonXScale, buttonYScale);
                }

                btn.setBackground(Color.red);
                btn.setForeground(Color.black);

                btn.setFont(btnFont);

                numberBtns[num].addActionListener(this);

                this.add(numberBtns[num]);
            }
        }
    }

    public void init() 
    {
        this.setLayout(null);

        AgainBtn.addActionListener(this);

        AgainBtn.setBounds(525, 500, 250, 40);

        this.add(AgainBtn);

        resize(1400, 700);

        shuffleArray(buttonInts);

        beginTime = System.currentTimeMillis();

        drawBtns();

        repaint();
    }

    public void actionPerformed(ActionEvent e) 
    { 
        for (int num = 0; num <= arraySize; num++) {
            if (e.getSource() == numberBtns[num]) {
                if (nextNum == buttonInts[num]) {
                    clickedRight(num); 
                } else {
                    wrongTriggered = true;
                } 
            }
        }

        if (nextNum == 26) {
            endTime = System.currentTimeMillis();

            elapsedTime = (endTime - beginTime) / 1000.0;

            AgainBtn.setVisible(true);          

            showButtons = false;
        }

        if (e.getSource() == AgainBtn) {
            showButtons = true;

            started = true;
            
            nextNum = 1;
            
            AgainBtn.setVisible(false);

            drawBtns();
        }

        repaint();
    }

    public void paint(Graphics g)
    {
        if (wrongTriggered) {
            clickedWrong(g);
        } else {
            g.setColor(Color.white);

            g.fillRect(0, 0, 1400, 700);
        }

        g.setColor(Color.black);

        g.setFont(new Font("Ariel", 1, 35));

        if (!showButtons) {
            if (started) {
                g.drawString("The Game has finished! Time: " + elapsedTime, 525, 200);

                AgainBtn.setLabel("Restart game");
            } else {
                g.drawString("Start the game!", 525, 200);

                AgainBtn.setLabel("Start game");
            }
        } else {
            g.drawString("Next Number: " + nextNum, 100, 100);
        }
    }
}