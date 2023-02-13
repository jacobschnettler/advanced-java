import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import java.util.Random;

public class ImageGuesser extends Applet implements MouseListener, ActionListener
{
    boolean squares[][] = new boolean[10][10];

    int mouseX = -200, mouseY = -200;

    int imgWidth = 550, imgHeight = 600;

    int squaresClicked = 0;

    int currentImage = 0;

    char answers[] = { 'C', 'A', 'C', 'A' };

    char choice;

    Button resetBTN = new Button("End Game");

    Button startBTN = new Button("Start Game");

    Button optionA_BTN = new Button("Jesus Christ");

    Button optionB_BTN = new Button("Ghandi");

    Button optionC_BTN = new Button("Pope Francis");

    Image pictures[] = new Image[4]; 

    String aAnswers[] = new String[3];

    String bAnswers[] = new String[3];

    String cAnswers[] = new String[3];

    String dAnswers[] = new String[3];

    Random rand = new Random(); 

    Boolean gameOver = false;

    Boolean guessedCorrectly = false; 

    Boolean choosingPage = true;

    public void setData()
    {   
        pictures[0] = this.getImage(this.getCodeBase(), "imgs/popeF.jpg");
        pictures[1] = this.getImage(this.getCodeBase(), "imgs/obama.jpg");
        pictures[2] = this.getImage(this.getCodeBase(), "imgs/popeP.jpg");
        pictures[3] = this.getImage(this.getCodeBase(), "imgs/4661.png");

        aAnswers[0] = "A$AP Rocky";
        aAnswers[1] = "Jesus Christ";
        aAnswers[2] = "Pope Francis";

        bAnswers[0] = "Obama";
        bAnswers[1] = "Mr. Luciano";
        bAnswers[2] = "Bill Clinton";

        cAnswers[0] = "Lil Uzi Vert";
        cAnswers[1] = "Reagen";
        cAnswers[2] = "Papa John Paul II";

        dAnswers[0] = "Joe Biden";
        dAnswers[1] = "Luke";
        dAnswers[2] = "Pope P";
    }

    public void toggleButtons(Boolean toggled)
    {   
        resetBTN.setVisible(toggled);
        startBTN.setVisible(toggled);
        optionA_BTN.setVisible(toggled);
        optionB_BTN.setVisible(toggled);
        optionC_BTN.setVisible(toggled);
    }

    public void startGame()
    {   
        toggleButtons(true);

        currentImage = rand.nextInt(pictures.length);

        if (currentImage == 0) {
            optionA_BTN.setLabel(aAnswers[0]);
            optionB_BTN.setLabel(aAnswers[1]);
            optionC_BTN.setLabel(aAnswers[2]);
        } else if (currentImage == 1) {
            optionA_BTN.setLabel(bAnswers[0]);
            optionB_BTN.setLabel(bAnswers[1]);
            optionC_BTN.setLabel(bAnswers[2]);
        } else if (currentImage == 2) {
            optionA_BTN.setLabel(cAnswers[0]);
            optionB_BTN.setLabel(cAnswers[1]);
            optionC_BTN.setLabel(cAnswers[2]);
        } else if (currentImage == 3) {
            optionA_BTN.setLabel(dAnswers[0]);
            optionB_BTN.setLabel(dAnswers[1]);
            optionC_BTN.setLabel(dAnswers[2]);
        }

        squaresClicked = 0;
        
        choosingPage = false;

        guessedCorrectly = false;

        gameOver = false;

        startBTN.setVisible(false);
        
        for (int r = 0; r <= 9; r++)
            for (int c = 0; c <= 9; c++)
                squares[r][c] = true;
    }

    public void togglePiece(int row, int col)
    {   
        squares[row][col] = false;

        squaresClicked++;
    }

    public void correctGuess(int row, int col)
    {   
    }

    public void wrongGuess(int row, int col)
    {   
    }

    public void init()
    {
        this.setLayout(null);

        setData();

        toggleButtons(false);

        resetBTN.addActionListener(this);

        resetBTN.setBounds(650, 550, 250, 40);

        this.add(resetBTN);

        startBTN.addActionListener(this);

        startBTN.setBounds(525, 500, 250, 40);

        this.add(startBTN);

        startBTN.setVisible(false);

        optionA_BTN.addActionListener(this);

        optionA_BTN.setBounds(750, 250, 250, 40);

        this.add(optionA_BTN);

        optionB_BTN.addActionListener(this);

        // 700, 350
        optionB_BTN.setBounds(750, 325, 250, 40);

        this.add(optionB_BTN);

        optionC_BTN.addActionListener(this);

        optionC_BTN.setBounds(750, 400, 250, 40);

        this.add(optionC_BTN);

        this.addMouseListener(this);

        resize(1400, 700);

        startBTN.setVisible(true);
    }

    public void mouseClicked(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        int x = (mouseX - 50) / (imgWidth/10);
        int y = (mouseY - 50) / (imgWidth/10);

        togglePiece(y, x);

        repaint();
    }

    public void paint(Graphics g)   
    {
        if (!choosingPage) {
            g.drawImage(
                pictures[currentImage],
                50, 
                50,
                imgWidth,
                imgHeight,
                this
            );

            g.setFont(new Font("Ariel", 1, 20));

            if (!gameOver) {
                g.setColor(Color.black);

                g.drawString("Squares Clicked: " + squaresClicked, 650, 150);

                g.drawString("Religious Figures Guessing Game", 650, 100);

                g.drawString("Make a Guess", 650, 200);

                g.setFont(new Font("Ariel", 1, 20));

                g.drawString("A: ", 700, 275);

                g.drawString("B: ", 700, 350);

                g.drawString("C: ", 700, 425);

                for (int r = 0; r <= 9; r++)
                    for (int c = 0; c <= 9; c++) 
                    {
                        if (squares[r][c]) {
                            g.fillRect(50 + imgWidth / 10 * c, 50 + imgHeight / 10 * r, imgWidth / 10 - 1, imgHeight / 10 - 1);
                        }
                    }
            } else {    
                g.drawString("Game Over!", 650, 150);

                if (guessedCorrectly)
                    g.drawString("You guessed right!", 650, 250);
                else
                    g.drawString("You guessed wrong!", 650, 250);
            }
        } else {
            g.drawString("Use the mouse to click on a black box, when clicked the box will disappear.", 650, 250);
            g.drawString("You can make a guess on the sidebar, and it will tell you if you guessed right..", 650, 450);}
    }

    public void actionPerformed(ActionEvent e) {
        if (!choosingPage) {
            if (e.getSource() == resetBTN) { 
                startGame();
            } else {
                if (e.getSource() == optionA_BTN) 
                    choice = 'A';
                else if (e.getSource() == optionB_BTN) 
                    choice = 'B';
                else if (e.getSource()  == optionC_BTN) 
                    choice = 'C';

                guessedCorrectly = choice == answers[currentImage];

                gameOver = true;

                toggleButtons(false);

                resetBTN.setVisible(true);

                resetBTN.setLabel("start Another Game");
            }
        } else {
            startGame();
        }

        repaint();
    }

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}
}