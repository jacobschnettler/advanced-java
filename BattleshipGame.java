// Mr. L
// Jacob Schnettler - 1 Player Battleship.
// 1/30/2023

import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import java.util.Random;
import java.applet.*;

// 0 = nothing
// 6 = miss
// 7 = hit

// 1 = aircraft carrier (5)
// 2 = destroyer (4)
// 3 = battleship (3)
// 4 = submarine (3)
// 5 = patrol boat (2)   

public class BattleshipGame extends Applet implements MouseListener, ActionListener, KeyListener
{    
    // Styling
    int paddingLeft = 150;
    int paddingTop = 100;

    int boxSize = 45;

    int logCount = 20;

    // Game Data
    int board[][] = new int[10][10]; // Stores ships 

    int rotation = 0; // 0 = top - bottom , 1 = left to right

    boolean settingShips = false; // when active enable the hover function, allow the user to use mouse to place ships. Also Press R to rotate 

    boolean attachMode = true; // if user can hit another ship.

    boolean showTitle = true;

    boolean gameOver = false;

    Button startGameBTN = new Button();

    AudioClip loadingMusic;

    // emmitted on key press to rotate ship.
    public void rotateShips()
    {
        if (rotation == 0)
            rotation = 1;
        else 
            rotation = 0;
    }

    // toggles the mouse hover selection.
    public void chooseShipLocations()
    {
        settingShips = true;

        // Mouse over and 
    }

    // Temporary
    public void fillArray()
    {
        board[1][0] = 1;
        board[1][1] = 1;
        board[1][2] = 1;
        board[1][3] = 1;
        board[1][4] = 1;

        board[3][1] = 2;
        board[3][2] = 2;
        board[3][3] = 2;
        board[3][4] = 2;

        board[1][7] = 3;
        board[2][7] = 3;
        board[3][7] = 3;

        board[8][5] = 4;
        board[8][6] = 4;
        board[8][7] = 4;

        board[9][8] = 5;
        board[9][9] = 5;
    }

    // on startup to begin game and set data.
    public void startGame()
    {
        chooseShipLocations();
    }

    public void init()
    {
        this.setLayout(null);

        this.addMouseListener(this);

        this.addKeyListener(this);

        this.resize(1400, 700);

        loadingMusic = this.getAudioClip(this.getCodeBase(), "imgs/cod.wav");

        startGameBTN.addActionListener(this);

        startGameBTN.setLabel("Start Game");

        startGameBTN.setBounds(600, 400, 150, 50);

        fillArray();

        this.add(startGameBTN);
    }

    // When a user clicks with mouse to "hit" ship.
    public void hitShip(int x, int y) 
    {
        int shipType = board[x][y];

        if (!(shipType == 6 || shipType == 7)) {
            if (shipType != 0) {
                board[x][y] = 7;
            } else {
                board[x][y] = 6;
            }
        }

        repaint();
    }

    // 
    public void mouseClicked(MouseEvent e) {
        if (gameOver) return;

        int mouseX = e.getX();
        int mouseY = e.getY();

        int x = (mouseX - paddingLeft) / (boxSize);
        int y = (mouseY - paddingTop) / (boxSize);

        hitShip(y, x);
    }

    public void keyPressed(KeyEvent e) {
        if (!settingShips || gameOver) return;

        int code = e.getKeyCode();

        if (code == e.VK_R) 
        {
            rotateShips();
        }
    }

    public void showTitleScreen() 
    {
        showTitle = true;
    }

