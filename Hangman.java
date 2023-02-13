
import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;

public class Hangman extends Applet implements ActionListener 
{
    Button letterBtns[] = new Button[26];

    Font font20 = new Font ("Ariel", 1, 20);

    String output = "";

    String input = "";

    char secretLetters[] = {'L', 'U', 'C', 'I', 'A', 'N', 'O'};

    char dashLetters[] = {'-', '-', '-', '-', '-', '-', '-'};

    char letter = 'A';

    char guess = '?';

    int numWrong = 0;

    Image hangedMan; 

    public void init() 
    {
        this.setLayout(null);

        for (int num=0; num <= 25; num++)
        {
            letterBtns[num] = new Button("" + letter);

            letterBtns[num].setBounds(50 + 50 * num, 600, 40, 40);
            letterBtns[num].addActionListener(this);

            this.add(letterBtns[num]);

            letter++;

            letterBtns[num].setBackground(Color.white);
            letterBtns[num].setForeground(Color.red);
        }

        hangedMan = this.getImage(this.getCodeBase(), "imgs/gallow-" + numWrong + ".png");

        repaint();
    }

    public void actionPerformed(ActionEvent e) 
    {
        for (int place=0; place < 26; place++) {
            if (e.getSource() == letterBtns[place]) 
            {
                guess = letterBtns[place].getLabel().charAt(0);

                letterBtns[place].setVisible(false);

                boolean letterFound = false;

                for (int letNum = 0; letNum <7; letNum++) 
                {
                    if (secretLetters[letNum] == guess) 
                    {
                        letterFound = true;

                        dashLetters[letNum] = guess;
                    }
                }

                if (!letterFound) 
                {
                    numWrong++;

                    hangedMan = this.getImage(this.getCodeBase(), "imgs/gallow-" + numWrong + ".png");
                }
            }
        }

        repaint();
    }

    public void paint(Graphics g)  
    {
        g.setFont(font20);

        g.drawImage(hangedMan, 100, 100, 350, 350, this);

        g.drawString("You have gotten " + numWrong + " Letters wrong", 550, 250);

        for (int place = 0; place< 7; place++)
            g.drawString("" + dashLetters[place], 550 + 40 * place, 400);
    }
}
