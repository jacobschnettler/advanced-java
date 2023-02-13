// Created by Jacob Schnettler
// Mr. L Pizza Picker

import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;

public class PizzaPicker extends Applet implements ActionListener 
{
    TextField extraCheeseTF = new TextField("");

    TextField pepperoniTF = new TextField("");

    TextField sausageTF = new TextField("");;

    TextField onionsTF = new TextField("");

    TextField peppersTF = new TextField("");

    TextField olivesTF = new TextField("");

    Button btn = new Button("Order Pizza."); 

    Font title = new Font("Ariel", 1, 40);

    Font paragraph = new Font("Ariel", 1, 20);

    Font heading = new Font("Ariel", 1, 15); 

    String Title = "Dominos Pizza Picker";

    boolean orderPlaced = false;

    String orderString = "";
        
    String cheeseToppingText = "";
    
    String pepperoniToppingText = "";
        
    String sausageToppingText = "";
        
    String onionsToppingText = "";
        
    String olivesToppingText = "";

    public void init() 
    {
        this.setLayout(null);

        btn.addActionListener(this);

        btn.setBounds(100, 360, 250, 40);

        this.add(btn);

        extraCheeseTF.setBounds(275, 135, 80, 20);

        this.add(extraCheeseTF);  

        pepperoniTF.setBounds(275, 160, 80, 20);

        this.add(pepperoniTF);   

        sausageTF.setBounds(275, 185, 80, 20);

        this.add(sausageTF);  

        onionsTF.setBounds(275, 210, 80, 20);

        this.add(onionsTF);  

        peppersTF.setBounds(275, 235, 80, 20);

        this.add(peppersTF);

        olivesTF.setBounds(275, 260, 80, 20);

        this.add(olivesTF);
    }

    public void actionPerformed( ActionEvent e ) 
    {
        orderPlaced = true;

        // Default plain pizza
        orderString = "Your Toppings: ";
        
        cheeseToppingText = extraCheeseTF.getText();
        
        pepperoniToppingText = pepperoniTF.getText();
        
        sausageToppingText = sausageTF.getText();
        
        onionsToppingText = onionsTF.getText();
        
        olivesToppingText = olivesTF.getText();

        if (
            (cheeseToppingText.equals("no") || cheeseToppingText.equals(""))&& 
            (pepperoniToppingText.equals("no") || pepperoniToppingText.equals(""))&& 
            (sausageToppingText.equals("no") || sausageToppingText.equals(""))&& 
            (onionsToppingText.equals("no") || onionsToppingText.equals("")) && 
            (olivesToppingText.equals("no") || olivesToppingText.equals(""))
        ) orderString = "Plain Pizza";
        
        if (cheeseToppingText.equals("yes"))
            orderString = "Your Toppings: Cheese";
            
        if (pepperoniToppingText.equals("yes"))
            orderString = orderString + ", Pepperoni";
        
        if (sausageToppingText.equals("yes"))
            orderString = orderString + ", Sausage";
        
        if (onionsToppingText.equals("yes"))
            orderString = orderString + ", Onions";
        
        if (olivesToppingText.equals("yes"))
            orderString = orderString + ", Olives";
        
        repaint();
    }

    public void paint(Graphics g) 
    {   
        // Title
        g.setFont(title);

        g.setColor(Color.black);

        g.drawString(Title, 100, 50);

        // Paragraph
        g.setFont(paragraph);

        g.setColor(Color.black);
        
        if (!orderPlaced){
            g.drawString(
                "What toppings would you like from my pizzeria wahoo", 
                450, 
                150
            );
        } else {
            g.drawString(
                "Your order has been placed.", 
                450, 
                175
            );

            g.drawString(orderString, 450, 250);
        }

        // Headings
        g.setFont(heading);
        
        g.drawString(
            "Directions: Please type yes or no in the text field.", 
            100, 
            90
        );

        g.drawString(
            "Toppings:", 
            100, 
            125
        );

        g.drawString(
            "Extra Cheese", 
            125, 
            150
        );

        g.drawString(
            "Pepperoni", 
            125, 
            175
        );

        g.drawString(
            "Sausage", 
            125, 
            200
        );

        g.drawString(
            "Onions", 
            125, 
            225
        );

        g.drawString(
            "Peppers", 
            125, 
            250
        );

        g.drawString(
            "Olives", 
            125, 
            275
        );
    }

}
