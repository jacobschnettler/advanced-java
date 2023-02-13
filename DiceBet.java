/**
 * Dice Betting
 * 
 * @author Jacob Schnettler
 * @period 2
 * @date 10/20/2022
 */

import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.util.Random;

public class DiceBet extends Applet implements ActionListener {
    Boolean gameStarted = false;

    Boolean increasingBet = true;

    Button startGameBTN = new Button();

    Button oddBetToggleBTN = new Button();

    Button evenBetToggleBTN = new Button();

    Button toggleBetTypeBTN = new Button();

    Button confirmBetBTN = new Button("Confirm Bet");

    Button betButton1 = new Button();

    Button betButton5 = new Button();

    Button betButton25 = new Button();

    Button allInBet = new Button();

    Random rand = new Random();

    Font title = new Font("Ariel", 1, 25);

    Font sub = new Font("Ariel", 1, 20);

    int balance = 1000;

    int currentBet = 0;

    int firstDye; 

    int secondDye;

    int diceTotal;

    Boolean betPlaced = false;

    Boolean betWon = false;

    Boolean cashoutShown = false;

    public void toggleDisplay(Boolean bool) 
    {   
        gameStarted = bool;

        betButton1.setVisible(bool);
        betButton5.setVisible(bool);
        betButton25.setVisible(bool);
        allInBet.setVisible(bool);

        confirmBetBTN.setVisible(bool);
        toggleBetTypeBTN.setVisible(bool);
    }

    public void setBTNLabel(Button btn, int amount, boolean allIn) 
    {
        if (allIn) {
            if (increasingBet) {
                btn.setLabel("All In");
            } else {
                btn.setLabel("Reset Bet");
            }
        } else {
            if (increasingBet) {
                btn.setLabel("Increase Bet By " + amount + " Chips");
            } else {
                btn.setLabel("Decrease Bet By " + amount + " Chips");
            }
        }
    }

    public void rollDice() 
    {
        if (currentBet != 0 && currentBet <= balance)
        {   
            betPlaced = true;

            firstDye = rand.nextInt(6) + 1; 

            secondDye = rand.nextInt(6) + 1;

            diceTotal = firstDye + secondDye;

            if (diceTotal % 2 == 1) {
                // 1.5x bet
                balance = balance + (currentBet * 3/2);

                betWon = true;
            } else {
                balance = balance - currentBet;

                betWon = false;
            }
        }
    }

    public void mapButton(Button btn, int position, int amount) 
    {
        btn.addActionListener(this);

        btn.setBounds(950, 100 + (50 * position), 250, 40);

        this.add(btn);

        setBTNLabel(btn, amount, position == 4 || false);
    }

    public void init() 
    {
        this.setLayout(null); 

        startGameBTN.addActionListener(this);

        startGameBTN.setBounds(100, 150, 150, 40);

        this.add(startGameBTN);

        toggleBetTypeBTN.addActionListener(this);

        toggleBetTypeBTN.setBounds(100, 200, 150, 40);

        this.add(toggleBetTypeBTN);

        confirmBetBTN.addActionListener(this);

        confirmBetBTN.setBounds(100, 250, 150, 40);

        this.add(confirmBetBTN);

        mapButton(betButton1, 1, 1);
        mapButton(betButton5, 2, 5);
        mapButton(betButton25, 3, 25);

        mapButton(allInBet, 4, balance);

        toggleDisplay(false);
    }

    public void manageBet(int amount, boolean allIn)
    {
        if (increasingBet) {
            if (allIn) 
                currentBet = amount;
            else if (balance >= currentBet + amount)
                currentBet = currentBet + amount;
        } else {
            if (allIn) 
                currentBet = 0;
            else if (0 <= currentBet - amount)
                currentBet = currentBet - amount;
        }
    }

    public void actionPerformed(ActionEvent e) 
    {
        if (e.getSource() == confirmBetBTN) 
            rollDice();

        if (e.getSource() == startGameBTN) 
        {
            if (cashoutShown) {
                gameStarted = true;

                increasingBet = true;

                balance = 1000;

                currentBet = 0;

                betPlaced = false;

                betWon = false;

                cashoutShown = false;

                toggleDisplay(true);
            } else if (betPlaced) {
                cashoutShown = true;
                
                toggleDisplay(false);
            } else
                toggleDisplay(!gameStarted);
        }

        if (e.getSource() == toggleBetTypeBTN)
            increasingBet = !increasingBet;

        if (e.getSource() == betButton1)
            manageBet(1, false);

        if (e.getSource() == betButton5)
            manageBet(5, false);

        if (e.getSource() == betButton25)
            manageBet(25, false);

        if (e.getSource() == allInBet)
            manageBet(balance, true);

        if (balance == 0) 
        {
            toggleDisplay(false);

            cashoutShown = true;
        }

        repaint();
    }

    public void paint(Graphics g) 
    {        
        g.setFont(title);

        if (cashoutShown) {
            startGameBTN.setLabel("Enter the gambling pit");
            
            if (balance != 0) 
                g.drawString("Cashing out " + balance + " Chips", 100, 100);
            else 
                g.drawString("You lost all your money!", 100, 100);
        } else if (!gameStarted) {
            startGameBTN.setLabel("Enter the gambling pit");

            g.drawString("Are you ready for Casino Royale?", 100, 100);

            g.drawString("Rules", 100, 400);
            g.drawString("You begin with 1000 chips, you select your bet a dice gets rolled. ", 100, 450);
            g.drawString("If the dice total is odd, one and a half of the bet is paid. if the dice total is even, the bet is lost.", 100, 500);
            
            g.setFont(sub);
        } else {
            g.drawString("Balance: " + balance + " Chips", 100, 100);

            if (betPlaced) 
                if (betWon)
                    g.drawString("Bet Won! You won " + currentBet + " Chips", 300, 250);
                else
                    g.drawString("Bet Lost! You lost  " + currentBet + " Chips", 300, 250);

            startGameBTN.setLabel("Cashout from Casino");

            setBTNLabel(betButton1, 1, false);
            setBTNLabel(betButton5, 5, false);
            setBTNLabel(betButton25, 25, false);
            setBTNLabel(allInBet, balance, true);

            if (increasingBet) 
                toggleBetTypeBTN.setLabel("Decrease Bet");
            else
                toggleBetTypeBTN.setLabel("Increase Bet");

            g.setFont(sub);

            g.drawString("Current Bet: " + currentBet + " Chips", 300, 200);
        }
    }
}
