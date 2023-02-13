import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import java.util.Random;

public class ConnectFour extends Applet implements MouseListener, ActionListener, KeyListener
{
    int board[][] = new int[6][7];

    boolean highlightedChips[][];

    int paddingLeft = 375;
    int paddingTop = 65;

    int totalChips = 0;
    int maxTotalChips = (6 * 7);

    int player = 1;

    int winner = 0;

    public int checkRowWin(int row, int col) {
        if (
        board[row][col] == board[row][col + 1] &&
        board[row][col + 1] == board[row][col + 2] &&
        board[row][col + 2] == board[row][col + 3] &&
        board[row][col] != 0
        ) {
            return board[row][col];
        } else {
            return 0;
        }
    }

    public int checkColWin(int row, int col) {
        if (
        board[row][col] == board[row + 1][col] &&
        board[row + 1][col] == board[row + 2][col] &&
        board[row + 2][col] == board[row + 3][col] &&
        board[row][col] != 0
        ) {
            return board[row][col];
        } else {
            return 0;
        }
    }

    public void init()
    {
        this.addKeyListener(this);
        this.resize(1400, 700);

        highlightedChips[2][1] = true;
    }

    public void mouseClicked(MouseEvent e) {
        repaint();
    }

    public void paint(Graphics g)   
    {
        if (totalChips == maxTotalChips && winner == 0) {
            g.drawString("GAME OVER. TIE", 200, 200);   
        }else {
            if (winner > 0) 
                g.drawString("Player " + winner + "Wins!!", 200, 200);

            for (int r = 0; r <= 5; r++)
                for (int c = 0; c <= 6; c++) 
                {
                    if (board[r][c] == 0)
                    {
                        g.setColor(Color.black);  

                        g.drawOval(paddingLeft + 90 *c, paddingTop + 80 * r, 70, 70);
                    } 
                    else
                    {
                        if (board[r][c] == 1) 
                        {
                            g.setColor(Color.red);
                        } else if (board[r][c] == 2) {
                            g.setColor(Color.black);
                        }

                        g.fillOval(paddingLeft + 90 *c, paddingTop + 80 * r, 70, 70);
                    }

                }
        }
    }

    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        int column = 0;

        if (code == e.VK_1 || code == e.VK_NUMPAD1) column = 0;

        if (code == e.VK_2 || code == e.VK_NUMPAD2) column = 1;

        if (code == e.VK_3 || code == e.VK_NUMPAD3) column = 2;

        if (code == e.VK_4 || code == e.VK_NUMPAD4) column = 3;

        if (code == e.VK_5 || code == e.VK_NUMPAD5) column = 4;

        if (code == e.VK_6 || code == e.VK_NUMPAD6) column = 5;

        if (code == e.VK_7   || code == e.VK_NUMPAD7) column = 6;

        boolean found = false;

        for (int r = 5; r >= 0 && found == false; r--) {
            if (board[r][column] == 0) {
                board[r][column] = player;

                found = true;

                if (player == 1) {
                    player = 2;
                } else {
                    player = 1;
                }

                totalChips++;
            }
        }

        for (int r = 0; r <= 5 && winner ==0; r++)
            for (int c = 0; c <= 3 && winner ==0; c++)
                winner = checkRowWin(r, c);

        for (int r = 0; r <= 2 && winner ==0; r++)
            for (int c = 0; c <= 6 && winner ==0; c++)
                winner = checkColWin(r, c);

        repaint();
    }

    public void keyReleased(KeyEvent e) {}

    public void keyTyped(KeyEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}
}