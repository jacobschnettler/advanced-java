import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;

public class YesOrNo extends Applet implements ActionListener 
{
    String input = "";
    
    Font font26 = new Font("Ariel", 1, 40);
    
    Button answerBtn = new Button("Enter"); 

    TextField inputTF = new TextField();
    
    TextField maybeTF = new TextField();
    
    Color purple = new Color(143, 2, 250);
    
    // Image customImage;
    
    public void init() 
    {
        this.setLayout(null);
        
        answerBtn.setBounds(800, 300, 75, 40);
        
        answerBtn.addActionListener(this);
        
        answerBtn.setBackground(Color.blue);
        
        answerBtn.setFont(font26);
        
        answerBtn.setForeground(Color.white);
        
        this.add(answerBtn);
        
        inputTF.setBounds(800, 100, 150, 40);
        
        this.add(inputTF);  
        
        maybeTF.setBounds(800, 200, 150, 40);
        
        this.add(maybeTF);  
        
       // customImage = this.getImage(this.getCodeBase(), "");
    }
    
    public void actionPerformed( ActionEvent e ) 
    {
        input = inputTF.getText();
        
        repaint();
    }
    
    public void paint(Graphics g) 
    {    
        g.setColor(Color.red);
        
        //g.drawImage(customImage, 200, 300, 400, 400, this);
        
        g.fillRect(0, 0, 1400, 700);
        
        g.setColor(Color.black);
        
        g.drawString("Enter yes or no", 800, 80);
        
        g.setColor(purple);
        
        g.setFont(font26);
        
        g.drawString(input, 800, 300);
    
    }


}
