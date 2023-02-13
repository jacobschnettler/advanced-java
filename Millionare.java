// By Jacob Schnettler
// Mr. L Advanced Java
// Who Wants to be a Millionare 12/1/22


import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import java.util.Random;

public class Millionare extends Applet implements ActionListener
{
    String questions[] = new String[8];

    String aAnswers[] = new String[8];
    String bAnswers[] = new String[8];
    String cAnswers[] = new String[8];
    String dAnswers[] = new String[8];

    Button optionA = new Button();
    Button optionB = new Button();
    Button optionC = new Button();
    Button optionD = new Button();

    Button phoneFriendBTN = new Button();
    Button audienceHelpBTN = new Button();
    Button fiftyFiftyBTN = new Button();
    Button walkawayBTN = new Button();

    Button visibleButtons[] = {
            optionA, 
            optionB, 
            optionC, 
            optionD, 
            phoneFriendBTN,
            audienceHelpBTN, 
            fiftyFiftyBTN,
            walkawayBTN
        };

    char answers[] = {'C', 'C', 'D', 'C', 'B', 'D', 'B', 'D'};

    char charArray[] = {'A', 'B', 'C', 'D'};  

    int moneyIncrements[] = new int[8];

    int questionCt = 0;

    char choice = 'Z';

    Font title = new Font("Ariel", 1, 25);

    Font paragraph = new Font("Ariel", 1, 15);

    Random rand = new Random();

    String output = "";

    Boolean walkedAway = false;

    Boolean usedLifeline = false;

    public void setData()
    {
        moneyIncrements[0] = 100;
        moneyIncrements[1] = 500;
        moneyIncrements[2] = 1000;
        moneyIncrements[3] = 10000;
        moneyIncrements[4] = 100000;
        moneyIncrements[5] = 500000;
        moneyIncrements[6] = 800000;
        moneyIncrements[7] = 1000000;

        questions[0] = "In the UK, the abbreviation NHS stands for National what Service?";   

        aAnswers[0] = "Honour";
        bAnswers[0] = "Humanity";
        cAnswers[0] = "Health";
        dAnswers[0] = "Household";

        questions[1] = "Which Disney character famously leaves a glass slipper behind at a royal ball?"; 

        aAnswers[1] = "Pocahontas";
        bAnswers[1] = "Sleeping Beauty";
        cAnswers[1] = "Cinderella";
        dAnswers[1] = "Elsa";

        questions[2] = "What name is given to the revolving belt machinery in an airport that delivers checked luggage from the plane to baggage reclaim?";  

        aAnswers[2] = "Hangar";
        bAnswers[2] = "Terminal";
        cAnswers[2] = "Concourse";
        dAnswers[2] = "Carousel";

        questions[3] = "Which of these brands was chiefly associated with the manufacture of household locks?";  

        aAnswers[3] = "Phillips";
        bAnswers[3] = "Flymo";
        cAnswers[3] = "Chubb";
        dAnswers[3] = "Ronseal";

        questions[4] = "The hammer and sickle is one of the most recognisable symbols of which political ideology?";    

        aAnswers[4] = "Republicanism";
        bAnswers[4] = "Communism";
        cAnswers[4] = "Conservatism";
        dAnswers[4] = "Liberalism";

        questions[5] = "Which toys have been marketed with the phrase “robots in disguise”?";    

        aAnswers[5] = "Bratz Dolls";
        bAnswers[5] = "Sylvanian Families";
        cAnswers[5] = "Hatchimals";
        dAnswers[5] = "Transformers";

        questions[6] = "What does the word loquacious mean?";    

        aAnswers[6] = "Angry";
        bAnswers[6] = "Chatty";
        cAnswers[6] = "Beautiful";
        dAnswers[6] = "Shy";

        questions[7] = "Obstetrics is a branch of medicine particularly concerned with what?";    

        aAnswers[7] = "Childbirth";
        bAnswers[7] = "Broken bones";
        cAnswers[7] = "Heart conditions";
        dAnswers[7] = "Old age";
    }

    public void toggleBTNS(Boolean bool) 
    {
        for (int num = 0; num <= visibleButtons.length; num++) 
            visibleButtons[num].setVisible(bool);
    }

    public void drawAnswer(Graphics g, String question, int x, int y, String option)
    {
        g.drawString("" + option + ": " + question, x, y);
    }

    public void drawOptionBTN(Button btn, String option, int x, int y) 
    {
        btn.addActionListener(this);

        btn.setLabel("Choose " + option);

        btn.setBounds(x, y + 15, 120, 40);

        this.add(btn);
    }

