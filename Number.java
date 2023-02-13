// Dan Buddin Number Time
import java.awt.*;
import java.applet.Applet;
import java.awt.event.*;
import java.util.Random;
public class Number extends Applet implements ActionListener, Runnable
{
    Thread main=new Thread (this);
    int X=100;
    char Num='1';
    Font font26 = new Font ( "Ariel",1,20);
    String output ="";
    String input ="";
    Button NumBtn[] = new Button [25];
    TextField inputTF = new TextField ();
    Button AgainBtn = new Button ("Play again");
    int nextNum=1;
    long beginTime,endTime;
    double elapsedTime;
    Image Clock;
    Color purple= new Color (190,13,190); 
    int Number[]={2,5,7,1,3,18,4,25,21,14,20,22,24,23,11,10,9,16,8,13,15,17,6,12,19,};
    public void init()
    {
        main.start();
        this.setLayout (null);   
        shuffleArray(Number);
        resize(1400,700);

        AgainBtn.setBounds( 700,600,100,50);
        AgainBtn.addActionListener(this);
        this.add(AgainBtn);
        setBackground(Color.orange);
        setForeground(Color.black);
        AgainBtn.setVisible(false); 
        
        
        Clock=this.getImage(this.getCodeBase(),"Clock.jpg");
        
        for(int num=0; num<=24; num++)
        {
            NumBtn[num]=new Button ("" + (Number[num])); 
            if(num<=7)
                NumBtn[num].setBounds(450+70*num,200,50,50);
            else if(num<=15)
                NumBtn[num].setBounds(-110+70*num,300,50,50);
            else if(num<=23)
                NumBtn[num].setBounds(-670+70*num,400,50,50);
            else if(num<=25)
                NumBtn[num].setBounds(-1000+70*num,500,50,50);                
            NumBtn[num].addActionListener(this);
            this.add(NumBtn[num]);
            NumBtn[num].setFont(font26);            
        }
        NumBtn[0].setBackground(Color.black);
        NumBtn[0].setForeground(Color.orange);
        NumBtn[1].setBackground(Color.black);
        NumBtn[1].setForeground(Color.red);
        NumBtn[2].setBackground(Color.black);
        NumBtn[2].setForeground(purple);
        NumBtn[3].setBackground(Color.black);
        NumBtn[3].setForeground(Color.green);
        NumBtn[4].setBackground(Color.black);
        NumBtn[4].setForeground(Color.orange);
        NumBtn[5].setBackground(Color.black);
        NumBtn[5].setForeground(Color.red);
        NumBtn[6].setBackground(Color.black);
        NumBtn[6].setForeground(purple);
        NumBtn[7].setBackground(Color.black);
        NumBtn[7].setForeground(Color.green);
        NumBtn[8].setBackground(Color.black);
        NumBtn[8].setForeground(Color.orange);
        NumBtn[9].setBackground(Color.black);
        NumBtn[9].setForeground(Color.red);
        NumBtn[10].setBackground(Color.black);
        NumBtn[10].setForeground(purple);
        NumBtn[11].setBackground(Color.black);
        NumBtn[11].setForeground(Color.green);
        NumBtn[12].setBackground(Color.black);
        NumBtn[12].setForeground(Color.orange);
        NumBtn[13].setBackground(Color.black);
        NumBtn[13].setForeground(Color.red);
        NumBtn[14].setBackground(Color.black);
        NumBtn[14].setForeground(purple);
        NumBtn[15].setBackground(Color.black);
        NumBtn[15].setForeground(Color.green);
        NumBtn[16].setBackground(Color.black);
        NumBtn[16].setForeground(Color.orange);
        NumBtn[17].setBackground(Color.black);
        NumBtn[17].setForeground(Color.red);
        NumBtn[18].setBackground(Color.black);
        NumBtn[18].setForeground(purple);
        NumBtn[19].setBackground(Color.black);
        NumBtn[19].setForeground(Color.green);
        NumBtn[20].setBackground(Color.black);
        NumBtn[20].setForeground(Color.orange);
        NumBtn[21].setBackground(Color.black);
        NumBtn[21].setForeground(Color.red);
        NumBtn[22].setBackground(Color.black);
        NumBtn[22].setForeground(purple);
        NumBtn[23].setBackground(Color.black);
        NumBtn[23].setForeground(Color.green);
        NumBtn[24].setBackground(Color.black);
        NumBtn[24].setForeground(Color.green);
    }

    public void actionPerformed( ActionEvent e )
    {
        for(int place=0;place<25;place++)
        { 
            if(e.getSource()==NumBtn[place])
            {
                if(nextNum==Number[place])

                {
                    if(nextNum==1)
                    beginTime=System.currentTimeMillis();
                    NumBtn[place].setVisible(false);
                    nextNum++;
                }
            }
        }
        if(nextNum==26)
        {
            endTime=System.currentTimeMillis();
            elapsedTime=(endTime-beginTime)/1000.0;
            AgainBtn.setVisible(true);          
        }
        if(e.getSource() == AgainBtn)
        {
            shuffleArray(Number);            
            for (int place=0;place<25;place++)
            {
                NumBtn[place].setLabel (Number[place]+"");
                NumBtn[place].setVisible(true);
            }
            nextNum=1;
            elapsedTime=0;
            beginTime=System.currentTimeMillis();
            AgainBtn.setVisible(false);               
        }      
        repaint();  
    }

    public void shuffleArray(int Number[])
    {
        Random rand=new Random(); 
        for (int place=0;place<Number.length;place++)
        {
            int randSpot =rand.nextInt(Number.length);
            int temp=Number[place];
            Number[place]=Number[randSpot];
            Number[randSpot]=temp;
        }   
    }
    
      public void run()
    {
      while(true)
      {
          X=X+5;
          endTime=System.currentTimeMillis();
          elapsedTime=(endTime-beginTime)/1000.0;          
          repaint();
          try {main.sleep(50);} catch(Exception e){}
      } 
    }
    
    public void paint ( Graphics g)
    {
        g.setColor (purple);
        g.fillRect(0,0,1400,700);  
        g.setFont(font26);
        g.setColor(Color.black);
        g.drawImage(Clock,X,350,this);
        g.drawString(" Click all 25 numbers in order starting at 1",700,50);
        g.drawString(" As soon as you click number 1, your time will start",700,100);
        g.drawString(" Try and get the fastest time! ",700,150);
        if(elapsedTime>0)
            g.drawString("Your time is " + elapsedTime,200,100);
    }
}