    public void paint(Graphics g)   
    {   
        g.setColor(Color.black);

        g.fillRect(0, 0, 1400, 700);

        g.setColor(Color.white);

        int sunkenShips = 0;

        int totalClicks = 0;

        for (int i = 0; i <= 4; i++)
        {
            String label = "";

            int shipSize = 0;

            boolean sunk = false;

            if (i == 0) {
                label = "Aircraft Carrier";
            } else if (i == 1) {
                label = "Destroyer";
            } else if (i == 2) {
                label = "Battleship";
            } else if (i == 3) {
                label = "Submarine"; 
            } else if (i == 4) {
                label = "Patrol Boat";
            }

            for (int r = 0; r <= 9; r++) 
            {
                for (int c = 0; c <= 9; c++) 
                {
                    int shipType = board[r][c];

                    if (shipType == (i + 1)) {
                        shipSize++;
                    }
                }
            }

            if (shipSize == 0) {
                sunkenShips++;
            }
        }

        for (int r = 0; r <= 9; r++) 
        {
            for (int c = 0; c <= 9; c++) 
            {
                int shipType = board[r][c];

                if (shipType == 6 || shipType == 7) { 
                    totalClicks++;
                }
            }
        }

        gameOver = sunkenShips == 5;

        if (showTitle) {
            g.setFont(new Font("Ariel", 1, 30));

            g.drawString("Battleship", 600, 250);

            g.setFont(new Font("Ariel", 1, 20));

            g.drawString("By: Jacob Schnettler", 570, 300);
        } else {
            g.setColor(Color.white);
            
            g.setFont(new Font("Ariel", 1, 15));
            
            g.drawString(" The objective is to guess were my battleships are located ",625,450);
            g.drawString(" and try to sink my whole fleet in the least amount of moves.",625,500);
            g.drawString(" When you hit my ship, you will be notifed, when you miss, you ",625,550);
            g.drawString(" will also be notifed. The game ends when all ships are sunk.",625,600);
            
            g.setFont(new Font("Ariel", 1, 30));
            g.drawString("Battleship", 600, 50);
            g.setFont(new Font("Ariel", 1, 20));
            g.drawString("By: Jacob Schnettler", 570, 75);
            g.setFont(new Font("Ariel", 1, 25));
            g.drawString("Ships Left:", 625, 125);
            g.drawString("Rules", 625, 400);
            g.drawString("" + totalClicks + " Total Clicks", 150, 80);
            g.setFont(new Font("Ariel", 1, 20));
            for (int i = 0; i <= 4; i++)
            {
                String label = "";

                int shipSize = 0;

                boolean sunk = false;

                if (i == 0) {
                    label = "Aircraft Carrier";
                } else if (i == 1) {
                    label = "Destroyer";
                } else if (i == 2) {
                    label = "Battleship";
                } else if (i == 3) {
                    label = "Submarine"; 
                } else if (i == 4) {
                    label = "Patrol Boat";
                }

                for (int r = 0; r <= 9; r++) 
                {
                    for (int c = 0; c <= 9; c++) 
                    {
                        int shipType = board[r][c];

                        if (shipType == (i + 1)) {
                            shipSize++;
                        }
                    }
                }

                if (shipSize == 0) sunk = true;

                if (sunk) {
                    g.drawString("" + label + " - SUNK!", 625, 175 + (i * 40));
                } else {
                    g.drawString("" + label + " - " + shipSize + " HP", 625, 175 + (i * 40));
                }
            }

            for (int r = 0; r <= 9; r++) 
            {
                for (int c = 0; c <= 9; c++) 
                {
                    int shipType = board[r][c];

                    if (shipType == 6) {
                        g.setColor(Color.white);
                    } else if (shipType == 7) {
                        g.setColor(Color.red);
                    } else {
                        g.setColor(Color.blue);
                    }

                    g.fillRect(paddingLeft + boxSize *c, paddingTop + boxSize * r, boxSize, boxSize);

                    g.setColor(Color.black);

                    g.drawRect(paddingLeft + boxSize *c, paddingTop + boxSize * r, boxSize, boxSize);
                }
            }
        }
    }

    public void keyReleased() {}

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startGameBTN)
        {
            showTitle = false;

            startGameBTN.setVisible(false);

            loadingMusic.stop();

            repaint();
        }
    }

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void keyReleased(KeyEvent e) {}

    public void keyTyped(KeyEvent e) {}
}