    public void drawLifeline(Button btn, String label, int x, int y) 
    {
        btn.addActionListener(this);

        btn.setLabel(label);

        btn.setBounds(x, y + 15, 120, 40);

        this.add(btn);
    }

    public void phoneFriend() {
        if (usedLifeline) return;

        usedLifeline = true;

        if (rand.nextInt(100) > 75) {
            output = "Your friend definately thinks its " + answers[questionCt];
        } else {
            output = "Your friend is not sure but thinks its " + charArray[rand.nextInt(4)];
        }

        phoneFriendBTN.setVisible(false);
    }

    public void audienceHelp() 
    {
        if (usedLifeline) return;

        usedLifeline = true;

        output = "";

        char currentAnswer = answers[questionCt];

        for (int index = 0; index <= 3; index++) {
            char currentCharacter = charArray[index];

            if (currentAnswer == currentCharacter) {
                output = output + (rand.nextInt(45) + 30) + "% chance its " + currentCharacter + ", ";
            } else {
                output = output +  rand.nextInt(25) + "% chance its " + currentCharacter + ", ";
            }
        }

        audienceHelpBTN.setVisible(false);
    }

    public void fiftyFifty() {
        if (usedLifeline) return;

        usedLifeline = true;

        if (answers[questionCt] == 'A' || answers[questionCt] == 'B') 
        {
            optionC.setVisible(false); 
            optionD.setVisible(false);
        } else {
            optionA.setVisible(false);
            optionB.setVisible(false);
        }

        fiftyFiftyBTN.setVisible(false);
    }

    public void walkaway() {
        if (usedLifeline) return;

        usedLifeline = true;

        output = "You walked away with " + moneyIncrements[questionCt] + "$";

        walkedAway = true;

        toggleBTNS(false);
    }

    public void init() 
    {
        this.setLayout(null);

        drawOptionBTN(optionA, "Option A", 100, 150);
        drawOptionBTN(optionB, "Option B", 100, 350);
        drawOptionBTN(optionC, "Option C", 700, 150);
        drawOptionBTN(optionD, "Option D", 700, 350);

        drawLifeline(phoneFriendBTN, "Phone a Friend", 400, 550);   
        drawLifeline(audienceHelpBTN, "Call on Audience", 250, 550);   
        drawLifeline(fiftyFiftyBTN, "50/50 Question", 100, 550);
        drawLifeline(walkawayBTN, "Walkaway", 550, 550);

        setData();
    }

    public void actionPerformed(ActionEvent e) 
    { 
        if (e.getSource() == optionA) 
            choice = 'A';
        else if (e.getSource() == optionB) 
            choice = 'B';
        else if (e.getSource()  == optionC) 
            choice = 'C';
        else if (e.getSource() == optionD) 
            choice = 'D';

        if (e.getSource() == phoneFriendBTN) 
            phoneFriend();
        else if (e.getSource() == audienceHelpBTN) 
            audienceHelp();
        else if (e.getSource() == fiftyFiftyBTN) 
            fiftyFifty();
        else if (e.getSource() == walkawayBTN) 
            walkaway();
        else if (answers[questionCt] == choice) {
            optionA.setVisible(true); 
            optionB.setVisible(true); 
            optionC.setVisible(true);
            optionD.setVisible(true); 

            output = "";
            
            usedLifeline = false;

            questionCt++;
        } else {
            toggleBTNS(false);    

            output = "Yo u lost man, feelsbadman :(";
        }

        repaint();
    }

    public void paint(Graphics g)
    {
        if (walkedAway) {
            g.setFont(title);

            g.drawString(output, 250, 250);
        } else {
            g.setFont(title);

            g.drawString(questions[questionCt], 100, 100);

            g.setFont(paragraph);

            drawAnswer(g, aAnswers[questionCt], 100, 150, "A");
            drawAnswer(g, bAnswers[questionCt], 100, 350, "B");
            drawAnswer(g, cAnswers[questionCt], 700, 150, "C");
            drawAnswer(g, dAnswers[questionCt], 700, 350, "D");

            g.drawString(output, 700, 500);

            for (int num = 0; num <= 7; num++) 
            {
                Boolean onCurrent = false;

                if (num == questionCt) {
                    onCurrent = true;

                    g.setColor(Color.red);
                }else if (num < questionCt) 
                    g.setColor(Color.green);
                else 
                    g.setColor(Color.blue);

                if (onCurrent) 
                    g.drawString("$" + moneyIncrements[num] + " - Current", 950, 150 + num * 25); 
                else 
                    g.drawString("$" + moneyIncrements[num], 950, 150 + num * 25);
            }    
        }
    }
}