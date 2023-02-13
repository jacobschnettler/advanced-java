// Created by Jacob Schnettler
// Mr. L NIM 753

import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.util.Random;

public class NIM753 extends Applet implements ActionListener {
    String Title = "Advanced Nim 7 5 3";

    Button firstPileBTN = new Button("Take.");

    Button secondPileBTN = new Button("Take.");

    Button thirdPileBTN = new Button("Take.");

    Button fourthPileBTN = new Button("Take.");

    Button resetGameBTN = new Button("Restart Game.");

    Button startGameBTN = new Button("Start Game.");

    Button switchPlayerBTN = new Button("Switch Player.");

    Font title = new Font("Ariel", 1, 40);

    Font subTitle = new Font("Ariel", 1, 20);

    Font paragraph = new Font("Ariel", 1, 20);

    Font heading = new Font("Ariel", 1, 15);

    Random rand = new Random();

    int firstRow = 0;

    int secondRow = 0;

    int thirdRow = 0;

    int fourthRow = 0;

    int take = 0;

    int player = 1;

    int chipsTaken = 0;

    String input = "";

    boolean gameEnded = false;

    boolean showDirections = true;

    public void drawBtn(Button btn, int x, int y) {
        btn.addActionListener(this);

        btn.setBounds(x, y + 150, 100, 40);

        this.add(btn);
    }

    public void drawRow(int numChips, int x, int y, Graphics g, Color color) {
        g.setColor(color);

        g.drawString(numChips + " Chips Left", x, y - 25);

        for (int n = 1; n <= numChips; n++) {
            if (n == 1) {
                g.fillOval(
                    x,
                    y,
                    40,
                    40
                );
            } else if (n == 2) {
                g.fillOval(
                    x,
                    y + 50,
                    40,
                    40
                );
            } else if (n == 3) {
                g.fillOval(
                    x + 50,
                    y,
                    40,
                    40
                );
            } else if (n == 4) {
                g.fillOval(
                    x + 50,
                    y + 50,
                    40,
                    40
                );
            }

            if (n == 1 || n == 2) {

            } else if (n == 3 || n == 4) {
                //g.fillOval(x + (n - 1) * 50, y + 10, 40, 40);
            }
        }
    }

    public void changePlayer() {
        switchPlayerBTN.setVisible(false);

        chipsTaken = 0;

        toggleBoard(true);

        if (player == 1) {
            player = 2;
        } else {
            player = 1;
        }
    }

    public void toggleBoard(boolean bool) {
        firstPileBTN.setVisible(bool);
        secondPileBTN.setVisible(bool);
        thirdPileBTN.setVisible(bool);
        fourthPileBTN.setVisible(bool);
    }

    public void init() {
        this.setLayout(null);

        resetGameBTN.addActionListener(this);

        resetGameBTN.setBounds(100, 450, 150, 40);

        this.add(resetGameBTN);

        resetGameBTN.setVisible(false);

        switchPlayerBTN.addActionListener(this);

        switchPlayerBTN.setBounds(275, 450, 150, 40);

        this.add(switchPlayerBTN);

        switchPlayerBTN.setVisible(false);

        startGameBTN.addActionListener(this);

        startGameBTN.setBounds(100, 450, 150, 40);

        this.add(startGameBTN);

        drawBtn(firstPileBTN, 100, 150);
        drawBtn(secondPileBTN, 250, 150);
        drawBtn(thirdPileBTN, 400, 150);
        drawBtn(fourthPileBTN, 550, 150);

        toggleBoard(false);
    }

    public void resetGame() {
        firstRow = 0;
        secondRow = 0;
        thirdRow = 0;
        fourthRow = 0;

        showDirections = true;

        gameEnded = false;
        
        chipsTaken = 0;

        toggleBoard(false);

        resetGameBTN.setVisible(false);
        startGameBTN.setVisible(true);

    }
    
    public void startGame() {
        firstRow = rand.nextInt(4) + 1;
        secondRow = rand.nextInt(4) + 1;
        thirdRow = rand.nextInt(4) + 1;
        fourthRow = rand.nextInt(4) + 1;
        
        chipsTaken = 0;
        
        showDirections = false;

        gameEnded = false;

        toggleBoard(true);

        switchPlayerBTN.setVisible(false);

        resetGameBTN.setVisible(true);
        startGameBTN.setVisible(false);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resetGameBTN)
            resetGame();

        if (e.getSource() == startGameBTN)
            startGame();
        
        if (e.getSource() == firstPileBTN && 1 <= firstRow) {
            firstRow = firstRow - 1;

            chipsTaken++;

            toggleBoard(false);

            switchPlayerBTN.setVisible(true);

            firstPileBTN.setVisible(true);

            if (firstRow == 0)
                firstPileBTN.setVisible(false);
        }

        if (e.getSource() == secondPileBTN && 1 <= secondRow) {
            secondRow = secondRow - 1;

            chipsTaken++;

            toggleBoard(false);

            secondPileBTN.setVisible(true);

            if (secondRow == 0)
                secondPileBTN.setVisible(false);

            switchPlayerBTN.setVisible(true);
        }

        if (e.getSource() == thirdPileBTN && 1 <= thirdRow) {
            thirdRow = thirdRow - 1;

            chipsTaken++;

            toggleBoard(false);

            thirdPileBTN.setVisible(true);

            switchPlayerBTN.setVisible(true);
        }

        if (e.getSource() == fourthPileBTN && 1 <= fourthRow) {
            fourthRow = fourthRow - 1;

            chipsTaken++;

            toggleBoard(false);

            fourthPileBTN.setVisible(true);

            switchPlayerBTN.setVisible(true);
        }

        if (e.getSource() == switchPlayerBTN)
            changePlayer();

        if (
            (firstRow == 0 && secondRow == 0 && thirdRow == 0 && fourthRow <= 1) ||
            (firstRow == 0 && secondRow == 0 && thirdRow <= 1 && fourthRow == 0) ||
            (firstRow == 0 && secondRow <= 1 && thirdRow == 0 && fourthRow == 0) ||
            (firstRow <= 1 && secondRow == 0 && thirdRow == 0 && fourthRow == 0)
        ) {
            gameEnded = true;
            
            toggleBoard(false);

            switchPlayerBTN.setVisible(false);
        }

        if (chipsTaken == 3) {
            chipsTaken = 0;

            toggleBoard(false);
        }

        repaint();
    }

    public void paint(Graphics g) {
        if (showDirections) {
            // Directions

            g.setFont(title);

            g.drawString("Rules/Directions", 100, 100);

            g.setFont(subTitle);

            g.drawString("There is four piles of chips, the goal of the game is to not be the last player to pickup the last chip.", 100, 170);
            
            g.drawString("You can only take a maximum of 3 chips and a minimum of 1 per pile, and you can only take from one pile during your turn.", 100, 220);
        } else {
            if (gameEnded)
                g.drawString("Player " + player + " Has beat the game!", 150, 250);
            else {
                drawRow(firstRow, 100, 150, g, Color.green);

                drawRow(secondRow, 250, 150, g, Color.red);

                drawRow(thirdRow, 400, 150, g, Color.orange);

                drawRow(fourthRow, 550, 150, g, Color.pink);
            }

            g.setColor(Color.black);

            g.setFont(subTitle);

            g.drawString("Player " + player + " Is up.", 100, 100);

            // Title
            g.setFont(title);

            g.drawString(Title, 100, 55);

            // Paragraph
            g.setFont(paragraph);

            g.setColor(Color.black);

            // Headings
            g.setFont(heading);
        }
    }
}