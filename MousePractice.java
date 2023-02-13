import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import java.util.Random;

public class MousePractice extends Applet implements MouseListener, ActionListener
{
    boolean lights[][] = new boolean[7][7];
    
    int mouseX = -200, mouseY = -200;

    Button resetBtn = new Button();

    Button toggleCustomColors = new Button();

    Boolean customColorsEnabled = false;

    Random rand = new Random();

    String moveHistory = "";

    public void resetGame()
    {
        for (int r = 1; r <= 5; r++) 
            for (int c = 1; c <= 5; c++) 
                lights[r][c] = false;
    }

    public void togglePiece(int row, int col)
    {   
        lights[row][col] = !lights[row][col];
    }

    public void addRandomTiles()
    {
        int randSpot = rand.nextInt(3);

        for (int r = 1; r <= 5; r++) 
            for (int c = 1; c <= 5; c++) 
            {
                int randNum = rand.nextInt(2);

                if (randNum == 0) {
                    lights[r][c] = false;
                } else {
                    lights[r][c] = true;
                }
            }
    }

    public Color randColor() {
        int randNum = rand.nextInt(4);

        if (randNum == 0) return Color.red;

        if (randNum == 1) return Color.blue;

        if (randNum == 2) return Color.green;

        if (randNum == 3) return Color.yellow;

        if (randNum == 4) return Color.orange;

        return Color.red;
    }

    public void init()
    {
        resize(1400, 700);

        resetBtn.addActionListener(this);

        resetBtn.setBounds(525, 500, 250, 40);

        this.add(resetBtn);

        this.addMouseListener(this);
    }

    public void mouseClicked(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        int x = (mouseX - 250) / 100;
        int y = mouseY / 100;

        togglePiece(y, x);

        togglePiece(y - 1, x);
        togglePiece(y + 1, x);

        togglePiece(y, x + 1);
        togglePiece(y, x - 1);

        moveHistory = moveHistory = "(" + y + ", " + x + ")";

        repaint();
    }

    public void paint(Graphics g)   
    {
        for (int r = 1; r <= 5; r++)
            for (int c = 1; c <= 5; c++) 
            {
                if (lights[r][c] == false)
                    g.setColor(Color.black);
                else
                    g.setColor(randColor());

                g.fillRect(250 + 100 * c,100 * r, 90, 90);
            }
    }

    public void actionPerformed(ActionEvent e) {
        //if (e.getSource() == resetBtn) 
        //resetGame();

        // repaint();
    }

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}
}