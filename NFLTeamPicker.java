// Created by Jacob Schnettler, and Luke Campbell

import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;

public class NFLTeamPicker extends Applet implements ActionListener 
{
    TextField text1 = new TextField("");
    
    TextField text2 = new TextField("");

    TextField text3 = new TextField("");

    TextField text4 = new TextField("");
    
    Button btn = new Button("Tell me my card."); 
    
    Font title = new Font("Ariel", 1, 40);
    
    Font paragraph = new Font("Ariel", 1, 20);
    
    Font heading = new Font("Ariel", 1, 15);
        
    Image deckOfCards;
    
    String team = "";
    
    String isNFC = "";
    
    String isAFCSouthOrNFCNorth = "";
    
    String topRowTrue = "";
    
    String leftColumn = "";
    
    public void init() 
    {
        deckOfCards = this.getImage(this.getCodeBase(), "imgs/nfl-directions.png");
        
        this.setLayout(null);
        
        btn.addActionListener(this);
        
        btn.setBounds(100, 360, 250, 40);
            
        this.add(btn);
        
        text1.setBounds(100, 100, 250, 40);
        
        this.add(text1);  
        
        text2.setBounds(100, 170, 250, 40);
        
        this.add(text2);  
        
        text3.setBounds(100, 240, 250, 40);
        
        this.add(text3);  
        
        text4.setBounds(100, 310, 250, 40);
        
        this.add(text4);  
    }
    
    public void actionPerformed( ActionEvent e ) 
    {
        isNFC = text1.getText();
    
        isAFCSouthOrNFCNorth = text2.getText();
        
        topRowTrue = text3.getText();
        
        leftColumn= text4.getText();
        
        if (
            isNFC.equals("no") && 
            isAFCSouthOrNFCNorth.equals("yes") && 
            topRowTrue.equals("yes") && 
            leftColumn.equals("yes")
        ){
            team = "Houston Texans";
        }
        
        if (
            isNFC.equals("no") && 
            isAFCSouthOrNFCNorth.equals("yes") && 
            topRowTrue.equals("no") && 
            leftColumn.equals("yes")
        ){
            team = "Colts";
        }
        
        if (
            isNFC.equals("no") && 
            isAFCSouthOrNFCNorth.equals("yes") && 
            topRowTrue.equals("yes") && 
            leftColumn.equals("no")
        ){
            team= "Jaguars";
        }
        
        if (
            isNFC.equals("no") && 
            isAFCSouthOrNFCNorth.equals("yes") && 
            topRowTrue.equals("no") && 
            leftColumn.equals("no")
        ){
            team = "Titans";
        }
        
        if (
            isNFC.equals("no") && 
            isAFCSouthOrNFCNorth.equals("no") && 
            topRowTrue.equals("yes") && 
            leftColumn.equals("yes")
        ){
            team = "Broncos";
        }
        
        if (
            isNFC.equals("no") && 
            isAFCSouthOrNFCNorth.equals("no") && 
            topRowTrue.equals("no") && 
            leftColumn.equals("yes")
        ){
            team = "Chiefs";
        }
        
        if (
            isNFC.equals("no") && 
            isAFCSouthOrNFCNorth.equals("no") && 
            topRowTrue.equals("yes") && 
            leftColumn.equals("no")
        ){
            team = "Raiders";
        }
        
        if (
            isNFC.equals("no") && 
            isAFCSouthOrNFCNorth.equals("no") && 
            topRowTrue.equals("no") && 
            leftColumn.equals("no")
        ){
            team = "Chargers";
        }
        
        if (
            isNFC.equals("yes") && 
            isAFCSouthOrNFCNorth.equals("yes") && 
            topRowTrue.equals("yes") && 
            leftColumn.equals("yes")
        ){
            team = "Chicago Bears";
        }
        
        if (
            isNFC.equals("yes") && 
            isAFCSouthOrNFCNorth.equals("yes") && 
            topRowTrue.equals("no") && 
            leftColumn.equals("yes")
        ){
            team = "Lions";
        }
        
        if (
            isNFC.equals("yes") && 
            isAFCSouthOrNFCNorth.equals("yes") && 
            topRowTrue.equals("yes") && 
            leftColumn.equals("no")
        ){
            team = "Packers";
        }
        
        if (
            isNFC.equals("yes") && 
            isAFCSouthOrNFCNorth.equals("yes") && 
            topRowTrue.equals("no") && 
            leftColumn.equals("no")
        ){
            team = "Vikings";
        }
        
        if (
            isNFC.equals("yes") && 
            isAFCSouthOrNFCNorth.equals("no") && 
            topRowTrue.equals("yes") && 
            leftColumn.equals("yes")
        ){
            team = "Cowboys";
        }
        
        if (
            isNFC.equals("yes") && 
            isAFCSouthOrNFCNorth.equals("no") && 
            topRowTrue.equals("no") && 
            leftColumn.equals("yes")
        ){
            team = "Giants";
        }
        
        if (
            isNFC.equals("yes") && 
            isAFCSouthOrNFCNorth.equals("no") && 
            topRowTrue.equals("yes") && 
            leftColumn.equals("no")
        ){
            team = "Eagles";
        }
        
        if (
            isNFC.equals("yes") && 
            isAFCSouthOrNFCNorth.equals("no") && 
            topRowTrue.equals("no") && 
            leftColumn.equals("no")
        ){
            team = "Commanders";
        }
        repaint();
    }
    
    public void paint(Graphics g) 
    {   
        // Title
        g.setFont(title);
        
        g.setColor(Color.black);
        
        g.drawString("NFL Team Picker", 100, 50);
        
        // Paragraph
        g.setFont(paragraph);
        
        g.setColor(Color.black);
        
        g.drawString(
            "Think of a NFL Team and answer depending the questions on where it is on the chart.", 
            450, 
            150
        );
        
        g.drawString(
            "Answer the 4 questions with a yes or no.", 
            450, 
            175
        );
       
        //if (team.equals("")) 
        
    Image deckOfCards;
        deckOfCards = this.getImage(this.getCodeBase(), "imgs/nfl-directions.png");
            g.drawImage(
                deckOfCards, 
                450, 
                200,
                791,
                291,
                this
            );
        //}
        
        g.drawString("Team: " + team, 450, 550); 
        
        // Headings
        g.setFont(heading);
        
        g.drawString(
            "Is your Team apart of the NCF?", 
            100, 
            90
        );
        
        g.drawString(
            "Is your team AFC South or NFC North?", 
            100, 
            160
        );
        
        g.drawString(
            "Is your Team on the top row?", 
            100, 
            240
        );
        
        g.drawString(
            "Is your Team on the left column?",
            100, 
            310
        );
    }


}
